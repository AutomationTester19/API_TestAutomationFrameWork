package com.qa.goRest.RestAPISuite;


import org.testng.annotations.Test;

import com.dev.goRest.RestClient.RestClient;
import com.dev.goRest.Utility.ExcelOperations;

public class ExcelVerification extends RestClient{

	
	@Test
	public void checkOne(){
		
		
		    ExcelOperations.getExcelColumnName("userDetails.xlsx", "users");
		
	}
}
