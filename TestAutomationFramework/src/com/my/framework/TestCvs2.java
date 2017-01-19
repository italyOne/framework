package com.my.framework;
import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class TestCvs2 {

	public static void main(String[] args) {
		 try {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             Connection connection = DriverManager.getConnection(
                             "jdbc:mysql://127.0.0.1:3306/","root","root");
             PreparedStatement psmnt = null;
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select airlines.airline from sys.airlines "
              		+ "inner join sys.flights on airlines.flightNumber = flights.flightNumber "
              		+ "inner join sys.airoports on airoports.airport = flights.arrivalAirport "
              		+ "where flights.arrivalAirport = 'Milan' or 'Helsinki' and airlines.webRegistration = 'yes';");

             HSSFWorkbook wb = new HSSFWorkbook();
             HSSFSheet sheet = wb.createSheet("Sheet1");
             HSSFRow rowhead = sheet.createRow((short) 0);
             rowhead.createCell((short) 0).setCellValue("Airlines");
//             rowhead.createCell((short) 1).setCellValue("Name");
//             rowhead.createCell((short) 2).setCellValue("Class");
//             rowhead.createCell((short) 3).setCellValue("Marks");
//             rowhead.createCell((short) 4).setCellValue("Grade");

             int index = 1;
             while (rs.next()) {

                     HSSFRow row = sheet.createRow((short) index);
                     row.createCell((short) 0).setCellValue(rs.getInt(1));
//                     row.createCell((short) 1).setCellValue(rs.getString(2));
//                     row.createCell((short) 2).setCellValue(rs.getString(3));
//                     row.createCell((short) 3).setCellValue(rs.getInt(4));
//                     row.createCell((short) 4).setCellValue(rs.getString(5));
                     index++;
             }
             FileOutputStream fileOut = new FileOutputStream("/Users/dvitalii/Desktop/myNew.xlsx");
             wb.write(fileOut);
             fileOut.close();
             System.out.println("Data is saved in excel file.");
             rs.close();
             connection.close();
     } catch (Exception e) {
     }

            
        
		
		
		
		
	}
}
