package zzz.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1234567L;
	private String user_id;
	private String account;
	private String password;
	private String email;
	private String portrait_path;
	private String nickname;
	private String register_time;
	private int manager_flag;
	private ArrayList<Article> articles;
	private int following_num;
	private int follower_num;
	private String last_time;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPortrait_path() {
		return portrait_path;
	}
	public void setPortrait_path(String portrait_path) {
		this.portrait_path = portrait_path;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegister_time() {
		String split[] = register_time.split(" ");
		
		return split[0];
	}
	public void setRegister_time(String register_time) {

		this.register_time = register_time;
	}
	public ArrayList<Article> getArticles() {
		return articles;
	}
	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}
	public int getFollowing_num() {
		return following_num;
	}
	public void setFollowing_num(int following_num) {
		this.following_num = following_num;
	}
	public int getFollower_num() {
		return follower_num;
	}
	public void setFollower_num(int follower_num) {
		this.follower_num = follower_num;
	}
	public int getManager_flag() {
		return manager_flag;
	}
	public void setManager_flag(int manager_flag) {
		this.manager_flag = manager_flag;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	
}
