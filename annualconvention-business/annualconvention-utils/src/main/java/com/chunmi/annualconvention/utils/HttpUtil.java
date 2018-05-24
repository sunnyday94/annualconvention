/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.chunmi.annualconvention.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * HTTP请求工具类
 *
 * @author NINGPAI-ZHOUY
 * @version 1.0
 * @since 2013年12月23日下午2:49:32
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static final String USERAGENT = "User-Agent";

	private static final String DEFAULT_CHARSET = "utf-8";

	public static final Charset DEFAULT_CHARSET1 = Charset.forName("UTF-8");

	private static final ContentType TEXT_CONTENT_TYPE = ContentType.create("text/plain", DEFAULT_CHARSET1);

	public static final int CONNECTIMEOUT = 5000;

	public static final int READTIMEOUT = 5000;

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like "
			+ "Gecko) Chrome/31.0.1650.63 Safari/537.36";

	private HttpUtil() {
	}

	/**
	 * Send a post request
	 *
	 * @param url
	 *            Url as string
	 * @param body
	 *            Request body as string
	 * @param headers
	 *            Optional map with headers
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String post(String url, String body, Map<String, String> headers) throws IOException {
		Map<String, String> headers1 = headers;
		// set content type
		if (headers1 == null) {
			headers1 = new HashMap<String, String>();
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			headers.put(USERAGENT, USER_AGENT);
		}
		return fetch("POST", url, body, headers1);
	}

	/**
	 * Send a post request
	 *
	 * @param url
	 *            Url as string
	 * @param body
	 *            Request body as string
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String post(String url, String body) throws IOException {
		return post(url, body, null);
	}

	/**
	 * Send a get request
	 *
	 * @param url
	 * @return response
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		return get(url, null);
	}

	/**
	 * Send a get request
	 *
	 * @param url
	 *            Url as string
	 * @param headers
	 *            Optional map with headers
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> headers) throws IOException {
		if (headers == null) {
			headers = new HashMap<String, String>();
			headers.put(USERAGENT, USER_AGENT);
		}
		return fetch("GET", url, null, headers);
	}

	/**
	 * Post a form with parameters
	 *
	 * @param url
	 *            Url as string
	 * @param params
	 *            map with parameters/values
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String postForm(String url, Map<String, String> params) throws IOException {
		return postForm(url, params, null);
	}

	/**
	 * Post a form with parameters
	 *
	 * @param url
	 *            Url as string
	 * @param params
	 *            Map with parameters/values
	 * @param headers
	 *            Optional map with headers
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String postForm(String url, Map<String, String> params, Map<String, String> headers)
			throws IOException {
		Map<String, String> headers1 = headers;
		if (headers1 == null) {
			headers1 = new HashMap<String, String>();
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			headers.put(USERAGENT, USER_AGENT);
		}

		String body = "";
		if (params != null) {
			boolean first = true;
			for (String param : params.keySet()) {
				if (first) {
					first = false;
				} else {
					body += "&";
				}
				String value = params.get(param);
				body += URLEncoder.encode(param, DEFAULT_CHARSET) + "=";
				body += URLEncoder.encode(value, DEFAULT_CHARSET);
			}
		}

		return post(url, body, headers1);
	}

	/**
	 * Append query parameters to given url
	 *
	 * @param url
	 *            Url as string
	 * @param params
	 *            Map with query parameters
	 * @return url Url with query parameters appended
	 * @throws IOException
	 */
	public static String appendQueryParams(String url, Map<String, String> params) throws IOException {
		String fullUrl = new String(url);

		if (params != null) {
			boolean first = fullUrl.indexOf('?') == -1;
			for (String param : params.keySet()) {
				if (first) {
					fullUrl += '?';
					first = false;
				} else {
					fullUrl += '&';
				}
				String value = params.get(param);
				fullUrl += URLEncoder.encode(param, DEFAULT_CHARSET) + '=';
				fullUrl += URLEncoder.encode(value, DEFAULT_CHARSET);
			}
		}

		return fullUrl;
	}

	/**
	 * Retrieve the query parameters from given url
	 *
	 * @param url
	 *            Url containing query parameters
	 * @return params Map with query parameters
	 * @throws IOException
	 */
	public static Map<String, String> getQueryParams(String url) throws IOException {
		Map<String, String> params = new HashMap<String, String>();

		int start = url.indexOf('?');
		while (start != -1) {
			int equals = url.indexOf('=', start);
			String param = "";
			if (equals != -1) {
				param = url.substring(start + 1, equals);
			} else {
				param = url.substring(start + 1);
			}

			String value = "";
			if (equals != -1) {
				start = url.indexOf('&', equals);
				if (start != -1) {
					value = url.substring(equals + 1, start);
				} else {
					value = url.substring(equals + 1);
				}
			}

			params.put(URLDecoder.decode(param, DEFAULT_CHARSET), URLDecoder.decode(value, DEFAULT_CHARSET));
		}

		return params;
	}

	/**
	 * Returns the url without query parameters
	 *
	 * @param url
	 *            Url containing query parameters
	 * @return url Url without query parameters
	 */
	public static String removeQueryParams(String url) {
		int q = url.indexOf('?');
		if (q != -1) {
			return url.substring(0, q);
		} else {
			return url;
		}
	}

	/**
	 * Send a request
	 *
	 * @param method
	 *            HTTP method, for example "GET" or "POST"
	 * @param url
	 *            Url as string
	 * @param body
	 *            Request body as string
	 * @param headers
	 *            Optional map with headers
	 * @return response Response as string
	 * @throws IOException
	 */
	public static String fetch(String method, String url, String body, Map<String, String> headers) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setConnectTimeout(CONNECTIMEOUT);
		conn.setReadTimeout(READTIMEOUT);

		if (method != null) {
			conn.setRequestMethod(method);
		}

		if (headers != null) {
			for (String key : headers.keySet()) {
				conn.addRequestProperty(key, headers.get(key));
			}
		}

		if (body != null) {
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
		}

		InputStream is = conn.getInputStream();
		String response = streamToString(is);
		is.close();

		if (conn.getResponseCode() == 301) {
			String location = conn.getHeaderField("Location");
			return fetch(method, location, body, headers);
		}
		return response;
	}

	/**
	 * Read an input stream into a string
	 *
	 * @param in
	 * @return string
	 * @throws IOException
	 */
	public static String streamToString(InputStream in) throws IOException {
		StringBuilder out = new StringBuilder();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	/****
	 * 
	 * @description:<p class="detail">get请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-上午11:27:45
	 * @param @param url
	 * @param @return
	 * @return String
	 * @throws HttpException 
	 */
	public static <T> T httpGet(String url) throws IOException, HttpException {
		return httpGet(url, null, null, null, null);
	}
	
	/***
	 * 
	 * @description: <p class="detail">get请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">Kimmyzhao</a>
	 * @date: 2016年11月7日-上午9:54:19
	 * @param @param url
	 * @param @param headers
	 * @param @param params
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @param @throws HttpException
	 * @return T
	 */
	public static <T> T httpGet(String url, Map<String, String> headers, Map<String, String> params,
			ResponseHandler<T> handler) throws IOException, HttpException {
		return httpGet(url, headers, params, null, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">get请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:41:35
	 * @param @param url
	 * @param @param context
	 * @param @param params
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	public static <T> T get(String url, HttpContext context, Map<String, String> params, ResponseHandler<T> handler)
			throws IOException, HttpException {
		return httpGet(url, null, params, context, handler);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param handler
	 * @return
	 * @throws IOException
	 * @throws HttpException 
	 */
	public static <T> T httpGet(String url, Map<String, String> headers, Map<String, String> params, HttpContext context,
			ResponseHandler<T> handler) throws IOException, HttpException {
		RequestBuilder builder = RequestBuilder.get();
		builder.setUri(url);
		HttpUriRequest request = request(builder, headers, params, null);
		logger.debug("request:{}", request);
		return response(request, context, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">post 请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:41:43
	 * @param @param url
	 * @param @param headers
	 * @param @param params
	 * @param @param fileParams
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	public static <T> T post(String url, Map<String, String> headers, Map<String, String> params,
			Map<String, File> fileParams, ResponseHandler<T> handler) throws IOException, HttpException {
		return post(url, headers, params, fileParams, null, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">post 请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:41:49
	 * @param @param url
	 * @param @param headers
	 * @param @param params
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	public static <T> T post(String url, Map<String, String> headers, Map<String, String> params,
			ResponseHandler<T> handler) throws IOException, HttpException {
		return post(url, headers, params, null, null, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">post 请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:41:57
	 * @param @param url
	 * @param @param headers
	 * @param @param params
	 * @param @param fileParams
	 * @param @param context
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	public static <T> T post(String url, Map<String, String> headers, Map<String, String> params,
			Map<String, File> fileParams, HttpContext context, ResponseHandler<T> handler) throws IOException, HttpException {
		RequestBuilder builder = RequestBuilder.post();
		builder.setUri(url);
		HttpUriRequest request = request(builder, headers, params, fileParams);
		logger.debug("request:{}", request);
		return response(request, context, handler);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param fileParams
	 * @param handler
	 * @return
	 * @throws IOException
	 * @throws HttpException 
	 */
	public static <T> T post(String url, Map<String, String> headers, Map<String, String> params, HttpContext context,
			ResponseHandler<T> handler) throws IOException, HttpException {
		RequestBuilder builder = RequestBuilder.post();
		builder.setUri(url);
		HttpUriRequest request = request(builder, headers, params);
		logger.debug("request:{}", request);
		return response(request, context, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">post 请求</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:42:46
	 * @param @param url
	 * @param @param body
	 * @param @param context
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	public static <T> T post(String url, String body, HttpContext context, ResponseHandler<T> handler)
			throws IOException, HttpException {
		RequestBuilder builder = RequestBuilder.post();
		builder.setUri(url);
		HttpEntity entity = new StringEntity(body, DEFAULT_CHARSET);
		builder.setEntity(entity);
		HttpUriRequest request = builder.build();
		logger.debug("request:{}", request);
		return response(request, context, handler);
	}

	/***
	 * 
	 * @description:<p class="detail">处理返回</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:42:59
	 * @param @param request
	 * @param @param context
	 * @param @param handler
	 * @param @return
	 * @param @throws IOException
	 * @return T
	 * @throws HttpException 
	 */
	@SuppressWarnings("unchecked")
	private static <T> T response(HttpUriRequest request, HttpContext context, ResponseHandler<T> handler)
			throws IOException, HttpException {
		HttpClient client = getIgnoreSslCertificateHttpClient();
		HttpResponse response = client.execute(request, context);
		if(handler == null){
			handler = (ResponseHandler<T>) new BasicResponseHandler();
		}
		return handler.handleResponse(response);
	}

	/***
	 * 
	 * @description:<p class="detail">创建request</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:43:12
	 * @param @param builder
	 * @param @param headers
	 * @param @param params
	 * @param @param fileParams
	 * @param @return
	 * @return HttpUriRequest
	 */
	private static HttpUriRequest request(RequestBuilder builder, Map<String, String> headers,
			Map<String, String> params, Map<String, File> fileParams) {
		// 添加head
		if (!CollectionUtils.isEmpty(headers)) {
			Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				builder.addHeader((String) entry.getKey(), (String) entry.getValue());

			}
		}
		if (HttpGet.METHOD_NAME.equalsIgnoreCase(builder.getMethod())) {
			return requestGet(builder, params);
		}
		if (HttpPost.METHOD_NAME.equalsIgnoreCase(builder.getMethod())) {
			return requestPost(builder, params, fileParams);
		}
		return builder.build();
	}


	/***
	 * 
	 * @description:<p class="detail">创建request</p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:43:29
	 * @param @param builder
	 * @param @param headers
	 * @param @param params
	 * @param @return
	 * @return HttpUriRequest
	 */
	private static HttpUriRequest request(RequestBuilder builder, Map<String, String> headers,
			Map<String, String> params) {
		// 添加head
		if (!CollectionUtils.isEmpty(headers)) {
			Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				builder.addHeader((String) entry.getKey(), (String) entry.getValue());

			}
		}
		if (HttpGet.METHOD_NAME.equalsIgnoreCase(builder.getMethod())) {
			return requestGet(builder, params);
		}
		if (HttpPost.METHOD_NAME.equalsIgnoreCase(builder.getMethod())) {
			return requestPost(builder, params);
		}
		return builder.build();
	}

	/***
	 * 
	 * @description:<p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:44:05
	 * @param @param builder
	 * @param @param params
	 * @param @return
	 * @return HttpUriRequest
	 */
	private static HttpUriRequest requestGet(RequestBuilder builder, Map<String, String> params) {
		if (!CollectionUtils.isEmpty(params)) {
			Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				builder.addParameter((String) entry.getKey(), (String) entry.getValue());

			}

		}
		return builder.build();
	}

	/***
	 * 
	 * @description:<p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:44:11
	 * @param @param builder
	 * @param @param params
	 * @param @param fileParams
	 * @param @return
	 * @return HttpUriRequest
	 */
	private static HttpUriRequest requestPost(RequestBuilder builder, Map<String, String> params,
			Map<String, File> fileParams) {
		HttpEntity entity = null;
		// 是否需要上传文件
		if (CollectionUtils.isEmpty(fileParams)) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			if (params != null) {

				Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					parameters.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));

				}

			}
			entity = new UrlEncodedFormEntity(parameters, DEFAULT_CHARSET1);
		} else {
			MultipartEntityBuilder multiBuilder = MultipartEntityBuilder.create().setCharset(DEFAULT_CHARSET1);
			Iterator<Map.Entry<String, File>> it = fileParams.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, File> entry = (Map.Entry<String, File>) it.next();
				multiBuilder.addBinaryBody((String) entry.getKey(), (File) entry.getValue());

			}

			if (!CollectionUtils.isEmpty(params)) {
				Iterator<Map.Entry<String, String>> it1 = params.entrySet().iterator();
				while (it1.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it1.next();
					multiBuilder.addTextBody((String) entry.getKey(), (String) entry.getValue(), TEXT_CONTENT_TYPE);

				}

			}
			entity = multiBuilder.build();
		}
		builder.setEntity(entity);
		return builder.build();
	}

	/***
	 * 
	 * @description:<p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月14日-下午1:44:20
	 * @param @param builder
	 * @param @param params
	 * @param @return
	 * @return HttpUriRequest
	 */
	private static HttpUriRequest requestPost(RequestBuilder builder, Map<String, String> params) {

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (params != null) {

			Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				parameters.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));

			}
		}
		HttpEntity entity = new UrlEncodedFormEntity(parameters, DEFAULT_CHARSET1);
		builder.setEntity(entity);

		return builder.build();
	}

	/***
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年10月16日-上午11:55:22
	 * @param @return
	 * @param @throws HttpException
	 * @return CloseableHttpClient
	 */
	public static CloseableHttpClient getIgnoreSslCertificateHttpClient() throws HttpException {
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				@Override
				public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {

					return true;
				}
			}).build();
		} catch (Exception e) {
			throw new HttpException("can not create http client.", e);
		}
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new NoopHostnameVerifier());
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
				.build();
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		
		RequestConfig defaultRequestConfig = RequestConfig.custom()
			    .setSocketTimeout(5000)
			    .setConnectTimeout(5000)
			    .setConnectionRequestTimeout(5000)
			    .build();
		return HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setSSLContext(sslContext).setConnectionManager(connMgr).build();
	}
	
	/****
	 * 利用httpclient提交json参数 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:zhaoshouyi@chunmi.com ">zhaoshouyi</a>
	 * @date: 2016年7月20日-上午10:27:46
	 * @param @param url
	 * @param @param json
	 * @param @return
	 * @param @throws Exception
	 * @return HttpResponse
	 */
	public static HttpResponse httpPostWithJSON(String url, String json) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		try {
			httpClient = getIgnoreSslCertificateHttpClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

			StringEntity se = new StringEntity(json, "utf-8");// 解决中文乱码问题
			se.setContentType("application/json");
			se.setContentEncoding("utf-8");

			httpPost.setEntity(se);
			return httpClient.execute(httpPost);
		} catch (UnsupportedCharsetException e) {
			logger.error("请求异常(UnsupportedCharsetException)===>", e);
		} catch (ClientProtocolException e) {
			logger.error("请求异常(ClientProtocolException)===>", e);
		} catch (HttpException e) {
			logger.error("请求异常(HttpException)===>", e);
		} catch (IOException e) {
			logger.error("请求异常(IOException)===>", e);
		}
		return null;
	}
	
	
}
