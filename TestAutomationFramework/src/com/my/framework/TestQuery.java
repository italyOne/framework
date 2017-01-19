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
	private String databaseDriver = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://127.0.0.1:3306/";
	private String username = "root";
	private String password = "root";
	
	

	public static void main(String[] args) {
		try {
            String url = "jdbc:mysql://127.0.0.1:3306/";
            Connection conn = DriverManager.getConnection(url,"root","root");
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * FROM sys.flights where flightNumber = 1001");
            while ( rs.next() ) {
                int flightNumber = rs.getInt("flightNumber");
                System.out.println(flightNumber);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

	}

}
