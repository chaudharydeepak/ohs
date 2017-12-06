package com.dc.ehs.domain;

import java.util.Date;

/**
 * Domain object for system user.
 * @author Deepak Chaudhary
 *
 */
public class User
{    
	int userID;
	String userName;
	String userPasswd;
	String userfName;
	String userlName;
	String userRole;
	String userEmail;
	Date created_dt;
	String created_by;
	Date modified_dt;
	String modified_by;

	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPasswd()
	{
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd)
	{
		this.userPasswd = userPasswd;
	}


	public Date getCreated_dt()
	{
		return created_dt;
	}

	public void setCreated_dt(Date created_dt)
	{
		this.created_dt = created_dt;
	}

	public String getCreated_by()
	{
		return created_by;
	}

	public void setCreated_by(String created_by)
	{
		this.created_by = created_by;
	}

	public Date getModified_dt()
	{
		return modified_dt;
	}

	public void setModified_dt(Date modified_dt)
	{
		this.modified_dt = modified_dt;
	}

	public String getModified_by()
	{
		return modified_by;
	}

	public void setModified_by(String modified_by)
	{
		this.modified_by = modified_by;
	}

	public String getUserRole()
	{
		return userRole;
	}

	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}

	public String getUserfName()
	{
		return userfName;
	}

	public void setUserfName(String userfName)
	{
		this.userfName = userfName;
	}

	public String getUserlName()
	{
		return userlName;
	}

	public void setUserlName(String userlName)
	{
		this.userlName = userlName;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

}
