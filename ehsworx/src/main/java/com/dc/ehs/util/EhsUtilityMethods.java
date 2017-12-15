package com.dc.ehs.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.common.EmailService;

/**
 * This class contains utility methods for use by application.
 * 
 * @author Deepak Chaudhary
 *
 */
public class EhsUtilityMethods
{
	private static final Logger	LOGGER	= Logger.getLogger( EhsUtilityMethods.class );
	
	@Autowired
	EmailService				emailService;
	
	/** path to upload docs on server **/
	@Value( "${app.doc_path}" )
	String						doc_path;
	
	/**
	 * This utility method returns current logged-in user username.
	 * 
	 * @return
	 */
	public String getLoggedInUser ( )
	{
		String currentAuthUser = "";
		Authentication authentication = SecurityContextHolder.getContext( ).getAuthentication( );
		if ( ! ( authentication instanceof AnonymousAuthenticationToken ) )
		{
			currentAuthUser = authentication.getName( );
		}
		return currentAuthUser;
	}
	
	/**
	 * This utility method checks if logged-in user has a particular role.
	 * 
	 * @param role
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	public boolean hasRole ( String role )
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
	
	/**
	 * This utility method uploads file to predefined locations.
	 * 
	 * @param multipartFile
	 * @throws IOException
	 */
	public void uploadFile ( MultipartFile multipartFile ) throws IOException
	{
		MultipartFile _multipartFile = multipartFile;
		LOGGER.info( "--" + _multipartFile );
		LOGGER.info( "---" + _multipartFile.getOriginalFilename( ) );
		if ( null != _multipartFile && null != _multipartFile.getOriginalFilename( )
		        && !_multipartFile.getOriginalFilename( ).trim( ).isEmpty( ) )
			FileCopyUtils.copy( _multipartFile.getBytes( ),
			        new File( doc_path + multipartFile.getOriginalFilename( ) ) );
	}
}
