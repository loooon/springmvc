/**
 * @(#)DynamicDataSource.java
 *
 * @author huawei
 * @version 1.0 2015-4-14
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    
    @Override
    protected Object determineCurrentLookupKey()
	{
        return DataSourceSwitcher.getDataSource();  
    }  
  
}


/**
 * $Log: DynamicDataSource.java,v $
 * 
 * @version 1.0 2015-4-14 
 *
 */