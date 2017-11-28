package com.dc.ehs.helper;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dc.ehs.domain.User;
import com.dc.ehs.service.ManageUserService;

public class EhsHelper
{
	@Autowired
	ManageUserService manageUserService;
	
	private static final Logger LOGGER = Logger.getLogger(EhsHelper.class);

	public List<User> loadAllUsers(String userName)
	{
		LOGGER.info("invoked loadAllUsers");
		return manageUserService.loadUsers(userName);
	}
}
