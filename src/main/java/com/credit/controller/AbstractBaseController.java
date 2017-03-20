package com.credit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.common.util.servlet.RequestUtil;
import com.credit.common.web.session.UserSession;
import com.credit.common.web.session.UserSessionFactory;

/**
 * Created by Michael Chan on 3/8/2017.
 */
public abstract class AbstractBaseController extends AbstractController
{

    protected Logger logger = null;

    public AbstractBaseController()
    {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        return null;
    }


    @ExceptionHandler
    public String exp(HttpServletRequest request, HttpServletResponse response, Exception ex)
    {
        logger.error("error", ex);
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestType))
        {
            printAjaxResult(response);
        }
        return "500";
    }

    private void printAjaxResult(HttpServletResponse response)
    {
        PrintWriter writer = null;
        try
        {
            writer = response.getWriter();

            writer.write(JSON.toJSONString(new ResultJson().error()));
        }
        catch (IOException e)
        {
			logger.error("error",e);
        }
        finally
        {
            if (writer != null)
                IOUtils.closeQuietly(writer);
        }
    }
}
