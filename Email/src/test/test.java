package test;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class test {


public static void main(String[] args) {
sendMail();
}



public static void sendMail(){

/*
* 1.�ı�����
* */
StringBuffer buffer = new StringBuffer();
buffer.append("������С��").append("\n");
buffer.append("���䣺18").append("\n");
buffer.append("���ã�������").append("\n");
buffer.append("    ---���Թ���").append("\n");
/*
 * 2.����Э��
 * */
Properties props = new Properties();
//Э��
props.setProperty("mail.transport.protocol", "smtp");
        //������
props.setProperty("mail.smtp.host", "smtp.163.com");
        //�˿�
props.setProperty("mail.smtp.port", "25");
        //ʹ��smtp�����֤
props.setProperty("mail.smtp.auth", "true");
        //ʹ��SSL����ҵ������裡
//������ȫЭ��
       MailSSLSocketFactory sf = null;
       try {
           sf = new MailSSLSocketFactory();
           sf.setTrustAllHosts(true);
       } catch (GeneralSecurityException e1) {
           e1.printStackTrace();
       }
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.ssl.socketFactory", sf);
       //���������˺�����
       Session mailSession = Session.getDefaultInstance(props, new MyAuthenricator("�����������˺�","��������������"));
       
            /*
             * 3.�����ʼ��Ự֮��Ҫ��д��Ϣ
             *Ҫ��д��Ϣ��Ҫ����javax.mail.Message�����ʵ�����Internet�ʼ�ʹ��javax.mail.interet.MimeMessage�ࡣ
             * */
       MimeMessage mimeMessage = new MimeMessage(mailSession);  
       try {
        //��Ϣ�����ߡ����ڡ�����
        mimeMessage.setFrom(new InternetAddress("18379168468@163.com"));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setSubject("java�������");
        
        //������Ϣ�Ľ������뷢���ߣ�Ѱַ���գ�
        mimeMessage.addRecipient(RecipientType.TO , new InternetAddress("18026926742@163.com"));
        
        //������Ϣ����
        mimeMessage.setText(buffer.toString());
        
       /*
        * 4.����Email,�������ı���ϢΪ��
        * */ 
       // ���淢����Ϣ
       mimeMessage.saveChanges();
       //���巢��Э��
       Transport transport = mailSession.getTransport("smtp");
       //��¼����
       transport.connect("18379168468@163.com", "481580o");
       //�����ʼ�
      transport.send(mimeMessage); 
      System.out.println("��Ϣ���ͳɹ���");
      } catch (Exception e) {
        e.printStackTrace();


}
      
}
static class MyAuthenricator extends Authenticator{
    String u = null;
    String p = null;
    public MyAuthenricator(String u,String p){
        this.u=u;
        this.p=p;
    }
  //  @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(u,p);
    }
}

}