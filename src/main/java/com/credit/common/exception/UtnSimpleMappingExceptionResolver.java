/**
 * @(#)UtnSimpleMappingExceptionResolver.java
 *
 * @author huawei
 * @version 1.0 2015-4-15
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 
 * Purpose:
 * 
 * @see	    
 * @since   1.1.0
 */
public class UtnSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver
{
    private static final Logger logger = LoggerFactory.getLogger(UtnSimpleMappingExceptionResolver.class);
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        logger.error(ex.getMessage(),ex);
        logger.error(handler.toString());
        logger.error(request.getRequestURI());
        return new ModelAndView("500");
    }
    public static void main(String[] args)
    {
            String url = "http://127.0.0.1:8080/ubd_local/domain/tomodify/0";
            String site ="http://127.0.0.1:8080/ubd_local";
            String s = url.replace(site, "");
            System.out.println(s);
    }

}


/**
 * $Log: UtnSimpleMappingExceptionResolver.java,v $
 * 
 * @version 1.0 2015-4-15 
 *
 */