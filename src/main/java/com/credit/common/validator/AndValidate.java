/**
 * @(#)AndValidate.java
 *
 * @author huawei
 * @version 1.0 2015-3-9
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
public class AndValidate extends AbstractValidator
{
    private List<IValidator> validates = new ArrayList<IValidator>();

    public AndValidate(IValidator validator, IValidator validator2)
    {
        this.validates.add(validator);
        this.validates.add(validator2);
    }

    public AndValidate and(IValidator validator)
    {
        this.validates.add(validator);
        return this;
    }

    public boolean doValidate(Object object) throws ValidateException
    {
        for (IValidator validator : validates)
        {
            if (!validator.doValidate(object))
                return false;
        }
        return true;
    }
}

/**
 * $Log: AndValidate.java,v $
 * 
 * @version 1.0 2015-3-9
 */