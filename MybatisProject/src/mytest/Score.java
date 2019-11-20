package mytest;

import java.util.List;

public class Score {
	
	private String userid;
	private List<Score_table> table;
	
	private double weight;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Score_table> getTable() {
		return table;
	}
	public void setTable(List<Score_table> table) {
		this.table = table;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
