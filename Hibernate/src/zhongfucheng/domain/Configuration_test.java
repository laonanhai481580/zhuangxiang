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
		//1.2如果配置文件不符合默认加载规则，我们可以调用
	    //new Configuration().configure(file);通过file加载
		SessionFactory sf=conf.buildSessionFactory();
		sf.openSession();
		sf.getCurrentSession();
	    //new Configuration().configure(path);通过路径加载
		// 1.3可以通过Configuration对象加载  映射文件（不推荐）
		//推荐hibernate.cfg.xml 使用mapping属性引入配置文件
		//规范：1>orm映射名与实体的简单类名一致
		// 2>orm映射文件需要与实体的类在同一包下
		conf.addClass(Userc.class);
	}*/
	 //@Test 
	 public void func1(){
		 //1加载配置
		 Configuration conf=new Configuration().configure();
		 //2根据Configuration 配置信息创建SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3.获得session
		 Session session=sf.openSession();
		 Userc u=new Userc();
		 u.setUsername("jedrry");
		 u.setPassword("12345");
		 u.setCellphone("11988244554");
		 //调用Session的save方法保存对象到数据库中
	        //使用Hibernate操作数据库，都要开启事务，得到事务对象
	     Transaction transaction =session.getTransaction();
	        //开启事务
	     transaction.begin();
		 session.save(u);
		//提交事务
	     transaction.commit();
		 //关闭资源
		 session.close();
		 sf.close();
	 }
	 
	 @Test 
	 public static void func11(){
		 //1加载配置
		 Configuration conf=new Configuration().configure();
		 //2根据Configuration 配置信息创建SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3.获得session
		 Session session=sf.openSession();
		 Userc u=new Userc();
		 u.setUsername("jedry");
		 u.setPassword("1234");
		 u.setCellphone("1198824455");
		 //调用Session的save方法保存对象到数据库中
	        //使用Hibernate操作数据库，都要开启事务，得到事务对象
	     Transaction transaction =session.getTransaction();
	        //开启事务
	     transaction.begin();
		 session.save(u);
		//提交事务
	     transaction.commit();
		 //关闭资源
		 session.close();
		 sf.close();
	 }
	 //@Test
	 public void fun2(){
		 //1.加载配置
		 Configuration conf=new Configuration().configure();
		 //2.根据Configuration 配置信息创建SessionFactory
		 SessionFactory sf=conf.buildSessionFactory();
		 //3 获取Session
		 Session session=sf.openSession();
		 //打开事务
		 Transaction ts=session.beginTransaction();
		 //先查寻出你要修改的对象
		 Userc user=(Userc)session.get(Userc.class, 1);
		 ts.commit();
		 //user.setUsername("汤姆");
		 // session.update(user);
		 //提交事务
		 //关闭资源
		 session.close();
		 sf.close();
		 System.out.println(user);
	 }
	 public static void main(String[] args) {
		 func11();
	}

}
