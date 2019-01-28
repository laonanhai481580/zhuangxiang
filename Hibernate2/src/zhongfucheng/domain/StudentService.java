package zhongfucheng.domain;

import java.util.List;


/**
 * ҵ��ӿ�
 * @author N000226
 *
 */
public interface StudentService {
	/**
	 * 1.��¼��ҵ��
	 * 
	 */
	Student login(String sname,String password);
	/**
	 * 2.ע��
	 */
	int regiseter(Student stu);
	/**
	 * 3.��ѯ����
	 */
	List<Student> findAll();
	/**
	 * 4.��������id��ѯ
	 */
	Student findById(Integer sid);
	/**
	 * 5.�޸�
	 */
	int update(Student stu);
	/**
	 * 6.ɾ��
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
