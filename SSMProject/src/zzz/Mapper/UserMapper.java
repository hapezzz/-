package zzz.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zzz.entity.Article;
import zzz.entity.Block;
import zzz.entity.User;

public interface UserMapper {
	//public abstract ArrayList<User> selectCurrent(Page page);
	//public abstract int selectTotalcount();
	//ע���û�
	public abstract int regeisterUser(User user);
	public abstract int queryEmail(String email);
	public abstract int queryAccount(String account);
	public abstract int queryNickname(String name);
	
	//��ѯ��ǰ�����ߵİ����Ϣ 
	public abstract List<String> managersBlock(String id);
	
	//��֤��½
	public abstract String queryLoginUser(String account);
	public abstract String query_logininfo(String id);
	public abstract void update_logininfo(@Param("id")String id,@Param("time")String time);
	public abstract void create_logininfo(@Param("id")String id,@Param("time")String time);
	//�û���ϵ
	public abstract int followingornot(String [] ids);
	
	//�û���Ϣ
	public abstract User queryUser(String key);
	public abstract User queryUserbyName(String name);
	public abstract int queryNumofFollowers(String userid);
	public abstract List<String> queryAllFollowers(@Param("id")String userid,@Param("currentPage")int cpage,@Param("pageSize")int tpage);
	public abstract int queryNumofFollowing(String userid);
	public abstract List<String> queryAllFollowing(@Param("id")String userid,@Param("currentPage")int cpage,@Param("pageSize")int tpage);
	public abstract int following(String [] focusing_info);
	public abstract List<Article> queryArts_User(String id);
	
	//��ѯ��Ϣ
	public abstract List<User> queryByusername(String name);
	public abstract List<Article> queryByartname(String name);
	public abstract List<Block> queryByblockname(String name);
	
	//ע���û�
	public abstract void drop_userarticle(String id);
	public abstract void drop_userlike(String id);
	public abstract void drop_userreport(String id);
	public abstract void drop_usercomment(String id);
	public abstract void drop_usermanager(String id);
	public abstract void drop_user(String id);
	
	
	//ȡ����ע
	public abstract int drop_following(@Param("er_id")String er_id,@Param("ing_id")String ing_id);
	
	public abstract int update_user(@Param("portrait")String portrait,@Param("nickname")String nickname,@Param("email")String email,@Param("account")String account);
	
	public abstract int update_password(@Param("password")String password,@Param("account")String account);
}
