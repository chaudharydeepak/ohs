package com.dc.ehs.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.dc.ehs.entity.MetaData;

public interface LoadMetadataDAO
{
	/**
	 * Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
	public HashMap<String, List<String>> loadObersvationsMetaData ( ) ;
	
	public List< MetaData > loadMetaDataForEdit ( );
	
	public String saveMetaData( int id , String metaType, String metaValue);
	
	public String createMetaData ( String metaType, String metaValue );
	
	public String deleteMetaData ( String metaData, String modfdBy );
}
