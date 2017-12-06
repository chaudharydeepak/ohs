package com.dc.ehs.dao;

import java.util.List;

import javax.sql.DataSource;

import com.dc.ehs.domain.User;

/**
 * Interface to handle user management in the system.
 * 
 * @author chaudhde
 *
 */
public interface ManageUserDAO
{
	/**
	 * interface method to Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
	/**
	 * interface method to load all users in the system. [ currently active only
	 * ]
	 * 
	 * @param username
	 * @return
	 */
	public List< User > loadUsers ( String username );
	
	/**
	 * interface method to save a user into the system.
	 * 
	 * @param username
	 * @param key
	 * @param value
	 * @param modfdBy
	 * @return
	 */
	public String saveUser ( String username, String key, String value, String modfdBy );
	
	/**
	 * interface method to create a new user into the system.
	 * 
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param role
	 * @param modfdBy
	 * @return
	 */
	public String createUser ( String username, String password, String firstname, String lastname, String role,
	        String modfdBy );
	
	/**
	 * interface method to delete user from system. soft-deletion.
	 * 
	 * @param userList
	 * @param modfdBy
	 * @return
	 */
	public String deleteUser ( String userList, String modfdBy );
	
}
