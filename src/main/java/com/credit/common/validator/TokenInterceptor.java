/**
 * @(#)TokenInterceptor.java
 *
 * @author huawei
 * @version 1.0 2015-4-14
 *
 * Copyright (C) 2012,2015 , PING' AN, Inc.
 */
package com.credit.common.validator;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.credit.common.annotation.Token;
import com.credit.common.validator.validate.RequestTokenException;


/**
 * Purpose: 防止表单重复提交
 * 
 * @see
 * @since 1.1.0
 */
public class TokenInterceptor extends HandlerInterceptorAdapter
{
    protected static Logger logger = null;

    public TokenInterceptor()
    {
        logger = LoggerFactory.getLogger(TokenInterceptor.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token token = method.getAnnotation(Token.class);
            if (token != null)
            {
                if (!isRepeatSubmit(request, token))
                {
                    logger.error("无效的token！");
                    throw new RequestTokenException(token.message(), token.path());
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request, Token token)
    {
        return TokenProcessor.getInstance().isTokenValid(request, true, token.name());
    }
}

/**
 * $Log: TokenInterceptor.java,v $
 * 
 * @version 1.0 2015-4-14
 */