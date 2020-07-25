package com.dev.goRest.RestClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.junit.Assert;

import com.dev.goRest.Utility.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	public static Properties prop = null;
	public static FileInputStream fis = null;
	
	/**
	 * 
	 * @return
	 */
	public static Properties configProperty(){
		
		String filePath = System.getProperty("user.dir")+"\\config\\GoRestAPI.properties";
		try {
		    fis = new FileInputStream(new File(filePath));
		    prop = new Properties();
			prop.load(fis);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static String getProperty(String propertyValue){
		
		if(propertyValue.isEmpty()){
			Assert.assertNull(propertyValue);
		}
		Properties prop = configProperty();
		String propVal = prop.getProperty(propertyValue);
		return propVal;
	}
	
	public static String setbaseURI(String baseURI){	
		return RestAssured.baseURI = baseURI;
	}
	
    
	
	/**
	 * @author Digvijay
	 * @return 
	 */
	public static RequestSpecification createhttpRequest(LinkedHashMap<String,String> param){
		
		RequestSpecification httpRequest = null;		
		String baseURI = "",token = "", contentType ="";
		try{ baseURI = param.get("baseURI"); if(baseURI==null) baseURI = ""; }catch(Exception e){}
		try{ token = param.get("access-token"); if(token==null) token = ""; }catch(Exception e){}
		try{ contentType = param.get("ContentType"); if(contentType==null) contentType = ""; }catch(Exception e){}
		
		if(!baseURI.isEmpty() && baseURI!=null)
		{
			if(baseURI.equals("random"))
			{
				RestAssured.baseURI = setbaseURI(getProperty("baseURI"));
			}
			else 
				RestAssured.baseURI = setbaseURI(baseURI);

		}

	if(!token.isEmpty() && token!=null)
	{
		if(token.equals("random"))
		{
			String accesstoken = Utils.generateRandomString(20);
			param.put(token, accesstoken);
		}
		if(String.valueOf(param.get("log")).equals("true"))
		{
			httpRequest = RestAssured.given().log().all().queryParam("access-token", token);
		}
		if(String.valueOf(param.get("log")).equals("false"))
		{
			httpRequest = RestAssured.given().queryParam("access-token", token);
		}
		
	}
	if(!contentType.isEmpty() && contentType!=null)
	{
		switch(contentType.toLowerCase()){
		case "json":
			httpRequest.contentType(ContentType.JSON);
			break;
		case "xml":
			httpRequest.contentType(ContentType.XML);
			break;
		case "text":
			httpRequest.contentType(ContentType.TEXT);
			break;
		case "html":
			httpRequest.contentType(ContentType.HTML);
			break;
		}
	}

	return httpRequest;
	}
	
	public static Response executeAPI(LinkedHashMap<String,String> param,RequestSpecification httpRequest,String httpMethod){
		
		Response response = null;
		httpRequest = createhttpRequest(param);
		switch(httpMethod.toLowerCase()) 
		{
		case "get":
			response =httpRequest.get(getProperty("endPointUrl"));
			break;
		}
		
		return response;
}
	
}
