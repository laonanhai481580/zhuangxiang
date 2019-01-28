package zhongfucheng.domain;

import java.util.List;

public interface StudentDao {
	/**
	 * 1.根据用户名和密码查询
	 */
	Student findByNameAndPwd(String sname,String password);
	/**
	 * 2.延时加载：查询所有
	 */
	List<Student> findAll();
	/**
	 * 3.保存
	 */
	int save(Student stu);
	/**
	 * 4.根据主键id查询单条
	 */
	Student findById(Integer sid);
	/**
	 * 5.修改
	 */
	int update(Student stu);
	/**
	 * 6.删除对象
	 */
	int delete(Student stu);
	/**
	 * 7.分页查询
	 */
	List<Student> findPage(Page page);
	/**
	 * 8.查询总条数
	 */
	int getTotalCount();

}
