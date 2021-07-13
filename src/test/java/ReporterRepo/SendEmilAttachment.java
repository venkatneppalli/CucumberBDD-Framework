package ReporterRepo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmilAttachment {
	public static ReportManager reportmanager;

	public static void main(String[] args) {
		sendReportAsEmail();
	}

	public static void sendReportAsEmail() {
		System.out.println("SimpleEmail Start");
		String smtpHostServer = "smtp.gmail.com";
		String port="587";
		     
		String emailID = "leelaram.neppalli@gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
	     
		
		Session session = Session.getInstance(props, null);

		// attachments
		String[] attachFiles = new String[1];
		attachFiles[0] = "D:\\Workspace_complete\\CucumberBDD-Framework\\src\\test\\resources\\Test_Results\\test.txt";//reportmanager.finalResPath;
		sendAttachmentEmail(session, emailID, "Hotel Application Automation Report",
				"Hi Team,"+"\n"+"Please find the attached test automation report"+"\n"+"Thanks & Regards,"+"\n"+"Venkat.", attachFiles);
	}

	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body,
			String[] attachFiles) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("no-reply@gmail.com", "DO NOT REPLY"));
			msg.setReplyTo(InternetAddress.parse("leelaram.neppalli@gmail.com,venkatselenium81@gmail.com", false));
			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText(body);
			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();
			// Set text message part
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
			System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
