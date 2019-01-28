package zhongfucheng.domain;

public class Student {
	private Integer sid;
	private String age;
	private String name;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", age=" + age + ", name=" + name + "]";
	}
	

}
