/**
 * @(#)WebsiteValidate.java
 *
 * @author huawei
 * @version 1.0 2015-1-26
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.validate;

import com.credit.common.validator.AbstractValidator;
import com.credit.common.validator.IValidator;
import com.credit.common.validator.ValidateException;

/**
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
public class NotNullValidate extends AbstractValidator
{

    public NotNullValidate()
    {
        setValidateMessage("数据不能为空!");
    }

    public NotNullValidate(IValidator parent)
    {
        setParent(parent);
    }

    public NotNullValidate(IValidator parent, IValidator next)
    {
        setParent(parent);
        setNext(next);
    }

    public NotNullValidate(String validateMessage)
    {
        setValidateMessage(validateMessage);
    }

    public NotNullValidate(String message, String errorPath)
    {
        setValidateMessage(message);
        setErrorPath(errorPath);
    }

    public boolean doValidate(Object object) throws ValidateException
    {
        if (object == null)
            throw new ValidateException(getValidateMessage(), getErrorPath());
        if (hasNext())
            return getNext().doValidate(object);
        return true;
    }
}

/**
 * $Log: WebsiteValidate.java,v $
 * 
 * @version 1.0 2015-1-26
 */