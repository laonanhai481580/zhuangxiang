package zhongfucheng.domain;

import java.util.List;

public interface StudentDao {
	/**
	 * 1.�����û����������ѯ
	 */
	Student findByNameAndPwd(String sname,String password);
	/**
	 * 2.��ʱ���أ���ѯ����
	 */
	List<Student> findAll();
	/**
	 * 3.����
	 */
	int save(Student stu);
	/**
	 * 4.��������id��ѯ����
	 */
	Student findById(Integer sid);
	/**
	 * 5.�޸�
	 */
	int update(Student stu);
	/**
	 * 6.ɾ������
	 */
	int delete(Student stu);
	/**
	 * 7.��ҳ��ѯ
	 */
	List<Student> findPage(Page page);
	/**
	 * 8.��ѯ������
	 */
	int getTotalCount();

}
