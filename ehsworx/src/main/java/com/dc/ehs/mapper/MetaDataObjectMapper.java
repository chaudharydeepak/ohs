package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dc.ehs.entity.MetaData;

public class MetaDataObjectMapper implements RowMapper<MetaData>
{
	public MetaData mapRow(ResultSet arg0, int arg1) throws SQLException
	{
		MetaData _meta = new MetaData();
		_meta.setId( arg0.getInt("id") );
		_meta.setType( arg0.getString("meta_key") );
		_meta.setValue( arg0.getString("meta_value") );
		return _meta;
	}
}
