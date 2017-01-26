package com.my.framework;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class NewCSV {

	public static void main(String[] args) {
		String filename = "/Users/dvitalii/Desktop/myExport.csv";
		try {
			FileWriter fw = new FileWriter(filename);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", "root");
			String query = "select * from sys.flights";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				fw.append(rs.getString(1));
				fw.append(',');
				fw.append(rs.getString(2));
				fw.append(',');
				fw.append(rs.getString(3));
				fw.append(',');
				fw.append(rs.getString(4));
				fw.append(',');
				fw.append(rs.getString(5));
				fw.append(',');
				fw.append(rs.getString(6));
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
