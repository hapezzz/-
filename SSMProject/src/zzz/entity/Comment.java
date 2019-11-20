package zzz.entity;

import java.io.Serializable;

public class Comment implements Serializable{

	private static final long serialVersionUID = 12345678L;
	private String comment_id;
	private String content;
	private String commentator;
	private String commented;
	private String article_id;
	private String comment_time;
	private String commentator_nickname;
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentator() {
		return commentator;
	}
	public String getCommentator_nickname() {
		return commentator_nickname;
	}
	public void setCommentator_nickname(String commentator_nickname) {
		this.commentator_nickname = commentator_nickname;
	}
	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}
	public String getCommented() {
		return commented;
	}
	public void setCommented(String commented) {
		this.commented = commented;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
}
