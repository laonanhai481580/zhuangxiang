package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTestDemo {
	// 定义变量
	Configuration config;
	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;

	@Before
	public void setUp() {
		// 1.加载hibernate.cfg.xml配置
		config = new Configuration().configure();
		// 2.获取SessionFactory
		sessionFactory = config.buildSessionFactory();
		// 3.获得一个 session
		session = sessionFactory.openSession();
		// 4.开始事务
		transaction = session.beginTransaction();
	}

	// 添加操作
	@Test
	public void insert() {
		// 5.操作
		Customer customer = new Customer();
		// customer.setId(1);
		customer.setName("zhangsan");
		customer.setAge(20);
		customer.setSex("m");
		customer.setCity("guangzhou");
		session.save(customer);
	}

	/*
	 * //删除操作
	 * 
	 * @Test public void delete() { //先查询 Customer
	 * customer=(Customer)session.get(Customer.class, 1); //再删除
	 * session.delete(customer); }
	 */
	// 查询操作
	@Test
	public void select() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer);
	}

	// 更新操作
	@Test
	public void update() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("zhangsan");
		customer.setAge(20);
		customer.setSex("m");
		// 修改地址为beijing
		customer.setCity("beijing");
		// 存在就更新，不存在就执行插入操作
		session.saveOrUpdate(customer);
	}

	@After
	public void closeTransaction() {
		// 6.提交事务
		transaction.commit();
		// 7.关闭资源
		session.close();
		sessionFactory.close();
	}

}
