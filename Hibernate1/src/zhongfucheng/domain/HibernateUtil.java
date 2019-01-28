package zhongfucheng.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration cf=new Configuration().configure();
	private static SessionFactory sf=cf.buildSessionFactory();
    //方法放回session
	public static Session getSession(){
		return sf.openSession();
	};
	public static void CloseSession(){
		getSession().close();
	}
}
