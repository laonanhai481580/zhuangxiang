package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test2 {
	Session session;
	Transaction tx;
	@After
	public void afterTest(){
		tx.commit();
		HibernateUtil.getSession();
	}
	@Before
	public void initData(){
		session=HibernateUtil.getSession();
		tx=session.beginTransaction();
	}
	
	@Test
	public void getData(){
		Student stu=(Student)session.get(Student.class, 3);
		System.out.println(stu);
	}
	/*
	 * ����
	 */
	@Test
	public void addData(){
		Student stu =new Student();
		
		stu.setAge(16);
		stu.setName("��С��11");
		//��ȡ�����ļ�����ȡ����������Ϣ
		Configuration cfg=new Configuration().configure();
		//����SessionFactory
		SessionFactory fa=cfg.buildSessionFactory();
		//�ӹ�Session
		Session se=fa.openSession();
		Transaction tx=se.beginTransaction();
		//����
		se.save(stu);
		//�����ύ
		tx.commit();
		se.close();
	    fa.close();
		System.out.println("save ok!");
	}
	/*
	 * ɾ��
	 */
	@Test
	public void delDate(){
		Session session=HibernateUtil.getSession();
		Student stu=new Student();
		stu.setSid(2);
		//stu.setAge(16);
		Transaction tx=session.beginTransaction();
		session.delete(stu);
		tx.commit();
		HibernateUtil.CloseSession();
		System.out.println("del ok");
	}
	/**
	 * �޸�
	 */
	@Test
	public void updateData(){
		Session session=HibernateUtil.getSession();
		Student stu=(Student) session.load(Student.class, 3);
		stu.setName("�Ǻ�");
		Transaction tx=session.beginTransaction();
		session.update(stu);
		tx.commit();
		HibernateUtil.CloseSession();
		System.out.println("update ok!");
	}

}
