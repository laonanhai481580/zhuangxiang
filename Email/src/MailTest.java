public class MailTest {

	public static void main(String[] args) throws Exception {
		    mail();
	}
	public static void mail() throws Exception{
		MailSender mailSender = MailSender.getInstance();
		MailInfo mailInfo = mailSender.getMailInfo();
		//mailSender.sendHtmlMail(mailInfo, 3);
		mailSender.sendTextMail(mailInfo, 3);
	}
}