package dbConfig;

import exceptions.ConnectionException;

import java.sql.*;

public class DBBuilder
{
    private Connection connection;

    public DBBuilder(Connection connection)
    {
        this.connection = connection;
    }

    public DBBuilder()
    {
        connection = null;
    }

    private boolean connectionExists() {
        return !(connection == null);
    }

    private boolean tableExists(String tbl_name) throws SQLException
    {
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet tables = metadata.getTables(null, null, tbl_name, null);
        if (tables.next())
            return true;
        return false;
    }
    
    //CREAZIONE TIPI ENUMERATIVI
    
    //ENUMERAZIONE RUOLO PER I MEMBRI
	/*
	 * public int createEnumRuolo() throws ConnectionException { int result= -1;
	 * 
	 * if(connectionExists()) { try { Statement st = connection.createStatement();
	 * 
	 * if(!tableExists("Ruolo")) { String sql =
	 * "CREATE TYPE ruolo AS ENUM ('Sviluppatore');"; //DA RIFARE CON TUTTI GLI
	 * ATTRIBUTI E CON UNA QUERY PIU' PRECISA
	 * 
	 * result = st.executeUpdate(sql); st.close(); } else {
	 * System.out.println("L'enumerazione Ruolo esiste gi�!"); } }
	 * catch(SQLException ex) {
	 * System.out.println("SQL Exception nella creazione dell'enumerazione Ruolo: "
	 * +ex); } } else { throw new ConnectionException("A connection must exist!"); }
	 * return result; }
	 * 
	 * //ENUMERAZIONE TIPOLOGIA PER I PROGETTI public int createEnumTipologia()
	 * throws ConnectionException { int result= -1;
	 * 
	 * if(connectionExists()) { try { Statement st = connection.createStatement();
	 * 
	 * if(!tableExists("Tipologia")) { String sql =
	 * "CREATE TYPE Tipologia AS ENUM ('Ricerca di base', 'Ricerca industriale', 'Ricerca sperimentale', 'Sviluppo sperimentale');"
	 * ; //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
	 * 
	 * result = st.executeUpdate(sql); st.close(); } else {
	 * System.out.println("L'enumerazione Ruolo esiste gi�!"); } }
	 * catch(SQLException ex) {
	 * System.out.println("SQL Exception nella creazione dell'enumerazione Ruolo: "
	 * +ex); } } else { throw new ConnectionException("A connection must exist!"); }
	 * return result; }
	 */
    
    //CREAZIONE SEQUENZE
    
    //SEQUENZA PROGETTO
    public int createSequenceProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodiceprogetti")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceProgetti " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice progetti esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice progetti: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //SEQUENZA PROJECT MANAGER
    public int createSequenceProjectManager() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodicemembri")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceProjectManager " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice project manager esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice prject manager: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //SEQUENZA SVILUPPATORE
    public int createSequenceSviluppatore() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodicesviluppatori")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceSviluppatori " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice sviluppatori esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice sviluppatori: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //SEQUENZA MEETING FISICO
    public int createSequenceMeetingFisico() throws ConnectionException
    {
 	   int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			if(!tableExists("sequenzacodicemeetingfisico")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceMeetingFisico " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} 
    			else {
    				System.out.println("La sequenza codice meeting fisico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice meeting fisico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //SEQUENZA MEETING TELEMATICO
    public int createSequenceMeetingTelematico() throws ConnectionException
    {
 	   int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			if(!tableExists("sequenzacodicemeetingtelematico")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceMeetingTelematico " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} 
    			else {
    				System.out.println("La sequenza codice meeting telematico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice meeting telematico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //SEQUENZA AMBITO
    public int createSequenceAmbito() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodiceambito")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceAmbito " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice ambito esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice ambito: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //SEQUENZA SKILLS
    public int createSequenceSkills() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodiceskills")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceSkills " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice skills esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice skills: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //FUNZIONI CREAZIONE TABELLE
    
    //TABELLA SVILUPPATORE
    public int createTableSviluppatore() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sviluppatore")) {
    				String sql = "CREATE TABLE sviluppatore " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "(nome VARCHAR(100) not NULL, " +
                            " cognome VARCHAR(100) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +
                            " ruolo VARCHAR(255) CHECK (ruolo LIKE 'Sviluppatore'),"+
                            " salarioMedio INTEGER not NULL,"+
                            " valutazione VARCHAR CHECK (valutazione LIKE 'Buona' OR  valutazione LIKE 'Mediocre' OR valutazione LIKE 'Male' OR  valutazione LIKE 'NULL' )," +
                            " PRIMARY KEY (codFiscale));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Sviluppatore esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Sviluppatore: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA PROJECT MANAGER
    public int createTableProjectManager() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("projectmanager")) {
    				String sql = "CREATE TABLE projectManager " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "(nome VARCHAR(100) not NULL, " +
                            " cognome VARCHAR(100) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +
                            " ruolo VARCHAR(255) CHECK (ruolo LIKE 'ProjectManager'),"+
                            " salarioMedio INTEGER not NULL,"+
                            " PRIMARY KEY (codFiscale));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella ProjectManager esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella ProjectManager: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA PROGETTO
    public int createTableProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("progetto")) {
    				String sql = "CREATE TABLE progetto " +        //DA RIVEDERE
                            "(nome VARCHAR(255) not NULL UNIQUE, " +
                            " tipologia VARCHAR(255) CHECK (tipologia LIKE 'Ricerca di base' OR tipologia like 'Ricerca industriale' OR tipologia LIKE 'Ricerca sperimentale' OR tipologia like 'Sviluppo sperimentale')," +
                            " codProgetto VARCHAR(255) PRIMARY KEY, " +
                            " stato VARCHAR(255) not NULL CHECK (stato LIKE 'Completo' OR stato LIKE 'Incompleto'),"+
                            " codfiscale VARCHAR(16) REFERENCES projectmanager(codfiscale));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Progetto esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Progetto: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
	}
    
    //TABELLA D'ASSOCIAZIONE PARTECIPANTI AI PROGETTI
    public int createTablePartecipazioniProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("partecipazioniprogetto")) {
    				String sql = "CREATE TABLE partecipazioniProgetto( \n"
    						+ "codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),\n"
    						+ "codprogetto VARCHAR(255) REFERENCES progetto(codProgetto));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Partecipazioni Progetto esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Partecipazioni Progetto: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA AMBITO
    public int createTableAmbito() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("ambito")) {
    				String sql = "CREATE TABLE ambito " +        //DA RIVEDERE
                            "(nome VARCHAR(255) not NULL UNIQUE, " +
                            " codAmbito VARCHAR(255) PRIMARY KEY);";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Ambito esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Ambito: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA CLASSE D'ASSOCIAZIONE AMBITO
    public int createTableAssociazioneAmbito() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("associazioneambito")) {
    				String sql = "CREATE TABLE associazioneAmbito(" +        //DA RIVEDERE
                            "codProgetto VARCHAR(255) REFERENCES Progetto(codProgetto)," +
                            "codAmbito VARCHAR(255) REFERENCES Ambito(codAmbito))";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Associazione Ambito esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Associazione Ambito: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
	}
    
    //TABELLA MEETING FISICO
    public int createTableMeetingFisico() throws ConnectionException
    {
    	int result= -1;
  	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
  			
    			if(!tableExists("meetingfisico")) {
    				String sql = "CREATE TABLE meetingFisico (" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                          " codiceMeeting VARCHAR(255) NOT NULL, " +
                          " data VARCHAR(200)  NOT NULL, " +
                          " oraInizio VARCHAR CHECK (oraInizio ~* '^[0-9][0-9]:[0-9][0-9]:[0-9][0-9]') NOT NULL,"+
                          " luogo VARCHAR,"+
                          " nomeSala VARCHAR,"+
                          " durata INTEGER NOT NULL,"+
  						  " PRIMARY KEY (codiceMeeting),"+
  						  " codProgetto VARCHAR(255) REFERENCES progetto(codProgetto));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Meeting Fisico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception in creation table Meeting Fisico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA D'ASSOCIAZIONE SVILUPPATORI A MEETING FISICO
    public int createTablePartecipazioniSviluppatoreMeetingFisico() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("partecipazionisviluppatoremeetingfisico")) {
    				String sql = "CREATE TABLE partecipazioniSviluppatoreMeetingFisico(" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                                 "codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),"+
    						     "codmeeting VARCHAR(255) REFERENCES meetingFisico(codiceMeeting));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Partecipazioni Sviluppatore Meeting Fisico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Partecipazioni Sviluppatore Meeting Fisico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA D'ASSOCIAZIONE PROJECT MANAGER A MEETING FISICO
    public int createTablePartecipazioniProjectManagerMeetingFisico() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("partecipazioniprojectmanagermeetingfisico")) {
    				String sql = "CREATE TABLE partecipazioniProjectManagerMeetingFisico(" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                                 "codfiscale VARCHAR(16) REFERENCES ProjectManager(codFiscale),"+
    						     "codmeeting VARCHAR(255) REFERENCES meetingFisico(codiceMeeting));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Partecipazioni Project Manager Meeting Fisico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Partecipazioni Sviluppatore Meeting Fisico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //TABELLA MEETING TELEMATICO
    public int createTableMeetingTelematico() throws ConnectionException
    {
    	int result= -1;
	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("meetingtelematico")) {
    				String sql = "CREATE TABLE meetingTelematico (" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                        " codiceMeeting VARCHAR(255) NOT NULL, " +
                        " data VARCHAR(200)  NOT NULL, " +
                        " oraInizio VARCHAR CHECK (oraInizio ~* '^[0-9][0-9]:[0-9][0-9]:[0-9][0-9]') NOT NULL,"+
                        " piattaforma VARCHAR,"+
                        " link VARCHAR,"+
                        " durata INTEGER NOT NULL,"+
                        " PRIMARY KEY (codiceMeeting),"+
						" codProgetto VARCHAR(255) REFERENCES progetto(codProgetto));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Meeting Telematico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception in creation table Meeting Telematico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //TABELLA D'ASSOCIAZIONE SVILUPPATORI A MEETING TELEMATICO
    public int createTablePartecipazioniSviluppatoreMeetingTelematico() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("partecipazionisviluppatoremeetingtelematico")) {
    				String sql = "CREATE TABLE partecipazioniSviluppatoreMeetingTelematico(" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                                 "codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),"+
    						     "codmeeting VARCHAR(255) REFERENCES meetingTelematico(codiceMeeting));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Partecipazioni Sviluppatore Meeting Telematico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Partecipazioni Sviluppatore Meeting Telematico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //TABELLA D'ASSOCIAZIONE PROJECT MANAGER A MEETING TELEMATICO
    public int createTablePartecipazioniProjectManagerMeetingTelematico() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("partecipazioniprojectmanagermeetingtelematico")) {
    				String sql = "CREATE TABLE partecipazioniProjectManagerMeetingTelematico(" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                                 "codfiscale VARCHAR(16) REFERENCES ProjectManager(codFiscale),"+
    						     "codmeeting VARCHAR(255) REFERENCES meetingTelematico(codiceMeeting));";
    				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Partecipazioni Project Manager Meeting Telematico esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Partecipazioni Sviluppatore Meeting Telematico: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    //TABELLA SKILLS
    public int createTableSkills() throws ConnectionException
    {
    	int result= -1;
	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("skills")) {
    				String sql = "CREATE TABLE skills (" +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
						     "nomeSkill VARCHAR(100) not NULL, "+
						    " codSkills VARCHAR(255) PRIMARY KEY);";
				
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Skills esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Skills: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //TABELLA D'ASSOCIAZONE SKILLS PROJECT MANAGER
    public int createTableAssociazioneSkillsProjectManager() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("associazioneskillsprojectmanager")) {
    				String sql = "CREATE TABLE associazioneSkillsProjectManager(" + //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
				             "codFiscale VARCHAR(16) REFERENCES projectManager(codFiscale),"+
						     "codSkills VARCHAR(255) REFERENCES skills(codSkills));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Associazione Skills Project Manager esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Associazione Skills Project Manager: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    //TABELLA D'ASSOCIAZONE SKILLS SVILUPPATORE
    public int createTableAssociazioneSkillsSviluppatore() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("associazioneskillssviluppatore")) {
    				String sql = "CREATE TABLE associazioneSkillsSviluppatore(" + //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
				             "codFiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),"+
						     "codSkills VARCHAR(255) REFERENCES skills(codSkills));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Associazione Skills Sviluppatore esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella crezione della tabella Associazione Skills Sviluppatore: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

}