package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTestDemo {
	// �������
	Configuration config;
	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;

	@Before
	public void setUp() {
		// 1.����hibernate.cfg.xml����
		config = new Configuration().configure();
		// 2.��ȡSessionFactory
		sessionFactory = config.buildSessionFactory();
		// 3.���һ�� session
		session = sessionFactory.openSession();
		// 4.��ʼ����
		transaction = session.beginTransaction();
	}

	// ��Ӳ���
	@Test
	public void insert() {
		// 5.����
		Customer customer = new Customer();
		// customer.setId(1);
		customer.setName("zhangsan");
		customer.setAge(20);
		customer.setSex("m");
		customer.setCity("guangzhou");
		session.save(customer);
	}

	/*
	 * //ɾ������
	 * 
	 * @Test public void delete() { //�Ȳ�ѯ Customer
	 * customer=(Customer)session.get(Customer.class, 1); //��ɾ��
	 * session.delete(customer); }
	 */
	// ��ѯ����
	@Test
	public void select() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer);
	}

	// ���²���
	@Test
	public void update() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("zhangsan");
		customer.setAge(20);
		customer.setSex("m");
		// �޸ĵ�ַΪbeijing
		customer.setCity("beijing");
		// ���ھ͸��£������ھ�ִ�в������
		session.saveOrUpdate(customer);
	}

	@After
	public void closeTransaction() {
		// 6.�ύ����
		transaction.commit();
		// 7.�ر���Դ
		session.close();
		sessionFactory.close();
	}

}
