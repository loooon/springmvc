/**
 * @(#)RequestTokenException.java
 *
 * @author huawei
 * @version 1.0 2015-2-13
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit.common.validator.ValidateException;


/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class RequestTokenException extends ValidateException
{
    private static final Logger logger = LoggerFactory.getLogger(RequestTokenException.class);
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public RequestTokenException(int errorCode, String errorString)
    {
        super(errorString);
        this.errorCode = errorCode;
        
        logger.error(String.format("RequestTokenException: errorCode=%d, errorString=%s", errorCode, errorString), this);
    }
    
    public RequestTokenException(int errorCode, String errorString, Throwable cause)
    {
        super(errorString, cause);
        this.errorCode = errorCode;
        logger.error(String.format("RequestTokenException: errorCode=%d, errorString=%s", errorCode, errorString), this);
    }

    public RequestTokenException(String errorString, String path)
    {
        super(errorString);
        setErrorPath(path);
        logger.error(String.format("RequestTokenException: errorString=%s", errorCode, errorString), this);
    }
    public RequestTokenException(String errorString)
    {
        super(errorString);
        logger.error(String.format("RequestTokenException: errorString=%s", errorCode, errorString), this);
    }
}


/**
 * $Log: RequestTokenException.java,v $
 * 
 * @version 1.0 2015-2-13 
 *
 */