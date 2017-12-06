package com.dc.ehs.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Observation
{
	List< Actions >		actionsList;
	List< Attachment >	attachList;
	String				obsID;
	String				obsRef;
	String				obsBehf;
	String				obsContctInfo;
	String				obsTxt;
	String				locations;
	String				propsdAction;
	String				status;
	String				active;
	String				propsdAction_2;
	
	String				propsdAction_3;
	
	String				propsdAction_4;
	
	String				propsdAction_5;
	
	String				departments;
	
	String				shoc;
	
	String				whobsvd;
	
	String				classification;
	
	String				responsibleManager;
	
	String				initiatedBy;
	
	MultipartFile		file;
	
	String				date;
	
	Date				created_dt;
	
	String				initiatedby;
	
	String				operationType;
	
	String				project;
	
	String				initrFname;
	
	String				initrLname;
	
	String				actionComments;
	
	public String getActionComments ( )
	{
		return actionComments;
	}
	
	public void setActionComments ( String actionComments )
	{
		this.actionComments = actionComments;
	}
	
	public String getInitrFname ( )
	{
		return initrFname;
	}
	
	public void setInitrFname ( String initrFname )
	{
		this.initrFname = initrFname;
	}
	
	public String getInitrLname ( )
	{
		return initrLname;
	}
	
	public void setInitrLname ( String initrLname )
	{
		this.initrLname = initrLname;
	}
	
	public String getProject ( )
	{
		return project;
	}
	
	public void setProject ( String project )
	{
		this.project = project;
	}
	
	public Observation ( )
	{
		this.operationType = "new";
	}
	
	public String getObsID ( )
	{
		return obsID;
	}
	
	public void setObsID ( String obsID )
	{
		this.obsID = obsID;
	}
	
	public String getObsRef ( )
	{
		return obsRef;
	}
	
	public void setObsRef ( String obsRef )
	{
		this.obsRef = obsRef;
	}
	
	public String getLocations ( )
	{
		return locations;
	}
	
	public void setLocations ( String locations )
	{
		this.locations = locations;
	}
	
	public String getDepartments ( )
	{
		return departments;
	}
	
	public void setDepartments ( String departments )
	{
		this.departments = departments;
	}
	
	public String getShoc ( )
	{
		return shoc;
	}
	
	public void setShoc ( String shoc )
	{
		this.shoc = shoc;
	}
	
	public String getWhobsvd ( )
	{
		return whobsvd;
	}
	
	public void setWhobsvd ( String whobsvd )
	{
		this.whobsvd = whobsvd;
	}
	
	public String getClassification ( )
	{
		return classification;
	}
	
	public void setClassification ( String classification )
	{
		this.classification = classification;
	}
	
	public String getResponsibleManager ( )
	{
		return responsibleManager;
	}
	
	public void setResponsibleManager ( String responsibleManager )
	{
		this.responsibleManager = responsibleManager;
	}
	
	public MultipartFile getFile ( )
	{
		return file;
	}
	
	public void setFile ( MultipartFile file )
	{
		this.file = file;
	}
	
	public String getObsBehf ( )
	{
		return obsBehf;
	}
	
	public void setObsBehf ( String obsBehf )
	{
		this.obsBehf = obsBehf;
	}
	
	public String getObsContctInfo ( )
	{
		return obsContctInfo;
	}
	
	public void setObsContctInfo ( String obsContctInfo )
	{
		this.obsContctInfo = obsContctInfo;
	}
	
	public String getObsTxt ( )
	{
		return obsTxt;
	}
	
	public void setObsTxt ( String obsTxt )
	{
		this.obsTxt = obsTxt;
	}
	
	public String getPropsdAction ( )
	{
		return propsdAction;
	}
	
	public void setPropsdAction ( String propsdAction )
	{
		this.propsdAction = propsdAction;
	}
	
	public String getDate ( )
	{
		return date;
	}
	
	public void setDate ( String date )
	{
		this.date = date;
	}
	
	public String getInitiatedby ( )
	{
		return initiatedby;
	}
	
	public void setInitiatedby ( String initiatedby )
	{
		this.initiatedby = initiatedby;
	}
	
	public String getPropsdAction_2 ( )
	{
		return propsdAction_2;
	}
	
	public void setPropsdAction_2 ( String propsdAction_2 )
	{
		this.propsdAction_2 = propsdAction_2;
	}
	
	public String getPropsdAction_3 ( )
	{
		return propsdAction_3;
	}
	
	public void setPropsdAction_3 ( String propsdAction_3 )
	{
		this.propsdAction_3 = propsdAction_3;
	}
	
	public String getPropsdAction_4 ( )
	{
		return propsdAction_4;
	}
	
	public void setPropsdAction_4 ( String propsdAction_4 )
	{
		this.propsdAction_4 = propsdAction_4;
	}
	
	public String getPropsdAction_5 ( )
	{
		return propsdAction_5;
	}
	
	public void setPropsdAction_5 ( String propsdAction_5 )
	{
		this.propsdAction_5 = propsdAction_5;
	}
	
	public String getStatus ( )
	{
		return status;
	}
	
	public void setStatus ( String status )
	{
		this.status = status;
	}
	
	public String getActive ( )
	{
		return active;
	}
	
	public void setActive ( String active )
	{
		this.active = active;
	}
	
	public Date getCreated_dt ( )
	{
		return created_dt;
	}
	
	public void setCreated_dt ( Date created_dt )
	{
		this.created_dt = created_dt;
	}
	
	public String getOperationType ( )
	{
		return operationType;
	}
	
	public void setOperationType ( String operationType )
	{
		this.operationType = operationType;
	}
	
	public String getInitiatedBy ( )
	{
		return initiatedBy;
	}
	
	public void setInitiatedBy ( String initiatedBy )
	{
		this.initiatedBy = initiatedBy;
	}
	
	public List< Actions > getActionsList ( )
	{
		return actionsList;
	}
	
	public void setActionsList ( List< Actions > actionsList )
	{
		this.actionsList = actionsList;
	}
	
	public List< Attachment > getAttachList ( )
	{
		return attachList;
	}
	
	public void setAttachList ( List< Attachment > attachList )
	{
		this.attachList = attachList;
	}
}
