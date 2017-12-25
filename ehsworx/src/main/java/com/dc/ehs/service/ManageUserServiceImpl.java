package com.dc.ehs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.dc.ehs.dao.ManageUserDAO;
import com.dc.ehs.domain.User;

@Transactional
public class ManageUserServiceImpl implements ManageUserService
{

	@Autowired
	ManageUserDAO manageUserDAO;

	private static final Logger LOGGER = Logger.getLogger(ManageUserServiceImpl.class);

	public List<User> loadUsers(String username)
	{
		LOGGER.info("invoked loadUsers");
		return manageUserDAO.loadUsers("username");
	}

	public String saveUser(String username, String key, String value, String modfdBy)
	{
		LOGGER.info("invoked saveUser");
		return manageUserDAO.saveUser(username, key, value, modfdBy);
	}

	public String createUser(String username, String password, String firstname, String lastname, String role,
			String modfdBy)
	{
		LOGGER.info("invoked createUser");
		return manageUserDAO.createUser(username, password, firstname, lastname, role, modfdBy);
	}

	public String deleteUser(String userList, String modfdBy)
	{
		LOGGER.info("invoked deleteUser");
		return manageUserDAO.deleteUser(userList, modfdBy);
	}

	@Override
	public boolean userExist(String username)
	{
		return manageUserDAO.userExist(username);
	}

	@Override
	public void createPasswordResetTokenForUser(String username)
	{
		final String token = UUID.randomUUID().toString();
		manageUserDAO.createPasswordResetTokenForUser(username, token);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String validatePasswordResetToken(String username, String token)
	{
		String returnMessage = manageUserDAO.validatePasswordResetToken(username, token);

		/*
		 * if the token is valid, the user will be authorized to change their password
		 * by granting them a CHANGE_PASSWORD_PRIVILEGE, and direct them to a page to
		 * update their password.
		 */
		if (null != returnMessage && returnMessage.trim().equalsIgnoreCase("OK"))
		{
			Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
					.getContext().getAuthentication().getAuthorities();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE");
			List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
			updatedAuthorities.add(authority);
			updatedAuthorities.addAll(oldAuthorities);

			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,
					SecurityContextHolder.getContext().getAuthentication().getCredentials(), updatedAuthorities));
		}
		return returnMessage;
	}
}
