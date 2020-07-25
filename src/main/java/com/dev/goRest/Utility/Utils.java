package com.dev.goRest.Utility;

import org.apache.commons.lang3.RandomStringUtils;

import com.dev.goRest.RestClient.RestClient;

public class Utils extends RestClient{

	public static int getStatusCode(String statuscode){
		
		int statusCode = 0;
		switch(statuscode.trim()){
		case "200":
			statusCode = Integer.valueOf(statuscode);
		case "402":
			statusCode = Integer.valueOf(statuscode);
		}
		
		return statusCode;
	}
	
	public static String generateRandomString(int count){
		String randomStr = RandomStringUtils.randomAlphanumeric(count);
		return randomStr;
	}
	
}
