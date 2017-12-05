package com.dc.ehs.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dc.ehs.dao.LoadMetadataDAO;
import com.dc.ehs.entity.MetaData;
import com.dc.ehs.exception.ServiceExceptionLayer;

@Transactional
public class LoadObservationMetadataServiceImpl implements LoadObservationMetadataService
{
	@Autowired
	LoadMetadataDAO loadMetadao;

	public HashMap<String, List<String>> loadObersvationsMetaData() throws ServiceExceptionLayer
	{
		return loadMetadao.loadObersvationsMetaData();
	}

	public List< MetaData > loadMetaDataForEdit ( )
	{
		return loadMetadao.loadMetaDataForEdit( );
	}
	
	public String saveMetaData( int id , String metaType, String metaValue)
	{
		return loadMetadao.saveMetaData( id, metaType, metaValue );
	}
	
	public String createMetaData ( String metaType, String metaValue )
	{
		return loadMetadao.createMetaData( metaType, metaValue );
	}
	
	public String deleteMetaData ( String metaData, String modfdBy )
	{
		return loadMetadao.deleteMetaData(metaData, modfdBy);
	}

}
