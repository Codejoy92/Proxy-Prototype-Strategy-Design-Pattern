package genericCheckpointing.util;

import java.io.IOException;

import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileProcessor {
	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bw = null;;
	FileWriter fw = null;

	public void openFile(String FileName) {
		try {
			fileReader = new FileReader(FileName);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("File not found at location: " + FileName);
			System.exit(1);
		}
	}

}
