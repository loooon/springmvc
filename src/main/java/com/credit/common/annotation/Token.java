/**
 * @(#)Token.java
 *
 * @author huawei
 * @version 1.0 2015-4-14
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token
{
    String name();
    String path();
    String message() default "不能重复提交表单！";
}


/**
 * $Log: Token.java,v $
 * 
 * @version 1.0 2015-4-14 
 *
 */