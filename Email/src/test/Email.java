package test;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public static void email(String email,int i)throws Exception {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");//�����ʼ�Э��
		properties.setProperty("mail.smtp.auth", "true");//��Ҫ��֤
		properties.setProperty("mail.debug", "true");//����debugģʽ ��̨����ʼ����͵Ĺ���
		Session session = Session.getInstance(properties);
		session.setDebug(true);//debugģʽ
		//�ʼ���Ϣ
		Message messgae = new MimeMessage(session);
		messgae.setFrom(new InternetAddress("18379168468@163.com"));//���÷�����
		messgae.setText("�����֤��Ϊ��"+i+"����ע�⣬��֤����Чʱ��Ϊ2���ӣ�����");//�����ʼ�����
		messgae.setSubject("������֤");//�����ʼ�����
		//�����ʼ�
		Transport tran = session.getTransport();
		tran.connect("smtp.163.com", 25, "18379168468@163.com", "481580o");//���ӵ��������������
		//tran.connect("smtp.qq.com",587, "Michael8@qq.vip.com", "xxxx");//���ӵ�QQ���������
		tran.sendMessage(messgae, new Address[]{ new InternetAddress(email)});//�����ʼ�������
		tran.close();
	}
	public static void main(String[] args) throws Exception {
		Email.email("18026926742@163.com",22);
		
	}

}
