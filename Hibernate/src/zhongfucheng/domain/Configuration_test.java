package zhongfucheng.domain;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Configuration_test {
	//@Test
/*	public void funceshi1(){
		Configuration conf=new Configuration().configure();
		//1.2��������ļ�������Ĭ�ϼ��ع������ǿ��Ե���
	    //new Configuration().configure(file);ͨ��file����
		SessionFactory sf=conf.buildSessionFactory();
		sf.openSession();
		sf.getCurrentSession();
	    //new Configuration().configure(path);ͨ��·������
		// 1.3����ͨ��Configuration�������  ӳ���ļ������Ƽ���
		//�Ƽ�hibernate.cfg.xml ʹ��mapping�������������ļ�
		//�淶��1>ormӳ������ʵ��ļ�����һ��
		// 2>ormӳ���ļ���Ҫ��ʵ�������ͬһ����
		conf.addClass(Userc.class);
	}*/
	 //@Test 
	 public void func1(){
		 //1��������
		 Configuration conf=new Configuration().configure();
		 //2����Configuration ������Ϣ����SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3.���session
		 Session session=sf.openSession();
		 Userc u=new Userc();
		 u.setUsername("jedrry");
		 u.setPassword("12345");
		 u.setCellphone("11988244554");
		 //����Session��save��������������ݿ���
	        //ʹ��Hibernate�������ݿ⣬��Ҫ�������񣬵õ��������
	     Transaction transaction =session.getTransaction();
	        //��������
	     transaction.begin();
		 session.save(u);
		//�ύ����
	     transaction.commit();
		 //�ر���Դ
		 session.close();
		 sf.close();
	 }
	 
	 @Test 
	 public static void func11(){
		 //1��������
		 Configuration conf=new Configuration().configure();
		 //2����Configuration ������Ϣ����SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3.���session
		 Session session=sf.openSession();
		 Userc u=new Userc();
		 u.setUsername("jedry");
		 u.setPassword("1234");
		 u.setCellphone("1198824455");
		 //����Session��save��������������ݿ���
	        //ʹ��Hibernate�������ݿ⣬��Ҫ�������񣬵õ��������
	     Transaction transaction =session.getTransaction();
	        //��������
	     transaction.begin();
		 session.save(u);
		//�ύ����
	     transaction.commit();
		 //�ر���Դ
		 session.close();
		 sf.close();
	 }
	 //@Test
	 public void fun2(){
		 //1.��������
		 Configuration conf=new Configuration().configure();
		 //2.����Configuration ������Ϣ����SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3 ��ȡSession
		 Session session=sf.openSession();
		 //������
		 Transaction ts=session.beginTransaction();
		 //�Ȳ�Ѱ����Ҫ�޸ĵĶ���
		 Userc user=(Userc)session.get(Userc.class, 1);
		 ts.commit();
		 //user.setUsername("��ķ");
		 // session.update(user);
		 //�ύ����
		 //�ر���Դ
		 session.close();
		 sf.close();
		 System.out.println(user);
	 }
	 public static void main(String[] args) {
		 func11();
	}

}
