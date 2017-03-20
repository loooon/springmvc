package com.credit.common.web.servlet.filter;

import com.credit.common.web.servlet.XssHttpServletRequestWrapper;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;



public class XssFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {

    }
}
