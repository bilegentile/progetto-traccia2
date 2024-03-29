package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Sviluppatore;


public interface SviluppatoreDAO {
	
	public List<Sviluppatore> getSviluppatoreByCodFiscale(String codfiscale) throws SQLException ;
	public int inserisciSviluppatore(Sviluppatore membro) throws SQLException;
	public int inserisciSkillSviluppatore(Sviluppatore membro, String s1) throws SQLException;
	public List<Sviluppatore> getAllSviluppatoriProgetto (String codfiscale) throws SQLException;
	public int inserisciValutazione(String valutazione, String codFiscale) throws SQLException;
	public List<Sviluppatore> getPartecipantiProgettoPS(String nomeprogetto) throws SQLException;
	public List<Sviluppatore> getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS(int salario, String skills, String valutazione, String progetto, String tipologia, String codfiscalePM) throws SQLException;
	public List<Sviluppatore> getAllSviluppatoriProgettoEMeeting (String codfiscale) throws SQLException;
}
