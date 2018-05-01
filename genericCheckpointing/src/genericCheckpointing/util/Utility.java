package genericCheckpointing.util;
import java.util.ArrayList;
import java.util.List;

public class Utility {
	public void validateFile(String[] args) {
		
		if(args.length != 3) {
		    	System.out.println("Incorrect number of arguments..!!!");
		    	System.exit(0);
		    }
	}
}
