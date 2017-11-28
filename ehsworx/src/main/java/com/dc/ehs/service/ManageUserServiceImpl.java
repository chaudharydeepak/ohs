package com.dc.ehs.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dc.ehs.dao.ManageUserDAO;
import com.dc.ehs.domain.User;
import com.dc.ehs.exception.ServiceExceptionLayer;

public class ManageUserServiceImpl implements ManageUserService
{

	@Autowired
	ManageUserDAO manageUserDAO;
	
	private static final Logger LOGGER = Logger.getLogger(ManageUserServiceImpl.class);
	
	public List<User> loadUsers(String username) throws ServiceExceptionLayer
	{
		LOGGER.info("invoked loadUsers");
		return manageUserDAO.loadUsers("username");
	}

}
