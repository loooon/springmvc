/**
 * @(#)ListIntegerConverter.java
 *
 * @author huawei
 * @version 1.0 2015-2-6
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.converter;

import java.util.Arrays;

import org.apache.commons.beanutils.ConversionException;

import com.credit.common.validator.AbstractConverter;


/**
 * Purpose:list的转换
 * 
 * @see
 * @since 1.1.0
 */
public class ListConverter extends AbstractConverter
{

    public Object convert(Object value)
    {
        if (value == null)
            return null;
        String stringArray[] = new String[0];
        if (value.getClass().isArray() && value.getClass() == stringArray.getClass())
        {
            stringArray = (String[]) value;
            return Arrays.asList(stringArray);
        }
        throw new ConversionException(value.toString() + "转换错误！");
    }

}

/**
 * $Log: ListIntegerConverter.java,v $
 * 
 * @version 1.0 2015-2-6
 */