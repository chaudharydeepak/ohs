package com.dc.ehs.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dc.ehs.domain.User;
import com.dc.ehs.mapper.ManageUserMapper;

public class ManageUserDAOImpl implements ManageUserDAO
{
	private static final Logger LOGGER = Logger.getLogger(ManageUserDAOImpl.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<User> loadUsers(String username)
	{
		LOGGER.info( "invoked  loadUsers " );
		String fetchUserSQL = "Select * from app.EHS_SECURITY";
		List<User> usersList = namedParameterJdbcTemplate.query(fetchUserSQL, new ManageUserMapper());
		return usersList;
	}

}
