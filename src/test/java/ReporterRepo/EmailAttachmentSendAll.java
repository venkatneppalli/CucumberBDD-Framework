package ReporterRepo;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentSendAll {

	// https://myaccount.google.com/lesssecureapps --- Turn ON

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message, String[] attachFiles)
			throws AddressException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		// InternetAddress[] toAddresses = { new
		// InternetAddress(toAddress).parse("venkatselenium81@gmail.com") };
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("venkatselenium81@gmail.com,leelaram.neppalli@gmail.com"));
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	public static void senEmail() {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "***********	";//provide your email
		String password = "******"; //provide your password

		// message info
		String mailTo = "venkatselenium81@gmail.com";
		String subject = "Automation Testing Report";
		String message = "Please find the attached test report";

		// attachments
		String[] attachFiles = new String[1];
		attachFiles[0] = ReportManager.finalResPath;
		
		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo, subject, message, attachFiles);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}

	
	
	
	/**
	 * Test sending e-mail with attachments
	 */
	public static void main(String[] args) {
		senEmail();
	}
}