package com.qa.goRest.RestAPISuite;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dev.goRest.Listeners.AllureTestListeners;
import com.dev.goRest.RestClient.RestClient;
import com.dev.goRest.Utility.Utils;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners({AllureTestListeners.class})
public class SampleTestSuite extends RestClient {

	Logger log = LogManager.getLogger(SampleTestSuite.class);
	
	@Test(enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Should Display User Details")
	@Story("GoRest_TC001_UserDetailsFlow")
	public void TC001_UserDetails(){
			       
		   log.info("Action Performed on Go Rest API");
	       LinkedHashMap<String,String> param = new LinkedHashMap<String,String>();
	       param.put("baseURI", getProperty("baseURI"));
	       param.put("access-token", getProperty("accesstoken"));
	       param.put("log", "true");
	       RequestSpecification httpRequest = createhttpRequest(param);
	       Response response = executeAPI(param, httpRequest, "GET");
	       response.prettyPrint();
	       
	   }
	
	@Test(enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validate Status Code For Go Rest API")
	@Story("GoRest_TC002_StatusCodeFlow")
	public void TC002_StatusCode(){
		
		   log.info("Action Performed on Go Rest API");
	       LinkedHashMap<String,String> param = new LinkedHashMap<String,String>();
	       param.put("baseURI", getProperty("baseURI"));
	       param.put("access-token", getProperty("accesstoken"));
	       param.put("log", "true");
	       RequestSpecification httpRequest = createhttpRequest(param);
	       Response response = executeAPI(param, httpRequest, "GET");
	      Assert.assertEquals(response.getStatusCode(), Utils.getStatusCode("200"));
	}
	
	@Test(enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validate Status Line For Go Rest API")
	@Story("GoRest_TC003_StatusLineFlow")
	public void TC003_StatusLine(){
		
		   log.info("Action Performed on Go Rest API");
	       LinkedHashMap<String,String> param = new LinkedHashMap<String,String>();
	       param.put("baseURI", getProperty("baseURI"));
	       param.put("access-token", getProperty("accesstoken"));
	       param.put("log", "true");
	       RequestSpecification httpRequest = createhttpRequest(param);
	       Response response = executeAPI(param, httpRequest, "GET");
	       Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validate Headers Displays Or Not")
	@Story("GoRest_TC004_VerifyHeaders_Flow")
	public void TC004_VerifyHeaders(){
		
		   log.info("Action Performed on Go Rest API");
	       LinkedHashMap<String,String> param = new LinkedHashMap<String,String>();
	       param.put("baseURI", getProperty("baseURI"));
	       param.put("access-token", getProperty("accesstoken"));
	       param.put("log", "true");
	       RequestSpecification httpRequest = createhttpRequest(param);
	       Response response = executeAPI(param, httpRequest, "GET");
	       Headers header = response.headers();
	       header.asList().stream().forEach(headr -> System.out.println(headr));
	}
	
	@Test(enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validate Headers Displays Or Not")
	@Story("GoRest_TC005_VerifyHeaders_Flow")
	public void TC005_validateDateFromHeaders(){
		
		   log.info("Action Performed on Go Rest API");
	       LinkedHashMap<String,String> param = new LinkedHashMap<String,String>();
	       param.put("baseURI", getProperty("baseURI"));
	       param.put("access-token", getProperty("accesstoken"));
	       param.put("log", "true");
	       RequestSpecification httpRequest = createhttpRequest(param);
	       Response response = executeAPI(param, httpRequest, "GET");
	       Headers header = response.headers();
	       String date = header.getValue("Date");
	       Assert.assertNotNull(date);
	       System.out.println(date);
	}

}
