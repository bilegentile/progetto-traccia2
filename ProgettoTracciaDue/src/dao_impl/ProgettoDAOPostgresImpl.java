package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ProgettoDAO;
import entity.Progetto;
import entity.ProjectManager;

public class ProgettoDAOPostgresImpl implements ProgettoDAO {
	
	private Connection connection;

	private PreparedStatement getProgettoByNomePS,getProgettiSviluppatore, inserisciProgettoPS, getAllProgettiPS, cambiaStatoProgettoPS, getProgettoProjectManager,inserisciArchivioPartecipantiProgettoPS,inserimentoAvvenutoConSuccesso;

	
	public ProgettoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getProgettoByNomePS = connection.prepareStatement("SELECT codprogetto FROM progetto WHERE nome LIKE ?");
		
		inserisciProgettoPS = connection.prepareStatement("INSERT INTO progetto VALUES (?,?,nextval(?),?, ? )");
		
		getAllProgettiPS = connection.prepareStatement("SELECT * FROM progetto WHERE stato LIKE 'Incompleto' ");
		
		cambiaStatoProgettoPS = connection.prepareStatement("UPDATE progetto SET stato = 'Completo' WHERE nome LIKE ?");
		
		getProgettoProjectManager =connection.prepareStatement("SELECT nome \n"
				+ "from progetto \n"
				+ "where codfiscale LIKE ? and stato LIKE 'Incompleto';");
		
		inserisciArchivioPartecipantiProgettoPS = connection.prepareStatement("INSERT INTO partecipazioniprogetto VALUES (?,?)");
		
		inserimentoAvvenutoConSuccesso= connection.prepareStatement("select codprogetto from partecipazioniprogetto where codfiscale LIKE ? AND codprogetto LIKE ?");
		
		getProgettiSviluppatore=connection.prepareStatement("select PR.codprogetto, PR.nome, PR.tipologia, PR.codfiscale "
				+ "			from partecipazioniprogetto PAR join progetto PR on PAR.codprogetto = PR.codprogetto "
				+ "			where PAR.codfiscale LIKE ?; ");
	}


	@Override
	public List<Progetto> getAllProgetti()  throws SQLException {
        ResultSet rs= getAllProgettiPS.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
            Progetto s = new Progetto(rs.getString("codprogetto")); 
            s.setNomeProgetto(rs.getString("nome"));
            s.setTipoProgetto(rs.getString("tipo"));
            s.setStato(rs.getString("stato"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
		
	@Override
	public String getProgettoByNome(String nome) throws SQLException {
		getProgettoByNomePS.setString(1, nome);
        ResultSet rs=  getProgettoByNomePS.executeQuery();
        rs.next();
        Progetto s = new Progetto(rs.getString("codprogetto")); //rs.getString(1)
        s.setCodiceProgetto(rs.getString("codprogetto"));
        rs.close();
        return s.getCodiceProgetto();
	}

	@Override
	public List<Progetto> getProgettoProjectManager(String codfiscale) throws SQLException {
		getProgettoProjectManager.setString(1, codfiscale);
        ResultSet rs= getProgettoProjectManager.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
        	Progetto s = new Progetto(rs.getString("nome")); //rs.getString(1)
        	s.setNomeProgetto(rs.getString("nome"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public List<Progetto> getProgettiSviluppatore(String codfiscale) throws SQLException {
		getProgettiSviluppatore.setString(1, codfiscale);
        ResultSet rs= getProgettiSviluppatore.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
        	Progetto s = new Progetto(rs.getString("codprogetto")); //rs.getString(1)
        	s.setNomeProgetto(rs.getString("nome"));
        	s.setTipoProgetto(rs.getString("tipologia"));
        	ProjectManager pm=new ProjectManager(codfiscale);
        	s.setProjectManagerProgetto(pm);
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int inserisciProgetto(Progetto progetto) throws SQLException {	
		inserisciProgettoPS.setString(1, progetto.getNomeProgetto());
    	inserisciProgettoPS.setString(2, progetto.getTipoProgetto());
    	inserisciProgettoPS.setString(3, progetto.getCodiceProgetto());
    	inserisciProgettoPS.setString(4, progetto.getStato());
    	ProjectManager p= progetto.getProjectManagerProgetto();
    	inserisciProgettoPS.setString(5, p.getCF());
    	int row = inserisciProgettoPS.executeUpdate();
    	return row;
	}
	
	@Override
	public int cambiaStatoProgetto(String nome) throws SQLException {
		cambiaStatoProgettoPS.setString(1, nome);
		int row = cambiaStatoProgettoPS.executeUpdate();
		return row;
	}
	
	@Override
	public int inserisciArchivioPartecipantiProgettoPS(String codFiscale, String codProgetto)throws SQLException  {
		inserisciArchivioPartecipantiProgettoPS.setString(1,codFiscale);
		inserisciArchivioPartecipantiProgettoPS.setString(2,codProgetto);
		int row = inserisciArchivioPartecipantiProgettoPS.executeUpdate();
	    System.out.print(row); 
		return row;
	}

	public int inserimentoAvvenutoConSuccesso(String codFiscale, String codProgetto) throws SQLException{
		inserimentoAvvenutoConSuccesso.setString(1, codFiscale);
		inserimentoAvvenutoConSuccesso.setString(2,codProgetto);
		ResultSet rs= inserimentoAvvenutoConSuccesso.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        int ris=-1;
        while(rs.next())
        {
        	ris++;
        	Progetto s = new Progetto(rs.getString("codprogetto")); 
            lista.add(s);
        }
        rs.close();
        System.out.println(ris);
        return ris;
	}
	
}
