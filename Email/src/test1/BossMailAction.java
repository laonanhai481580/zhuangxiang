package test1;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BossMailAction {
    public static void sendMail(String fromEmail,String toEmail,String emailName,String emailPassword,String title, String centent) throws Exception {
  /*      Properties properties = new Properties();// ����Properties����
        properties.setProperty("mail.transport.protocol", "smtp");// ���ô���Э��
        properties.put("mail.smtp.host", "smtp.163.com");// ���÷��������smtp��ַ
        properties.setProperty("mail.smtp.auth", "true"); // ��֤
        properties.put("mail.debug", "true");//���ڵ���
        Authenticator auth = new MailAuthenticator(emailName,
                emailPassword); // ʹ����֤������һ��Authenticator
        Session session = Session.getDefaultInstance(properties, auth);// ����Properties��Authenticator����Session
        Message message = new MimeMessage(session);// Message�洢���͵ĵ����ʼ���Ϣ
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                toEmail));// ������������
        // ָ���������ݼ�ContentType�ͱ��뷽ʽ
        message.setContent(centent, "text/html;charset=utf-8");
        message.setSubject(title);// ��������
        message.setSentDate(new Date());// ���÷���ʱ��
        Transport.send(message);// ����*/
    }
}
