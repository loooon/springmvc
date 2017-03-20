/**
 * @(#)DataSourceSwitcher.java
 *
 * @author huawei
 * @version 1.0 2015-4-14
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.datasource;

import org.springframework.util.Assert;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class DataSourceSwitcher 
{
    private static final ThreadLocal contextHolder = new ThreadLocal();
  
    @SuppressWarnings("unchecked")
    public static void setDataSource(String dataSource)
	{
        Assert.notNull(dataSource, "dataSource cannot be null");
        contextHolder.set(dataSource);  
    }  
  
    public static void setWrite()
	{
        clearDataSource();
        setDataSource("write");
    }  
      
    public static void setRead() 
	{
        setDataSource("read");  
    }  
      
    public static String getDataSource()
	{
        return (String) contextHolder.get();
    }  
  
    public static void clearDataSource() 
	{
        contextHolder.remove();  
    }  
}  


/**
 * $Log: DataSourceSwitcher.java,v $
 * 
 * @version 1.0 2015-4-14 
 *
 */