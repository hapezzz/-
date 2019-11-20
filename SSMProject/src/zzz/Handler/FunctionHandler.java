package zzz.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import zzz.Mapper.UserMapper;
import zzz.entity.Article;
import zzz.entity.Page;
import zzz.entity.User;

@Controller
public class FunctionHandler {
	@Autowired
	@Qualifier("userMapper")
	UserMapper userMapper;

	@RequestMapping("follow/{following}")

	// 添加关注
	public String follow(@SessionAttribute("user") User user, Map<String, Object> map,
			@PathVariable("following") String following) {
		if (user == null) {
			map.put("mes", "logouterror");
			return "redirect:/loginandregister/login.jsp";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] info = { user.getUser_id(), following, sf.format(new Date()).toString() };
		userMapper.following(info);

		return "redirect:/reload";
	}

	// 查找用户或者文章
	@RequestMapping("find")
	public String find(HttpSession session, @RequestParam("name") String name, Map<String, Object> map) {
		List<User> users = userMapper.queryByusername(name);
		List<Article> arts = userMapper.queryByartname(name);

		User user = (User)session.getAttribute("user");
		if(user!=null) {
			for(User temp:users) {
				if(temp.getUser_id().equalsIgnoreCase(user.getUser_id())) {
					users.remove(temp);
					break;
				}
			}
		}
		
		map.put("users", users);
		map.put("arts", arts);
		return "success";
	}

	// 查看另一个用户的部分信息（用户名，等级（暂），注册时长，发表过的文章，是否是管理员等）
	@RequestMapping("find_user/{uname}")
	public String find_user(@PathVariable("uname") String uname, Map<String, Object> map, HttpSession session) {
		User user = userMapper.queryUserbyName(uname);
		user.setFollower_num(userMapper.queryNumofFollowers(user.getUser_id()));
		user.setFollowing_num(userMapper.queryNumofFollowing(user.getUser_id()));
		User c_user = (User)session.getAttribute("user");
		if(c_user!=null) {
			String[] ids = { c_user.getUser_id(), user.getUser_id() };
			map.put("flag", userMapper.followingornot(ids));
		}
		map.put("f_user", user);
		return "otheruser";
	}

	@Autowired
	@Qualifier("page")
	Page page;

	// 查看粉丝或者关注的人
	@RequestMapping("queryfollow/{key}")
	public String queryfollow(@PathVariable("key") String key, HttpServletRequest request, HttpSession session,
			Map<String, Object> map) {

		List<User> follow = new ArrayList<User>();

		int currentPage = 0;

		page.setTotalCount(Integer.parseInt(request.getParameter("followcount")));
		page.setPageSize(3);
		page.setTotalPage();
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		page.setCurrentPage(currentPage);

		if (key.equalsIgnoreCase("following")) {
			List<String> ingids = userMapper.queryAllFollowing(((User) session.getAttribute("user")).getUser_id(),
					page.getCurrentPage(), page.getPageSize());
			for (String id : ingids) {
				User e = userMapper.queryUser(id);
				follow.add(e);
			}
			map.put("key", "following");
		} else if (key.equalsIgnoreCase("follower")) {
			List<String> ingids = userMapper.queryAllFollowers(((User) session.getAttribute("user")).getUser_id(),
					page.getCurrentPage(), page.getPageSize());
			for (String id : ingids) {
				User e = userMapper.queryUser(id);
				follow.add(e);
			}
			map.put("key", "follower");
		}

		map.put("currentPage", page.getCurrentPage());
		map.put("totalPage", page.getTotalPage());
		map.put("follow", follow);
		map.put("followcount", page.getTotalCount());

		return "follow";
	}

	// 取消关注
	@RequestMapping("cancle_follow/{ing_id}")
	public String cancle_follow(HttpSession session, @PathVariable("ing_id") String ing_id) {
		User user = (User) session.getAttribute("user");
		userMapper.drop_following(user.getUser_id(), ing_id);
		return "redirect:/reload";
	}

	@RequestMapping("view_ownarts")
	public String view_ownarts(HttpSession session,Map<String,Object>map) {
		
		User user = (User)session.getAttribute("user");
		if(user==null) {
			return"";
		}
		List<Article> ownarts = userMapper.queryArts_User(user.getUser_id());
		map.put("ownarts", ownarts);
		return "ownarts";
		
	}
	
	// 修改部分用户信息
	@RequestMapping("update_user")
	public String update_user(HttpSession session, HttpServletRequest request,@RequestParam("portrait")MultipartFile file) throws Exception {
		String nickname = null, email = null,portrait = null;
		User user =(User)session.getAttribute("user");
		nickname = (String) request.getParameter("nickname");
		if (userMapper.queryNickname(nickname) == 1) {
			return "redirect:/loginandregister/update_user.jsp";
		}
		email = (String) request.getParameter("email");
		if (userMapper.queryEmail(email) == 1) {
			return "redirect:/loginandregister/update_user.jsp";
		}
		if(file.getOriginalFilename().contains(".")) {
			InputStream in = file.getInputStream();
			int len = -1;
			byte [] buf = new byte[1024*100];
			FileOutputStream out = null;
			portrait ="image/portrait/"+ user.getUser_id()+file.getOriginalFilename();
			if(!user.getPortrait_path().contains(user.getUser_id())) {
				
				out = new FileOutputStream("D:\\workspace\\SSMProject\\WebContent\\image\\portrait\\"+user.getUser_id()+file.getOriginalFilename());
			}else {
				
				File old_file = new File("D:\\workspace\\SSMProject\\WebContent\\"+user.getPortrait_path());
				old_file.renameTo(new File("D:\\workspace\\SSMProject\\WebContent\\image\\portrait\\"+user.getUser_id()+file.getOriginalFilename()));
				out = new FileOutputStream("D:\\workspace\\SSMProject\\WebContent\\image\\portrait\\"+user.getUser_id()+file.getOriginalFilename());
			}
			while((len = in.read(buf))!=-1){
				out.write(buf,0,len);
			}
			in.close();
			out.close();
			
		}
		userMapper.update_user(portrait,nickname, email, user.getAccount());
		session.setAttribute("wait_flag",1);
		return "redirect:/reload";
		
	}
	
	@RequestMapping("update_password")
	public String update_password(HttpSession session,HttpServletRequest request) {
		User user = (User)session.getAttribute("user");
		String password = null;
		String old_pwd = (String) request.getParameter("old_password");
		if (!userMapper.queryLoginUser(user.getAccount()).equalsIgnoreCase(DigestUtils.md5Hex(old_pwd))) {
			return "redirect:/loginandregister/update_user.jsp";
		}else {
			password = DigestUtils.md5Hex((String) request.getParameter("password"));
			userMapper.update_password(password, user.getAccount());
			return "redirect:/logout";
		}
	}

	
	
//	@RequestMapping("find_art/{artname}")
//	public String find_art(@PathVariable("artname")String artname,Map<String,Object> map) {
//		
//		
//		
//		return ""; 
//	}
}
