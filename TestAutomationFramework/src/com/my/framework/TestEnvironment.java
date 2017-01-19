package com.my.framework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestEnvironment {
	

	public static final String DATA_FILE = "/Users/dvitalii/Documents/Eclipse/workspace/TestAutomationFramework/configuration/framework.ini";
	private String pathToExcelFile = "";
	private String pathToScripts = "";
	private String reportingFolder = "";
	private String databaseDriver = "";
	private String databaseURL = "";
	private String username = "";
	private String password = "";
	

	
		private String getValueFromFile(String fileName, String keyWord) {

			String value = "";
			String line = "";
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
if (keyWord.length()<line.length()) {
					if (line.substring(0, keyWord.length()).equals(keyWord)) {
						value = line.substring(keyWord.length() + 3, line.length());
					}
}
				}
				bufferedReader.close();
				return value;

			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + fileName + "'");
				return value;
			} catch (IOException ex) {
				System.out.println("Error reading file '" + fileName + "'");
				return value;
			}
}
		
	
	public void initialiseEnvironmentVariables() {
		pathToExcelFile = getValueFromFile(DATA_FILE, "pathToExcelFile");
		pathToScripts = getValueFromFile(DATA_FILE, "pathToScripts");
		reportingFolder = getValueFromFile(DATA_FILE, "reportingFolder");
		databaseDriver = getValueFromFile(DATA_FILE, "databaseDriver");
		databaseURL = getValueFromFile(DATA_FILE, "databaseURL");
		username = getValueFromFile(DATA_FILE, "username");
		password = getValueFromFile(DATA_FILE, "password");

	}

	public void createEnvironment() {
		Database myDatabase = new Database(databaseURL, username, password, pathToScripts);
		//myDatabase.createTablesInDatabaseAndInsertData();
		myDatabase.createTestData();
		
	}

	public void runTests() {
		//Prepare Excel file with test cases
		//Create arrayList of arrays, get data from Excel to this arraylist
		//Execute queries from arrayList and write result to array in arrayList 
		//Compare results
	}
	
	
	

}
