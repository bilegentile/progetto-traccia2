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
    
	private boolean functionExists(String nomeFun)throws SQLException {
		DatabaseMetaData metadata = connection.getMetaData();
        ResultSet function = metadata.getFunctions(null, null, nomeFun);
        if (function.next())
            return true;
		return false;
	}
		
	//CREAZIONE DATABASE
    public int createDatabase() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("BDProgettoTracciaDue")) {
    				String sql = "CREATE DATABASE BDProgettoTracciaDue;" ;
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("il database BDProgettoTracciaDue esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione del database BDProgettoTracciaDue : "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
        
    //CREAZIONE SEQUENZE
    
    //SEQUENZA PROGETTO
    public int createSequenceProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodiceprogetti")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceProgetti " + 
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

    //SEQUENZA MEETING FISICO
    public int createSequenceMeetingFisico() throws ConnectionException
    {
 	   int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			if(!tableExists("sequenzacodicemeetingfisico")) {
    				String sql = "CREATE SEQUENCE sequenzaCodiceMeetingFisico " +      
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
    				String sql = "CREATE TABLE sviluppatore " + 
                            "(nome VARCHAR(100) not NULL, " +
                            " cognome VARCHAR(100) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +  
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
    				String sql = "CREATE TABLE projectManager " +   
                            "(nome VARCHAR(100) not NULL, " +
                            " cognome VARCHAR(100) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +
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
    				String sql = "CREATE TABLE progetto " +  
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
    				String sql = "CREATE TABLE ambito " +   
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
    				String sql = "CREATE TABLE associazioneAmbito(" +   
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
    				String sql = "CREATE TABLE meetingFisico (" +    
                          " codiceMeeting VARCHAR(255) NOT NULL, " +
    					  " titolo VARCHAR(255) NOT NULL, " +
                          " data DATE NOT NULL, " +
                          " oraInizio TIME(0) NOT NULL CHECK ( oraInizio < oraFine ),"+
                          " oraFine TIME (0) NOT NULL CHECK ( oraFine > oraInizio ),"+
                          " luogo VARCHAR,"+
                          " nomeSala VARCHAR,"+
  						  " PRIMARY KEY (codiceMeeting),"+
  						  " codProgetto VARCHAR(255) REFERENCES progetto(codProgetto), "+
  						  " codProjectManager VARCHAR(16) REFERENCES projectmanager(codFiscale));";
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
    				String sql = "CREATE TABLE partecipazioniSviluppatoreMeetingFisico(" +  
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
    
   
    //TABELLA MEETING TELEMATICO
    public int createTableMeetingTelematico() throws ConnectionException
    {
    	int result= -1;
	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("meetingtelematico")) {
    				String sql = "CREATE TABLE meetingTelematico (" +        
                        " codiceMeeting VARCHAR(255) NOT NULL, " +
    					" titolo VARCHAR(255) NOT NULL, " +
                        " data DATE NOT NULL, " +
                        " oraInizio TIME(0) NOT NULL CHECK ( oraInizio < oraFine ),"+
                        " oraFine TIME (0) NOT NULL CHECK ( oraFine > oraInizio ),"+
                        " piattaforma VARCHAR,"+
                        " PRIMARY KEY (codiceMeeting),"+
						" codProgetto VARCHAR(255) REFERENCES progetto(codProgetto), " +
						" codProjectManager VARCHAR(16) REFERENCES projectmanager(codFiscale));";
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
    				String sql = "CREATE TABLE partecipazioniSviluppatoreMeetingTelematico(" +     
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
  
    //TABELLA SKILLS
    public int createTableSkills() throws ConnectionException
    {
    	int result= -1;
	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
			
    			if(!tableExists("skills")) {
    				String sql = "CREATE TABLE skills (" +       
						     "nomeSkill VARCHAR(100) not NULL, "+
						    " codSkills VARCHAR(255) PRIMARY KEY,"
						    + "UNIQUE(nomeSkill))";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Skills esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della tabella Skills: "+ex);
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
    				String sql = "CREATE TABLE associazioneSkillsProjectManager(" + 
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
    				String sql = "CREATE TABLE associazioneSkillsSviluppatore(" + 
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

    
    // TRIGGER PER GESTIRE LA PARTECIPAZIONE DI UNO STESSO MEMBRO A UN PROGETTO   
    public int createTriggerPartecipazioneAlProgetto() throws ConnectionException
    {
    	int result= -1;
		    	
    	if(connectionExists()) {
    		try {
		    	Statement st = connection.createStatement();
		    	if(!functionExists("function_partecipazione_al_progetto")) {
		    		String sql = " CREATE FUNCTION function_partecipazione_al_progetto() RETURNS TRIGGER AS $Trigger_Partecipazione_Al_Progetto$"
		    				+ "BEGIN "
		    				+ "IF((SELECT P.codfiscale "
		    				+ "FROM progetto AS P "
		    				+ "WHERE (NEW.codfiscale = P.codfiscale AND NEW.codprogetto = P.codprogetto)) "
		 					+ "IS NOT NULL) THEN "
		    				+ "DELETE FROM partecipazioniprogetto AS PP "
		    				+ "WHERE PP.codfiscale = NEW.codfiscale; "
		 					+ "END IF; "
		 					+ "Return NEW; "
		    				+ "END; "
		    				+ "$Trigger_Partecipazione_Al_Progetto$ LANGUAGE plpgsql; "
		    				+ "CREATE TRIGGER Trigger_Partecipazione_Al_Progetto "
		    				+ "AFTER INSERT OR UPDATE "
		    				+ "ON partecipazioniprogetto "
		    				+ "FOR EACH ROW "
		    				+ "EXECUTE PROCEDURE function_partecipazione_al_progetto();";
		    		result = st.executeUpdate(sql);
		    		st.close();

		    	} else {
		    		System.out.println("Il trigger TriggerPartecipazioneAlProgetto esiste gi�!");
		    	}
		    	
		    } catch(SQLException ex) {
		    	System.out.println("SQL Exception nella creazione della tabella FunctionPartecipazioneAlProgetto : "+ex);
		    }
		} else {
		    throw new ConnectionException("A connection must exist!");
		}
    	return result;
    }
		 		 
	// TRIGGER PER EVITARE VENGANO CREATI DUE SKILL CON LO STESSO NOME E
    //GESTIRE IL CASO IN CUI VENGA INSERITA UNA SKILL CON STESSO CODICE DI UNA GIA' PRESENTE
    public int createTriggerDuplicatidelleSkills() throws ConnectionException
    {
    	int result= -1;
		    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			if(!functionExists("function_duplicati_delle_skills")) {
    				String sql = " CREATE FUNCTION function_duplicati_delle_skills() RETURNS TRIGGER AS $Trigger_Duplicati_delle_Skills$"
    					+ "BEGIN "
    					+ "IF((SELECT Sk.nomeskill "
    					+ "		FROM skills AS Sk "
    					+ "		WHERE (NEW.nomeskill = Sk.nomeskill AND Sk.codskills != New.codskills))"
    					+ "IS NOT NULL) THEN "
    					+ "		DELETE FROM skills AS Sk "
    					+ "		WHERE (NEW.nomeskill = Sk.nomeskill AND Sk.codskills = New.codskills); "
    					+ "END IF; "					
    					+ "Return NEW; "
    					+ "END; "
    					+ "$Trigger_Duplicati_delle_Skills$ LANGUAGE plpgsql; "
    					+ "CREATE TRIGGER Trigger_Duplicati_delle_Skills "
    					+ "AFTER INSERT OR UPDATE "
    					+ "		ON skills "
    					+ "FOR EACH ROW "
    					+ "EXECUTE PROCEDURE function_duplicati_delle_skills();";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("Il trigger Duplicati_delle_Skills esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della tabella Duplicati_delle_Skills : "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
   
 // TRIGGER GESTIRE LA CREAZIONE DI UNA SKILL CON CODICE DI UNA SKILL GIA' PRESENTE
    public int createTriggerCodSkills() throws ConnectionException
       {
       	int result= -1;
   		    	
       	if(connectionExists()) {
       		try {
       			Statement st = connection.createStatement();
       			if(!functionExists("function_cod_skills")) {
       				String sql = " CREATE FUNCTION function_cod_skills() RETURNS TRIGGER AS $trigger_cod_skills$"
       						+ "BEGIN "
       						+ "WHILE((SELECT DISTINCT Sk.nomeskill "
       						+ "		 FROM skills AS Sk "
       						+ "		 WHERE (Sk.codskills = NEW.codskills)) "
       						+ "		IS NOT NULL)"
       						+ "LOOP "
       						+ "NEW.codskills := nextval('sequenzacodiceskills'); "
       						+ "END LOOP; "
       						+ "RETURN NEW; "
       						+ "END "
       						+ "$trigger_cod_skills$ LANGUAGE plpgsql; "
       						+ "CREATE TRIGGER trigger_cod_skills "
       						+ "BEFORE INSERT OR UPDATE "
       						+ "ON skills "
       						+ "FOR EACH ROW "
       						+ "EXECUTE PROCEDURE function_cod_skills();";
       				result = st.executeUpdate(sql);
       				st.close();
       			} else {
       				System.out.println("Il TriggerCodSkills esiste gi�!");
       			}
       		} catch(SQLException ex) {
       			System.out.println("SQL Exception nella creazione del TriggerCodSkills : "+ex);
       		}
       	} else {
       		throw new ConnectionException("A connection must exist!");
       	}
       	return result;
       }
    
    
    // TRIGGER CHE GESTISCE LA CREAZIONE DI UN AMBITO CON CODICE DI UN AMBITO GIA' PRESENTE
    public int createTrigger_cod_ambito() throws ConnectionException
    {
    	int result= -1;
		    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			if(!functionExists("function_cod_ambito")) {
    				String sql = " CREATE FUNCTION function_cod_ambito() RETURNS TRIGGER AS $trigger_cod_ambito$"
    						+ "BEGIN "
    						+ "WHILE((SELECT DISTINCT AM.nome "
    						+ "		 FROM ambito AS AM "
    						+ "		 WHERE (AM.codambito = NEW.codambito)) "
    						+ "		IS NOT NULL)"
    						+ "LOOP "
    						+ "NEW.codambito := nextval('sequenzacodiceambito'); "
    						+ "END LOOP; "
    						+ "RETURN NEW; "
    						+ "END "
    						+ "$trigger_cod_ambito$ LANGUAGE plpgsql; "
    						+ "CREATE TRIGGER trigger_cod_ambito "
    						+ "BEFORE INSERT OR UPDATE "
    						+ "ON ambito "
    						+ "FOR EACH ROW "
    						+ "EXECUTE PROCEDURE function_cod_ambito();";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("Il trigger_cod_ambito esiste gi�!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione del trigger_cod_ambito : "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
    // TRIGGER CHE GESTISCE LA CREAZIONE DI UN PROGETTO CON CODICE DI UN PROGETTO GIA' PRESENTE
       public int createTriggerCodProgetto() throws ConnectionException
          {
          	int result= -1;
      		    	
          	if(connectionExists()) {
          		try {
          			Statement st = connection.createStatement();
          			if(!functionExists("function_cod_progetto")) {
          				String sql = " CREATE FUNCTION function_cod_progetto() RETURNS TRIGGER AS $trigger_cod_progetto$"
          						+ "BEGIN "
          						+ "WHILE((SELECT DISTINCT Pr.nome "
          						+ "		 FROM progetto AS Pr "
          						+ "		 WHERE (Pr.codprogetto = NEW.codprogetto)) "
          						+ "		IS NOT NULL)"
          						+ "LOOP "
          						+ "NEW.codprogetto := nextval('sequenzacodiceprogetti'); "
          						+ "END LOOP; "
          						+ "RETURN NEW; "
          						+ "END "
          						+ "$trigger_cod_progetto$ LANGUAGE plpgsql; "
          						+ "CREATE TRIGGER trigger_cod_progetto "
          						+ "BEFORE INSERT  "
          						+ "ON progetto "
          						+ "FOR EACH ROW "
          						+ "EXECUTE PROCEDURE function_cod_progetto();";
          				result = st.executeUpdate(sql);
          				st.close();
          			} else {
          				System.out.println("Il trigger_cod_progetto esiste gi�!");
          			}
          		} catch(SQLException ex) {
          			System.out.println("SQL Exception nella creazione del trigger_cod_progetto : "+ex);
          		}
          	} else {
          		throw new ConnectionException("A connection must exist!");
          	}
          	return result;
          }
       
       // TRIGGER GESTIRE LA CREAZIONE DI MEETING FISICI CON CODICE UGUALE A MEETING FISICI GIA' PRESENTI
       //TRIGGER GESTISCE IL CASO IN CUI SI CREI UN MEETING CON NOME UGUALE A UN MEETING GIA' PRESENTE
       public int createTriggerCodMeeting_Fisico() throws ConnectionException
       {
       	int result= -1;
   		    	
       	if(connectionExists()) {
       		try {
       			Statement st = connection.createStatement();
       			if(!functionExists("function_cod_meeting_fisico")) {
       				String sql = " CREATE FUNCTION function_cod_meeting_fisico() RETURNS TRIGGER AS $trigger_cod_meeting_fisico$"
       						+ "BEGIN "
       						+ "WHILE((SELECT DISTINCT Me_F.titolo "
       						+ "		 FROM meetingfisico AS Me_F "
       						+ "		 WHERE (Me_F.codicemeeting = NEW.codicemeeting)) "
       						+ "		IS NOT NULL)"
       						+ "LOOP "
       						+ "NEW.codicemeeting := nextval('sequenzacodicemeetingfisico'); "
       						+ "END LOOP; "
       						+ "IF ((SELECT DISTINCT Me_F.titolo "
       						+ "  FROM meetingfisico AS Me_F "
       						+ "  WHERE (Me_F.titolo = NEW.titolo)) "
       						+ "IS NOT NULL) THEN "
       						+ "NEW.titolo := NEW.titolo ||'.' ||SUBSTRING(NEW.codicemeeting,1,4);"
       						+ "END IF;"
       						+ "RETURN NEW; "
       						+ "END "
       						+ "$trigger_cod_meeting_fisico$ LANGUAGE plpgsql; "
       						+ "CREATE TRIGGER trigger_cod_meeting_fisico "
       						+ "BEFORE INSERT  "
       						+ "ON meetingfisico "
       						+ "FOR EACH ROW "
       						+ "EXECUTE PROCEDURE function_cod_meeting_fisico();";
       				result = st.executeUpdate(sql);
       				st.close();
       			} else {
       				System.out.println("Il trigger_cod_meeting_fisico esiste gi�!");
       			}
       		} catch(SQLException ex) {
       			System.out.println("SQL Exception nella creazione del trigger_cod_meeting_fisico : "+ex);
       		}
       	} else {
       		throw new ConnectionException("A connection must exist!");
       	}
       	return result;
       }
         
       // TRIGGER GESTIRE LA CREAZIONE DI MEETING TELEMATICI CON CODICE UGUALE A MEETING TELEMATICI GIA' PRESENTI
       //TRIGGER GESTISCE IL CASO IN CUI SI CREI UN MEETING CON NOME UGUALE A UN MEETING GIA' PRESENTE
       public int createTriggerCodMeeting_Telematico() throws ConnectionException
       {
       	int result= -1;
   		    	
       	if(connectionExists()) {
       		try {
       			Statement st = connection.createStatement();
       			if(!functionExists("function_cod_meeting_telematico")) {
       				String sql = " CREATE FUNCTION function_cod_meeting_telematico() RETURNS TRIGGER AS $trigger_cod_meeting_telematico$"
       						+ "BEGIN "
       						+ "WHILE((SELECT DISTINCT Me_T.titolo "
       						+ "		 FROM meetingtelematico AS Me_T "
       						+ "		 WHERE (Me_T.codicemeeting = NEW.codicemeeting)) "
       						+ "		IS NOT NULL)"
       						+ "LOOP "
       						+ "NEW.codicemeeting := nextval('sequenzacodicemeetingtelematico'); "
       						+ "END LOOP; "
       						+ "IF ((SELECT DISTINCT Me_T.titolo "
       						+ "  FROM meetingtelematico AS Me_T "
       						+ "  WHERE (Me_T.titolo = NEW.titolo)) "
       						+ "IS NOT NULL) THEN "
       						+ "NEW.titolo := NEW.titolo ||'.' ||SUBSTRING(NEW.codicemeeting,1,4);"
       						+ "END IF;"
       						+ "RETURN NEW; "
       						+ "END "
       						+ "$trigger_cod_meeting_telematico$ LANGUAGE plpgsql; "
       						+ "CREATE TRIGGER trigger_cod_meeting_telematico "
       						+ "BEFORE INSERT  "
       						+ "ON meetingtelematico "
       						+ "FOR EACH ROW "
       						+ "EXECUTE PROCEDURE function_cod_meeting_telematico();";
       				result = st.executeUpdate(sql);
       				st.close();
       			} else {
       				System.out.println("Il trigger_cod_meeting_telematico esiste gi�!");
       			}
       		} catch(SQLException ex) {
       			System.out.println("SQL Exception nella creazione del trigger_cod_meeting_telematico : "+ex);
       		}
       	} else {
       		throw new ConnectionException("A connection must exist!");
       	}
       	return result;
       }
       
}