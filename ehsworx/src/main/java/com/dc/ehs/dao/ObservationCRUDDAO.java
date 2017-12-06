package com.dc.ehs.dao;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.entity.Observation;

/**
 * Interface to handle observation operations.
 * 
 * @author chaudhde
 *
 */
public interface ObservationCRUDDAO
{
	
	/**
	 * Interface to Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
	/**
	 * Interface to save observation into the system.
	 * 
	 * @param observation
	 * @return
	 * @throws MessagingException
	 * @throws ParseException
	 */
	public int saveObservation ( Observation observation ) throws MessagingException, ParseException;
	
	/**
	 * Interface to load an observation from the system.
	 * 
	 * @param observationId
	 * @param loggedInUser
	 * @return
	 */
	public Observation loadObservation ( int observationId, String loggedInUser );
	
	/**
	 * Interface to load observations in the system.
	 * 
	 * @param username
	 * @return
	 */
	public List< Observation > loadAllObservations ( String username );
	
	/**
	 * Interface to set status for an observation into the system.
	 * 
	 * @param observationId
	 * @param status
	 * @param user
	 * @param actionComments
	 * @param multipartFile
	 * @return
	 */
	public String setStatusOnObservation ( int observationId, String status, String user, String actionComments,
	        MultipartFile multipartFile );
	
	/**
	 * Interface to delete an observation from the system. soft-deletion only.
	 * 
	 * @param obsId
	 * @param mdfdby
	 * @return
	 */
	public String deleteObservation ( int obsId, String mdfdby );
	
	/**
	 * Interface to load assigned responsible manager for an observation from
	 * the system.
	 * 
	 * @param obsID
	 * @return
	 */
	public String fetchRespManagerForObs ( int obsID );
	
	/**
	 * Interface to fetch status of an observation in the system.
	 * 
	 * @param obsID
	 * @return
	 */
	public String fetchStatusForObs ( int obsID );
}
