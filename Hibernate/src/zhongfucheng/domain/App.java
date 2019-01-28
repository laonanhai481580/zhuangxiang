package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class App {
	public static void main(String[] args){
		//��������
		Userc user =new Userc();
		user.setPassword("123");
		user.setCellphone("1222222");
		user.setUsername("nihao");
        //��ȡ�������ù�����
        Configuration configuration = new Configuration();
        //����������Ĭ�ϼ���hibernate.cfg.xml
        configuration.configure();
        //����Session��������
        SessionFactory factory=configuration.buildSessionFactory();
        //�õ�Session����
        Session session=factory.openSession();
     
        //ʹ��Hibernate�������ݿ⣬��Ҫ�������񣬵õ��������
        Transaction transaction =session.getTransaction();
        //��������
        transaction.begin();
        //�Ѷ�����ӵ����ݿ���
        session.save(user);
        //�ύ����
        transaction.commit();
        
        Userc  u=(Userc)session.get(Userc.class, 1); 
        System.out.println(u);
        //�ر�Session
        
        session.close();
	}
}
