/**
 * @(#)ResultJson.java
 *
 * @author yubo
 * @version 1.0 2014年10月31日
 *
 * Copyright (C) 2012,2014 , PING' AN, Inc.
 */
package com.credit.common.result;

/**
 * Purpose:
 *
 * @see
 * @since 1.1.0
 */
public class ResultJson
{
    // ajax 请求服务器代码放回状态
    // 成功
    public static final Integer STATUS_CODE_SUCCESS_YES_DATA = 200;

    // 成功
    public static final Integer STATUS_CODE_SUCCESS = 0;

    // 未找到数据
    public static final Integer STATUS_CODE_NO_DATA = 2;

    // 接口未授权
    public static final Integer STATUS_CODE_NOT_AUTH = 401;

    // SESSION 失效错误
    public static final Integer STATUS_CODE_SESSION_INVALID = 5;

    // 请求已过期
    public static final Integer STATUS_CODE_REQUEST_EXPIRE = 4;

    // 请求参数错误代码
    public static final Integer STATUS_CODE_PARAM_ERROR = 5;

    // 失败
    public static final Integer STATUS_CODE_FAIL = 6;

    // 出现异常
    public static final Integer STATUS_CODE_EXCEPTION = 99999;

    public static final String STATUS_CODE_NO_DATA_MESSAGE = "暂无数据";

    public static final String STATUS_CODE_PARAM_ERROR_MESSAGE = "参数错误";

    public static final String STATUS_CODE_EXCEPTION_MESSAGE = "未知错误";

    public static final String STATUS_CODE_SUCCESS_MESSAGE = "请求成功";

    public static final String STATUS_CODE_SUCCESS_YES_DATA_MESSAGE = "请求成功且有匹配的数据返回";

    public static final String STATUS_CODE_NOT_AUTH_MESSAGE = "接口未授权";

    public static final String STATUS_CODE_REQUEST_EXPIRE_MESSAGE = "请求过期";

    public static final String STATUS_CODE_SESSION_INVALID_MESSAGE = "session已失效";

    private int result;

    private String message;

    private Object data;

    public ResultJson success()
    {
        this.result = STATUS_CODE_SUCCESS;
        this.message = "";
        return this;
    }

    public ResultJson success(Object data)
    {
        this.result = STATUS_CODE_SUCCESS_YES_DATA;
        this.message = "";
        this.data = data;
        return this;
    }

    public ResultJson success(Object data, String statusMessage)
    {
        this.result = STATUS_CODE_SUCCESS_YES_DATA;
        this.message = statusMessage;
        this.data = data;
        return this;
    }

    public ResultJson error()
    {
        this.result = STATUS_CODE_EXCEPTION;
        this.message = STATUS_CODE_EXCEPTION_MESSAGE;
        return this;
    }

    public ResultJson error(String message)
    {
        this.result = STATUS_CODE_EXCEPTION;
        this.message = message;
        return this;
    }

    public ResultJson error(int statusCode, String message)
    {
        this.result = statusCode;
        this.message = message;
        return this;
    }

    public ResultJson noData()
    {
        this.result = STATUS_CODE_NO_DATA;
        this.message = STATUS_CODE_NO_DATA_MESSAGE;
        return this;
    }

    public ResultJson paramError()
    {
        this.result = STATUS_CODE_PARAM_ERROR;
        this.message = STATUS_CODE_PARAM_ERROR_MESSAGE;
        return this;
    }

    public ResultJson paramError(String message)
    {
        this.result = STATUS_CODE_PARAM_ERROR;
        this.message = message;
        return this;
    }

    public ResultJson notAuth()
    {
        this.result = STATUS_CODE_NOT_AUTH;
        this.message = STATUS_CODE_NOT_AUTH_MESSAGE;
        return this;
    }
	public ResultJson notAuth(String message)
	{
		this.result = STATUS_CODE_NOT_AUTH;
		this.message = message;
		return this;
	}
    public ResultJson requestExpire()
    {
        this.result = STATUS_CODE_REQUEST_EXPIRE;
        this.message = STATUS_CODE_REQUEST_EXPIRE_MESSAGE;
        return this;
    }

    public ResultJson sessionInvalid()
    {
        this.result = STATUS_CODE_SESSION_INVALID;
        this.message = STATUS_CODE_SESSION_INVALID_MESSAGE;
        return this;
    }

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}

/**
 * $Log: ResultJson.java,v $
 *
 * @version 1.0 2014年10月31日
 */
