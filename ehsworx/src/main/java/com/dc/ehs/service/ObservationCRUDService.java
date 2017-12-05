package com.dc.ehs.service;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import com.dc.ehs.entity.Observation;


public interface ObservationCRUDService
{
	public int saveObservation( Observation observation) throws MessagingException, ParseException;
	
	public Observation loadObservation( int observationId );
	
	public List<Observation> loadAllObservations( String username);
	
	public String setStatusOnObservation( int observationId, String status, String user);
	
	public String deleteObservation( int obsId, String mdfdby );
}
