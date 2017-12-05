package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MetaDataMapper implements RowMapper< String >
{

	public String mapRow(ResultSet arg0, int arg1) throws SQLException
	{
		return arg0.getString( "meta_value" );
	}

}
