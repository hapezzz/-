package zzz.Mapper;

import org.apache.ibatis.annotations.Param;

import zzz.entity.Article;

public interface ArticleMapper {
	//写文章
	public abstract int create_arts(Article article);
	
	//用户是否赞过文章
	public abstract int query_like(@Param("user_id")String user_id,@Param("art_id")String art_id);
	public abstract int query_report(@Param("user_id")String user_id,@Param("art_id")String art_id);
	
	//查询基本信息
	public abstract Article view_article(String id);
	public abstract int query_likes(String id);
	public abstract int query_reports(String id);
	public abstract int query_comments(String id);
	public abstract int[] query_scores(String id);
	
	//操作文章
	public abstract int like_article(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("date")String date);
	public abstract int report_article(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("date")String date);
	
	//文章的评分，初次查看默认1，点赞为+4，举报为-2
	public abstract int query_scoreexist(@Param("user_id")String user_id,@Param("art_id")String art_id);
	public abstract int update_score(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("score")int score);
	public abstract int score(@Param("user_id")String user_id,@Param("art_id")String art_id);
	
	//删除文章
	public abstract void drop_article_likes(String id);
	public abstract void drop_article_reports(String id);
	public abstract void drop_article_comments(String id);
	public abstract int drop_article(String id);
}
