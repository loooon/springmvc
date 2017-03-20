package com.credit.common.validator.validate;///**
// * @(#)StringValidate.java
// *
// * @author huawei
// * @version 1.0 2015-1-30
// *
// * Copyright (C) 2012,2015 , PING' AN, Inc.
// */
//package utn.app.validator.validate;
//
//import org.apache.commons.lang.StringUtils;
//
//import utn.app.validator.AbstractValidator;
//import utn.app.validator.ValidateException;
//
///**
// * 
// * Purpose:
// * 
// * @see	    
// * @since   1.1.0
// */
//public class StringNotEmptyValidate extends AbstractValidator
//{
//    public StringNotEmptyValidate(){}
//    public StringNotEmptyValidate(String message){
//        setValidateMessage(message);
//    }
//    public StringNotEmptyValidate(String message,String errorPath){
//        setValidateMessage(message);
//        setErrorPath(errorPath);
//    }
//    public boolean doValidate(Object object) throws ValidateException
//    {
//        if(object == null)
//            throw new ValidateException(getValidateMessage(),getErrorPath());
//        if(object instanceof String)
//            if(StringUtils.isBlank(object.toString()))
//                throw new ValidateException(getValidateMessage(),getErrorPath());
//            else
//                return true;
//       throw new ValidateException(getValidateMessage(),getErrorPath());
//    }
//}
//
//
///**
// * $Log: StringValidate.java,v $
// * 
// * @version 1.0 2015-1-30 
// *
// */