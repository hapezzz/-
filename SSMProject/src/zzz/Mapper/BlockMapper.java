package zzz.Mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import zzz.entity.Article;
import zzz.entity.Block;


public interface BlockMapper {
	//�������
	public abstract int uniqueblock(String name);
	public abstract int create_block(Block block);
	
	//��ѯ�����Ϣ
	public abstract Block queryInfo_block(String key);
	public abstract String query_blockid(String name);
	public abstract ArrayList<Article>queryallArt(String id);
	public abstract ArrayList<String>query_blockname();
	
	
	//�޸İ��
	public abstract int update_block(@Param("new_name")String new_name,@Param("old_key")String key);
	public abstract int update_manager(@Param("manager")String manager_id,@Param("old_key")String key);
	
	//ɾ�����
	public abstract void drop_block_art(String id);
	public abstract int drop_block(String key);
}
