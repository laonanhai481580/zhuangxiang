import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
	/** �û��˺� */
	private String userName = null;
	/** �û����� */
	private String password = null;

	/**
	 * @param userName
	 * @param password
	 */
	public MailAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * �����֤
	 * 
	 * @return
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}