/**
 * @(#)TransactionInterceptor.java
 *
 * @author huawei
 * @version 1.0 2015-4-17
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.datasource;

import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * Purpose: 根据方法名动态选择读写数据源
 * 
 * @see	    
 * @since   1.1.0
 */
public class TransactionInterceptor extends org.springframework.transaction.interceptor.TransactionInterceptor
{
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        for(String prefix : prefixs)
		{
            if(invocation.getMethod().getName().startsWith(prefix))
            {
                DataSourceSwitcher.setWrite();  
                return super.invoke(invocation);
            }
        }
        DataSourceSwitcher.setRead();
        return super.invoke(invocation);
    }
    
    public Set<String> getPrefixs()
    {
        return prefixs;
    }
    public void setPrefixs(Set<String> prefixs)
    {
        this.prefixs = prefixs;
    }
    private Set<String> prefixs = new HashSet<String>();
}


/**
 * $Log: TransactionInterceptor.java,v $
 * 
 * @version 1.0 2015-4-17 
 *
 */