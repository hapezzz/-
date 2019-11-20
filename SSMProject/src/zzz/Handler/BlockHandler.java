package zzz.Handler;


import java.text.SimpleDateFormat;
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

import zzz.Mapper.BlockMapper;
import zzz.Mapper.UserMapper;
import zzz.entity.Article;
import zzz.entity.Block;
import zzz.entity.User;

@Controller

public class BlockHandler {
	@Autowired
	@Qualifier("blockMapper")
	BlockMapper blockMapper;
	
	@Autowired
	@Qualifier("userMapper")
	UserMapper userMapper;
	
	@Autowired
	@Qualifier("block")
	private Block block;
	

	//创建板块
	@RequestMapping("create_block")
	public String create_block(@RequestParam("block_name")String block_name,@RequestParam("manager") String manager) {
		//板块名字唯一
		if(blockMapper.uniqueblock(block_name)==1) {
			
			return "redirect:/userinfo.jsp?mes=exist";
		}
		//产生标识符
		String a = UUID.randomUUID().toString().replace("-", "") ;
		StringBuilder id = new StringBuilder(a);
		id.insert(0, 'B');
		//创建日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sf.format(new Date());
		block.setBlock_id(id.toString());
		block.setBlock_name(block_name);
		block.setCreate_time(date);
		block.setManager(manager);
		
		blockMapper.create_block(block);
		return "redirect:/userinfo.jsp?mes=success";
	}
	
	@RequestMapping("query_block/{block_name}")
	public String query_block(@PathVariable("block_name")String key,Map<String,Object> map) {
		//查看板块的基本信息 
		Block block = blockMapper.queryInfo_block(key);
		map.put("block",block);
		
		//管理员
		User manager = userMapper.queryUser(block.getManager());
		map.put("manager",manager);
		//查询文章信息
		List<Article> arts = blockMapper.queryallArt(block.getBlock_id());
		if(arts.size()==0) {
			map.put("message", "当前板块暂无文章");
		}
		map.put("arts", arts);
		
		return "blocks";
	}
	
	//修改板块信息
	@RequestMapping("update_block/{block_name}")
	public String update_block(@PathVariable("block_name")String key,HttpServletRequest request,Map<String,Object> map) {
		String name = request.getParameter("new_name");
		String manager = request.getParameter("manager");
		map.put("block", blockMapper.queryInfo_block(key));
		if(name.length()>0) {
			
			if(blockMapper.uniqueblock(name)==1) {
				map.put("block_mes", "name_exist");
				return "blocks";
			}
			blockMapper.update_block(name, key);
		}
		if(manager.length()>0) {
			User user = userMapper.queryUser(manager);
			if(user==null) {
				map.put("block_mes", "user_notexist");
				return "blocks";
			}else if(user.getManager_flag()==0) {
				map.put("block_mes", "user_notmanager");
				return "blocks";
			}
			blockMapper.update_manager(user.getUser_id(), key);
		}
		return "redirect:/reload";
	}
	
	
	//删除板块
	@RequestMapping("delete_block")
	public String delete_block(@RequestParam("block_name")String name) {
		String block_id = blockMapper.query_blockid(name);
		
		blockMapper.drop_block_art(block_id);
		blockMapper.drop_block(block_id);
		
		return "redirect:/reload";
	}
	
}
