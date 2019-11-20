package zzz.entity;

import java.io.Serializable;

public class Article implements Serializable{
	private static final long serialVersionUID = 123456L;
	private String article_id;
	private String content;
	private String block_id;
	private String author_id;
	private String upload_date;
	private String title;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	private int like_num;
	private int report_num;
	private int comment_num;
	private int avg_score;
	private String sim_info;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private User author;
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	public String getTag4() {
		return tag4;
	}
	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}
	public String getTag5() {
		return tag5;
	}
	public void setTag5(String tag5) {
		this.tag5 = tag5;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getReport_num() {
		return report_num;
	}
	public void setReport_num(int report_num) {
		this.report_num = report_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getAvg_score() {
		return avg_score;
	}
	public void setAvg_score(int avg_score) {
		this.avg_score = avg_score;
	}
	public String getSim_info() {
		return sim_info;
	}
	public void setSim_info(String sim_info) {
		this.sim_info = sim_info;
	}
	
}
