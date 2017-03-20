/**
 * @(#)ListJsonConverter.java
 *
 * @author huawei
 * @version 1.0 2015-1-26
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.converter;


import com.alibaba.fastjson.JSON;
import com.credit.common.validator.AbstractConverter;

/**
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
public class ListJsonConverter<T> extends AbstractConverter
{
    private Class<T> beanClass;

    public ListJsonConverter(Class<T> clazz)
    {
        beanClass = clazz;
    }

    public Object convert(Object value)
    {
        if (value == null)
            return null;
        if (value instanceof String)
        {
            return JSON.parseArray(value.toString(), beanClass);
        }
        return null;
    }

}

/**
 * $Log: ListJsonConverter.java,v $
 * 
 * @version 1.0 2015-1-26
 */