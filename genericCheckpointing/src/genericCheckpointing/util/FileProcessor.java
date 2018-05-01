package genericCheckpointing.util;

import java.io.IOException;

import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileProcessor {
	FileReader fileReader;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	FileWriter fileWriter;

	public void openFile(String FileName) {
		try {
			fileReader = new FileReader(FileName);
			bufferedReader = new BufferedReader(fileReader);
			
			fileWriter = new FileWriter(FileName);
			bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			System.out.println("File not found at location: " + FileName);
			System.exit(1);
		}
	}
	
	public void closeFile() {
		// TODO Auto-generated method stub
		
	}

	public void writeToFile(String string) {
		// TODO Auto-generated method stub
		
	}

	public String readLine() {
		String line = null;
		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println("Error while reading line");
			System.exit(1);
		}
		return line;
	}

}
