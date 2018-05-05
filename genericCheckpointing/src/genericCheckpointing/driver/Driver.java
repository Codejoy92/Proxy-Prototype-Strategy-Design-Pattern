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
		int noOfObjects = Integer.parseInt(args[1]);
		// The mode could be "serdeser" or "deser"
		// processing serialization and deserialization
		if (mode.equals("deser")) {
			handler.setFileprocessor(processor);
			((RestoreI) srObject).readObj("XML");
		} else if (mode.equals("serdeser")) {
			// serialization starts
			processor.openFileToWrite(filename);
			handler.setFileprocessor(processor);
			randomValueGenerator(rand, serobjList, srObject, noOfObjects);
			processor.closeFile();
			// serialization ends
			
			// Deserialzaton starts
			processor.openFile(filename);
			handler.setFileprocessor(processor);
			System.out.println("random generated objects");
			((RestoreI) srObject).readObj("XML");
			processor.closeFile();
			// Deserialzaton ends

			// Print all objects created
			serReadObj.addAll(processor.getSerReadObj());
			System.out.println("Objects after deserialization");
			for (SerializableObject obj : serobjList) {
				System.out.println(obj);
			}

			// counting mismatch
			int mismatchCount = 0;
			for (int j = 0; j < serobjList.size(); j++) {
				if (!serobjList.get(j).equals(serReadObj.get(j))) {
					mismatchCount++;
				}
			}
			System.out.println("Mismatch Count is: " + mismatchCount);
		} else {
			System.out.println("No such mode");
			processor.closeFile();
			System.exit(0);
		}
		processor.closeFile();

	}

	private static void randomValueGenerator(Random rand, List<SerializableObject> serobjList, StoreRestoreI srObject,
			int noOfObjects) {
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;
		char myChar = 'A';
		// noOfObjects:number of objects to be deserialized
		for (int i = 0; i < noOfObjects; i++) {
			// random value generation logic
			int value = rand.nextInt(20)+1;
			int myInt = value;
			int myOtherInt = value;
			long myLong = (long) value;
			long myOtherLong = (long) value;
			String myString = "Design patterns" + i;
			boolean myBool = Math.random() < 0.5;

			myFirst = new MyAllTypesFirst(myInt, myOtherInt, myLong, myOtherLong, myString, myBool);
			((StoreI) srObject).writeObj(myFirst, 1, "XML");
			serobjList.add(myFirst);

			double val = 20 * rand.nextDouble();
			double myDouble = val;
			double myOtherDouble = val;
			float myFloat =(float) val;
			short myShort = (short) val;
			myChar += 1;

			mySecond = new MyAllTypesSecond(myDouble, myOtherDouble, myFloat, myShort, myShort, myChar);
			((StoreI) srObject).writeObj(mySecond, 2, "XML");
			serobjList.add(mySecond);
		}
	}

}
