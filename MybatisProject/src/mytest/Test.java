package mytest;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import personmapper.PerMapper;
import studentMapper.StuMapper;

public class Test {

	public static void main(String[] args) {
		Reader reader = null;
		SqlSession session = null,session2=null;
		try {
			reader = Resources.getResourceAsReader("conf.xml");
			SqlSessionFactory sqlfactory = new SqlSessionFactoryBuilder().build(reader);
			session = sqlfactory.openSession();
			
			// 一般配置
//			String statement = "personmapper.personMapper.selectPerson";
//			Person per = session.selectOne(statement,1);
//			System.out.println(per);

//			String statement = "personmapper.personMapper.selectAllPerson";
//			List<Person> list = session.selectList(statement);
//			for(Person per:list) {
//				System.out.println(per.toString());
//			}

//			String statement = "personmapper.personMapper.insertPerson";
//			Person per = new Person(1,"zs");
//			int count = session.insert(statement,per);
//			if(count==1) {
//				System.out.println("插入成功");
//				session.commit();
//			}

//			String statement = "personmapper.personMapper.deletePerson";
//			int count = session.delete(statement,1);
//			if(count==1) {
//				System.out.println("删除成功");
//				session.commit();
//			}

//			String statement = "personmapper.personMapper.updatePerson";
//			Person per = new Person(2,"ls");
//			int count = session.update(statement,per);
//			if(count==1) {
//				System.out.println("更新成功");
//				session.commit();
//			}

			// 基于约定的配置
//			PerMapper mapper = session.getMapper(PerMapper.class);
//			Person per = mapper.selectPerson(2);
//			Person per2 = mapper.selectPerson(2);
//			System.out.println(per.toString());
		
			StuMapper mapper = session.getMapper(StuMapper.class);

			List<Student> students2 = mapper.selectStudentandCard();
			if (session2 != null)
				session2.close();
			List<Student> students = mapper.selectStudentandCard();
			if (session != null)
				session.close();
			for(Student student:students) {
				System.out.print(student.getStuName()+",");
				System.out.println(student.getCard().getScId());
			}

//			Class stuclass = mapper.selectClassandStu(1);
//			System.out.println(stuclass.getcId()+","+stuclass.getcName());
//			for(Student student:stuclass.getStudents()) {
//				System.out.println(student.getStuNo()+","+student.getStuName()+","+student.getCard().getScId());
//			}
			
//			List<Person>list = mapper.selectAllPerson();
//			System.out.println(list);

//			Person per = new Person(4,"zs",false);
//			int count = mapper.insertPerson(per);
//			if(count>0) {
//				System.out.println("插入成功");
//				session.commit();
//			}

//			int count = mapper.deletePerson(1);
//			if(count>0) {
//				System.out.println("删除成功");
//				session.commit();
//			}

//			Person per = new Person(2,"lsss",true);
//			int count = mapper.updatePerson(per);
//			if(count>0) {
//				System.out.println("更新成功");
//				session.commit();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
