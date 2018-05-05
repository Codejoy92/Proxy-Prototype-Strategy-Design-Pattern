package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
	FileReader fileReader;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	FileWriter fileWriter;
	List<SerializableObject> serReadObj = new ArrayList<SerializableObject>();

	/**
	 * This method is used to get the list and returns it
	 * 
	 * @return serReadObj
	 * 
	 */
	public List<SerializableObject> getSerReadObj() {
		return serReadObj;
	}

	/**
	 * This method is used to set the list and returns it
	 * 
	 */
	public void setSerReadObj(List<SerializableObject> serReadObj) {
		this.serReadObj = serReadObj;
	}

	/**
	 * This method is used to opens the provided file
	 * 
	 * @param FileName
	 * 
	 */
	public void openFile(String FileName) {
		try {
			bufferedReader = new BufferedReader(new FileReader(FileName));
		} catch (IOException e) {
			System.out.println("File not found at location: " + FileName);
			System.exit(1);
		}
	}

	/**
	 * This method is used to close the provided open file
	 * 
	 */
	public void closeFile() {
		try {
			if (null != bufferedReader)
				this.bufferedReader.close();
			if (null != fileReader)
				this.fileReader.close();
			if (null != bufferedWriter)
				this.bufferedWriter.close();
			if (null != fileWriter)
				this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to write the content to the open file
	 * 
	 */
	public void writeToFile(String str) {
		try {
			bufferedWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * This method is used to Read the content of the open file
	 * 
	 */
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

	/**
	 * This method is used to open the file to write the content
	 * 
	 */
	public void openFileToWrite(String filename) {

		try {
			bufferedWriter = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e) {
			System.out.println("File not found at location: " + filename);
			System.exit(1);
		}

	}

}
