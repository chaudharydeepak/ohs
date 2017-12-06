package com.dc.ehs.service;

import java.util.HashMap;
import java.util.List;

import com.dc.ehs.entity.MetaData;

public interface LoadObservationMetadataService
{
	public HashMap<String, List<String>> loadObersvationsMetaData();
	
	public List< MetaData > loadMetaDataForEdit ( );
	
	public String saveMetaData( int id , String metaType, String metaValue);
	
	public String createMetaData ( String metaType, String metaValue );
	
	public String deleteMetaData ( String metaData, String modfdBy );
}
