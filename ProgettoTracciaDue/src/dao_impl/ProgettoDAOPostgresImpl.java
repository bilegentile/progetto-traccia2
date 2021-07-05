package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ProgettoDAO;
import entity.Progetto;

public class ProgettoDAOPostgresImpl implements ProgettoDAO {
	
	private Connection connection;
	private PreparedStatement getProgettoByNomePS, inserisciProgettoPS, getAllProgettiPS,cambiaStatoProgettoPS, getProgettoProjectManager;
	
	public ProgettoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getProgettoByNomePS = connection.prepareStatement("SELECT * FROM progetto WHERE nome LIKE ?");
		inserisciProgettoPS = connection.prepareStatement("INSERT INTO progetto VALUES (?, ?, ?, nextval(?), ? )");
		getAllProgettiPS = connection.prepareStatement("SELECT * FROM progetto WHERE stato LIKE 'Incompleto' ");
		cambiaStatoProgettoPS = connection.prepareStatement("UPDATE progetto SET stato = 'Completo' WHERE nome LIKE ?");
		getProgettoProjectManager =connection.prepareStatement("select nome from progetto join archiviopartecipantiprogetto on nomeprogetto=nome where codfiscale LIKE ?");
	}
	
	@Override
	public List<Progetto> getAllProgetti()  throws SQLException {
        ResultSet rs= getAllProgettiPS.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
            Progetto s = new Progetto(rs.getString("codProgetto")); 
            s.setNomeProgetto(rs.getString("nome"));
            s.setAmbitoProgetto(rs.getString("ambito"));
            s.setTipoProgetto(rs.getString("tipo"));
            s.setStato(rs.getString("stato"));
            
            
            lista.add(s);
        }
        rs.close();
        return lista;
	}
		
	@Override
	public List<Progetto> getProgettoByNome(String nome) throws SQLException {
		getProgettoByNomePS.setString(1, nome);
        ResultSet rs= getProgettoByNomePS.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
            Progetto s = new Progetto(rs.getString("codProgetto")); //rs.getString(1)
            s.setNomeProgetto(rs.getString("nome"));
            s.setAmbitoProgetto(rs.getString("ambito"));
            s.setTipoProgetto(rs.getString("tipo"));
            s.setStato(rs.getString("stato"));
            
            lista.add(s);
        }
        rs.close();
        return lista;
	}


	@Override
	public List<Progetto> getProgettoByCodProgetto(String codice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Progetto> getProgettoByAmbito(String ambito) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Progetto> getMembroByTipo(String tipo) {
		// TODO Auto-generated method stub
		return null;
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
	public int inserisciProgetto(Progetto progetto) throws SQLException {	
	inserisciProgettoPS.setString(1, progetto.getNomeProgetto());
    inserisciProgettoPS.setString(2, progetto.getTipoProgetto());
    inserisciProgettoPS.setString(3, progetto.getAmbitoProgetto());
    inserisciProgettoPS.setString(4, progetto.getStato());
    inserisciProgettoPS.setString(5, progetto.getCodiceProgetto());
    int row = inserisciProgettoPS.executeUpdate();
    return row;
}

	@Override
	public int cancellaProgetto(Progetto progetto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int cambiaStatoProgetto(String nome) throws SQLException {
		cambiaStatoProgettoPS.setString(1, nome);
		int row = cambiaStatoProgettoPS.executeUpdate();
		return row;
	}

}
