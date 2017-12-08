package com.dc.ehs.helper;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.domain.User;
import com.dc.ehs.entity.MetaData;
import com.dc.ehs.entity.Observation;
import com.dc.ehs.service.LoadObservationMetadataService;
import com.dc.ehs.service.ManageUserService;
import com.dc.ehs.service.ObservationCRUDService;
import com.dc.ehs.util.EhsUtilityMethods;

public class EhsHelper
{
	@Autowired
	ManageUserService				manageUserService;
	
	@Autowired
	LoadObservationMetadataService	loadmetaDataService;
	
	@Autowired
	ObservationCRUDService			obsCRUDService;
	
	@Autowired
	BCryptPasswordEncoder			encoder;
	
	@Autowired
	EhsUtilityMethods				utilMethods;
	
	private static final Logger		LOGGER	= Logger.getLogger( EhsHelper.class );
	
	public List< User > loadAllUsers ( String userName )
	{
		LOGGER.info( "invoked loadAllUsers" );
		return manageUserService.loadUsers( userName );
	}
	
	public HashMap< String, List< String > > loadObersvationsMetaData ( )
	{
		LOGGER.info( "invoked loadObersvationsMetaData" );
		return loadmetaDataService.loadObersvationsMetaData( );
	}
	
	public List< MetaData > loadMetaDataForEdit ( )
	{
		LOGGER.info( "invoked loadObersvationsMetaData" );
		return loadmetaDataService.loadMetaDataForEdit( );
	}
	
	public String saveMetaData ( int id, String metaType, String metaValue )
	{
		return loadmetaDataService.saveMetaData( id, metaType, metaValue );
	}
	
	public String createMetaData ( String metaType, String metaValue )
	{
		return loadmetaDataService.createMetaData( metaType, metaValue );
	}
	
	public String deleteMetaData ( String metaData )
	{
		return loadmetaDataService.deleteMetaData( metaData, utilMethods.getLoggedInUser( ) );
	}
	
	public String fetchStatusForObs ( int obsID )
	{
		return obsCRUDService.fetchStatusForObs( obsID );
	}
	
	public int saveObservation ( Observation observation ) throws IOException, MessagingException, ParseException
	{
		observation.setInitiatedBy( utilMethods.getLoggedInUser( ) );
		
		/* lets upload file first. */
		utilMethods.uploadFile( observation.getFile( ) );
		
		return obsCRUDService.saveObservation( observation );
	}
	
	public List< Observation > loadAllObservations ( )
	{
		return obsCRUDService.loadAllObservations( utilMethods.getLoggedInUser( ) );
	}
	
	public Observation loadObservation ( String obs_id )
	{
		String respManager = obsCRUDService.fetchRespManagerForObs( Integer.valueOf( obs_id ) );
		LOGGER.info( "respManager : " + respManager );
		if ( !respManager.contains( utilMethods.getLoggedInUser( ) ) && !utilMethods.hasRole( "ROLE_ADMIN" ) )
			throw ( new AccessDeniedException(
			        "You do not have access to this observtion or observation does not exist." ) );
		
		return obsCRUDService.loadObservation( Integer.valueOf( obs_id ), utilMethods.getLoggedInUser( ) );
	}
	
	public String setStatus ( int obsId, String status, String actionComments, MultipartFile multipartFile )
	        throws IOException
	{
		String respManager = obsCRUDService.fetchRespManagerForObs( obsId );
		LOGGER.info( "respManager : " + respManager );
		if ( !respManager.contains( utilMethods.getLoggedInUser( ) ) && !utilMethods.hasRole( "ROLE_ADMIN" ) )
			throw ( new AccessDeniedException(
			        "You do not have access to this observtion or observation does not exist." ) );
		
		/* lets upload file first. */
		utilMethods.uploadFile( multipartFile );
		
		return obsCRUDService.setStatusOnObservation( obsId, status, utilMethods.getLoggedInUser( ), actionComments,
		        multipartFile );
	}
	
	public String saveUser ( String username, String key, String value )
	{
		if ( key.equalsIgnoreCase( "pswd" ) )
		{
			value = encoder.encode( value );
		}
		return manageUserService.saveUser( username, key, value, utilMethods.getLoggedInUser( ) );
	}
	
	public String createUser ( String username, String password, String role, String firstname, String lastname )
	{
		return manageUserService.createUser( username, encoder.encode( password ), firstname, lastname, role,
		        utilMethods.getLoggedInUser( ) );
	}
	
	public String deletUser ( String userList )
	{
		return manageUserService.deleteUser( userList, utilMethods.getLoggedInUser( ) );
	}
	
	public String deleteObservation ( int obsId )
	{
		return obsCRUDService.deleteObservation( obsId, utilMethods.getLoggedInUser( ) );
	}
	
	public String getLoggedInUserWrapper ( )
	{
		return utilMethods.getLoggedInUser( );
	}
}
