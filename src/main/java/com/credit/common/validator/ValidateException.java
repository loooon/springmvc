/**
 * @(#)ValidateException.java
 *
 * @author huawei
 * @version 1.0 2015-2-2
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class ValidateException extends Exception
{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ValidateException.class);

    protected int errorCode = 0;
    private String errorPath;
    
    public ValidateException(int errorCode, String errorString)
    {
        super(errorString);
        this.errorCode = errorCode;
    }
    
    public ValidateException(int errorCode, String errorString, Throwable cause)
    {
        super(errorString, cause);
        this.errorCode = errorCode;
    }
    
    public ValidateException(String errorString, String errorPath, Throwable cause)
    {
        super(errorString, cause);
        this.errorPath = errorPath;
    }

    public ValidateException(String errorString, String path)
    {
        super(errorString);
        this.errorPath = path;
    }
    public ValidateException(String errorString)
    {
        super(errorString);
    }
    
    public ValidateException(String errorString, Throwable cause)
    {
        super(errorString, cause);
    }

    public int getErrorCode() 
    {
        return this.errorCode;
    }

    public String getErrorString()
    {
        return this.getMessage();
    }

    public String getErrorPath()
    {
        return errorPath;
    }

    public void setErrorPath(String errorPath)
    {
        this.errorPath = errorPath;
    }
    
}

/**
 * $Log: ValidateException.java,v $
 * 
 * @version 1.0 2015-2-2 
 *
 */