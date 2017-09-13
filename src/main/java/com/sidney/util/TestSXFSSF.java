package com.sidney.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
public class TestSXFSSF {
	   public static void main(String[] args) throws Throwable {
		   
		   InputStream in = new FileInputStream("D:\\test.xlsx");
		   XSSFWorkbook xw = new XSSFWorkbook(in);
		    SXSSFWorkbook wb = new SXSSFWorkbook(xw, 100);
		    Sheet sheet = null;

		    int size = wb.getNumberOfSheets();
		    System.err.println("size = " + size);
		    
		    for( int sheetIndex = 0; sheetIndex < size ; sheetIndex ++){
		    	 sheet = wb.getSheetAt(0);
		    	 Row tmp1 = sheet.getRow(0);
		    	 System.err.println("tmp1="+ JSON.toJSONString(tmp1));
		    	 Iterator<Row> rowIterator = sheet.rowIterator();
		    	 
		    	 int rowIndex = 0;
		    	
		    	 while(rowIterator.hasNext()){
		    		 Row row = rowIterator.next();
		    		 if(row == null){
		    			 if( 0 == rowIndex){
		    				 System.err.println("Error");
		    				 break;
		    			 }
		    			 continue;
		    		 }
		    		 
		    		 
		    		 
		    		 int maxColumnLen = 4;
		    		 
		    		 String tmp = "";
		    		 
		    		 for(int j = 0; j < maxColumnLen; j++){
		    			 
		    			 Cell cell = row.getCell(j);
		    			 tmp += cell.getStringCellValue();
		    			 tmp += "\t";
		    		 }
		    		 System.err.println(tmp);
		    		 
		    		 rowIndex++;
		    	 }
		    	 System.err.println("totalRow=" + rowIndex);
		    }
		    
		    wb.dispose();
		    in.close();


	   }

}
