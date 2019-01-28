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
* 1.文本内容
* */
StringBuffer buffer = new StringBuffer();
buffer.append("姓名：小明").append("\n");
buffer.append("年龄：18").append("\n");
buffer.append("爱好：打篮球").append("\n");
buffer.append("    ---来自官网").append("\n");
/*
 * 2.配置协议
 * */
Properties props = new Properties();
//协议
props.setProperty("mail.transport.protocol", "smtp");
        //服务器
props.setProperty("mail.smtp.host", "smtp.163.com");
        //端口
props.setProperty("mail.smtp.port", "25");
        //使用smtp身份验证
props.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
//开启安全协议
       MailSSLSocketFactory sf = null;
       try {
           sf = new MailSSLSocketFactory();
           sf.setTrustAllHosts(true);
       } catch (GeneralSecurityException e1) {
           e1.printStackTrace();
       }
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.ssl.socketFactory", sf);
       //发送邮箱账号密码
       Session mailSession = Session.getDefaultInstance(props, new MyAuthenricator("发送者邮箱账号","发送者邮箱密码"));
       
            /*
             * 3.配置邮件会话之后，要编写消息
             *要编写消息就要生成javax.mail.Message子类的实例或对Internet邮件使用javax.mail.interet.MimeMessage类。
             * */
       MimeMessage mimeMessage = new MimeMessage(mailSession);  
       try {
        //消息发送者、日期、主题
        mimeMessage.setFrom(new InternetAddress("18379168468@163.com"));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setSubject("java邮箱测试");
        
        //设置消息的接受者与发送者（寻址接收）
        mimeMessage.addRecipient(RecipientType.TO , new InternetAddress("18026926742@163.com"));
        
        //设置消息内容
        mimeMessage.setText(buffer.toString());
        
       /*
        * 4.发送Email,这里以文本消息为例
        * */ 
       // 保存发送信息
       mimeMessage.saveChanges();
       //定义发送协议
       Transport transport = mailSession.getTransport("smtp");
       //登录邮箱
       transport.connect("18379168468@163.com", "481580o");
       //发送邮件
      transport.send(mimeMessage); 
      System.out.println("消息发送成功！");
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