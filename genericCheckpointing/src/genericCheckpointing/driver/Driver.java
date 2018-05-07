package genericCheckpointing.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.Utility;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	public static void main(String[] args) {
		int noOfObjects = 0;
		Random rand = new Random();
		Utility util = new Utility();
		ProxyCreator proxy = new ProxyCreator();
		FileProcessor processor = new FileProcessor();
		StoreRestoreHandler handler = new StoreRestoreHandler();
		List<SerializableObject> serobjList = new ArrayList<SerializableObject>();
		List<SerializableObject> serReadObj = new ArrayList<SerializableObject>();

		util.validateFile(args);
		StoreRestoreI srObject = (StoreRestoreI) proxy.createProxy(new Class[] { StoreI.class, RestoreI.class },
				handler);

		String mode = args[0];
		String filename = args[2];
		processor.openFile(filename);
		try {
			noOfObjects = Integer.parseInt(args[1]);
		}catch(NumberFormatException e){
			System.out.println("No of Object should be an integer");
			System.exit(1);
		}
		// The mode could be "serdeser" or "deser"
		// processing serialization and deserialization
		if (mode.equals("deser")) {
			handler.setFileprocessor(processor);
			((RestoreI) srObject).readObj("XML");
		} else if (mode.equals("serdeser")) {
			// serialization starts
			processor.openFileToWrite(filename);
			handler.setFileprocessor(processor);
			util.randomValueGenerator(rand, serobjList, srObject, noOfObjects);
			processor.closeFile();
			// serialization ends
			
			// Print all objects created
			System.out.println("\nRandom generated objects");
			for (SerializableObject obj : serobjList) {
				System.out.println(obj);
			}
			
			// Deserialzaton starts
			processor.openFile(filename);
			handler.setFileprocessor(processor);
			System.out.println("\nObjects after deserialization");
			((RestoreI) srObject).readObj("XML");
			processor.closeFile();
			// Deserialzaton ends

			
			// counting mismatch
			serReadObj.addAll(processor.getSerReadObj());
			int mismatchCount = 0;
			for (int j = 0; j < serobjList.size(); j++) {
				if (!serobjList.get(j).equals(serReadObj.get(j))) {
					mismatchCount++;
				}
			}
			System.out.println("\nMismatch Count is: " + mismatchCount);
		} else {
			System.out.println("No such mode");
			processor.closeFile();
			System.exit(0);
		}
		processor.closeFile();

	}

}
