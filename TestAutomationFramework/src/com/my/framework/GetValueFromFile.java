package com.my.framework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetValueFromFile {
	private String getValueFromFile() {
		String fileName = "/Users/dvitalii/git/Automation/TestAutomationFramework/configuration/scripts";

		String value = "";
		String line = "";
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) 
				System.out.println(line);

			
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
	

	public static void main(String[] args) {
		GetValueFromFile myInstance = new GetValueFromFile();
		myInstance.getValueFromFile();

	}

}
