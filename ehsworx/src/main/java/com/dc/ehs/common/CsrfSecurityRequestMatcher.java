package com.dc.ehs.common;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher
{
	private Pattern				allowedMethods		= Pattern.compile( "^(GET|HEAD|TRACE|OPTIONS|POST|PUT)$" );
	private RegexRequestMatcher	unprotectedMatcher	= new RegexRequestMatcher( "/manageuser", null );
	
	@Override
	public boolean matches ( HttpServletRequest request )
	{
		if ( allowedMethods.matcher( request.getMethod( ) ).matches( ) )
		{
			return false;
		}
		return !unprotectedMatcher.matches( request );
	}
	
}
