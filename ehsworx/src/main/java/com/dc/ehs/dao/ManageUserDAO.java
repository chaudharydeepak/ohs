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
}
