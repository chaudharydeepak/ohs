package com.dc.ehs.entity;

public class Attachment
{
	String fileName;
	String filePath;
	int obsId;
	int attch_id;
	String type;
	
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	public int getObsId()
	{
		return obsId;
	}
	public void setObsId(int obsId)
	{
		this.obsId = obsId;
	}
	public int getAttch_id()
	{
		return attch_id;
	}
	public void setAttch_id(int attch_id)
	{
		this.attch_id = attch_id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}
