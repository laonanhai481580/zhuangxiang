package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class App {
	public static void main(String[] args){
		//创建对象
		Userc user =new Userc();
		user.setPassword("123");
		user.setCellphone("1222222");
		user.setUsername("nihao");
        //获取加载配置管理类
        Configuration configuration = new Configuration();
        //不给参数就默认加载hibernate.cfg.xml
        configuration.configure();
        //创建Session工厂对象
        SessionFactory factory=configuration.buildSessionFactory();
        //得到Session对象
        Session session=factory.openSession();
     
        //使用Hibernate操作数据库，都要开启事务，得到事务对象
        Transaction transaction =session.getTransaction();
        //开启事务
        transaction.begin();
        //把对象添加到数据库中
        session.save(user);
        //提交事务
        transaction.commit();
        
        Userc  u=(Userc)session.get(Userc.class, 1); 
        System.out.println(u);
        //关闭Session
        
        session.close();
	}
}
