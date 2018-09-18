package com.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientCalls {
    private static final Logger logger = LogManager.getLogger(HttpClientCalls.class);

    private static String httpPost(String url, CloseableHttpClient client) {
	HttpPost postRequest = new HttpPost(url);
	String responseString = null;
	try {

	    CloseableHttpResponse response = null;
	    postRequest = new HttpPost(url);

	    postRequest.setHeader("Accept", "application/json");

	    postRequest.addHeader("content-type", "application/json");

	    {
		response = client.execute(postRequest);
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode == HttpServletResponse.SC_OK || responseCode == HttpServletResponse.SC_CREATED) {
		    HttpEntity entity = response.getEntity();
		    responseString = EntityUtils.toString(entity, "UTF-8");
		}
		response.close();
	    }
	} catch (IOException e) {
	    logger.debug(e.getMessage());
	}
	if (logger.isDebugEnabled())
	    logger.debug("HttpUtil  httpPost:::" + responseString);
	return responseString;

    }

    private static String httpPost3(String ticketBody, String url) {
	HttpPost postRequest = new HttpPost(url);
	String responseString = null;
	try {
	    HttpEntity httpEntity = new ByteArrayEntity(ticketBody.getBytes("UTF-8"));
	    CloseableHttpResponse response = null;
	    postRequest = new HttpPost(url);

	    postRequest.setHeader("Accept", "application/json");

	    postRequest.addHeader("content-type", "application/json");
	    postRequest.setEntity(httpEntity);
	    {
		CloseableHttpClient client = getCloseableHttpClient(null, null);
		response = client.execute(postRequest);
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode == HttpServletResponse.SC_OK || responseCode == HttpServletResponse.SC_CREATED) {
		    HttpEntity entity = response.getEntity();
		    responseString = EntityUtils.toString(entity, "UTF-8");
		}
		response.close();
		client.close();
	    }
	} catch (IOException e) {
	    logger.debug(e.getMessage());
	}
	if (logger.isDebugEnabled())
	    logger.debug("HttpUtil  httpPost:::" + responseString);
	return responseString;

    }

    private static String httpGet(String url, String sessionId) {
	HttpGet getRequest = new HttpGet(url);
	String responseString = null;

	try {
	    CloseableHttpResponse response = null;
	    getRequest.addHeader("Accept", "application/json");
	    getRequest.addHeader("content-type", "application/json");

	    CookieStore cookieStore = new BasicCookieStore();
	    BasicClientCookie cookie = new BasicClientCookie("session_id", sessionId);
	    cookie.setDomain("fs-arista-cvp");
	    cookie.setPath("/");
	    cookieStore.addCookie(cookie);

	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);

	    CloseableHttpClient client = getCloseableHttpClient(null, null);

	    response = client.execute(getRequest, context);
	    int responseCode = response.getStatusLine().getStatusCode();
	    if (responseCode == HttpServletResponse.SC_OK) {
		HttpEntity entity = response.getEntity();
		responseString = EntityUtils.toString(entity, "UTF-8");
	    }
	    response.close();
	    client.close();

	} catch (UnsupportedEncodingException e) {
	    if (logger.isDebugEnabled())
		logger.debug(e.getMessage(), e);
	} catch (ClientProtocolException e) {
	    if (logger.isDebugEnabled())
		logger.debug(e.getMessage(), e);
	} catch (IOException e) {
	    if (logger.isDebugEnabled())
		logger.debug(e.getMessage(), e);
	}
	return responseString;

    }

    private static CloseableHttpClient getCloseableHttpClient(CredentialsProvider credsProvider,
	    CookieStore cookieStore) {
	SSLContext sslContext = null;
	CloseableHttpClient client = null;
	HttpClientBuilder clientBuilder = null;
	TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

	    public boolean isTrusted(X509Certificate[] certificate, String authType) {
		return true;
	    }
	};
	try {
	    sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

	    clientBuilder = HttpClients.custom()
		    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
		    .setSslcontext(sslContext);

	    if (credsProvider != null) {
		clientBuilder = clientBuilder.setDefaultCredentialsProvider(credsProvider);
	    }
	    if (cookieStore != null)
		clientBuilder = clientBuilder.setDefaultCookieStore(cookieStore);

	    client = clientBuilder.build();
	} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
	    logger.warn("error while getting sslContext", e.getMessage());
	}

	return client;
    }

    public static CredentialsProvider getCredential(String url, String userName, String password) {
	if (logger.isDebugEnabled())
	    logger.debug("getCredential::url::" + url + "::userName::" + userName);
	CredentialsProvider credsProvider = new BasicCredentialsProvider();
	String domain = getDomainName(url);
	if (logger.isDebugEnabled())
	    logger.debug("getCredential::domain::" + domain);
	if (domain != null) {
	    if (userName != null) {
		credsProvider.setCredentials(new org.apache.http.auth.AuthScope(new HttpHost(domain)),
			new UsernamePasswordCredentials(userName, password));
		return credsProvider;
	    } else {
		logger.warn("ServiceNowUtil getCredential UserName is not set NULL");
	    }
	} else {
	    logger.warn("ServiceNowUtil getCredential Domain name is not set NULL");
	}
	return null;
    }

    public static String getDomainName(String url) {
	URI uri = null;
	try {
	    if (url != null) {
		uri = new URI(url);
		String domain = uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	    }
	} catch (URISyntaxException e) {
	    logger.warn("Invalid url:" + e.getMessage(), e);
	}
	return null;
    }

    private static String parsingJsonString(String jsonString) {

	// InputStream stream = new
	// ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8.name()));
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonString);
	    JsonNode sessionNode = actualObj.get("sessionId");
	    String sessionId = sessionNode.asText();
	    System.out.println(sessionId);
	    return sessionId;
	} catch (Exception e) {
	    logger.error("parsing error" + e.getMessage());
	}
	return null;
    }

}
