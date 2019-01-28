package zhongfucheng.domain;

import java.util.List;


/**
 * 业务接口
 * @author N000226
 *
 */
public interface StudentService {
	/**
	 * 1.登录的业务
	 * 
	 */
	Student login(String sname,String password);
	/**
	 * 2.注册
	 */
	int regiseter(Student stu);
	/**
	 * 3.查询所有
	 */
	List<Student> findAll();
	/**
	 * 4.根据主键id查询
	 */
	Student findById(Integer sid);
	/**
	 * 5.修改
	 */
	int update(Student stu);
	/**
	 * 6.删除
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
