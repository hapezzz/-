package zzz.Mapper;

import org.apache.ibatis.annotations.Param;

import zzz.entity.Article;

public interface ArticleMapper {
	//д����
	public abstract int create_arts(Article article);
	
	//�û��Ƿ��޹�����
	public abstract int query_like(@Param("user_id")String user_id,@Param("art_id")String art_id);
	public abstract int query_report(@Param("user_id")String user_id,@Param("art_id")String art_id);
	
	//��ѯ������Ϣ
	public abstract Article view_article(String id);
	public abstract int query_likes(String id);
	public abstract int query_reports(String id);
	public abstract int query_comments(String id);
	public abstract int[] query_scores(String id);
	
	//��������
	public abstract int like_article(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("date")String date);
	public abstract int report_article(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("date")String date);
	
	//���µ����֣����β鿴Ĭ��1������Ϊ+4���ٱ�Ϊ-2
	public abstract int query_scoreexist(@Param("user_id")String user_id,@Param("art_id")String art_id);
	public abstract int update_score(@Param("user_id")String user_id,@Param("art_id")String art_id,@Param("score")int score);
	public abstract int score(@Param("user_id")String user_id,@Param("art_id")String art_id);
	
	//ɾ������
	public abstract void drop_article_likes(String id);
	public abstract void drop_article_reports(String id);
	public abstract void drop_article_comments(String id);
	public abstract int drop_article(String id);
}
