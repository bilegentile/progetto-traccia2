package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;

import dao_impl.AmbitoDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import daos.AmbitoDAO;
import daos.ProgettoDAO;
import daos.ProjectManagerDAO;
import daos.SkillsDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import exceptions.ConnectionException;
import gui.BenvenutoProjectManager;
import gui.ErroreCodiceFiscaleSbagliato;

public class ProjectManager extends Membro {

	public ProjectManager(String CF) {
		super(CF);
	}

	public ProjectManager(String nome, String cognome, String cF, String ruolo, int salarioMedio) {
		super(nome, cognome, cF, ruolo, salarioMedio);
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
	public void RegistraPM(String nome, String cognome, String codfiscale, String salario, List<String> list ) {
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
	            SkillsDAO daoSkill =null;

	            daoProjectManager = new ProjectManagerDAOPostgresImpl(connection);
	            
	            ProjectManager m1  =  new ProjectManager(nome, cognome, codfiscale, "ProjectManager", Integer.valueOf(salario));
	           
	            int res =  daoProjectManager.inserisciProjectManager(m1);
	            int i= 0;
            	while (i<list.size()) {
            		String s1=list.get(i);
            		int res2= daoProjectManager.inserisciSkillProjectManager(m1,s1);
            		i++;
            	}
	        }
	        catch (SQLException exception)
	        {
	            System.out.println("Errore SQLException: "+ exception.getMessage());
	        } catch (ConnectionException e) {
	        	  System.out.println("CE: "+e);
			}
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
        	int res =  dao1.inserisciProgetto(p2);
  	
        	builder.createTableAmbito();
        	AmbitoDAO dao3 = null;
        	dao3 = new AmbitoDAOPostgresImpl(connection);
        	int i= 0;
        	while (i<ListaAmbiti.size()) {
        		String s1=ListaAmbiti.get(i);
        		int res3= dao3.inserisciAmbitoProgetto(nomeProgetto,s1);
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
}
