package zzz.entity;

import java.io.Serializable;

public class Block implements Serializable{

	private static final long serialVersionUID = 123456L;
	private String block_id;
	private String block_name;
	private String create_time;
	private String manager;

	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public String getCreate_time() {
		String[] split = create_time.split(" ");
		String date = split[0];
		return date;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
}
