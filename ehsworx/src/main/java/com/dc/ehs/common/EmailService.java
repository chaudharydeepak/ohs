package com.dc.ehs.common;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * EmailService allows sending emails via JavaMailSender.
 * @author Deepak Chaudhary
 *
 */
public class EmailService
{

	private JavaMailSender mailSender;

	/**
	 * Set Mail Sender.
	 * @param mailSender
	 */
	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	/**
	 * Actual method to send emails.
	 * @param from
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws MessagingException
	 */
	public void sendMail(String from, String to, String subject, String msg) throws MessagingException
	{

		MimeMessage message = mailSender.createMimeMessage();

		message.setSubject(subject);
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setText(msg, true);
		mailSender.send(message);
	}
}
