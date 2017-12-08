package com.dc.ehs.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.common.EmailService;
import com.dc.ehs.entity.Observation;

/**
 * This class contains utility methods for use by application.
 * 
 * @author Deepak Chaudhary
 *
 */
public class EhsUtilityMethods
{
	private static final Logger LOGGER = Logger.getLogger(EhsUtilityMethods.class);

	@Autowired
	EmailService emailService;

	/**
	 * This utility method returns current logged-in user username.
	 * 
	 * @return
	 */
	public String getLoggedInUser()
	{
		String currentAuthUser = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		{
			currentAuthUser = authentication.getName();
		}
		return currentAuthUser;
	}

	/**
	 * This utility method checks if logged-in user has a particular role.
	 * 
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasRole(String role)
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities)
		{
			hasRole = authority.getAuthority().equals(role);
			if (hasRole)
			{
				break;
			}
		}
		return hasRole;
	}

	/**
	 * This utility method generates email notification at certain application
	 * events.
	 * 
	 * @param obsID
	 * @param recp
	 * @return
	 * @throws MessagingException
	 */
	/*public String sendEhsNotification(String obsID, String recp, Observation observation) throws MessagingException
	{
		LOGGER.info("sending notification for " + recp);
		String respManager = (recp.split("\\(")[1]).replace(")", "");
		LOGGER.info("updated notification for " + respManager);
		emailService.sendMail("admin@ehs.com", respManager, "Observation assigned for your action", "Dear " + recp
				+ ", <br><br> New Observation is assigned for your action. Please access below:<br>http://ec2-34-201-58-201.compute-1.amazonaws.com:8080/ehsworx/welcome/action?name="
				+ obsID + "<br><br>Best Regards,<br>EHS Observations", observation,
				observation.getFile().getOriginalFilename(), );
		return "success";
	}*/

	/**
	 * This utility method uploads file to predefined locations.
	 * 
	 * @param multipartFile
	 * @throws IOException
	 */
	public void uploadFile(MultipartFile multipartFile) throws IOException
	{
		MultipartFile _multipartFile = multipartFile;
		LOGGER.info("--" + _multipartFile);
		LOGGER.info("---" + _multipartFile.getOriginalFilename());
		if (null != _multipartFile && null != _multipartFile.getOriginalFilename()
				&& !_multipartFile.getOriginalFilename().trim().isEmpty())
			FileCopyUtils.copy(_multipartFile.getBytes(),
					new File("/home/ec2-user/tools/docs/" + multipartFile.getOriginalFilename()));
	}
}
