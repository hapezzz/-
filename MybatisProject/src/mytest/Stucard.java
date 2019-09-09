package mytest;

import java.io.Serializable;

public class Stucard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 12345678L;
	private int scId;
	private String scInfo;
	public int getScId() {
		return scId;
	}
	public void setScId(int scId) {
		this.scId = scId;
	}
	public String getScInfo() {
		return scInfo;
	}
	public void setScInfo(String scInfo) {
		this.scInfo = scInfo;
	}
}
