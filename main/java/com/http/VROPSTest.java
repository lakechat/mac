package com.http;

import org.apache.http.client.CredentialsProvider;

public class VROPSTest {
    
    private static final String protocol = "HTTPS://";
    private static final String urlPrefix = "/suite-api/api/resources/";
    private static final String statkeys = "statkeys?";
    private static final String query = "stats/query";
    
    public static void main(String[] args){
	
	   // String url = composeBasicUrl("192.168.128.99", "80", "admin", "FixStream123!");
	    CredentialsProvider cred = HttpClientCalls.getCredential("https://192.168.128.99", "admin123", "FixStream123!");
	   // CloseableHttpClient client = HttpClientCalls.getCloseableHttpClient()
		   // String result = HttpClientCalls.httpGet("https://admin:FixStream123!@192.168.128.99/suite-api/api/resources/statkeys?resourceId=00d68281-41bf-43e0-9bad-2afd7a55c480", null,null);
		    String result = HttpClientCalls.httpGet("https://192.168.128.99/suite-api/api/resources/statkeys?resourceId=00d68281-41bf-43e0-9bad-2afd7a55c480", null,null);
		    System.out.println(result);
	
    }
    
    
    private static String composeBasicUrl(String hostIp, String port, String username, String password) {
        String result = null;
        String auth = username + ":" + password + "@";
        result = protocol + auth + hostIp + ":" + port + urlPrefix;

        return result;
    }

}
