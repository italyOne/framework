package com.my.framework;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestQuery {
	
	
	

	public static void main(String[] args) {
		
		try {
           
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/","root","root");
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("select airlines.airline from sys.airlines "
            		+ "inner join sys.flights on airlines.flightNumber = flights.flightNumber "
            		+ "inner join sys.airoports on airoports.airport = flights.arrivalAirport "
            		+ "where flights.arrivalAirport = 'Milan' or 'Helsinki' and airlines.webRegistration = 'yes';");
            while ( rs.next() ) {
                String airline = rs.getString("airline");
                System.out.println(airline);
            }
            
            
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		

	}
	
	
	

}
