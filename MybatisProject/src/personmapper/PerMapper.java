package personmapper;

import java.util.List;

import mytest.Person;

public interface PerMapper {
	public abstract Person selectPerson(int id) ;
	public abstract List<Person> selectAllPerson();
	public abstract int insertPerson(Person per);
	public abstract int deletePerson(int id);
	public abstract int updatePerson(Person per);
}
