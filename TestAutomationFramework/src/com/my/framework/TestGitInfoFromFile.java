package com.my.framework;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class TestGitInfoFromFile {
	//private String pathToExcelFile = "";
	private String pathToScripts = "/Users/dvitalii/Documents/Eclipse/workspace/TestAutomationFramework/configuration/scripts";
	//private String reportingFolder = "";
	private String databaseDriver = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://127.0.0.1:3306/";
	private String username = "root";
	private String password = "root";
	
	
//	public void createTablesInDatabaseAndInsertData() {
//
//		String value = "";
//		String line = "";
//		try {
//			FileInputStream fstream = new FileInputStream(pathToScripts);
//			DataInputStream in = new DataInputStream(fstream);
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//			while ((value = br.readLine()) != null) {
//
//				try {
//					Connection myConn = DriverManager.getConnection(databaseURL, username, password);
//					Statement myStat = myConn.createStatement();
//
//					String myQuery = value;
//
//					myStat.executeUpdate(myQuery);
//					System.out.println("Table created sucessfully");
//
//				} catch (Exception exc) {
//					System.out.println("Your table already exists");
//				}
//
//			}
//		} catch (FileNotFoundException ex) {
//			System.out.println("Unable to open file '" + pathToScripts + "'");
//
//		} catch (IOException ex) {
//			System.out.println("Error reading file '" + pathToScripts + "'");
//
//		}
//
//	}

	public void createTestData() {

		String value = "";
		String line = "";

		try {
			Connection myConn = DriverManager.getConnection(databaseURL, username, password);
			Statement myStat = myConn.createStatement();

			String query = "SELECT * FROM sys.flights";
			Statement st = myConn.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println(prepareTestData(rs));

		} catch (Exception exc) {
			System.out.println("Your table already exists");
		}

	}

	private String prepareTestData(ResultSet rs) throws SQLException {
		String expectedResult = "";
		while (rs.next()) {
			int id = rs.getInt("departureAiroport");
			String firstName = rs.getString("departureAiroport");

			// print the results
			expectedResult += id + ";" + firstName + ";";
		}

		return expectedResult;

	}
	public static void main(String [] args){
		TestGitInfoFromFile myInstance = new TestGitInfoFromFile();
		myInstance.createTestData();
	}
	

}
