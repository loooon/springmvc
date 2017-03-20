package com.credit.common.validator;

import org.apache.commons.beanutils.Converter;

public interface IConverter extends Converter
{
	   Object convert(Object value);
}
