package com.my.framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class TestCVS3 {

	  
	 public static void main(String [] args){
		 try{
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/","root","root");
	            Statement statement = con.createStatement();
	            FileOutputStream fileOut;
	            fileOut = new FileOutputStream("/Users/dvitalii/Desktop/myNew.xls");
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet worksheet = workbook.createSheet("Sheet1");
	            Row row1 = worksheet.createRow((short)0);
	            row1.createCell(0).setCellValue("Flight number");
	            row1.createCell(1).setCellValue("Airline");
	            Row row2 ;
	            ResultSet rs = statement.executeQuery("select * from sys.airlines "
            		+ "inner join sys.flights on airlines.flightNumber = flights.flightNumber "
            		+ "inner join sys.airoports on airoports.airport = flights.arrivalAirport "
            		+ "where flights.arrivalAirport = 'Milan' or 'Helsinki' and airlines.webRegistration = 'yes';");
	            while(rs.next()){
	                int a = rs.getRow();
	                row2 = worksheet.createRow((short)a);
	                row2.createCell(0).setCellValue(rs.getString(1));
	                row2.createCell(1).setCellValue(rs.getString(2));
	            }
	            workbook.write(fileOut);
	            fileOut.flush();
	            fileOut.close();
	            rs.close();
	            statement.close();
	            con.close();
	            System.out.println("Export Success");
	        }catch(ClassNotFoundException e){
	            System.out.println(e);
	        }catch(SQLException ex){
	            System.out.println(ex);
	        }catch(IOException ioe){
	            System.out.println(ioe);
	        }
	   
	 }

}
