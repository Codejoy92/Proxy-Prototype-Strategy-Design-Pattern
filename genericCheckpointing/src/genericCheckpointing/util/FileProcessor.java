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
			 bufferedReader = new BufferedReader(new FileReader(FileName));	
		} catch (IOException e) {
			System.out.println("File not found at location: " + FileName);
			System.exit(1);
		}
	}
	
	public void closeFile() {
		try{
			if(null!= bufferedReader)
		    this.bufferedReader.close();
			if(null!= fileReader)
		    this.fileReader.close();
			if(null!= bufferedWriter)
		    this.bufferedWriter.close();
			if(null!= fileWriter)
		    this.fileWriter.close();
		}catch(IOException e){
		    e.printStackTrace();
		}
		
	}

	public void writeToFile(String FileName) {
		try {
			 bufferedWriter = new BufferedWriter(new FileWriter(FileName));
		} catch (IOException e) {
			System.out.println("File not found at location: " + FileName);
			System.exit(1);
		}
		
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
