package com.my.framework;

import java.beans.Statement;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TestCvs {

	public static void main(String[] args) {
		 String filename ="Desktop:test.csv";
	        try {
	            FileWriter fw = new FileWriter(filename);
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            Connection conn = DriverManager.getConnection("jdbc:mysql://h04.hvosting.ua:3306/testclub", "testclub", "testclub");
	            String query = "select * from dv_flights";
	            Statement stmt = (Statement) conn.createStatement();
	            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	            while (rs.next()) {
	                fw.append(rs.getString(1));
	                fw.append(',');
	                fw.append(rs.getString(2));
	                fw.append(',');
	                fw.append(rs.getString(3));
	                fw.append('\n');
	               }
	            fw.flush();
	            fw.close();
	            conn.close();
	            System.out.println("CSV File is created successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	}


