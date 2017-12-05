package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dc.ehs.domain.User;



public class ManageUserMapper implements RowMapper< User >
{
	public User mapRow ( ResultSet arg0, int arg1 ) throws SQLException
	{
		User _user = new User( );
		_user.setUserID(arg0.getInt( "ID" ));
		_user.setUserName(arg0.getString( "USERNAME" ) );
		_user.setUserfName(arg0.getString( "FIRST_NAME" ) );
		_user.setUserlName(arg0.getString( "LAST_NAME" ) );
		_user.setUserRole(arg0.getString( "ROLE" ));
		_user.setUserPasswd(arg0.getString( "PASSWORD" ));
		return _user;
	}
}

/*
1. we must hit 100% thisweek and start reconfirmations.
2. 4D power is key. daily emails.
3. Template is coming, transportation incharge.
--> 

make up my mind -
we cannot win by core. we need 4d power -
build 4d momentum -
*/

