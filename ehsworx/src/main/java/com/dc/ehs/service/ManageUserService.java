package com.dc.ehs.service;

import java.util.List;

import com.dc.ehs.domain.User;
import com.dc.ehs.entity.MetaData;
import com.dc.ehs.exception.ServiceExceptionLayer;


public interface ManageUserService 
{
	public List< User > loadUsers ( String username ) throws ServiceExceptionLayer;
	
	public String saveUser ( String username , String key, String value, String modfdBy  ) throws ServiceExceptionLayer;
	
	public String createUser ( String username, String password, String firstname, String lastname, String role,
	        String modfdBy ) throws ServiceExceptionLayer;
	
	
	public String deleteUser ( String userList, String modfdBy );
	
	

}
