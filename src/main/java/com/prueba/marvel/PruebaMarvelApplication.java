package com.prueba.marvel;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaMarvelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaMarvelApplication.class, args);
	
	}
	
	public static String sendPOST(String url,JSONObject inputJson) throws IOException {
		//Does a post request
	    String result = "";
	    HttpPost post = new HttpPost(url);
		StringEntity stringEntity = new StringEntity(inputJson.toString());
		post.setEntity(stringEntity);
			
		post.setHeader("Accept", "application/json");
	    post.setHeader("Content-type", "application/json");
	    try (CloseableHttpClient httpClient = HttpClients.createDefault();
	    	CloseableHttpResponse response = httpClient.execute(post)){
	    	result = EntityUtils.toString(response.getEntity());
	    }
	    return result;
	}
	    
	public static JSONObject sendGET(String url,String queryAPI) throws IOException, ParseException, JSONException {
		//Does a get request with parameters
		JSONObject result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try
		{
			HttpGet request = new HttpGet(url + "?" + queryAPI);
			CloseableHttpResponse response = httpClient.execute(request);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = new JSONObject(EntityUtils.toString(entity));
				}
				return result;
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}

	public static JSONArray sendGET(String url) throws IOException, ParseException, JSONException {
		//Does a get request without parameters
    	JSONArray result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

    	try
    	{

    		HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	HttpEntity entity = response.getEntity();
    			if (entity != null) {
                	result = new JSONArray(EntityUtils.toString(entity));
                }
    	        return result;

            } finally {
                response.close();
            }

        } finally {
            httpClient.close();
        }

    }

}

