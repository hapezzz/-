package zzz.Mapper;

import zzz.entity.Student;

public interface StudentMapper {
	public abstract Student queryStudent(int stuno);
	public abstract int addStudent(Student student);
}
