package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;

import dao_impl.AmbitoDAOPostgresImpl;
import dao_impl.MeetingFisicoDAOPostgresImpl;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.AmbitoDAO;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
import daos.ProgettoDAO;
import daos.ProjectManagerDAO;
import daos.SkillsDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import exceptions.ConnectionException;
import gui.BenvenutoProjectManager;
import gui.ErroreCodiceFiscaleSbagliato;
import gui.ValutazioneAvvenutaConSuccesso;

public class ProjectManager extends Membro {

	public ProjectManager(String CF) {
		super(CF);
	}

	public ProjectManager(String nome, String cognome, String cF, int salarioMedio) {
		super(nome, cognome, cF,  salarioMedio);
	}

	//METODI
	
	//verifica se il codice fiscale inserito dal project manager risulta corretto, se lo � avvia il benvenuto altrimenti da un messaggio di errore
	public List<ProjectManager> accediPm(String codiceFiscale) {
			DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;
	        List<ProjectManager> lista = null;
	        try
	        {
				dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            ProjectManagerDAO dao = null;
	            dao = new ProjectManagerDAOPostgresImpl(connection);
	            
	            lista = dao.getProjectManagerByCodFiscale(codiceFiscale);
	         
	         }
	        catch (SQLException exception)
	        {
	            System.out.println("Errore SQLException: "+ exception.getMessage());
	        }
	        return lista;
		}
	
	//Creazione di un nuovo project manager 
	public boolean RegistraPM(String nome, String cognome, String codfiscale, String salario, List<String> listaAmbiti )
		{
            	boolean errore=false;

				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
		       
		        try
		        {
		        	dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            builder.createTableProjectManager();
		            builder.createTableSkills();
		            builder.createTableAssociazioneSkillsProjectManager(); 
		            ProjectManagerDAO daoProjectManager = null;
		            ProjectManagerDAO daoPm =null;
		            SkillsDAO daoSkill =null;
		            
		            daoPm = new ProjectManagerDAOPostgresImpl(connection);
		            daoProjectManager = new ProjectManagerDAOPostgresImpl(connection);
		            

		            ProjectManager m1  =  new ProjectManager(nome, cognome, codfiscale,  Integer.valueOf(salario));
				    ProjectManager m  =  new ProjectManager(nome, cognome, codfiscale,  Integer.valueOf(salario));		          
		            
				    // Vedo se esiste gi� il Project Manager con quel codice fiscale. Se non esiste lo creo, altrimenti errore.
		          
		            List<ProjectManager> listaPmConCf = daoPm.getProjectManagerByCodFiscale(codfiscale);
		            if(listaPmConCf.isEmpty()) {

		            	daoProjectManager.inserisciProjectManager(m1);
		            	int i= 0;
	            			while (i<listaAmbiti.size()) {
	            				String s1=listaAmbiti.get(i);
	            				daoProjectManager.inserisciSkillProjectManager(m1,s1);
	            				i++;
	            			}
            				errore=false;

		            }
		            else {
		            	errore=true;
		            }
		        }
		        
		        catch (SQLException exception)
		        {
		            System.out.println("Errore SQLException: "+ exception.getMessage());
		        } catch (ConnectionException e) {
		        	  System.out.println("CE: "+e);
				}
				return errore;

		}
			
	//Creazione di un nuovo progetto
	public void CreaProgetto(String nomeProgetto, String tipoProgetto , List<String> ListaAmbiti) {
			DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;
	        try
	        {
	        	dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            
	        	builder.createTableProgetto();
	        	ProgettoDAO dao1 = null;
	        	dao1 = new ProgettoDAOPostgresImpl(connection);
	        	Progetto p2  =  new Progetto (nomeProgetto, tipoProgetto, "sequenzacodiceprogetti", "Incompleto", this);
	        	dao1.inserisciProgetto(p2);
	  	
	        	builder.createTableAmbito();
	        	AmbitoDAO dao3 = null;
	        	dao3 = new AmbitoDAOPostgresImpl(connection);
	        	int i= 0;
	        	while (i<ListaAmbiti.size()) {
	        		String s1=ListaAmbiti.get(i);
	        		dao3.inserisciAmbitoProgetto(nomeProgetto,s1);
	        		i++;
	        	}
	        }
	        catch (SQLException exception)
	        {
	        	System.out.println("Errore SQLException: "+ exception.getMessage());
	     	}
	        catch (ConnectionException ex)
	        {
	        	System.out.println("CE: "+ex);
	        }  	
		}
	
	//Valutazione di un membro
	public void ValutazioneMembro(String valutazione, String CodiceFiscale) {
		DBConnection dbconn = null;
		Connection connection = null;
		DBBuilder builder = null;
		try
		{
			dbconn = DBConnection.getInstance();
			connection = dbconn.getConnection();
			builder = new DBBuilder(connection);
			SviluppatoreDAO dao = null;
        
			dao = new SviluppatoreDAOPostgresImpl(connection);
			dao.inserisciValutazione(valutazione , CodiceFiscale );
			
		}
		catch (SQLException exception)
		{
			System.out.println("Errore SQLException: "+ exception.getMessage());
		}
	}
	
	//Eliminazione di un progetto
	public void EliminaProgetto( String nomeProgetto) {
	
	DBConnection dbconn = null;
    Connection connection = null;
    DBBuilder builder = null;

    try
    {
        dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        builder = new DBBuilder(connection);
        ProgettoDAO dao = null;
        
        dao = new ProgettoDAOPostgresImpl(connection);
        
        dao.cambiaStatoProgetto(nomeProgetto);
    }catch (SQLException exception)
    {
    	System.out.println("Errore SQLException: "+ exception.getMessage());
    }
	}
	
	//Creazione di un Meeting
	public void CreazioneMeeting( String tipologia, String titolo, Date data, String oraInizio, String oraFine, String luogo, String nomeSala, String piattaforma, String organizzatore, String NomeProgetto) {
	

		DBConnection dbconn = null;
	    Connection connection = null;
	    DBBuilder builder = null;
	    String codiceProgetto;
	    Progetto p;
        try
        {
            	
            	dbconn = DBConnection.getInstance();
            	connection = dbconn.getConnection();
            	builder = new DBBuilder(connection);
            	ProgettoDAO dao = null;			
            	ProjectManager da=null;
            	
            	dao = new ProgettoDAOPostgresImpl(connection);
            	codiceProgetto= dao.getProgettoByNome(NomeProgetto);
            	p= new Progetto(codiceProgetto);
            	
            	
            	dbconn = DBConnection.getInstance();
                connection = dbconn.getConnection();
                builder = new DBBuilder(connection);
                
            	if(tipologia.equals("Telematico")) {
            		builder.createTableMeetingTelematico();
            		MeetingTelematicoDAO dao1 = null;
            		dao1 = new MeetingTelematicoDAOPostgresImpl(connection);
            		MeetingTelematico p1  =  new MeetingTelematico("sequenzacodicemeetingtelematico", titolo, data, oraInizio , oraFine, p,organizzatore,piattaforma );
            		dao1.inserisciMeetingTelematico(p1);
            		
            	}else if (tipologia.equals("Fisico")) {
            		builder.createTableMeetingFisico(); 
            		MeetingFisicoDAO dao1 = null;
            		dao1 = new MeetingFisicoDAOPostgresImpl(connection);
            		MeetingFisico p1  =  new MeetingFisico("sequenzacodicemeetingfisico", titolo, data, oraInizio , oraFine , p ,organizzatore, luogo, nomeSala);
            		dao1.inserisciMeetingFisico(p1);
            	}
        
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        catch (ConnectionException ex)
        {
            System.out.println("CE: "+ex);
        }
		
	}	

}
