package com.dc.ehs.dao;

import java.util.List;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import com.dc.ehs.entity.Observation;

public interface ObservationCRUDDAO
{

	/**
	 * Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
    public int saveObservation( Observation observation) throws MessagingException;
	
	public Observation loadObservation( int observationId );
	
	public List<Observation> loadAllObservations( String username );
	
	public String setStatusOnObservation( int observationId, String status, String user);
	
}
