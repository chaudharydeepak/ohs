package com.dc.ehs.dao;

import java.util.List;

import javax.sql.DataSource;

import com.dc.ehs.domain.User;

public interface ManageUserDAO
{
	/**
	 * Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
	public List< User > loadUsers ( String username );
	
	public String saveUser ( String username, String key, String value, String modfdBy );
	
	public String createUser ( String username, String password, String firstname, String lastname, String role,
	        String modfdBy );
	
	public String deleteUser ( String userList, String modfdBy );
	
}
