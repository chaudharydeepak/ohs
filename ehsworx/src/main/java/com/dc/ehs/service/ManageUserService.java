package com.dc.ehs.service;

import java.util.List;

import com.dc.ehs.domain.User;
import com.dc.ehs.exception.ServiceExceptionLayer;


public interface ManageUserService 
{
	public List< User > loadUsers ( String username ) throws ServiceExceptionLayer;
}
