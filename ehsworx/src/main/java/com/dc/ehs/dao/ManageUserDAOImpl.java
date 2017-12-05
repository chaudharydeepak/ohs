package com.dc.ehs.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dc.ehs.domain.User;
import com.dc.ehs.mapper.ManageUserMapper;

public class ManageUserDAOImpl implements ManageUserDAO
{
	private static final Logger			LOGGER	= Logger.getLogger( ManageUserDAOImpl.class );
	
	private NamedParameterJdbcTemplate	namedParameterJdbcTemplate;
	
	private JdbcTemplate				jdbcTemplate;
	
	@Autowired
	public void setDataSource ( DataSource dataSource )
	{
		this.jdbcTemplate = new JdbcTemplate( dataSource );
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
	}
	
	public List< User > loadUsers ( String username )
	{
		LOGGER.info( "invoked  loadUsers " );
		String fetchUserSQL = "Select * from app.EHS_SECURITY_USERPROFILE u, app.EHS_SECURITY_USERAUTHORITY a where u.username = a.username and active=true";
		List< User > usersList = namedParameterJdbcTemplate.query( fetchUserSQL, new ManageUserMapper( ) );
		return usersList;
	}
	
	@Override
	public String saveUser ( String username, String key, String value, String modfdBy )
	{
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "mdfdby", modfdBy );
		namedParameters.put( "username", username );
		namedParameters.put( "value", value );
		String _SQL4Profile = "";
		String _SQL4Auth = "";
		if ( key.equalsIgnoreCase( "fname" ) )
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, FIRST_NAME=:value where USERNAME=:username";
		}
		else if ( key.equalsIgnoreCase( "lname" ) )
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, LAST_NAME=:value where USERNAME=:username";
		}
		if ( key.equalsIgnoreCase( "role" ) )
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby where USERNAME=:username";
			_SQL4Auth = "update app.EHS_SECURITY_USERAUTHORITY set  ROLE=:value where USERNAME=:username";
		}
		if ( key.equalsIgnoreCase( "pswd" ) )
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set PASSWORD=:value, MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby where USERNAME=:username";
		}
		
		if ( null != _SQL4Profile && _SQL4Profile.trim( ).length( ) > 0 )
			namedParameterJdbcTemplate.update( _SQL4Profile, namedParameters );
		
		if ( null != _SQL4Auth && _SQL4Auth.trim( ).length( ) > 0 )
			namedParameterJdbcTemplate.update( _SQL4Auth, namedParameters );
		
		return "success";
	}
	
	public String createUser ( String username, String password, String firstname, String lastname, String role,
	        String modfdBy )
	{
		String _SQL4Profile = "insert into app.EHS_SECURITY_USERPROFILE(USERNAME, FIRST_NAME, LAST_NAME, PASSWORD, CREATED_BY , CREATED_DT, ACTIVE) values(:username, :firstname, :lastname, :password, :modfdBy, CURRENT_DATE, true)";
		String _SQL4Auth = " insert into app.EHS_SECURITY_USERAUTHORITY values (:username, :role)";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "username", username );
		namedParameters.put( "firstname", firstname );
		namedParameters.put( "lastname", lastname );
		namedParameters.put( "password", password );
		namedParameters.put( "modfdBy", modfdBy );
		namedParameters.put( "role", role );
		
		namedParameterJdbcTemplate.update( _SQL4Profile, namedParameters );
		namedParameterJdbcTemplate.update( _SQL4Auth, namedParameters );
		return "success";
	}
	
	public String deleteUser ( String userList, String modfdBy )
	{
		List< String > userslist = new ArrayList< String >( Arrays.asList( userList.split( "," ) ) );
		String _SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, active=false where USERNAME IN (:usernameList)";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "usernameList", userslist );
		namedParameters.put( "mdfdby", modfdBy );
		namedParameterJdbcTemplate.update( _SQL4Profile, namedParameters );
		return "success";
	}
	
}
