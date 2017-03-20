/**
 * @(#)OrValidate.java
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
public class OrValidate extends AbstractValidator
{
    private List<IValidator> validates = new ArrayList<IValidator>();

    public OrValidate(IValidator validator, IValidator validator2)
    {
        this.validates.add(validator);
        this.validates.add(validator2);
    }

    public OrValidate or(IValidator validator)
    {
        this.validates.add(validator);
        return this;
    }

    public boolean doValidate(Object object) throws ValidateException
    {
        for (IValidator validator : validates)
        {
            if (validator.doValidate(object))
                return true;
        }
        if (validates.isEmpty())
            return true;
        return false;
    }
}

/**
 * $Log: OrValidate.java,v $
 * 
 * @version 1.0 2015-3-9
 */