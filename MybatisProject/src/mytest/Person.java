package mytest;

public class Person {
	private int id;
	private String name;
	private boolean sex;
	public Person() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	public Person(int id, String name,boolean sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	
}
