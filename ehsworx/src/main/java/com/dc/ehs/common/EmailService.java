package com.dc.ehs.common;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService
{

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

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
