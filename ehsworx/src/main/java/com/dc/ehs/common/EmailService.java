package com.dc.ehs.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.dc.ehs.entity.Observation;

/**
 * EmailService allows sending emails via JavaMailSender.
 * 
 * @author Deepak Chaudhary
 *
 */
public class EmailService
{

	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	/**
	 * Set Mail Sender.
	 * 
	 * @param mailSender
	 */
	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	/**
	 * Set Mail Sender.
	 * 
	 * @param mailSender
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine)
	{
		this.velocityEngine = velocityEngine;
	}

	/**
	 * Actual method to send emails.
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws MessagingException
	 */
	public void sendMail(String from, String to, String subject, Observation observation, String fileName, int obs_id, String initiatorName)
			throws MessagingException
	{

		/*
		 * MimeMessage message = mailSender.createMimeMessage();
		 * 
		 * message.setSubject(subject); MimeMessageHelper helper; helper = new
		 * MimeMessageHelper(message, true); helper.setFrom(from); helper.setTo(to);
		 * helper.setText(msg, true); mailSender.send(message);
		 */
		MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			@SuppressWarnings(
			{ "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setBcc("chaudharydeepak08@gmail.com");
				message.setFrom(from);
				message.setSubject(subject);
				message.setSentDate(new Date());
				Map model = new HashMap();
				
				model.put("observation", observation);
				model.put("fileName", fileName);
				model.put("obs_id", obs_id);
				model.put("initiatorName", initiatorName);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"observationNotificationMessage.vm", "UTF-8", model);
				message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}
}
