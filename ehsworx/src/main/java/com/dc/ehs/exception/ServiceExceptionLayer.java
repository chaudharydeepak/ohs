package com.dc.ehs.exception;

import org.apache.log4j.Logger;

/**
 * This is my service layer exception.
 * 
 * @author chaudhde
 * 
 */
public class ServiceExceptionLayer extends RuntimeException
{
	static final Logger	logger	= Logger.getLogger( ServiceExceptionLayer.class );
	
	private static final long	serialVersionUID	= 1L;
	
	private String	          exceptionMsg;
	
	private String	          calleClass;
	
	private String	          calleMethod;
	
	public ServiceExceptionLayer ( String exceptionMsg, String calleClass, String calleMethod )
	{
		logger.info( "######################" );
		logger.info( "invoked ServiceExceptionLayer:" );
		logger.info( "exceptionMsg:" + exceptionMsg );
		logger.info( "calleClass:" + calleClass );
		logger.info( "calleMethod:" + calleMethod );
		logger.info( "######################" );
		this.exceptionMsg = exceptionMsg;
		this.calleClass = calleClass;
		this.calleMethod = calleMethod;
	}
	
	public ServiceExceptionLayer ( String exceptionMsg )
	{
		logger.info( "**********************" );
		logger.info( "**********************" );
		this.exceptionMsg = exceptionMsg;
	}
	
	public String getExceptionMsg ( )
	{
		return exceptionMsg;
	}
	
	public void setExceptionMsg ( String exceptionMsg )
	{
		this.exceptionMsg = exceptionMsg;
	}
	
	public String getCalleClass ( )
	{
		return calleClass;
	}
	
	public void setCalleClass ( String calleClass )
	{
		this.calleClass = calleClass;
	}
	
	public String getCalleMethod ( )
	{
		return calleMethod;
	}
	
	public void setCalleMethod ( String calleMethod )
	{
		this.calleMethod = calleMethod;
	}
	
}
