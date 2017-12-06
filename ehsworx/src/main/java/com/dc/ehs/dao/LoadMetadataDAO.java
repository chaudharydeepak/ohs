package com.dc.ehs.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.dc.ehs.entity.MetaData;

/**
 * interface to handle all meta data load activities.
 * @author chaudhde
 *
 */
public interface LoadMetadataDAO
{
	/**
	 * interface method to Set Data Source.
	 */
	public void setDataSource ( DataSource dataSource );
	
	/**
	 * interface method to load observations metadata.
	 * @return
	 */
	public HashMap<String, List<String>> loadObersvationsMetaData ( ) ;
	
	/**
	 * interface method to load observations metadata for editing.
	 * @return
	 */
	public List< MetaData > loadMetaDataForEdit ( );
	
	/**
	 * interface method to Save metadata into the system.
	 * @param id
	 * @param metaType
	 * @param metaValue
	 * @return
	 */
	public String saveMetaData( int id , String metaType, String metaValue);
	
	/**
	 * Create new metadata line items in the system.
	 * @param metaType
	 * @param metaValue
	 * @return
	 */
	public String createMetaData ( String metaType, String metaValue );
	
	/**
	 * interface method to delete an individual metadata from the system.
	 * @param metaData
	 * @param modfdBy
	 * @return
	 */
	public String deleteMetaData ( String metaData, String modfdBy );
}
