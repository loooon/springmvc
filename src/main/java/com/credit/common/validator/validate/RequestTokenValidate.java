/**
 * @(#)TokenValidate.java
 *
 * @author huawei
 * @version 1.0 2015-1-29
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.validate;

import javax.servlet.http.HttpServletRequest;

import com.credit.common.validator.AbstractValidator;
import com.credit.common.validator.TokenProcessor;
import com.credit.common.validator.ValidateException;


/**
 * Purpose: token标志验证，一个token只能使用一次
 * 
 * @see
 * @since 1.1.0
 */
public class RequestTokenValidate extends AbstractValidator
{
    private HttpServletRequest request;

    private String tokenName;

    private boolean reset;

    public RequestTokenValidate(HttpServletRequest request, String tokenName, String message)
    {
        this(request, tokenName, message, null, true);

    }

    public RequestTokenValidate(HttpServletRequest request, String tokenName, String message, String errorPath, boolean reset)
    {
        this.request = request;
        this.tokenName = tokenName;
        this.reset = reset;
        setValidateMessage(message);
        setErrorPath(errorPath);
    }

    public boolean doValidate(Object object) throws ValidateException
    {
        if (object == null)
            throw new RequestTokenException(getValidateMessage(), getErrorPath());
        if (!TokenProcessor.getInstance().isTokenValid(request, reset, tokenName))
        {
            throw new RequestTokenException(getValidateMessage(), getErrorPath());
        }
        return true;
    }

}

/**
 * $Log: TokenValidate.java,v $
 * 
 * @version 1.0 2015-1-29
 */