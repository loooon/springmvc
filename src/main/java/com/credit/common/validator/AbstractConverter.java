package com.credit.common.validator;

public abstract class AbstractConverter implements IConverter
{
    public Object convert(Class type, Object value)
    {
        return convert(value);
    }
}
