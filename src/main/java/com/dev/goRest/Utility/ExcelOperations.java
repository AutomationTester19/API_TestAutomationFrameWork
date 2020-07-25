package com.dev.goRest.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dev.goRest.RestClient.RestClient;

public class ExcelOperations extends RestClient {

	public static FileInputStream fis;
	public static Workbook workbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	
	public static String filePath = System.getProperty("user.dir")+"\\Resources\\testData";
	
	/**
	 * 
	 * @param fileName
	 * @param sheetName
	 * @return
	 */
	public static int getRowCount(String fileName,String sheetName){

		int rowCount;
		String fileExtension;
        try {fis = new FileInputStream(filePath+"\\\\"+fileName);} catch (FileNotFoundException e) {e.printStackTrace();}
        
		if(fileName==null)
			fileName ="";		
		
		fileExtension = fileName.substring(fileName.indexOf("."));   
	   if(fileExtension.contains(".xlsx"))
		try {workbook = new XSSFWorkbook(fis);} catch (IOException e) {e.printStackTrace();}
	   
	   else if(fileExtension.contains(".xls"))   
		   try {workbook = new HSSFWorkbook(fis);} catch (IOException e) {e.printStackTrace();}
	   
	   sheet = workbook.getSheet(sheetName);
	   rowCount = sheet.getLastRowNum();
	   if(rowCount==0)
		   return 0;
	   
	   return rowCount;
	}
	
    public static int getNumberOfColumns(String fileName,String sheetName){
		
    	int colSize=0;
		try{
			colSize = sheet.getRow(0).getLastCellNum();
			if(colSize==0)
				return 0;
			
		}catch(NullPointerException e) {
			e.printStackTrace();
		}	
		return colSize;
	}
	/**
	 * @author Digvijay Anney
	 * @description : To Get Excel Column/Header Names
	 * @param fileName
	 * @param sheetName
	 * @return data
	 */
	public static LinkedHashMap<String, Integer> getExcelColumnName(String fileName,String sheetName){
		
		LinkedHashMap<String,Integer> data = new LinkedHashMap<String,Integer>();
		int colSize = getNumberOfColumns(fileName, sheetName);
		row = sheet.getRow(colSize);
		int count =0;
		while(count<colSize){		
			data.put(row.getCell(count).getStringCellValue(),count);		
			count++;
		}
		return data;
	}
	
	
	public static LinkedHashMap<Integer, LinkedHashMap<String, String>> getExcelDataUsingMap(String fileName,String sheetName){
		
		LinkedHashMap<Integer,LinkedHashMap<String,String>> excelData = new LinkedHashMap<Integer,LinkedHashMap<String,String>>();
		LinkedHashMap<String, Integer> data = getExcelColumnName(fileName, sheetName);
		LinkedHashMap<String,String> innerMap = new LinkedHashMap<String,String>();
		int count =1;
		for(String excelheadr : data.keySet())
		{		
			// excelheader --- displays only excel header
			row = sheet.getRow(count);
			for(int col = 1; col<row.getLastCellNum();col++)
			{
				innerMap.put(excelheadr, row.getCell(col).getStringCellValue());
			}
			excelData.put(count, innerMap);
		}
		System.out.println(excelData);
		return excelData;
	}
}
