package com.dc.ehs.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dc.ehs.entity.MetaData;
import com.dc.ehs.mapper.MetaDataMapper;
import com.dc.ehs.mapper.MetaDataObjectMapper;

/**
 * Implementation of Interface LoadMetadataDAO interface.
 * @author Deepak Chaudhary
 *
 */
public class LoadMetadataDAOImpl implements LoadMetadataDAO
{
	private static final Logger			LOGGER	= Logger.getLogger( LoadMetadataDAOImpl.class );
	
	private JdbcTemplate				jdbcTemplate;
	private NamedParameterJdbcTemplate	namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource ( DataSource dataSource )
	{
		this.jdbcTemplate = new JdbcTemplate( dataSource );
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dc.ehs.dao.LoadMetadataDAO#loadObersvationsMetaData()
	 */
	@Override
	public HashMap< String, List< String > > loadObersvationsMetaData ( )
	{
		LOGGER.info( "invoked loadObersvationsMetaData" );
		HashMap< String, List< String > > metaDataMap = new HashMap< String, List< String > >( );
		String _sql = "select meta_value from app.obervationsMetadata where meta_key='location' and active = true";
		metaDataMap.put( "location", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select meta_value from app.obervationsMetadata where meta_key='department' and active = true";
		metaDataMap.put( "department", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select meta_value from app.obervationsMetadata where meta_key='who_observed' and active = true";
		metaDataMap.put( "who_observed", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select meta_value from app.obervationsMetadata where meta_key='type_of_observation' and active = true";
		metaDataMap.put( "type_of_observation", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select meta_value from app.obervationsMetadata where meta_key='classification' and active = true";
		metaDataMap.put( "classification", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select meta_value from app.obervationsMetadata where meta_key='areas' and active = true";
		metaDataMap.put( "areas", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		_sql = "select first_name || ' ' || last_name || '(' || p.username || ')' as meta_value from app.EHS_SECURITY_USERPROFILE p ,\n"
		        + "app.EHS_SECURITY_USERAUTHORITY a where p.username = a.username\n" + "and p.active= true";
		metaDataMap.put( "responsibleUser", jdbcTemplate.query( _sql, new MetaDataMapper( ) ) );
		
		return metaDataMap;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dc.ehs.dao.LoadMetadataDAO#loadMetaDataForEdit()
	 */
	@Override
	public List< MetaData > loadMetaDataForEdit ( )
	{
		String _sql = "select * from app.obervationsMetadata where active = true order by meta_key";
		return jdbcTemplate.query( _sql, new MetaDataObjectMapper( ) );
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dc.ehs.dao.LoadMetadataDAO#saveMetaData(int, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveMetaData ( int id, String metaType, String metaValue )
	{
		String _sql = "update app.obervationsMetadata set meta_value=:metaValue where meta_key=:metaType and id=:id";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "metaValue", metaValue );
		namedParameters.put( "metaType", metaType );
		namedParameters.put( "id", id );
		namedParameterJdbcTemplate.update( _sql, namedParameters );
		return "success";
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dc.ehs.dao.LoadMetadataDAO#createMetaData(java.lang.String, java.lang.String)
	 */
	@Override
	public String createMetaData ( String metaType, String metaValue )
	{
		String fetchCurrentID = "select case when max(id) is null then 1 else max(id) end from APP.obervationsMetadata";
		int meta_id = jdbcTemplate.queryForObject( fetchCurrentID, Integer.class );
		meta_id = meta_id + 1;
		String _sql = "insert into app.obervationsMetadata values (:id, :metaType, :metaValue, true)";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		namedParameters.put( "id", meta_id );
		namedParameters.put( "metaType", metaType );
		namedParameters.put( "metaValue", metaValue );
		namedParameterJdbcTemplate.update( _sql, namedParameters );
		return "success";
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.dc.ehs.dao.LoadMetadataDAO#deleteMetaData(java.lang.String, java.lang.String)
	 */
	@Override
	public String deleteMetaData ( String metaData, String modfdBy )
	{
		List< String > metaDataList = new ArrayList< String >( Arrays.asList( metaData.split( "," ) ) );
		String _SQL4Meta = "update app.obervationsMetadata set active=false where meta_key=:meta_key and id=:id";
		Map< String, Object > namedParameters = new HashMap< String, Object >( );
		metaDataList.forEach( metaobj -> {
			String meta[] = metaobj.split( "_" );
			namedParameters.put( "id", meta[ 0 ] );
			namedParameters.put( "meta_key", meta[ 1 ] );
			namedParameterJdbcTemplate.update( _SQL4Meta, namedParameters );
		} );
		return "success";
	}
}
