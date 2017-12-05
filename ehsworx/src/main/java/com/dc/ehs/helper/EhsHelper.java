package com.dc.ehs.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.common.EmailService;
import com.dc.ehs.domain.User;
import com.dc.ehs.entity.MetaData;
import com.dc.ehs.entity.Observation;
import com.dc.ehs.service.LoadObservationMetadataService;
import com.dc.ehs.service.ManageUserService;
import com.dc.ehs.service.ObservationCRUDService;

public class EhsHelper
{
	@Autowired
	ManageUserService				manageUserService;
	
	@Autowired
	LoadObservationMetadataService	loadmetaDataService;
	
	@Autowired
	ObservationCRUDService			obsCRUDService;
	
	@Autowired
	EmailService					emailService;
	
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
	
	public String saveMetaData( int id , String metaType, String metaValue)
	{
		return loadmetaDataService.saveMetaData( id, metaType, metaValue );
	}
	
	public String createMetaData ( String metaType, String metaValue )
	{
		return loadmetaDataService.createMetaData( metaType, metaValue );
	}
	
	public String deleteMetaData ( String metaData )
	{
		String modfdBy = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			modfdBy = authentication.getName( );
		}
		
		return loadmetaDataService.deleteMetaData(metaData, modfdBy);
	}
	
	public int saveObservation ( Observation observation ) throws IOException, MessagingException
	{
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			observation.setInitiatedby( authentication.getName( ) );
		}
		/* lets upload file first */
		MultipartFile multipartFile = observation.getFile( );
		
		LOGGER.info( "--" + multipartFile );
		LOGGER.info( "---" + multipartFile.getOriginalFilename( ) );
		if ( null != multipartFile && null != multipartFile.getOriginalFilename( )
		        && !multipartFile.getOriginalFilename( ).trim( ).isEmpty( ) )
			FileCopyUtils.copy( multipartFile.getBytes( ),
			        new File( "/Users/deepakchaudhary/dc_consulting/EHS/uploaded_docs/"
			                + multipartFile.getOriginalFilename( ) ) );
		
		return obsCRUDService.saveObservation( observation );
	}
	
	public List< Observation > loadAllObservations ( )
	{
		String username = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			username = authentication.getName( );
		}
		
		return obsCRUDService.loadAllObservations( username );
	}
	
	public Observation loadObservation ( String obs_id )
	{
		return obsCRUDService.loadObservation( Integer.valueOf( obs_id ) );
	}
	
	public String setStatus ( int obsId, String status )
	{
		String username = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			username = authentication.getName( );
		}
		return obsCRUDService.setStatusOnObservation( obsId, status, username );
	}
	
	public String sendEhsNotification ( String obsID, String recp ) throws MessagingException
	{
		LOGGER.info( "sending notification for " + recp );
		String respManager = ( recp.split( "\\(" )[ 1 ] ).replace( ")", "" );
		LOGGER.info( "updated notification for " + respManager );
		emailService.sendMail( "admin@ehs.com", respManager, "Observation assigned for your action",
		        "Dear " + recp
		                + ", <br><br> New Observation is assigned for your action. Please access below:<br>http://ec2-34-201-58-201.compute-1.amazonaws.com:8080/ehsworx/welcome/action?name="
		                + obsID + "<br><br>Best Regards,<br>EHS Observations" );
		return "success";
	}
	
	public String saveUser ( String username, String key, String value )
	{
		String modfdBy = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			modfdBy = authentication.getName( );
		}
		return manageUserService.saveUser( username, key, value, modfdBy );
	}
	
	public String createUser ( String username, String password, String role, String firstname, String lastname )
	{
		String modfdBy = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			modfdBy = authentication.getName( );
		}
		return manageUserService.createUser( username, password, firstname, lastname, role, modfdBy );
	}
	
	public String deletUser ( String userList )
	{
		String modfdBy = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			modfdBy = authentication.getName( );
		}
		return manageUserService.deleteUser( userList, modfdBy );
	}
	
	
	
}
