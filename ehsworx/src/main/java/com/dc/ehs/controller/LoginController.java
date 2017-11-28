package com.dc.ehs.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController
{
	/** Application name displayed on Log-In screen. **/
	String	appName	= "EHS";
	
	private static final Logger	LOGGER	= Logger.getLogger( LoginController.class );
	
	/**
	 * 
	 * @param error
	 *            paramter to show authentication failure message.
	 * @param logout
	 *            parameter to show success logout message.
	 * @param unauth
	 *            parameter to show authorization failure.
	 * @return login page.
	 */
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public ModelAndView login ( @RequestParam( value = "error", required = false ) String error,
	        @RequestParam( value = "logout", required = false ) String logout,
	        @RequestParam( value = "unauth", required = false ) String unauth )
	{
		LOGGER.info("invoked login" + error + "--" + unauth);
		ModelAndView model = new ModelAndView( );
		if ( error != null )
		{
			/* Customize your username/password authentication message here. */
			model.addObject( "error", "Authentication Failure : Invalid username/password!" );
		}
		
		if ( logout != null )
		{
			/* Customize your application logout message here. */
			model.addObject( "msg", "You've been logged out successfully." );
		}
		
		if ( unauth != null )
		{
			/* Customize your application logout message here. */
			model.addObject( "error", "Authorization Failure : You are not authorized to access: " + appName );
		}
		
		/* Customize your application name here. */
		model.addObject( "appName", appName );
		
		model.setViewName( "login" );
		
		return model;
		
	}
}
