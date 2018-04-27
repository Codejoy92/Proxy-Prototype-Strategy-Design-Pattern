package genericCheckpointing.util;
import java.util.ArrayList;
import java.util.List;

public class Utility {
	public String mode;
	public int noOfObjects;
	public String filename;
	
	FileProcessor processor = new FileProcessor();
	
	public void validateFile(String[] args) {
		
		if(args.length != 3) {
		    	System.out.println("Incorrect number of arguments..!!!");
		    	System.exit(0);
		    }
		mode = args[0];
	    noOfObjects = Integer.parseInt(args[1]);
	    filename = args[2];
	}
	public String getMode() {
		return mode;
	}
	public int getNoOfObjects() {
		return noOfObjects;
	}
	public String getFilename() {
		return filename;
	}
	public void process() {
		
		
		if(mode.equals("deser")) {
			List<SerializableObject> objectToDeser = new ArrayList<SerializableObject>();
			processor.openFile(filename);
		}else if(mode.equals("serdeser")) {
			
		}else {
			
		}
		
	}
}
