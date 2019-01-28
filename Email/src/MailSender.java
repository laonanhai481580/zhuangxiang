import java.util.Calendar;
//import java.util.List;
import java.util.Properties;

import java.util.Random;

//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import javax.mail.internet.MimeUtility;

public class MailSender {

	private static MailSender sender = null;

	public static MailSender getInstance() {
		if (sender == null) {
			sender = new MailSender();
		}
		return sender;
	}

	/**
	 * ���ı���ʽ�����ʼ�
	 * 
	 * @param mailInfo
	 *            �ʼ���Ϣ
	 * @param mailType
	 *            �ʼ����� 1-error;2-warning;3-notify;
	 * @return
	 * @throws Exception
	 */
	public boolean sendTextMail(MailInfo mailInfo, int mailType)
			throws Exception {

		// ��Ҫ�����֤������һ��������֤��
		MailAuthenticator authenticator = new MailAuthenticator(
				mailInfo.getUsername(), mailInfo.getPassword());

		Properties prop = mailInfo.getProperties();
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(prop,
				authenticator);

		try {
			// ����session����һ���ʼ���Ϣ
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo.getUsername());
			// �����ʼ���Ϣ�ķ�����
			mailMessage.setFrom(from);

			// �����ʼ��Ľ����ߵ�ַ to:����;CC:����
			Address[][] maillToArr = getMailToAddress(mailInfo, mailType);

			// �����ʼ���Ϣ�Ľ�����,����,����
			if (maillToArr != null && maillToArr[0] != null
					&& maillToArr[0].length > 0) {
				mailMessage.setRecipients(Message.RecipientType.TO,
						maillToArr[0]);
			}
			if (maillToArr != null && maillToArr[1] != null
					&& maillToArr[1].length > 0) {
				mailMessage.setRecipients(Message.RecipientType.CC,
						maillToArr[1]);
			}
			// �����ʼ���Ϣ������
			mailMessage.setSubject(mailInfo.getSubject());
			// �����ʼ���Ϣ���͵�ʱ��
			mailMessage.setSentDate(Calendar.getInstance().getTime());
			// �����ʼ���Ϣ����Ҫ����
			mailMessage.setText(mailInfo.getContent());
			// �����ʼ�
			Transport.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * ��HTML��ʽ�����ʼ�
	 * 
	 * @param mailInfo
	 *            �ʼ���Ϣ
	 * @param mailType
	 *            �ʼ����� 1-error;2-warning��Ĭ�ϣ�;3-notify;
	 * @return
	 * @throws Exception
	 */
	public boolean sendHtmlMail(MailInfo mailInfo, int mailType)
			throws Exception {

		// ��Ҫ�����֤������һ��������֤��
		MailAuthenticator authenticator = new MailAuthenticator(
				mailInfo.getUsername(), mailInfo.getPassword());

		Properties prop = mailInfo.getProperties();
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(prop,
				authenticator);

		try {
			// ����session����һ���ʼ���Ϣ
			Message mailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(mailInfo.getUsername());
			// �����ʼ���Ϣ�ķ�����
			mailMessage.setFrom(from);

			// �����ʼ��Ľ����ߵ�ַ to:����;CC:����
			Address[][] maillToArr = getMailToAddress(mailInfo, mailType);

			// �����ʼ���Ϣ�Ľ�����,����,����
			if (maillToArr != null && maillToArr[0] != null
					&& maillToArr[0].length > 0) {
				mailMessage.setRecipients(Message.RecipientType.TO,
						maillToArr[0]);
			}

			if (maillToArr != null && maillToArr[1] != null
					&& maillToArr[1].length > 0){
				mailMessage.setRecipients(Message.RecipientType.CC,
						maillToArr[1]);
			}
			// �����ʼ���Ϣ������
			mailMessage.setSubject(mailInfo.getSubject());
			// �����ʼ���Ϣ���͵�ʱ��
			mailMessage.setSentDate(Calendar.getInstance().getTime());
			// MimeMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
			Multipart multiPart = new MimeMultipart();
			// ����һ������HTML���ݵ�MimeBodyPart
			BodyPart bodyPart = new MimeBodyPart();
			// ����HTML�ʼ���Ϣ����
			bodyPart.setContent(mailInfo.getContent(),
					"text/html; charset=utf-8");
			multiPart.addBodyPart(bodyPart);

			// ��Ӹ���
			/*
			 * if(mailInfo.getAttachFileNames().length != 0){ for(String
			 * attachFile : mailInfo.getAttachFileNames()){ bodyPart=new
			 * MimeBodyPart(); FileDataSource fds=new
			 * FileDataSource(attachFile); //�õ�����Դ bodyPart.setDataHandler(new
			 * DataHandler(fds)); //�õ�������������BodyPart
			 * bodyPart.setFileName(MimeUtility.encodeText(fds.getName()));
			 * //�õ��ļ��������루��ֹ�����ļ������룩ͬ������BodyPart
			 * multiPart.addBodyPart(bodyPart); } }
			 */

			// �����ʼ���Ϣ����Ҫ����
			mailMessage.setContent(multiPart);

			// �����ʼ�
			Transport.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * ���������ʼ��б��ַ����
	 * 
	 * @param mailInfo
	 *            �ʼ���Ϣ
	 * @param mailType
	 *            �ʼ����� 1-error;2-warning��Ĭ�ϣ�;3-notify;
	 * @return Address[0]:���͵�ַ����;Address[1]:���͵�ַ����
	 */
	private Address[][] getMailToAddress(MailInfo mailInfo, int mailType)
			throws AddressException {
		Address[] toAdds = null;
		Address[] ccAdds = null;

		String[] toMails = mailInfo.getNotifyTo().split(";");
		toAdds = new InternetAddress[toMails.length];
		for (int index = 0; index < toMails.length; index++) {
			toAdds[index] = new InternetAddress(toMails[index]);
		}
		String[] ccMails = mailInfo.getNotifyCc().split(";");
		ccAdds = new InternetAddress[ccMails.length];
		for (int index = 0; index < ccMails.length; index++) {
			ccAdds[index] = new InternetAddress(ccMails[index]);
		}

		Address[][] result = { toAdds, ccAdds };
		return result;
	}

	/**
	 * ����MailInfo����
	 * 
	 * @return
	 */
	public MailInfo getMailInfo() {

		MailInfo info = new MailInfo();
		// �ʼ��ṩ�̵�STMP������
		info.setMailHost("smtp.163.com");
		// SSLͨ�Žӿ�
		info.setMailPort("465");
		// �ʼ�����������
		info.setUsername("of103914@163.com"); // lyp99966@163.com
		// �ʼ��������ʼ���Ȩ��
		info.setPassword("481580o");
		// �ʼ�������
		info.setNotifyCc("of103914@163.com"); // of103914@163.com
												// ;18026926742@163.com;fangyuan.chen@o-film
		// �ʼ�������
		info.setNotifyTo("fangyuan.chen@o-film.com;18026926742@163.com;18379168468@163.com");// jing.li2@o-film.com

		info.setSubject("qis�쳣֪ͨ");
		Random r = new Random();
		int t1 = r.nextInt();
		info.setContent("QIS system and database connection interruption, resulting in QIS system pages can not be opened, please deal with it in a timely manner."
				+ t1); // qisϵͳ�����ݿ������ж�,����qisϵͳ��ҳ�򲻿�,�뼰ʱ����
		// info.setContent("dgfffde");
		// info.setContent(""+t1);
		// info.setAttachFileNames(new String[]{"����һ", "������"});//��Ӹ���
		return info;
	}
}