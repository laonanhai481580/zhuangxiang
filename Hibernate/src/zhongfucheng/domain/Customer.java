package zhongfucheng.domain;

public class Customer {
	private Integer id;  //主键id
	private String  name; //客户姓名
	private Integer age;//客户年龄
	private String  sex;//客户性别
	private String  city;//客户地址
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    //重写toString()方法
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", city=" + city + "]";
    }
	

}
