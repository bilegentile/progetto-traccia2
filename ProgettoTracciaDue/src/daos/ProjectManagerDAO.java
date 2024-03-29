package daos;

import java.sql.SQLException;
import java.util.List;
import entity.ProjectManager;

public interface ProjectManagerDAO {
	
	public List<ProjectManager> getProjectManagerByCodFiscale(String codfiscale) throws SQLException;
	
	public int inserisciProjectManager(ProjectManager membro) throws SQLException;
	
	public int inserisciSkillProjectManager(ProjectManager membro, String s1) throws SQLException ;
}
