package com.credit.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.credit.common.result.ResultJson;
import com.credit.common.util.servlet.RequestUtil;
import com.credit.common.web.session.UserSession;
import com.credit.common.web.session.UserSessionFactory;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter
{
	public static final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);

	/**
	 * 不做登陆校验list集合
	 */
	private List<String> unCheckUrlList;

	public List<String> getUnCheckUrlList()
	{
		return unCheckUrlList;
	}

	public void setUnCheckUrlList(List<String> unCheckUrlList)
	{
		this.unCheckUrlList = unCheckUrlList;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (unCheckUrlList != null && unCheckUrlList.size() > 0)
		{
//			String uri = request.getRequestURI();
			String uri = request.getServletPath();
			if (request.getPathInfo() != null)
			{
				uri = uri + request.getPathInfo();
			}
			logger.debug("request uri : {}", uri);
			if (!unCheckUrlList.contains(uri))
			{
				UserSession userSession = UserSessionFactory.getUserSession(request);
				if (userSession == null)
				{
					if (RequestUtil.isXhr(request))
					{
						printNoLoginResult(response);
					}
					else
					{
						response.sendRedirect(request.getContextPath() + "/login");
					}
					return false;
				}
			}
		}
		return true;
	}

	private void printNoLoginResult(HttpServletResponse response)
	{
		PrintWriter writer = null;
		try
		{
			writer = response.getWriter();
			writer.write(JSON.toJSONString(new ResultJson().sessionInvalid()));
		}
		catch (IOException e)
		{
			// TODO ignore
		}
		finally
		{
			if (writer != null)
				IOUtils.closeQuietly(writer);
		}

	}
}
