package com.dc.ehs.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.common.EmailService;
import com.dc.ehs.entity.Actions;
import com.dc.ehs.entity.Attachment;
import com.dc.ehs.entity.Observation;
import com.dc.ehs.mapper.ActionMapper;
import com.dc.ehs.mapper.AttachmentMapper;
import com.dc.ehs.mapper.ObservationMapper;

/**
 * 
 * @author deepakchaudhary
 *
 */
public class ObservationCRUDDAOImpl implements ObservationCRUDDAO
{
	private static final Logger			LOGGER	= Logger.getLogger( ObservationCRUDDAOImpl.class );
	
	private NamedParameterJdbcTemplate	namedParameterJdbcTemplate;
	private JdbcTemplate				jdbcTemplate;
	
	@Autowired
	public void setDataSource ( DataSource dataSource )
	{
		this.jdbcTemplate = new JdbcTemplate( dataSource );
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
		
	}
	
	@Autowired
	EmailService emailService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#saveObservation(com.dc.ehs.entity.
	 * Observation)
	 */
	public int saveObservation ( Observation observation ) throws MessagingException
	{
		int obs_id = 0;
		int actionNumber = 0;
		int attachNumber = 0;
		String _SQL = "";
		Map< String, Object > namedParameters;
		if ( observation.getOperationType( ).equalsIgnoreCase( "new" ) )
		{
			String fetchObsNm = "select t from ( values next value for app.observation_number_id ) s( t) ";
			obs_id = jdbcTemplate.queryForObject( fetchObsNm, Integer.class );
			_SQL = "insert into app.ObservationMaster ( obs_id, \n" + "status,\n" + "active,\n" + "reference,  \n"
			        + "location, \n" + "department, \n" + "observrType, \n" + "behalfOf,\n" + "contact_info, \n"
			        + "shoc, \n" + "classification, \n" + "obsTxt,\n" + "respMgr, \n" + "initiatedBy, \n"
			        + "creatd_dt, \n" + "creatd_by) values (" + obs_id + ",\n" + "'Assigned'" + ",\n" + "'true'" + ",\n"
			        + "'" + observation.getObsRef( ) + "',\n" + "'" + observation.getLocations( ) + "',\n" + "'"
			        + observation.getDepartments( ) + "',\n" + "'" + observation.getWhobsvd( ) + "',\n" + "'"
			        + observation.getObsBehf( ) + "',\n" + "'" + observation.getObsContctInfo( ) + "',\n" + "'"
			        + observation.getShoc( ) + "',\n" + "'" + observation.getClassification( ) + "',\n" + "'"
			        + observation.getObsTxt( ) + "',\n" + "'" + observation.getResponsibleManager( ) + "',\n" + "'"
			        + observation.getInitiatedby( ) + "',\n" + "CURRENT_DATE" + ",\n" + "'"
			        + observation.getInitiatedby( ) + "')";
			
			jdbcTemplate.update( _SQL );
			
			if ( null != observation.getPropsdAction( ) && observation.getPropsdAction( ).trim( ).length( ) > 0 )
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
				        + "," + "'" + observation.getPropsdAction( ).trim( ) + "')";
				jdbcTemplate.update( insertSQLAction );
			}
			
			if ( null != observation.getPropsdAction_2( ) && observation.getPropsdAction_2( ).trim( ).length( ) > 0 )
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
				        + "," + "'" + observation.getPropsdAction_2( ).trim( ) + "')";
				jdbcTemplate.update( insertSQLAction );
			}
			
			if ( null != observation.getPropsdAction_3( ) && observation.getPropsdAction_3( ).trim( ).length( ) > 0 )
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
				        + "," + "'" + observation.getPropsdAction_3( ).trim( ) + "')";
				jdbcTemplate.update( insertSQLAction );
			}
			
			if ( null != observation.getPropsdAction_4( ) && observation.getPropsdAction_4( ).trim( ).length( ) > 0 )
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
				        + "," + "'" + observation.getPropsdAction_4( ).trim( ) + "')";
				jdbcTemplate.update( insertSQLAction );
			}
			
			if ( null != observation.getPropsdAction_5( ) && observation.getPropsdAction_5( ).trim( ).length( ) > 0 )
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
				        + "," + "'" + observation.getPropsdAction_5( ).trim( ) + "')";
				jdbcTemplate.update( insertSQLAction );
			}
			
			MultipartFile multipartFile = observation.getFile( );
			
			LOGGER.info( "--" + multipartFile );
			LOGGER.info( "---" + multipartFile.getOriginalFilename( ) );
			if ( null != multipartFile && null != multipartFile.getOriginalFilename( )
			        && !multipartFile.getOriginalFilename( ).trim( ).isEmpty( ) )
			{
				attachNumber = attachNumber + 1;
				String insertSQLAttachment = "insert into APP.ObservationAttachmnts values(" + obs_id + ","
				        + attachNumber + "," + "'File'" + ",'" + observation.getFile( ).getOriginalFilename( ) + "',"
				        + "'/Users/deepakchaudhary/dc_consulting/EHS/uploaded_docs/'" + ")";
				jdbcTemplate.update( insertSQLAttachment );
			}
			
			/* Lets send email */
			String respManager = ( observation.getResponsibleManager( ).split( "\\(" )[ 1 ] ).replace( ")", "" );
			LOGGER.info( "updated notification for " + respManager );
			emailService.sendMail( "chaudharydeepak08@gmail.com", respManager, "Observation assigned for your action",
			        "Dear " + observation.getResponsibleManager( )
			                + ", <br><br> New Observation is assigned for your action. Please access below:<br>http://ec2-34-201-58-201.compute-1.amazonaws.com:8080/ehsworx/welcome/action?name="
			                + obs_id + "<br><br>Best Regards,<br>EHS Observations" );
		}
		else
		{
			_SQL = "update app.ObservationMaster set reference=:ref, location=:loc, department=:dept, observrType=:obsType, behalfOf=:behalfOf, contact_info=:cntct, shoc=:sho, classification=:class"
			        + ", obsTxt=:obsText, respMgr=:resp, initiatedBy=:initby, modfd_dt=CURRENT_DATE, modfd_by=:mdfdby where obs_id=:obsId and upper(status) not like '%COMP%'";
			
			namedParameters = new HashMap< String, Object >( );
			
			namedParameters.put( "ref", observation.getObsRef( ) );
			namedParameters.put( "loc", observation.getLocations( ) );
			namedParameters.put( "dept", observation.getDepartments( ) );
			namedParameters.put( "obsType", observation.getWhobsvd( ) );
			namedParameters.put( "behalfOf", observation.getObsBehf( ) );
			namedParameters.put( "cntct", observation.getObsContctInfo( ) );
			namedParameters.put( "sho", observation.getShoc( ) );
			namedParameters.put( "class", observation.getClassification( ) );
			namedParameters.put( "obsText", observation.getObsTxt( ) );
			namedParameters.put( "resp", observation.getResponsibleManager( ) );
			namedParameters.put( "initby", observation.getInitiatedby( ) );
			namedParameters.put( "mdfdby", observation.getInitiatedby( ) );
			namedParameters.put( "obsId", Integer.valueOf( observation.getObsID( ) ) );
			namedParameterJdbcTemplate.update( _SQL, namedParameters );
			
			obs_id = Integer.valueOf( observation.getObsID( ) );
			
			String _actionsSQL = "update app.ObservationActions set action_txt=:actionTXT where obs_id=:obsId and action_id=:actionID";
			
			List< Actions > _actionList = observation.getActionsList( );
			
			_actionList.forEach( actionObj -> {
				LOGGER.warn( "check updated action item -- " + actionObj.getActionTxt( ) + " : "
				        + actionObj.getActionId( ) );
				namedParameters.put( "actionTXT", actionObj.getActionTxt( ) );
				namedParameters.put( "actionID", actionObj.getActionId( ) );
				namedParameters.put( "obsId", Integer.valueOf( observation.getObsID( ) ) );
				namedParameterJdbcTemplate.update( _actionsSQL, namedParameters );
			} );
			
			MultipartFile multipartFile = observation.getFile( );
			
			LOGGER.info( "--" + multipartFile );
			LOGGER.info( "---" + multipartFile.getOriginalFilename( ) );
			if ( null != multipartFile && null != multipartFile.getOriginalFilename( )
			        && !multipartFile.getOriginalFilename( ).trim( ).isEmpty( ) )
			{
				String fetchCurrentAttchID = "select case when max(attch_id) is null then 1 else max(attch_id) end from APP.ObservationAttachmnts where obs_id=:obsid";
				namedParameters.put( "obsid", Integer.valueOf( observation.getObsID( ) ) );
				int currentAttchID = namedParameterJdbcTemplate.queryForObject( fetchCurrentAttchID, namedParameters,
				        Integer.class );
				currentAttchID = currentAttchID + 1;
				LOGGER.info( "currentAttchID " + currentAttchID );
				/*
				 * String insertAttach =
				 * "insert into APP.ObservationAttachmnts(obs_id, attch_id,attach_type,attach_name,attach_path) values ( :obsid, :attachNmbr, :type, :fileName, :filePath)"
				 * ; namedParameters.put("obsid",
				 * Integer.valueOf(observation.getObsID()));
				 * namedParameters.put("attachNmbr",
				 * Integer.valueOf(currentAttchID)); namedParameters.put("type",
				 * "File"); namedParameters.put("fileName",
				 * multipartFile.getOriginalFilename());
				 * namedParameters.put("filePath",
				 * "/Users/deepakchaudhary/dc_consulting/EHS/uploaded_docs/");
				 */
				
				String insertSQLAttachment = "insert into APP.ObservationAttachmnts values(" + obs_id + ","
				        + currentAttchID + "," + "'File'" + ",'" + observation.getFile( ).getOriginalFilename( ) + "',"
				        + "'/Users/deepakchaudhary/dc_consulting/EHS/uploaded_docs/'" + ")";
				
				jdbcTemplate.update( insertSQLAttachment );
			}
		}
		return obs_id;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#loadObservation(int)
	 */
	public Observation loadObservation ( int observationId )
	{
		Observation obsMaster = null;
		String fetchObservation = "Select * from app.ObservationMaster where obs_id=:obsid";
		
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		
		namedParameters.put( "obsid", observationId );
		
		List< Observation > obsFrmDB = namedParameterJdbcTemplate.query( fetchObservation, namedParameters,
		        new ObservationMapper( ) );
		
		if ( null != obsFrmDB && !obsFrmDB.isEmpty( ) )
		{
			obsMaster = obsFrmDB.get( 0 );
			
			if ( null != obsMaster )
			{
				/* fetch actions */
				String fetchActions = "Select * from app.ObservationActions where obs_id=:obsid";
				
				List< Actions > actionList = namedParameterJdbcTemplate.query( fetchActions, namedParameters,
				        new ActionMapper( ) );
				
				if ( null != actionList && !actionList.isEmpty( ) )
					obsMaster.setActionsList( actionList );
				
				/* fetch attacments */
				String fetchAttach = "Select * from app.ObservationAttachmnts where obs_id=:obsid";
				List< Attachment > attachList = namedParameterJdbcTemplate.query( fetchAttach, namedParameters,
				        new AttachmentMapper( ) );
				
				if ( null != attachList && !attachList.isEmpty( ) )
				{
					LOGGER.info( "##################setting attachment --> " + attachList.size( ) );
					obsMaster.setAttachList( attachList );
				}
			}
		}
		return obsMaster;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#loadAllObservations()
	 */
	public List< Observation > loadAllObservations ( String username )
	{
		String fetchAllObservations = "select * from app.ObservationMaster";
		List< Observation > obsList = null;
		if ( !hasRole( "ROLE_ADMIN" ) )
		{
			LOGGER.info( "username is not admin -- fetching assigned obs :" );
			fetchAllObservations = "select * from app.ObservationMaster where respMgr like '%" + username + "%'";
		}
		obsList = namedParameterJdbcTemplate.query( fetchAllObservations, new ObservationMapper( ) );
		return obsList;
	}
	
	/*
	 * Utility method - move to util class.
	 */
	@SuppressWarnings( "unchecked" )
	private static boolean hasRole ( String role )
	{
		Collection< GrantedAuthority > authorities = ( Collection< GrantedAuthority > ) SecurityContextHolder
		        .getContext( ).getAuthentication( ).getAuthorities( );
		boolean hasRole = false;
		for ( GrantedAuthority authority : authorities )
		{
			hasRole = authority.getAuthority( ).equals( role );
			if ( hasRole )
			{
				break;
			}
		}
		return hasRole;
	}
	
	@Override
	public String setStatusOnObservation ( int observationId, String status, String user )
	{
		String _SQL = "update app.ObservationMaster set modfd_dt=CURRENT_DATE, modfd_by=:mdfdby, status=:status where obs_id=:obsId";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "mdfdby", user );
		namedParameters.put( "status", status );
		namedParameters.put( "obsId", observationId );
		namedParameterJdbcTemplate.update( _SQL, namedParameters );
		return "success";
	}
	
}