package com.dc.ehs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dc.ehs.entity.Observation;

public class ObservationMapper implements RowMapper< Observation >
{
	public Observation mapRow ( ResultSet arg0, int arg1 ) throws SQLException
	{
		Observation _obs = new Observation( );
		
		_obs.setObsID( String.valueOf( arg0.getInt( "obs_id" ) ) );
		// _obs.setObsTxt(arg0.getString("obsTxt"));
		_obs.setInitiatedBy( arg0.getString( "initiatedBy" ) );
		_obs.setResponsibleManager( arg0.getString( "respMgr" ) );
		_obs.setActive( String.valueOf( arg0.getBoolean( "active" ) ) );
		_obs.setStatus( arg0.getString( "status" ) );
		_obs.setCreated_dt( arg0.getDate( "creatd_dt" ) );
		_obs.setObsRef( arg0.getString( "reference" ) );
		_obs.setLocations( arg0.getString( "location" ) );
		_obs.setClassification( arg0.getString( "classification" ) );
		_obs.setDepartments( arg0.getString( "department" ) );
		_obs.setObsBehf( arg0.getString( "behalfOf" ) );
		//_obs.setInitiatedby( arg0.getString( "initiatedBy" ) );
		_obs.setObsContctInfo( arg0.getString( "contact_info" ) );
		_obs.setObsTxt( arg0.getString( "obsTxt" ) );
		_obs.setDate( String.valueOf( arg0.getDate( "obs_date" ) ) );
		_obs.setOperationType( "edit" );
		_obs.setProject( arg0.getString( "project" ) );
		_obs.setInitrFname( arg0.getString( "first_name" ) );
		_obs.setInitrLname( arg0.getString( "last_name" ) );
		_obs.setShoc( arg0.getString( "shoc" ) );
		_obs.setActionComments( arg0.getString( "actiontxt" ) );
		_obs.setAreas(arg0.getString( "area" ));
		_obs.setWhobsvd(arg0.getString( "observrtype" ));
		_obs.setLast_mdfd_date(String.valueOf(arg0.getDate( "modfd_dt" )));
		// _obs.setPropsdAction(arg0.getString("reference"));
		return _obs;
	}
}

/*
 * 1. we must hit 100% thisweek and start reconfirmations. 2. 4D power is key.
 * daily emails. 3. Template is coming, transportation incharge. -->
 * 
 * make up my mind - we cannot win by core. we need 4d power - build 4d momentum
 * -
 */
