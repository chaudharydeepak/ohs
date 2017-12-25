package com.dc.ehs.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dc.ehs.common.EmailService;
import com.dc.ehs.domain.User;
import com.dc.ehs.mapper.ManageUserMapper;

/**
 * Implementation of Interface ManageUserDAO interface.
 * 
 * @author Deepak Chaudhary
 *
 */
public class ManageUserDAOImpl implements ManageUserDAO
{
	private static final Logger LOGGER = Logger.getLogger(ManageUserDAOImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	EmailService emailService;

	@Value("${email.username}")
	String frm_email;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ManageUserDAO#loadUsers(java.lang.String)
	 */
	@Override
	public List<User> loadUsers(String username)
	{
		LOGGER.info("invoked  loadUsers ");
		String fetchUserSQL = "Select * from app.EHS_SECURITY_USERPROFILE u, app.EHS_SECURITY_USERAUTHORITY a where u.username = a.username and active=true";
		List<User> usersList = namedParameterJdbcTemplate.query(fetchUserSQL, new ManageUserMapper());
		return usersList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ManageUserDAO#saveUser(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveUser(String username, String key, String value, String modfdBy)
	{
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("mdfdby", modfdBy);
		namedParameters.put("username", username);
		namedParameters.put("value", value);
		String _SQL4Profile = "";
		String _SQL4Auth = "";
		if (key.equalsIgnoreCase("fname"))
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, FIRST_NAME=:value where USERNAME=:username";
		} else if (key.equalsIgnoreCase("lname"))
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, LAST_NAME=:value where USERNAME=:username";
		}
		if (key.equalsIgnoreCase("role"))
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby where USERNAME=:username";
			_SQL4Auth = "update app.EHS_SECURITY_USERAUTHORITY set  ROLE=:value where USERNAME=:username";
		}
		if (key.equalsIgnoreCase("pswd"))
		{
			_SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set PASSWORD=:value, MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby where USERNAME=:username";
		}

		if (null != _SQL4Profile && _SQL4Profile.trim().length() > 0)
			namedParameterJdbcTemplate.update(_SQL4Profile, namedParameters);

		if (null != _SQL4Auth && _SQL4Auth.trim().length() > 0)
			namedParameterJdbcTemplate.update(_SQL4Auth, namedParameters);

		if ( key.equalsIgnoreCase("pswd") )
		{
			String fetchFullName = "select FIRST_NAME || ' ' || LAST_NAME from app.EHS_SECURITY_USERPROFILE where username=:username";
			namedParameters.put("username", username);
			String fullName = namedParameterJdbcTemplate.queryForObject(fetchFullName, namedParameters, String.class);
			emailService.sendMail("chaudharydeepak08@gmail.com", username, "passwordResetConfirmationMessage.vm",
					"OHES Password Reset Confirmation", fullName);
		}
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ManageUserDAO#createUser(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String createUser(String username, String password, String firstname, String lastname, String role,
			String modfdBy)
	{
		String _SQL4Profile = "insert into app.EHS_SECURITY_USERPROFILE(USERNAME, FIRST_NAME, LAST_NAME, PASSWORD, CREATED_BY , CREATED_DT, ACTIVE) values(:username, :firstname, :lastname, :password, :modfdBy, CURRENT_DATE, true)";
		String _SQL4Auth = " insert into app.EHS_SECURITY_USERAUTHORITY values (:username, :role)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("username", username);
		namedParameters.put("firstname", firstname);
		namedParameters.put("lastname", lastname);
		namedParameters.put("password", password);
		namedParameters.put("modfdBy", modfdBy);
		namedParameters.put("role", role);

		namedParameterJdbcTemplate.update(_SQL4Profile, namedParameters);
		namedParameterJdbcTemplate.update(_SQL4Auth, namedParameters);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ManageUserDAO#deleteUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String deleteUser(String userList, String modfdBy)
	{
		List<String> userslist = new ArrayList<String>(Arrays.asList(userList.split(",")));
		String _SQL4Profile = "update app.EHS_SECURITY_USERPROFILE set MODIFIED_DT=CURRENT_DATE, MODIFIED_BY=:mdfdby, active=false where USERNAME IN (:usernameList)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("usernameList", userslist);
		namedParameters.put("mdfdby", modfdBy);
		namedParameterJdbcTemplate.update(_SQL4Profile, namedParameters);
		return "success";
	}

	@Override
	public boolean userExist(String username)
	{
		String fetchUserCount = "select count(*) from APP.EHS_SECURITY_USERPROFILE where username=:username";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("username", username);
		int userCount = namedParameterJdbcTemplate.queryForObject(fetchUserCount, namedParameters, Integer.class);

		if (userCount > 0)
			return true;
		else
			return false;
	}

	@Override
	public void createPasswordResetTokenForUser(String username, String token)
	{
		String fetchCurrentID = "select case when max(id) is null then 0 else max(id) end from app.usertoken";
		String fetchUserTokenCount = "select count(*) from APP.usertoken where username=:username";
		String createToken = "insert into app.usertoken(id , token, username, expiryDate, created_dt) values (  :id , :token, :username, {fn TIMESTAMPADD(SQL_TSI_HOUR, 24, CURRENT_TIMESTAMP)}, current_timestamp )";
		String updateToken = " update app.usertoken set token=:token, expiryDate={fn TIMESTAMPADD(SQL_TSI_HOUR, 24, CURRENT_TIMESTAMP)}, created_dt=current_timestamp where username=:username";

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("username", username);
		int tokenCount = namedParameterJdbcTemplate.queryForObject(fetchUserTokenCount, namedParameters, Integer.class);

		/* check if any existing token for the user, if yes, then update, else insert */
		if (tokenCount > 0)
		{
			namedParameters.put("username", username);
			namedParameters.put("token", token);
			namedParameterJdbcTemplate.update(updateToken, namedParameters);
		} else
		{
			int currentID = jdbcTemplate.queryForObject(fetchCurrentID, Integer.class);
			namedParameters.put("id", currentID + 1);
			namedParameters.put("username", username);
			namedParameters.put("token", token);
			namedParameterJdbcTemplate.update(createToken, namedParameters);
		}

		String fetchFullName = "select FIRST_NAME || ' ' || LAST_NAME from app.EHS_SECURITY_USERPROFILE where username=:username";
		namedParameters.put("username", username);
		String fullName = namedParameterJdbcTemplate.queryForObject(fetchFullName, namedParameters, String.class);

		emailService.sendMail("chaudharydeepak08@gmail.com", username, "passwordResetNotificationMessage.vm",
				"OHES Password Reset Notification", token, fullName);

	}

	@Override
	public String validatePasswordResetToken(String username, String token)
	{
		String validateToken = " Select expiryDate from app.usertoken where token=:token and username=:username";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("token", token);
		namedParameters.put("username", username);
		java.sql.Timestamp expiryTS = null;
		try
		{
			expiryTS = namedParameterJdbcTemplate.queryForObject(validateToken, namedParameters,
					java.sql.Timestamp.class);
		} catch (EmptyResultDataAccessException e)
		{
			LOGGER.error("invalid token - no record in system for token / user combination.");
		}
		if (null == expiryTS)
		{
			return "invalidToken";
		}
		Calendar cal = Calendar.getInstance();
		if (expiryTS.getTime() - cal.getTime().getTime() <= 0)
		{
			return "expired";
		}

		return "OK";
	}

}
