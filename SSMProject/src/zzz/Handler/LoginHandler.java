package zzz.Handler;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import zzz.Mapper.UserMapper;
import zzz.entity.Article;
import zzz.entity.User;

@Controller
public class LoginHandler {
	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;

	@RequestMapping(value = "check", method = RequestMethod.POST)
	public String check(@RequestParam("account") String account, @RequestParam("pwd") String password,
			Map<String, Object> map, HttpServletResponse response, HttpSession session) {
		if (session.getAttribute("currentuser") != null) {
			map.put("mes", "toomany");
			return "redirect:/loginandregister/login.jsp";
		}
		String pwd = userMapper.queryLoginUser(account);
		password = DigestUtils.md5Hex(password);
		if (pwd == null) {
			map.put("mes", "accounterror");
			return "redirect:/loginandregister/login.jsp";
		}
		if (pwd.equals(password)) {

			User user = userMapper.queryUser(account);
			user.setFollower_num(userMapper.queryNumofFollowers(user.getUser_id()));
			user.setFollowing_num(userMapper.queryNumofFollowing(user.getUser_id()));
			String date = userMapper.query_logininfo(user.getUser_id());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if (date == null) {
				userMapper.create_logininfo(user.getUser_id(), sf.format(new Date()));
			} else {
				user.setLast_time(date);
				userMapper.update_logininfo(user.getUser_id(), sf.format(new Date()));
			}

			session.setAttribute("currentuser", user);
			
			List<Article> ownarts = userMapper.queryArts_User(user.getUser_id());
			session.setAttribute("ownarts", ownarts);
			
			if (user.getManager_flag() == 1) {
				List<String> blocks = userMapper.managersBlock(user.getUser_id());
				session.setAttribute("blocks", blocks);
			}
			return "redirect:/index.jsp";

		} else {
			map.put("mes", "passworderror");
			return "redirect:/loginandregister/login.jsp";
		}

	}

	@RequestMapping("homepage")
	public String homepage(HttpSession session) {
		if(session.getAttribute("currentuser")==null) {
			return "redirect:/loginandregister/login.jsp";
		}
		return "redirect:/userinfo.jsp";
	}
	
	@Autowired
	@Qualifier("user")
	private User user;

//	@ResponseBody

	@RequestMapping("register")
	public String register(@RequestParam("portrait") MultipartFile file, @RequestParam("account") String account,
			@RequestParam("pwd") String password, @RequestParam("email") String email, HttpServletResponse response,
			@RequestParam("name") String name, Map<String, Object> map) throws IOException {
		if (userMapper.queryAccount(account) == 1) {
			return "redirect:/loginandregister/register.jsp?mes=accounterror";
		} else if (userMapper.queryEmail(email) == 1) {
			return "redirect:/loginandregister/register.jsp?mes=emailerror";
		} else if (userMapper.queryNickname(name) == 1) {
			return "redirect:/loginandregister/register.jsp?mes=nameerror";
		} else {
			// 生成用户的唯一标识
			String a = UUID.randomUUID().toString().replace("-", "");
			StringBuilder id = new StringBuilder(a);
			id.insert(0, 'U');

			// 获取用户上传的头像，默认是default.png
			String path = "image/portrait/default.png";

			if (file.getOriginalFilename().contains(".")) {
				InputStream in = file.getInputStream();
				int len = -1;
				byte[] buf = new byte[1024 * 100];
				FileOutputStream out = new FileOutputStream(
						"D:\\workspace\\SSMProject\\WebContent\\image\\portrait\\" + id + file.getOriginalFilename());
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
				path = "image/portrait/" + id + file.getOriginalFilename();
			}

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sf.format(new Date());

			// 获取用户对象并赋值
			password = DigestUtils.md5Hex(password);
			user.setUser_id(id.toString());
			user.setAccount(account);
			user.setEmail(email);
			user.setPassword(password);
			user.setPortrait_path(path);
			user.setNickname(name);
			user.setRegister_time(date);
			userMapper.regeisterUser(user);
			// 跳转到登陆界面
			return "redirect:/loginandregister/login.jsp";
		}
	}

	@RequestMapping("reload")
	public String reload(HttpSession session) {

		if (session.getAttribute("currentuser") != null) {
			User user = userMapper.queryUser(((User) session.getAttribute("currentuser")).getAccount());
			user.setFollower_num(userMapper.queryNumofFollowers(user.getUser_id()));
			user.setFollowing_num(userMapper.queryNumofFollowing(user.getUser_id()));
			user.setLast_time(userMapper.query_logininfo(user.getUser_id()));
			List<Article> ownarts = userMapper.queryArts_User(user.getUser_id());
			session.setAttribute("ownarts", ownarts);
			session.setAttribute("user", user);
			if (user.getManager_flag() == 1) {
				List<String> blocks = userMapper.managersBlock(user.getUser_id());
				session.setAttribute("blocks", blocks);
			}
			if(session.getAttribute("wait_flag")==null) {
				return "redirect:/userinfo.jsp";
			}else {
				return "redirect:/pre.jsp";
			}
		}else {
			return "redirect:/index.jsp";
		}
		
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("ownarts");
		return "redirect:/index.jsp";
	}
}
