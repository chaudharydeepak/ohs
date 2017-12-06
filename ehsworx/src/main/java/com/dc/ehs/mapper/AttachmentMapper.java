package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dc.ehs.entity.Attachment;

public class AttachmentMapper implements RowMapper<Attachment>
{
	public Attachment mapRow(ResultSet arg0, int arg1) throws SQLException
	{
		Attachment _attachmnt = new Attachment();
		_attachmnt.setAttch_id(arg0.getInt("attch_id"));
		_attachmnt.setFileName(arg0.getString("attach_name"));
		_attachmnt.setFilePath(arg0.getString("attach_path"));
		_attachmnt.setObsId(arg0.getInt("obs_id"));
		_attachmnt.setType(arg0.getString("attach_type"));
		return _attachmnt;
	}
}
