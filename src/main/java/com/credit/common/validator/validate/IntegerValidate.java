/**
 * @(#)IntegerValidate.java
 *
 * @author huawei
 * @version 1.0 2015-1-27
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator.validate;


import com.credit.common.validator.AbstractValidator;
import com.credit.common.validator.ValidateException;

/**
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
public class IntegerValidate extends AbstractValidator
{
    public IntegerValidate()
    {
        setParent(new NotNullValidate("不是数字类型！"));
        setValidateMessage("不是数字类型！");
    }

    public IntegerValidate(String message, String errorPath)
    {
        setParent(new NotNullValidate("不是数字类型！"));
        setValidateMessage(message);
        setErrorPath(errorPath);
    }

    @Override
    public boolean doValidate(Object object) throws ValidateException
    {
        if (hasParent() && getParent().doValidate(object))
        {
            try
            {
                Integer.parseInt(object.toString());
                return true;
            }
            catch (RuntimeException ex)
            {
                throw new ValidateException(getValidateMessage(), getErrorPath());
            }
        }
        return false;
    }

}

/**
 * $Log: IntegerValidate.java,v $
 * 
 * @version 1.0 2015-1-27
 */