package zhongfucheng.domain;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class StudentDaoImpl implements StudentDao{
 /**
  * 2.根据用户名和密码查询
  */
	@Override
	public Student findByNameAndPwd(String sname, String password) {
		// 1.获取session对象
		Session session=Hibernate_Util.getSession();
		//2.定义hql语句(命名参数)
		String hql="from Student where sname=:sname and password=:password";
		//3.Session创建Query对象
		Query query=session.createQuery(hql);
		//4.query对象给占位符赋值
		query.setParameter("sname", sname);
		query.setParameter("password", password);
		//5.执行操作（uniqueResult()查询的单行数据）
		Student stu=(Student)query.uniqueResult();
		//6.释放资源
		Hibernate_Util.closeSession();
		return stu;
		
	}
  /**
   * 1.查询所有：立即加载
   */
	@Override
	public List<Student> findAll() {
		// 1.获取session对象
		Session session=Hibernate_Util.getSession();
		//2.定义hql语句
		String hql="from Student";
		//3.Session创建Query对象
		Query query=session.createQuery(hql);
		//4.query对象执行操作
		List<Student> it=query.list();
		//5.释放资源
		Hibernate_Util.closeSession();
		return it;
	}
 /**
  * 添加
  */
	@Override
	public int save(Student stu) {
		int num=1;
		//1.获取session对象
		Session session=Hibernate_Util.getSession();
		//执行操作
		try {
			session.save(stu);
			session.beginTransaction().commit();
		} catch (HibernateException e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.释放资源 
			Hibernate_Util.closeSession();
		}
		return num;
	}
 /**
  * 根据id主键查询单条
  */
	@Override
	public Student findById(Integer sid) {
		//1.获取session对象
		Session session=Hibernate_Util.getSession();
		//2.利用session根据主键查询
		Student stu=(Student)session.get(Student.class, sid);
		/*
		 * 
		 */
		Hibernate_Util.closeSession();
        //session.clear();
		return stu;
	}
  /**
   * 修改
   */
	@Override
	public int update(Student stu) {
		int num=1;
		//1.获取session对象
		Session session=Hibernate_Util.getSession();
		try {
			//执行操作
			//该对象的主键id必须存在
			session.update(stu);
			session.beginTransaction().commit();
		} catch (HibernateException e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.释放资源
			Hibernate_Util.closeSession();
		}
		return num;
	}

	/**
	 * 删除对象
	 */
	@Override
	public int delete(Student stu) {
		int num=1;
		//1.获取session对象
		Session session=Hibernate_Util.getSession();
		try {
			//session删除操作
			session.delete(stu);
			session.beginTransaction().commit();
		} catch (Exception e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.释放资源
			Hibernate_Util.closeSession();
		}
		return num;
	}
  /**
   * 7.分页查询
   */
	@Override
	public List<Student> findPage(Page page) {
		// 1.获取session
		Session session=Hibernate_Util.getSession();
		//2.定义查询最大记录数的Query对象
		 String hql="from Student";
		 //3.定义查询最大记录数的Query对象
		 Query querypage=session.createQuery(hql);
		 //4.查询最大记录数的数据
		 querypage.setMaxResults(page.getPagesize());
		 //5.确定查询起点
		 querypage.setFirstResult(page.getStartrow());
		 //6.分页查询
		 List<Student> list=querypage.list();
		 //7.关闭session
		 Hibernate_Util.closeSession();
		return list;
	}
  /**
   * 查询总条数
   */
	@Override
	public int getTotalCount() {
		// 1.获取sessoion
		Session session=Hibernate_Util.getSession();
		//2.定义查询总条数hql语句
		String hqlcount="select count(*) from Student";
		//3.利用Session创建Query对象
		Query querycount=session.createQuery(hqlcount);
		//4.获取总条数（返回单行数据uniqueResult()）
		Integer totalCount=Integer.parseInt(querycount.uniqueResult().toString());
		//5.释放资源
		Hibernate_Util.closeSession();
		return 0;
	}
	/**
	 * 1.查询所有：立即加载
	 */

}
