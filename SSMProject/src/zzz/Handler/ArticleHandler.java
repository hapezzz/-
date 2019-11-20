package zzz.Handler;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import zzz.Mapper.ArticleMapper;
import zzz.Mapper.BlockMapper;
import zzz.Mapper.CommentMapper;
import zzz.Mapper.UserMapper;
import zzz.entity.Article;
import zzz.entity.Comment;
import zzz.entity.Page;
import zzz.entity.User;

@Controller
public class ArticleHandler {
	
	@Autowired
	@Qualifier("articleMapper")
	ArticleMapper articleMapper;
	
	@Autowired
	@Qualifier("blockMapper")
	BlockMapper blockMapper;
	
	@Autowired
	@Qualifier("article")
	Article article;
	
	//写帖子
	@SuppressWarnings("unchecked")
	@RequestMapping("create_article")
	public String create_article(@RequestParam("content")String content,HttpSession session,@RequestParam("blockname")String  blockname,@RequestParam("tag")String []tags,@RequestParam("title")String title) {
		User user = (User)session.getAttribute("user");
		if(user==null) {
			//return "<a href='loginandregister/login.jsp'>还未登陆，请登录</a>";
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sf.format(new Date());
		
		String [] insert_tags = {" "," "," "," "," "," "};
		for(int i=0;i<tags.length;i++) {
			insert_tags[i] = tags[i];
		}
		
		String block_id = blockMapper.query_blockid(blockname);
		String a = UUID.randomUUID().toString().replace("-", "") ;
		StringBuilder id = new StringBuilder(a);
		id.insert(0, 'A');
		
		article.setTitle(title);
		article.setArticle_id(id.toString());
		article.setAuthor_id(user.getUser_id());
		article.setBlock_id(block_id);
		article.setContent(content);
		article.setUpload_date(date);
		@SuppressWarnings("rawtypes")
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		Arrays.sort(tags,cmp);
		article.setTag1(insert_tags[0]);
		article.setTag2(insert_tags[1]);
		article.setTag3(insert_tags[2]);
		article.setTag4(insert_tags[3]);
		article.setTag5(insert_tags[4]);
		articleMapper.create_arts(article);
		
		return "redirect:/reload";
	}
	
	@Autowired
	@Qualifier("userMapper")
	UserMapper userMapper;
	
	@Autowired
	@Qualifier("commentMapper")
	CommentMapper commentMapper;
	
	@Autowired
	@Qualifier("page")
	Page page;
	
	
	@RequestMapping("view_art/{art_id}")
	public String view_art(@PathVariable("art_id")String art_id,HttpSession session,Map<String,Object>map) {
		User user = (User)session.getAttribute("user");
		
		Article c_article = articleMapper.view_article(art_id);
		int comment_num = articleMapper.query_comments(art_id);
		if(user!=null) {
			map.put("like_flag", 0);
			map.put("report_flag", 0);
			if(articleMapper.query_like(user.getUser_id(),art_id)==1) {
				map.put("like_flag", 1);
			}
			if(articleMapper.query_report(user.getUser_id(), art_id)==1) {
				map.put("report_flag", 1);
			}
			if(articleMapper.query_scoreexist(user.getUser_id(),art_id)==0) {
				articleMapper.score(user.getUser_id(), art_id);
			}

		}
		int like_num = articleMapper.query_likes(art_id);		
		int report_num = articleMapper.query_reports(art_id);
		
		int [] scores = articleMapper.query_scores(art_id);
		int total = 0;
		for(int score:scores) {
			total += score;
		}
		String parts [] = c_article.getContent().split("\n");
		c_article.setLike_num(like_num);
		c_article.setAvg_score(total/scores.length);
		c_article.setReport_num(report_num);
		c_article.setSim_info(parts[0]);
		c_article.setComment_num(comment_num);
//		map.put("avg",total/scores.length);
//		map.put("like_num", like_num);
//		map.put("comment_num", comment_num);
//		map.put("report_num", report_num);
		map.put("article", c_article);
		
		
		
		
		List<Comment> comments = commentMapper.query_comments(art_id);
		for(Comment comment:comments) {
			comment.setCommentator_nickname(userMapper.queryUser(comment.getCommentator()).getNickname());
		}
		map.put("comments", comments);
		
		return "art_info";
		
		//评论,分页
//		int totalCount = commentMapper.query_NumofComments(art_id);
//		page.setTotalCount(totalCount);
//		page.setPageSize(4);
//		page.setTotalPage();
//		ArrayList<Comment>comments = (ArrayList<Comment>)commentMapper.query_comments(page.getPageSize(),page.getCurrentPage(),art_id);
//		page.setDisplays(comments);
//		map.put("comment_page", page);
	}
	
	@ResponseBody
	@RequestMapping("like/{art_id}")
	
	public String like(HttpSession session,@PathVariable("art_id")String art_id) {
		
		User user = (User)session.getAttribute("user");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sf.format(new Date());
		
		articleMapper.like_article(user.getUser_id(), art_id,date);
		articleMapper.update_score(user.getUser_id(),art_id,4);
		
		return "art_info";
	}
	
	@ResponseBody
	@RequestMapping("report/{art_id}")
	
	public String report(HttpSession session,@PathVariable("art_id")String art_id) {
		
		User user = (User)session.getAttribute("user");
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sf.format(new Date());
		
		articleMapper.report_article(user.getUser_id(), art_id,date);
		articleMapper.update_score(user.getUser_id(), art_id, -2);
		
		return "art_info";
	}
	
	@ResponseBody
	@RequestMapping("comment")
	public void comment(HttpSession session,HttpServletRequest request) {
		String art_id = request.getParameter("art_id");
		User user = (User)session.getAttribute("user");
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		
		//产生标识符
		String a = UUID.randomUUID().toString().replace("-", "") ;
		StringBuilder id = new StringBuilder(a);
		id.insert(0, 'C');
		//创建日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sf.format(new Date());
		
		comment.setComment_id(id.toString());
		comment.setContent(content);
		comment.setCommentator(user.getUser_id());
		comment.setCommented(art_id);
		comment.setArticle_id(art_id);
		comment.setComment_time(date);
		
		commentMapper.create_comment(comment);
	}
	
}
