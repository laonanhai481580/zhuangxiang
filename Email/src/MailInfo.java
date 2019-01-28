import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailInfo {
	/** �����ʼ��ķ�������IP */
	private String mailHost;
	/** �����ʼ��ķ������Ķ˿� */
	private String mailPort = "465";
	/** �����ʼ����û���������ȫ���ƣ� */
	private String username;
	/** �����ʼ������� */
	private String password;
	/** ������Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String errorTo;
	/** ������Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String errorCc;
	/** ������Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String warningTo;
	/** ������Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String warningCc;
	/** ֪ͨ��Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String notifyTo;
	/** ֪ͨ��Ϣ���͵�ַ������ʼ���ַ��";"�ָ��� */
	private String notifyCc;
	/** �ʼ����� */
	private String subject;
	/** �ʼ����� */
	private String content;
	/** �ʼ��������ļ��� */
	private String[] attachFileNames;
	/**
	 * ��ȡ�ʼ�����
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	public Properties getProperties() throws GeneralSecurityException {
		Properties props = new Properties();
		props.put("mail.smtp.host", getMailHost());
		props.put("mail.smtp.port", getMailPort());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
	    //props.put("mail.smtp.localhost", "mail.digu.com");
		//��ȫ��֤
		MailSSLSocketFactory sslSF = new MailSSLSocketFactory();
		sslSF.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sslSF);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.user", getUsername());
		props.put("mail.password", getPassword());
		return props;
	}

	/**
	 * @return the mailHost
	 */
	public String getMailHost() {
		return mailHost;
	}
	/**
	 * @param mailHost
	 * 
	 */
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	/**
	 * @return the mailPort
	 */
	public String getMailPort() {
		return mailPort;
	}

	/**
	 * @param mailPort
	 * 
	 */
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 * 
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * @param password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the errorTo
	 */
	public String getErrorTo() {
		return errorTo;
	}

	/**
	 * @param errorTo
	 * 
	 */
	public void setErrorTo(String errorTo) {
		this.errorTo = errorTo;
	}

	/**
	 * @return the errorCc
	 */
	public String getErrorCc() {
		return errorCc;
	}

	/**
	 * @param errorCc
	 * 
	 */
	public void setErrorCc(String errorCc) {
		this.errorCc = errorCc;
	}

	/**
	 * @return the warningTo
	 */
	public String getWarningTo() {
		return warningTo;
	}

	/**
	 * @param warningTo
	 * 
	 */
	public void setWarningTo(String warningTo){
		this.warningTo = warningTo;
	}

	/**
	 * @return the warningCc
	 */
	public String getWarningCc() {
		return warningCc;
	}

	/**
	 * @param warningCc
	 * 
	 */
	public void setWarningCc(String warningCc) {
		this.warningCc = warningCc;
	}

	/**
	 * @return the notifyTo
	 */
	public String getNotifyTo() {
		return notifyTo;
	}

	/**
	 * @param notifyTo
	 * 
	 */
	public void setNotifyTo(String notifyTo) {
		this.notifyTo = notifyTo;
	}

	/**
	 * @return the notifyCc
	 */
	public String getNotifyCc() {
		return notifyCc;
	}

	/**
	 * @param notifyCc
	 * 
	 */
	public void setNotifyCc(String notifyCc) {
		this.notifyCc = notifyCc;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 * 
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return Returns the attachFileNames.
	 */
	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	/**
	 * @param attachFileNames
	 * 
	 */
	public void setAttachFileNames(String[] attachFileNames){
		this.attachFileNames = attachFileNames;
	}
}