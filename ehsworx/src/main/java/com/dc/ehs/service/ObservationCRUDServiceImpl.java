package com.dc.ehs.service;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dc.ehs.dao.ObservationCRUDDAO;
import com.dc.ehs.entity.Observation;

@Transactional
public class ObservationCRUDServiceImpl implements ObservationCRUDService
{
	
	@Autowired
	ObservationCRUDDAO obsCRUDdao;
	
	public int saveObservation(Observation observation) throws MessagingException, ParseException
	{
		
		return obsCRUDdao.saveObservation(observation);
	}

	public Observation loadObservation(int observationId)
	{
		
		return obsCRUDdao.loadObservation(observationId);
	}

	public List<Observation> loadAllObservations( String username )
	{
		return obsCRUDdao.loadAllObservations( username );
	}
	
	public String setStatusOnObservation( int observationId, String status, String user)
	{
		return obsCRUDdao.setStatusOnObservation(observationId, status, user);
	}
	
	public String deleteObservation( int obsId, String mdfdby )
	{
		return obsCRUDdao.deleteObservation( obsId, mdfdby );
	}

}
