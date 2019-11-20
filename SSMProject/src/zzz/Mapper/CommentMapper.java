package zzz.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zzz.entity.Comment;

public interface CommentMapper {
	public abstract void create_comment(Comment comment);
	
	//public abstract List<Comment> query_comments(@Param("pageSize")int pagesize,@Param("currentPage")int currentpage,@Param("art_id")String art_id);
	public abstract List<Comment> query_comments(String art_id);
}
