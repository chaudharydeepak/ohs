package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dc.ehs.entity.Actions;

public class ActionMapper implements RowMapper<Actions>
{
	public Actions mapRow(ResultSet arg0, int arg1) throws SQLException
	{
		Actions _action = new Actions();
		_action.setActionId(arg0.getInt("action_id"));
		_action.setObsId(arg0.getInt("obs_id"));
		_action.setActionTxt(arg0.getString("action_txt"));
		return _action;
	}

}
