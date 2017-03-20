package com.credit.common.util.http;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public final class HttpUtil {
	private final static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();
	private final static String CHARSET = "UTF-8";

	/**
	 * 发送Get请求
	 * @param httpGet
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) throws Exception {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String respContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			respContent = EntityUtils.toString(entity, CHARSET);
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
			}
		}
		return respContent;
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) throws Exception {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String respContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			respContent = EntityUtils.toString(entity, "UTF-8");
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
			}
		}
		return respContent;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            请求的URL
	 * @param paramMap
	 *            普通表单域参数，key:参数名;value:参数值
	 * @param fileParamMap
	 *            文件域参数，key:参数名;value:参数值
	 * @return 返回请求结果
	 */
	public static String postReq(final String url, final Map<String, String> paramMap, final Map<String, File> fileParamMap) throws Exception {
		HttpPost httpPost = null;
		MultipartEntityBuilder multipartBuilder = null;
		httpPost = new HttpPost(url);
		multipartBuilder = MultipartEntityBuilder.create();
		if (paramMap != null && !paramMap.isEmpty()) {
			for (String paramName : paramMap.keySet()) {
				multipartBuilder.addPart(paramName, new StringBody(paramMap.get(paramName), ContentType.TEXT_PLAIN));
			}
		}
		if (fileParamMap != null && !fileParamMap.isEmpty()) {
			File tmpFile = null;
			for (String paramName : fileParamMap.keySet()) {
				tmpFile = fileParamMap.get(paramName);
				if (tmpFile == null) {
					continue;
				}
				if (!tmpFile.exists()) {
					throw new Exception("文件[" + tmpFile.getAbsolutePath() + "]不存在");
				}
				if (tmpFile.isDirectory()) {
					throw new Exception("文件[" + tmpFile.getAbsolutePath() + "]是一个文件夹");
				}
				multipartBuilder.addPart(paramName, new FileBody(tmpFile));
			}
		}
		HttpEntity reqEntity = multipartBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            请求的URL
	 * @param paramMap
	 *            普通表单域参数，key:参数名;value:参数值
	 * @return 返回请求结果
	 */
	public static String getReq(final String url, final Map<String, String> paramMap) throws Exception {
		HttpGet httpGet = null;
		StringBuffer paramStr = new StringBuffer();
		if (paramMap != null && !paramMap.isEmpty()) {
			int idx = 0;
			for (String paramName : paramMap.keySet()) {
				if(paramName == null || paramName.trim().length() == 0) {
					continue;
				}
				if(idx > 0) {
					paramStr.append("&");
				}
				paramStr.append(paramName).append("=").append(paramMap.get(paramName));
				idx += 1;
			}
		}
		if(paramStr.length() > 0) {
			if(url.indexOf("?") > 0) {
				httpGet = new HttpGet(url + "&" + paramStr.toString());
			} else {
				httpGet = new HttpGet(url + "?" + paramStr.toString());
			}	
		} else {
			httpGet = new HttpGet(url);
		}
		return sendHttpGet(httpGet);
	}

	public static void main(String[] args) throws Exception {

	}
}
