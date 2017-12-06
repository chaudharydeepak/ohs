package com.dc.ehs.service;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.entity.Observation;


public interface ObservationCRUDService
{
	public int saveObservation( Observation observation) throws MessagingException, ParseException;
	
	public Observation loadObservation( int observationId, String loggedInUser );
	
	public List<Observation> loadAllObservations( String username);
	
	public String setStatusOnObservation( int observationId, String status, String user, String actionComments, MultipartFile multipartFile);
	
	public String deleteObservation( int obsId, String mdfdby );
	
	public String fetchRespManagerForObs( int obsID);
	
	public String fetchStatusForObs ( int obsID );
}
