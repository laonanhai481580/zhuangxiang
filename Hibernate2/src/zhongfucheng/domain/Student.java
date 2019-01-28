package zhongfucheng.domain;

public class Student{
	private Integer sid;
	private String sname;
	private String password;
	private String sex;
	private Integer cid;

	public Student() {

	}

	public Student(Integer sid, String sname, String password, String sex,
			Integer cid) {
		super();
		this.sid = sid;
		this.sname =sname;
		this.password = password;
		this.sex = sex;
		this.cid = cid;
	}
	public Student(String sname,String password,String sex,Integer cid){
		this.sname=sname;
		this.password=password;
		this.sex=sex;
		this.cid=cid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", password="
				+ password + ", sex=" + sex + ", cid=" + cid + "]";
	}
	

}
