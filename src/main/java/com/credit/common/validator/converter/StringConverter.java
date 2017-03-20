package com.credit.common.validator.converter;


import com.credit.common.validator.AbstractConverter;

public class StringConverter extends AbstractConverter
{
    private String defaultValue;

    private boolean useDefault;

    public StringConverter()
    {
    }

    public StringConverter(String defaultValue)
    {
        this.defaultValue = defaultValue;
        this.useDefault = true;
    }

    public Object convert(Object value)
    {
        if (value == null)
            if (useDefault)
                return this.defaultValue;
        return value.toString();
    }
}
