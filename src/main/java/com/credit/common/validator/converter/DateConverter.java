/**
 * @(#)DataConverter.java
 *
 * @author huawei
 * @version 1.0 2015-1-26
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;

import com.credit.common.validator.AbstractConverter;


/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class DateConverter extends AbstractConverter
{
    private Date defaultValue;
    private boolean useDefault;
    private static final String format ="yyyy-MM-dd HH:mm:ss";
    private String formatStr;
    private boolean isFormat;
    public DateConverter(){}
    
    public DateConverter(Date defaultValue)
    {
        this.defaultValue = defaultValue;
        this.useDefault = true;
    }
    public DateConverter(Date defaultValue, String formatStr)
    {
        this.defaultValue = defaultValue;
        this.useDefault = true;
        this.isFormat = true;
        this.formatStr = formatStr;
    }

    public Object convert(Object value)
    {

        if(value == null)
            if(useDefault)
                return this.defaultValue;
        if(value instanceof Date)
            return value;
        if(value instanceof String)
        {
            try
            {
                return format(value,isFormat);
            }
            catch (ParseException e)
            {
                throw new ConversionException(value.toString(), e);
            }
        }
        return null;
    }
    
    private Date format(Object value, Boolean isFormat) throws ParseException
	{
        SimpleDateFormat dateFormat = new SimpleDateFormat(isFormat ? formatStr : format);
        return dateFormat.parse(value.toString());
    }
    
}


/**
 * $Log: DataConverter.java,v $
 * 
 * @version 1.0 2015-1-26 
 *
 */