package studentMapper;

import java.util.List;

import mytest.Student;
import mytest.Class;
public interface StuMapper {
	public abstract List<Student>selectStudentandCard();
	public abstract Class selectClassandStu(int classid);
}
