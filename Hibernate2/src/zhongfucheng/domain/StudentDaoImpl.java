package zhongfucheng.domain;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class StudentDaoImpl implements StudentDao{
 /**
  * 2.�����û����������ѯ
  */
	@Override
	public Student findByNameAndPwd(String sname, String password) {
		// 1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		//2.����hql���(��������)
		String hql="from Student where sname=:sname and password=:password";
		//3.Session����Query����
		Query query=session.createQuery(hql);
		//4.query�����ռλ����ֵ
		query.setParameter("sname", sname);
		query.setParameter("password", password);
		//5.ִ�в�����uniqueResult()��ѯ�ĵ������ݣ�
		Student stu=(Student)query.uniqueResult();
		//6.�ͷ���Դ
		Hibernate_Util.closeSession();
		return stu;
		
	}
  /**
   * 1.��ѯ���У���������
   */
	@Override
	public List<Student> findAll() {
		// 1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		//2.����hql���
		String hql="from Student";
		//3.Session����Query����
		Query query=session.createQuery(hql);
		//4.query����ִ�в���
		List<Student> it=query.list();
		//5.�ͷ���Դ
		Hibernate_Util.closeSession();
		return it;
	}
 /**
  * ���
  */
	@Override
	public int save(Student stu) {
		int num=1;
		//1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		//ִ�в���
		try {
			session.save(stu);
			session.beginTransaction().commit();
		} catch (HibernateException e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.�ͷ���Դ 
			Hibernate_Util.closeSession();
		}
		return num;
	}
 /**
  * ����id������ѯ����
  */
	@Override
	public Student findById(Integer sid) {
		//1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		//2.����session����������ѯ
		Student stu=(Student)session.get(Student.class, sid);
		/*
		 * 
		 */
		Hibernate_Util.closeSession();
        //session.clear();
		return stu;
	}
  /**
   * �޸�
   */
	@Override
	public int update(Student stu) {
		int num=1;
		//1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		try {
			//ִ�в���
			//�ö��������id�������
			session.update(stu);
			session.beginTransaction().commit();
		} catch (HibernateException e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.�ͷ���Դ
			Hibernate_Util.closeSession();
		}
		return num;
	}

	/**
	 * ɾ������
	 */
	@Override
	public int delete(Student stu) {
		int num=1;
		//1.��ȡsession����
		Session session=Hibernate_Util.getSession();
		try {
			//sessionɾ������
			session.delete(stu);
			session.beginTransaction().commit();
		} catch (Exception e) {
			num=0;
			e.printStackTrace();
		}finally{
			//5.�ͷ���Դ
			Hibernate_Util.closeSession();
		}
		return num;
	}
  /**
   * 7.��ҳ��ѯ
   */
	@Override
	public List<Student> findPage(Page page) {
		// 1.��ȡsession
		Session session=Hibernate_Util.getSession();
		//2.�����ѯ����¼����Query����
		 String hql="from Student";
		 //3.�����ѯ����¼����Query����
		 Query querypage=session.createQuery(hql);
		 //4.��ѯ����¼��������
		 querypage.setMaxResults(page.getPagesize());
		 //5.ȷ����ѯ���
		 querypage.setFirstResult(page.getStartrow());
		 //6.��ҳ��ѯ
		 List<Student> list=querypage.list();
		 //7.�ر�session
		 Hibernate_Util.closeSession();
		return list;
	}
  /**
   * ��ѯ������
   */
	@Override
	public int getTotalCount() {
		// 1.��ȡsessoion
		Session session=Hibernate_Util.getSession();
		//2.�����ѯ������hql���
		String hqlcount="select count(*) from Student";
		//3.����Session����Query����
		Query querycount=session.createQuery(hqlcount);
		//4.��ȡ�����������ص�������uniqueResult()��
		Integer totalCount=Integer.parseInt(querycount.uniqueResult().toString());
		//5.�ͷ���Դ
		Hibernate_Util.closeSession();
		return 0;
	}
	/**
	 * 1.��ѯ���У���������
	 */

}
