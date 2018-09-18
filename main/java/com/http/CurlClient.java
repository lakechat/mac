package com.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;



public class CurlClient {
    public static Logger logger = LogManager.getLogger(CurlClient.class);
    private static Map<String, String> deviceIdSerialNumberMap;
    private static Map<String, Map<String,String>> ifxRateMap;

    private static final String deviceInfoUrl = "https://fs-arista-cvp/aeris/v1/rest/analytics/DatasetInfo/EosSwitches/?pretty=true";
    private static final String login = "{\"userId\":\"cvpadmin\",\"password\":\"FsAdmin123\"}";
    private static final String loginUrl = "https://fs-arista-cvp/cvpservice/login/authenticate.do";
    private static final String metricUrlPrefix = "https://fs-arista-cvp/aeris/v1/rest/analytics";
    private static final String ifxPostfix = "/versioned-data/interfaces/data/?pretty=true";
    private static final String META_DEVICE = "device";
    private static final String META_INTERFACE = "interface";
    private static final String META_RATE = "rate";
    private static final DateTimeFormatter jodaCompleteDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private static Map<String,String> metricUnitMap;
    private static final String defaultMetricDataFormat = "/?versions=6&pretty=true";
    
    public static void main(String[] args){
	
	metricUnitMap = new HashMap<>();
	for(CloudVisionMetricUnitEnum metricUnit : CloudVisionMetricUnitEnum.values())
	    metricUnitMap.put(metricUnit.getMetricName(), metricUnit.getMetricUnit());
	
	String serial = getSerialNumberByDeviceId("fs-arista-2");
	getCloudVisionServiceList( serial, null);
	getCloudVisionIfxTimeseries("Ethernet20","inOctetsRate");
	
	//System.out.println(getMetricDataFormat(1));
	
	
    }
    
    private static String httpPost(String ticketBody, String url,CloseableHttpClient client) {
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
    
    private static CloseableHttpClient getCloseableHttpClient(CredentialsProvider credsProvider) {
        SSLContext sslContext = null;
        CloseableHttpClient client = null;
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

            public boolean isTrusted(X509Certificate[] certificate, String authType) {
                return true;
            }
        };
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            if (credsProvider != null) {
                client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider)
                	.setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                        .setSslcontext(sslContext)
                	.build();
                	
            } else {
        	
                client = HttpClients.custom()
                                    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                                    .setSslcontext(sslContext).build();
        	
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            logger.warn("error while getting sslContext", e.getMessage());
        }

        return client;
    }
    
    private static String httpGet(String url, CloseableHttpClient client) {
	HttpGet getRequest = new HttpGet(url);
	String responseString = null;

	try {
	    HttpResponse response = null;
	    getRequest.addHeader("Accept", "application/json");
	    getRequest.addHeader("content-type", "application/json");

	    response = client.execute(getRequest);
	    int responseCode = response.getStatusLine().getStatusCode();
	    if (responseCode == HttpServletResponse.SC_OK) {
		HttpEntity entity = response.getEntity();
		responseString = EntityUtils.toString(entity, "UTF-8");
	    }

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
    
    public static <T> T getObjectFromJson(String jsonString, Class<T> classType) {
        T obj = null;
        try {
            obj = new ObjectMapper().configure(MapperFeature.USE_GETTERS_AS_SETTERS, false)
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                    .registerModule(new JodaModule()).readValue(jsonString, classType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    
    
    public static String getSerialNumberByDeviceId(String deviceId) {
	if (deviceIdSerialNumberMap == null || deviceIdSerialNumberMap.size() == 0) {
	    deviceIdSerialNumberMap = new HashMap<>();
	    String deviceString = getCloudVisionData(deviceInfoUrl);

	    CloudVisionDeviceData dataList = getObjectFromJson(deviceString, CloudVisionDeviceData.class);
	    if (dataList.getNotifications() != null) {
		for (DeviceNotification notification : dataList.getNotifications()) {
		    Map<String, DeviceUpdate> deviceUpdateEntry = notification.getUpdates();
		    if (deviceUpdateEntry != null) {
			for (Entry<String, DeviceUpdate> entryUpdate : deviceUpdateEntry.entrySet()) {
			    String serial = entryUpdate.getKey();
			    DeviceUpdate deviceUpdate = entryUpdate.getValue();
			    Map<String, String> valueMap = deviceUpdate.getValue();
			    for (Entry<String, String> entryValue : valueMap.entrySet()) {
				String key = entryValue.getKey();
				if (key.equals("hostname"))
				    deviceIdSerialNumberMap.put(entryValue.getValue().toLowerCase(), serial);
			    }
			}
		    }
		}
	    }
	}
	logger.error(deviceIdSerialNumberMap.get(deviceId));
	return deviceIdSerialNumberMap.get(deviceId);
    }
    
    public static List<ServiceName> getCloudVisionServiceList(String serialNumber, String type) {
	
	ServiceName serviceNames = null;
	List<GroupName> metricGroups = new ArrayList<>();
	Map<String, String> ifxPtrMap = new HashMap<>();
	List<ServiceName> deviceMetricTimeSeries = new ArrayList<>();
	ifxRateMap = new HashMap<>();
	// 1. get device interfaces
	String ifxUrl = metricUrlPrefix + "/Devices/" + serialNumber + ifxPostfix;
	ifxPtrMap = getIfxInfoMap(ifxUrl);
	
	// 2. get rate names of each ifx
	for(Entry<String,String> ifxEntry : ifxPtrMap.entrySet()){
	    String ifx = ifxEntry.getKey();
	    String ptr = ifxEntry.getValue();
	    String rateNameUrl = metricUrlPrefix+ptr+"/rates";
	    Map<String, String> ratePtrMap = new HashMap<>();
	    ratePtrMap = getIfxInfoMap(rateNameUrl);
	    if(ratePtrMap.size()>0){
		GroupName rateGroup = new GroupName(ifx,ratePtrMap);
		metricGroups.add(rateGroup);
	    }
		
	    ifxRateMap.put(ifx, ratePtrMap);
	   // logger.error(rateNameUrl);
	}
	
	if(metricGroups.size()>0){
	     serviceNames = new ServiceName("Cloud Vision",metricGroups);
	     logger.error("Cloud Vision");
	     logger.error("|");
	     logger.error("|");
	     /*
	     for(GroupName groups : metricGroups){
		 logger.error("----"+groups.getGroupName());
		 logger.error("   |");
		 for(Entry<String,String> rates : groups.getMetricNames().entrySet()){
		     logger.error("    ----"+rates.getKey());
		 }
	     }
	     */
	     deviceMetricTimeSeries.add(serviceNames);
	}
	     

	return deviceMetricTimeSeries;
    }
    
    public static Map<String,String> getIfxInfoMap(String url){
	
	Map<String,String> resultMap = new HashMap<>();
	String resultString = getCloudVisionData(url);

	CloudVisionDeviceData dataList = getObjectFromJson(resultString, CloudVisionDeviceData.class);
	if (dataList.getNotifications() != null) {
	    for (DeviceNotification notification : dataList.getNotifications()) {
		Map<String, DeviceUpdate> deviceUpdateEntry = notification.getUpdates();
		if (deviceUpdateEntry != null) {
		    for (Entry<String, DeviceUpdate> entryUpdate : deviceUpdateEntry.entrySet()) {
			
			    String key = entryUpdate.getKey();
			    DeviceUpdate deviceUpdate = entryUpdate.getValue();
			    Map<String, String> valueMap = deviceUpdate.getValue();
			    for (Entry<String, String> entryValue : valueMap.entrySet()) {
				resultMap.put(key, entryValue.getValue());
			    }
		    }
		}
	    }

	}
	
	return resultMap;
    }
    
    public static Map<String, List<MetricTimeSeries>> getCloudVisionIfxTimeseries(String ifx, String rateName) {

	if (ifxRateMap == null || ifxRateMap.size() == 0)
	    return null;
	else {
	    // 1. get ratePtrMap
	    Map<String, String> ratePtrMap = ifxRateMap.get(ifx);
	    if (ratePtrMap != null) {
		// 2. get rate url post fix
		String ratePtr = ratePtrMap.get(rateName);
		if (ratePtr != null) {
		    // 3. form url, we retrieve 7 records
		    List<MetricTimeStampClass> dataSet = new ArrayList<>();
		    String dataformat = getMetricDataFormat(1);
		    String rateUrl = metricUrlPrefix + ratePtr + dataformat;
		    System.out.println(rateUrl);
		    String resultString = getCloudVisionData(rateUrl);

		    CloudVisionMetricData dataList = getObjectFromJson(resultString, CloudVisionMetricData.class);
		    if (dataList.getNotifications() != null) {
			for (CloudVisionMetricNotification notification : dataList.getNotifications()) {
			    try {

				CloudVisionMetricUpdate metricUpdate = notification.getUpdates();
				if (metricUpdate != null) {
				    CloudVisionMetricRate rate = metricUpdate.getRate();
				    if (rate != null) {
					float value = rate.getValue();
					MetricTimeStampClass data = new MetricTimeStampClass();
					data.setSeverity("CLEARED");
					long timestamp = notification.getTimestamp();
					data.setTimeStamp(covertEpocToReadable(timestamp));
					data.setMetricValue(value);
					dataSet.add(data);
					logger.error(data.getTimeStamp()+" -- "+value+" --- "+metricUnitMap.get(rateName));
				    }

				}
			    } catch (NumberFormatException e) {
				logger.error("convert timestamp exception: " + e.getMessage());
			    }
			}
		    }
		   // System.out.println(dataSet.size());
		}
	    }

	    return null;
	}
    }

    
    private static String getCloudVisionData(String url) {

	CloseableHttpClient client = getCloseableHttpClient(null);
	String result = httpPost(login, loginUrl, client);

	String result2 = httpGet(url, client);


	try {
	    client.close();
	} catch (IOException e) {
	    logger.error("close http client exception");
	}

	return result2;
    }
    
    private static String covertEpocToReadable(long epocTime) {
	try {
	    int length = (int)(Math.log10(epocTime)+1);
	    if(length == 19)
		epocTime /=1000000L;
	    else if(length == 10)
		epocTime *=1000L;
	    DateTime dt = new DateTime(epocTime).withZone(DateTimeZone.forID("America/Los_Angeles"));
	    
	    String result = jodaCompleteDateFormatter.print(dt);
	    return result;
	} catch (Exception e) {
	    System.out.println("convert time string exception");
	}

	return null;

    }
    
    private static String getMetricDataFormat(int days){
	String result = defaultMetricDataFormat;
	if(days >0 && days <7){
	    long end = System.currentTimeMillis();
	    long start = (end - 86400*1000);
	    result = "/?start="+start*1000000+"&end="+end*1000000+"&pretty=true";
	}
	
	
	return result;
    }
    
    
    

}
