package com.dc.ehs.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dc.ehs.entity.Actions;
import com.dc.ehs.entity.Observation;
import com.dc.ehs.helper.EhsHelper;

/**
 * 
 * @author Deepak Chaudhary
 *
 */
@Controller
@RequestMapping( "/welcome" )
public class ApplicationController
{
	
	@Autowired
	EhsHelper					ehsHelper;
	
	private static final Logger	LOGGER	= Logger.getLogger( ApplicationController.class );
	
	
	@RequestMapping( method = RequestMethod.GET )
	public String loadLanding ( ModelMap model )
	{
		
		LOGGER.info( "invoked loadHome " );
		/* return doc search view */
		//model.addAttribute( "observationList", ehsHelper.loadAllObservations( ) );
		return "landing";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/mgobs", method = RequestMethod.GET )
	public String loadHome ( ModelMap model )
	{
		
		LOGGER.info( "invoked loadHome " );
		/* return doc search view */
		model.addAttribute( "observationList", ehsHelper.loadAllObservations( ) );
		return "home";
	}
	
	/**
	 * 
	 * @param model
	 * @param observation
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( value = "/actionobs", method = RequestMethod.POST, params =
	{ "assigned" } )
	public ModelAndView approve ( ModelMap model, Observation observation ) throws IOException
	{
		LOGGER.info( "invoked assigned " );
		ehsHelper.setStatus( Integer.valueOf( observation.getObsID( ) ), "In_Progress" );
		return new ModelAndView( "redirect:/welcome/action?name=" + observation.getObsID( ) );
	}
	
	/**
	 * 
	 * @param model
	 * @param observation
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( value = "/actionobs", method = RequestMethod.POST, params =
	{ "inprogress" } )
	public ModelAndView deny ( ModelMap model, Observation observation ) throws IOException
	{
		LOGGER.info( "invoked inprogress " );
		ehsHelper.setStatus( Integer.valueOf( observation.getObsID( ) ), "Completed." );
		return new ModelAndView( "redirect:/welcome/action?name=" + observation.getObsID( ) );
	}
	
	/**
	 * 
	 * @param obsId
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/action", method = RequestMethod.GET )
	public String loadActionOnObservation ( @RequestParam( "name" ) String obsId, ModelMap model )
	{
		
		LOGGER.info( "invoked loadbservation for " + obsId );
		
		// ehsHelper.loadObservation(obsId);
		
		LOGGER.info( "successfully created observation with ID " + obsId );
		
		/* now lest load all observations */
		HashMap< String, List< String > > metaDataMap = ehsHelper.loadObersvationsMetaData( );
		
		model.addAttribute( "locationList", metaDataMap.get( "location" ) );
		model.addAttribute( "departmentList", metaDataMap.get( "department" ) );
		model.addAttribute( "obeservertypeList", metaDataMap.get( "obeservertype" ) );
		model.addAttribute( "shocList", metaDataMap.get( "shoc" ) );
		model.addAttribute( "classificationList", metaDataMap.get( "classification" ) );
		model.addAttribute( "respManagerList", metaDataMap.get( "responsibleUser" ) );
		
		Observation retreivedObs = ehsHelper.loadObservation( obsId );
		
		model.addAttribute( "attachList", retreivedObs.getAttachList( ) );
		model.addAttribute( "status", retreivedObs.getStatus( ) );
		model.addAttribute( "observation", retreivedObs );
		
		/* return doc search view */
		return "observation_action";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/admin", method = RequestMethod.GET )
	public String loadObservation ( ModelMap model )
	{
		LOGGER.info( "invoked loadObservation " );
		
		HashMap< String, List< String > > metaDataMap = ehsHelper.loadObersvationsMetaData( );
		
		model.addAttribute( "locationList", metaDataMap.get( "location" ) );
		model.addAttribute( "departmentList", metaDataMap.get( "department" ) );
		model.addAttribute( "obeservertypeList", metaDataMap.get( "obeservertype" ) );
		model.addAttribute( "shocList", metaDataMap.get( "shoc" ) );
		model.addAttribute( "classificationList", metaDataMap.get( "classification" ) );
		model.addAttribute( "respManagerList", metaDataMap.get( "responsibleUser" ) );
		
		// Observation observation = new Observation();
		// observation.setOperationType("new");
		model.addAttribute( "observation", new Observation( ) );
		
		/* return doc search view */
		return "admin/observation";
	}
	
	/**
	 * 
	 * @param observation
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 * @throws ParseException
	 */
	@RequestMapping( value = "/admin/createobs", method = RequestMethod.POST )
	public ModelAndView createObservation ( Observation observation, ModelMap model )
	        throws IOException, MessagingException, ParseException
	{
		LOGGER.info( "invoked createObservation " + observation );
		
		if ( null != observation )
		{
			LOGGER.info( " Requested operation -- " + observation.getOperationType( ) );
		}
		
		LOGGER.info( " -- " + observation.getDate( ) );
		
		int obsId = ehsHelper.saveObservation( observation );
		
		LOGGER.info( "successfully" + observation.getOperationType( ) + " observation with ID " + obsId );
		
		/* now lest load all observations */
		if ( observation.getOperationType( ).equalsIgnoreCase( "new" ) )
		{
			return new ModelAndView( "redirect:/welcome" );
		}
		else
		{
			return new ModelAndView( "redirect:/admin/loadObs?name=" + obsId );
		}
	}
	
	/**
	 * 
	 * @param obsId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( value = "/admin/loadObs", method = RequestMethod.GET )
	public String loadbservation ( @RequestParam( "name" ) String obsId, ModelMap model ) throws IOException
	{
		LOGGER.info( "invoked loadbservation for " + obsId );
		
		// ehsHelper.loadObservation(obsId);
		
		LOGGER.info( "successfully created observation with ID " + obsId );
		
		/* now lest load all observations */
		HashMap< String, List< String > > metaDataMap = ehsHelper.loadObersvationsMetaData( );
		
		model.addAttribute( "locationList", metaDataMap.get( "location" ) );
		model.addAttribute( "departmentList", metaDataMap.get( "department" ) );
		model.addAttribute( "obeservertypeList", metaDataMap.get( "obeservertype" ) );
		model.addAttribute( "shocList", metaDataMap.get( "shoc" ) );
		model.addAttribute( "classificationList", metaDataMap.get( "classification" ) );
		model.addAttribute( "respManagerList", metaDataMap.get( "responsibleUser" ) );
		
		Observation retreivedObs = ehsHelper.loadObservation( obsId );
		
		// LOGGER.info("this has action count -- " +
		// retreivedObs.getActionsList().size());
		// LOGGER.info("this has attach count -- " +
		// retreivedObs.getAttachList().size());
		model.addAttribute( "attachList", retreivedObs.getAttachList( ) );
		model.addAttribute( "observation", retreivedObs );
		
		/* return doc search view */
		return "admin/observation_edit";
	}
	
	/**
	 * 
	 * @param observation
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 * @throws ParseException
	 */
	@RequestMapping( value = "/admin/editobs", method = RequestMethod.POST )
	public ModelAndView editObservation ( Observation observation, ModelMap model )
	        throws IOException, MessagingException, ParseException
	{
		LOGGER.info( "invoked createObservation " + observation );
		
		if ( null != observation )
		{
			LOGGER.info( " Requested operation -- " + observation.getOperationType( ) );
		}
		/* return doc search view */
		observation.setOperationType( "edit" );
		LOGGER.info( " Check Requested operation -- " + observation.getOperationType( ) );
		
		List< Actions > _actionsList = observation.getActionsList( );
		
		_actionsList.forEach( actionObj -> {
			LOGGER.warn( "check updated action item " + actionObj.getActionTxt( ) );
		} );
		
		LOGGER.warn( "$$$$$$$$$$$ 1-- > " + observation.getPropsdAction( ) );
		LOGGER.warn( "$$$$$$$$$$$ 2-- > " + observation.getPropsdAction_2( ) );
		LOGGER.warn( "$$$$$$$$$$$ 3-- > " + observation.getPropsdAction_3( ) );
		LOGGER.warn( "$$$$$$$$$$$ 4-- > " + observation.getPropsdAction_4( ) );
		LOGGER.warn( "$$$$$$$$$$$ 5-- > " + observation.getPropsdAction_5( ) );
		
		LOGGER.info( "########### Check Requested operation -- " + observation.getObsID( ) );
		int obsId = ehsHelper.saveObservation( observation );
		
		LOGGER.info( "successfully" + observation.getOperationType( ) + " observation with ID " + obsId );
		
		/* now lest load all observations */
		if ( observation.getOperationType( ).equalsIgnoreCase( "new" ) )
		{
			return new ModelAndView( "redirect:/welcome" );
		}
		else
		{
			return new ModelAndView( "redirect:/welcome/admin/loadObs?name=" + obsId );
		}
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/admin/manageuser", method = RequestMethod.GET )
	public String loadUser ( ModelMap model )
	{
		LOGGER.info( "invoked loadUser " );
		model.addAttribute( "userList", ehsHelper.loadAllUsers( "all" ) );
		/* return doc search view */
		return "admin/manageuser";
	}
	
	/**
	 * 
	 * @param formParams
	 * @param type
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/saveUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
	public ResponseEntity< String > saveUser ( @RequestBody MultiValueMap< String, String > formParams,
	        @RequestParam( value = "type", required = true ) String type )
	{
		LOGGER.info( "invoked saveUser type: " + type );
		System.out.println( "form params received: " + formParams );
		HttpHeaders responseHeaders = new HttpHeaders( );
		String userName = "";
		String valueToSet = "";
		String replaceString = "";
		if ( type.equalsIgnoreCase( "uname" ) )
			replaceString = "username_";
		else if ( type.equalsIgnoreCase( "fname" ) )
			replaceString = "userFname_";
		else if ( type.equalsIgnoreCase( "lname" ) )
			replaceString = "userLname_";
		else if ( type.equalsIgnoreCase( "role" ) )
			replaceString = "userroles_";
		else if ( type.equalsIgnoreCase( "pswd" ) )
			replaceString = "userPswd_";
		
		userName = formParams.get( "name" ).get( 0 );
		valueToSet = formParams.get( "value" ).get( 0 );
		
		LOGGER.info( "replaceString: " + replaceString + " userName: " + userName.replace( replaceString, "" )
		        + " & newValue: " + valueToSet );
		try
		{
			ehsHelper.saveUser( userName.replace( replaceString, "" ), type, valueToSet );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param formParams
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
	public ResponseEntity< String > createUser ( @RequestBody MultiValueMap< String, String > formParams )
	{
		LOGGER.info( "invoked createUser : " );
		System.out.println( "form params received: " + formParams );
		HttpHeaders responseHeaders = new HttpHeaders( );
		String role = formParams.get( "role" ).get( 0 );
		String firstname = formParams.get( "firstname" ).get( 0 );
		String lastname = formParams.get( "lastname" ).get( 0 );
		String username = formParams.get( "username" ).get( 0 );
		String password = formParams.get( "password" ).get( 0 );
		try
		{
			ehsHelper.createUser( username, password, role, firstname, lastname );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param users
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/deleteUser", method = RequestMethod.POST )
	public ResponseEntity< String > deleteUser ( @RequestParam( value = "users", required = true ) String users )
	{
		LOGGER.info( "invoked deleteUser : " );
		System.out.println( "params received: " + users );
		HttpHeaders responseHeaders = new HttpHeaders( );
		try
		{
			ehsHelper.deletUser( users );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping( value = "/admin/managmtdt", method = RequestMethod.GET )
	public String loadMetadata ( ModelMap model )
	{
		model.addAttribute( "metaData", ehsHelper.loadMetaDataForEdit( ) );
		return "admin/managemetadata";
	}
	
	/**
	 * 
	 * @param formParams
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/saveMetaData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
	public ResponseEntity< String > saveMetaData ( @RequestBody MultiValueMap< String, String > formParams )
	{
		LOGGER.info( "invoked saveMetaData" );
		System.out.println( "form params received: " + formParams );
		HttpHeaders responseHeaders = new HttpHeaders( );
		String metaInfo = "";
		String valueToSet = "";
		String replaceString = "metas_";
		
		metaInfo = ( formParams.get( "name" ).get( 0 ) ).replaceAll( replaceString, "" );
		valueToSet = formParams.get( "value" ).get( 0 );
		
		String MetaInfoArry[] = metaInfo.split( "_" );
		
		if ( null != MetaInfoArry && MetaInfoArry.length > 1 )
		{
			LOGGER.info( MetaInfoArry[ 0 ] + " : " + MetaInfoArry[ 1 ] + " & newValue: " + valueToSet );
			try
			{
				ehsHelper.saveMetaData( Integer.valueOf( MetaInfoArry[ 0 ] ), MetaInfoArry[ 1 ], valueToSet );
			}
			catch ( Exception e )
			{
				return new ResponseEntity< String >( e.getMessage( ), responseHeaders,
				        HttpStatus.INTERNAL_SERVER_ERROR );
			}
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/createMetaData", method = RequestMethod.POST )
	public ResponseEntity< String > createMetaData ( @RequestParam( value = "value", required = true ) String value,
	        @RequestParam( value = "type", required = true ) String type )
	{
		LOGGER.info( "invoked createUser : " );
		System.out.println( "form params received: " + value + " : " + type );
		HttpHeaders responseHeaders = new HttpHeaders( );
		try
		{
			ehsHelper.createMetaData( type, value );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param metadata
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/deleteMetaData", method = RequestMethod.POST )
	public ResponseEntity< String > deleteMetaData (
	        @RequestParam( value = "metadata", required = true ) String metadata )
	{
		LOGGER.info( "invoked deleteMetaData : " );
		System.out.println( "params received: " + metadata );
		HttpHeaders responseHeaders = new HttpHeaders( );
		try
		{
			ehsHelper.deleteMetaData( metadata );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
	/**
	 * 
	 * @param metadata
	 * @return
	 */
	@Secured(value = {"ROLE_ADMIN"})
	@RequestMapping( value = "/deleteObs", method = RequestMethod.POST )
	public ResponseEntity< String > deleteObs ( @RequestParam( value = "obsId", required = true ) String obsId )
	{
		LOGGER.info( "invoked deleteObs : " );
		System.out.println( "params received: " + obsId );
		HttpHeaders responseHeaders = new HttpHeaders( );
		try
		{
			ehsHelper.deleteObservation( Integer.valueOf( obsId.split( "_" )[ 1 ] ) );
		}
		catch ( Exception e )
		{
			return new ResponseEntity< String >( e.getMessage( ), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		return new ResponseEntity< String >( responseHeaders, HttpStatus.OK );
	}
	
}
