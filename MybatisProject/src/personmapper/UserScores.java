package personmapper;

import java.util.List;

import mytest.Score_table;

public interface UserScores {
	public abstract List<Score_table> queryScores(String userid);
	public abstract List<String> queryUsers();
	public abstract List<String> queryArts();
}
