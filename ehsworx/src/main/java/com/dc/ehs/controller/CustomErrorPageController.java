package com.dc.ehs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to handle custom exceptions for standard error messages.
 * 
 * @author Deepak Chaudhary
 *
 */
@Controller
public class CustomErrorPageController
{
	/**
	 * Method invoked when system generates 403 access denied errors.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping( value = "/403", method = RequestMethod.GET )
	public ModelAndView accesssDenied ( Principal user )
	{
		
		ModelAndView model = new ModelAndView( );
		
		if ( user != null )
		{
			model.addObject( "msg",
			        "     Hi " + user.getName( ) + ", you do not have permission to access this page!" );
		}
		else
		{
			model.addObject( "msg", "You do not have permission to access this page!" );
		}
		
		model.setViewName( "403" );
		return model;
		
	}
	
	/**
	 * Generic error handler
	 * 
	 * @param httpRequest
	 * @return
	 */
	@RequestMapping( value = "errors", method =
	{ RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView renderErrorPage ( HttpServletRequest httpRequest )
	{
		
		ModelAndView errorPage = new ModelAndView( "errorPage" );
		String errorMsg = "";
		int httpErrorCode = getErrorCode( httpRequest );
		
		switch ( httpErrorCode )
		{
			case 400:
			{
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401:
			{
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 404:
			{
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			case 500:
			{
				errorMsg = "Http Error Code: 500. Internal Server Error:<br><br><i>"
				        + httpRequest.getAttribute( "javax.servlet.error.exception" ) + "</i>";
				break;
			}
		}
		errorPage.addObject( "errorMsg", errorMsg );
		/*
		 * TODO - might consider sending email notification to admin about error
		 * occured.
		 */
		return errorPage;
	}
	
	private int getErrorCode ( HttpServletRequest httpRequest )
	{
		return ( Integer ) httpRequest.getAttribute( "javax.servlet.error.status_code" );
	}
}
