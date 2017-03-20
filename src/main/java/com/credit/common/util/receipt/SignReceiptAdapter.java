/**
 * @(#)SignReceiptAdapter.java
 *
 * @author xuji
 * @version 1.0 2014-7-11
 *
 * Copyright (C) 2012,2014 , PING' AN, Inc.
 */
package com.credit.common.util.receipt;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import com.credit.common.util.security.DigestUtils;


/**
 * Purpose:
 * 
 * @see
 * @since 1.1.0
 */
public class SignReceiptAdapter
{
    private static String SIGN_URL = "http://localhost:8888/ucp/receipt/sign.action";

    private String pname;

    private String pkey;

    public SignReceiptAdapter(String pname, String pkey)
    {
        this.pname = pname;
        this.pkey = pkey;
    }

    public String sign(String seller, String website, String order, long price, String imageDigest) throws Exception
    {
        String[] params = new String[6];
        String timestamp = String.valueOf(System.currentTimeMillis());
        params[0] = pkey;
        params[1] = timestamp;
        params[2] = order.trim();
        params[3] = String.valueOf(price);
        params[4] = imageDigest.trim();
        params[5] = pkey;
        String vkey = getVKey(params);

        String encodeSeller = URLEncoder.encode(seller, "UTF-8");
        NameValuePair[] httpParams = new NameValuePair[]{new NameValuePair("pname", pname.trim()), new NameValuePair("ptime", timestamp),
                new NameValuePair("seller", encodeSeller.trim()), new NameValuePair("website", website.trim()), new NameValuePair("order", order.trim()),
                new NameValuePair("price", String.valueOf(price)), new NameValuePair("imageDigest", imageDigest.trim()), new NameValuePair("vkey", vkey),};

        return doPostRequest(SIGN_URL, httpParams);
    }

    private HttpClient getHttpClient()
    {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(httpClient.getParams().USER_AGENT, "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; 360SE)");
        return httpClient;
    }

    /**
     * 发送post请求
     * 
     * @param url
     *            请求地址
     * @return
     */
    private String doPostRequest(String url, NameValuePair[] params) throws Exception
    {
        PostMethod method = null;
        try
        {
            HttpClient httpClient = getHttpClient();
            httpClient.getState().clear();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 300000);

            method = new PostMethod(url);

            // 设置请求头部信息
            setRequestHeaders(method);

            method.addParameters(params);

            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK)
            {
                return null;
            }

            return method.getResponseBodyAsString();

        }
        catch (HttpException e)
        {
            throw e;
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (null != method)
            {
                method.releaseConnection();
            }
        }
    }

    private void setRequestHeaders(HttpMethodBase method)
    {
        if (null != method)
        {
            method.addRequestHeader("Connection", "keep-alive");
            method.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");

        }
    }

    /**
     * 计算参数vkey
     * 
     * @param params
     * @return
     * @throws Exception
     */
    private String getVKey(String[] params) throws Exception
    {
        String paramStr = StringUtils.join(params, "_");
        return DigestUtils.md5(paramStr.getBytes("UTF-8"));
    }

    public static void main(String[] args) throws Exception
    {
        SignReceiptAdapter adapter = new SignReceiptAdapter("ucweb", "1ab4b1482c79f98984f4b83e09141616");
        System.out.println(adapter.sign("京东", "jd.com", "6666666666", 1900000l, "test"));
    }
}

/**
 * $Log: SignReceiptAdapter.java,v $
 * 
 * @version 1.0 2014-7-11
 */