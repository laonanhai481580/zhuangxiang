package test;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{
   public static void main(String [] args)
   {   
      // �ռ��˵�������
      String to = "18379168468@163.com";
 
      // �����˵�������
      String from = "18026926742@163.com";
 
      // ָ�������ʼ�������Ϊ localhost
      String host = "smtp.163.com";
 
      // ��ȡϵͳ����
      Properties properties = System.getProperties();
 
      // �����ʼ�������
      properties.setProperty("mail.smtp.host", host);
 
      // ��ȡĬ��session����
      Session session = Session.getDefaultInstance(properties);
 
      try{
         // ����Ĭ�ϵ� MimeMessage ����
         MimeMessage message = new MimeMessage(session);
 
         // Set From: ͷ��ͷ�ֶ�
         message.setFrom(new InternetAddress(from));
 
         // Set To: ͷ��ͷ�ֶ�
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
 
         // Set Subject: ͷ��ͷ�ֶ�
         message.setSubject("This is the Subject Line!");
 
         // ������Ϣ��
         message.setText("This is actual message");
 
         // ������Ϣ
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}