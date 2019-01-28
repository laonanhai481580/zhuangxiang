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
  /*      Properties properties = new Properties();// 创建Properties对象
        properties.setProperty("mail.transport.protocol", "smtp");// 设置传输协议
        properties.put("mail.smtp.host", "smtp.163.com");// 设置发信邮箱的smtp地址
        properties.setProperty("mail.smtp.auth", "true"); // 验证
        properties.put("mail.debug", "true");//便于调试
        Authenticator auth = new MailAuthenticator(emailName,
                emailPassword); // 使用验证，创建一个Authenticator
        Session session = Session.getDefaultInstance(properties, auth);// 根据Properties，Authenticator创建Session
        Message message = new MimeMessage(session);// Message存储发送的电子邮件信息
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                toEmail));// 设置收信邮箱
        // 指定邮箱内容及ContentType和编码方式
        message.setContent(centent, "text/html;charset=utf-8");
        message.setSubject(title);// 设置主题
        message.setSentDate(new Date());// 设置发信时间
        Transport.send(message);// 发送*/
    }
}
