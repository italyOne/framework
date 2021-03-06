package com.my.framework;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Database {
	private String databaseURL = "";
	private String username = "";
	private String password = "";
	private String pathToScripts = "";

	public Database(String databaseURL, String username, String password, String pathToScripts) {
		this.databaseURL = databaseURL;
		this.username = username;
		this.password = password;
		this.pathToScripts = pathToScripts;

	}

	public void createTablesInDatabaseAndInsertData() {

		String value = "";
		String line = "";
		try {
			FileInputStream fstream = new FileInputStream(pathToScripts);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while ((value = br.readLine()) != null) {

				try {
					Connection myConn = DriverManager.getConnection(databaseURL, username, password);
					Statement myStat = myConn.createStatement();

					String myQuery = value;

					myStat.executeUpdate(myQuery);
					System.out.println("Table created sucessfully");

				} catch (Exception exc) {
					System.out.println("Your table already exists");
				}

			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + pathToScripts + "'");

		} catch (IOException ex) {
			System.out.println("Error reading file '" + pathToScripts + "'");

		}

	}

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
			
			 ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String []columnName = new String[columnCount];
			for (int i = 1; i <= columnCount; i++ ) {
			 columnName[i] = rsmd.getColumnName(i);
			 ;

			  expectedResult += rs.getString(columnName[i]) + ";";
			  
			}
			expectedResult+="\n";
			 

			
		}

		return expectedResult;

	}
	public static void main(String [] args){
		Database myInstance = new Database("jdbc:mysql://127.0.0.1:3306/", "root", "root", "/Users/dvitalii/Documents/Eclipse/workspace/TestAutomationFramework/configuration/scripts");
		myInstance.createTestData();
		
		
	}
	

}
