package com.dc.ehs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EHSExceptionController
{
	@ExceptionHandler( Exception.class )
	public ModelAndView myError ( Exception exception )
	{
		System.out.println( "----Caught Exception----" );
		ModelAndView mav = new ModelAndView( );
		mav.addObject( "exc", exception );
		//mav.addObject( "exc", exception.getStackTrace( ) );
		mav.setViewName( "customerror" );
		return mav;
	}
}
