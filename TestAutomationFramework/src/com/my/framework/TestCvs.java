package com.my.framework;

import java.io.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.beans.Statement;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TestCvs {

	
	
	

	public static void main(String[] args) {
		XSSFWorkbook workbook = new XSSFWorkbook(); 
        try
        {
            Class.forName("org.postgresql.Driver");
             Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/","root","root");        
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
             Statement stmt =  c.createStatement();
              String MyQuery="select airlines.airline from sys.airlines "
              		+ "inner join sys.flights on airlines.flightNumber = flights.flightNumber "
              		+ "inner join sys.airoports on airoports.airport = flights.arrivalAirport "
              		+ "where flights.arrivalAirport = 'Milan' or 'Helsinki' and airlines.webRegistration = 'yes';"; 
     XSSFSheet sheet = workbook.createSheet("NewData");
        
     //This data needs to be written (Object[])
     Map<String, Object[]> data = new TreeMap<String, Object[]>();
     data.get(MyQuery); 
     //Iterate over data and write to sheet
     Set<String> keyset = data.keySet();
     int rownum = 0;
     for (String key : keyset)
     {
         Row row = sheet.createRow(rownum++);
         Object [] objArr = data.get(key);
         int cellnum = 0;
         for (Object obj : objArr)
         {
            Cell cell = row.createCell(cellnum++);
            if(obj instanceof String)
                 cell.setCellValue((String)obj);
             else if(obj instanceof Integer)
                 cell.setCellValue((Integer)obj);
         }
     }
             c.commit();
             c.close();
     
         }
  catch ( NullPointerException e ) 
             {
              System.out.println( " is not updated because cells cannot be left null/empty ");     
             }
             
   catch ( Exception e ) 
                {
                  System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                  System.exit(0);       
                }
     
     try
     {
         //Write the workbook in file system
         FileOutputStream out = new FileOutputStream(new File("/Users/dvitalii/Desktop/myNew.xlsx"));
         workbook.write(out);
         out.close();
         System.out.println("first_excel.xlsx written successfully on disk.");
     } 
     catch (Exception e) 
     {
         e.printStackTrace();
     }
 }
		
		
		

	}


