package com.dc.ehs.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dc.ehs.dao.ManageUserDAO;
import com.dc.ehs.domain.User;
import com.dc.ehs.entity.MetaData;
import com.dc.ehs.exception.ServiceExceptionLayer;

@Transactional
public class ManageUserServiceImpl implements ManageUserService
{
	
	@Autowired
	ManageUserDAO				manageUserDAO;
	
	private static final Logger	LOGGER	= Logger.getLogger( ManageUserServiceImpl.class );
	
	public List< User > loadUsers ( String username )
	{
		LOGGER.info( "invoked loadUsers" );
		return manageUserDAO.loadUsers( "username" );
	}
	
	public String saveUser ( String username, String key, String value, String modfdBy )
	{
		LOGGER.info( "invoked saveUser" );
		return manageUserDAO.saveUser( username, key, value, modfdBy );
	}
	
	public String createUser ( String username, String password, String firstname, String lastname, String role,
	        String modfdBy )
	{
		LOGGER.info( "invoked createUser" );
		return manageUserDAO.createUser( username, password, firstname, lastname, role, modfdBy );
	}
	
	
	public String deleteUser ( String userList, String modfdBy )
	{
		LOGGER.info( "invoked deleteUser" );
		return manageUserDAO.deleteUser( userList, modfdBy );
	}
}
