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
	 * 增加
	 */
	@Test
	public void addData(){
		Student stu =new Student();
		
		stu.setAge(16);
		stu.setName("李小龙11");
		//读取配置文件，获取连接连接信息
		Configuration cfg=new Configuration().configure();
		//创建SessionFactory
		SessionFactory fa=cfg.buildSessionFactory();
		//加工Session
		Session se=fa.openSession();
		Transaction tx=se.beginTransaction();
		//保存
		se.save(stu);
		//事务提交
		tx.commit();
		se.close();
	    fa.close();
		System.out.println("save ok!");
	}
	/*
	 * 删除
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
	 * 修改
	 */
	@Test
	public void updateData(){
		Session session=HibernateUtil.getSession();
		Student stu=(Student) session.load(Student.class, 3);
		stu.setName("呵呵");
		Transaction tx=session.beginTransaction();
		session.update(stu);
		tx.commit();
		HibernateUtil.CloseSession();
		System.out.println("update ok!");
	}

}
