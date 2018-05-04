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
import genericCheckpointing.util.XMLDeserialization;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	public static void main(String[] args) {
		Random rand = new Random();
		Utility util = new Utility();
		ProxyCreator proxy = new ProxyCreator();
		FileProcessor processor = new FileProcessor();
		StoreRestoreHandler handler = new StoreRestoreHandler();
		List<SerializableObject> SerobjList = new ArrayList<SerializableObject>();

		util.validateFile(args);
		StoreRestoreI srObject = (StoreRestoreI) proxy.createProxy(new Class[] { StoreI.class, RestoreI.class },
				handler);

		MyAllTypesFirst myFirst = null;
		MyAllTypesSecond mySecond = null;
		String mode = args[0];
		String filename = args[2];
		processor.openFile(filename);
		int noOfObjects = Integer.parseInt(args[1]);
		// noOfObjects:number of objects to be deserialized..
		// The mode could be "serdeser" or "deser"
		// processing serialization and deserialization
		if (mode.equals("deser")) {
			handler.setFileprocessor(processor);
			((RestoreI) srObject).readObj("XML");
		} else if (mode.equals("serdeser")) {
			processor.openFileToWrite(filename);
			handler.setFileprocessor(processor);

			for (int i = 0; i < noOfObjects; i++) {
				// random value generation logic
				int value = rand.nextInt(20);
				int myInt = value;
				int myOtherInt = value;
				long myLong = (long) value;
				long myOtherLong = (long) value;
				String myString = "Design patterns" + i;
				boolean myBool = Math.random() < 0.5;

				myFirst = new MyAllTypesFirst(myInt, myOtherInt, myLong, myOtherLong, myString, myBool);
				if (value >= 10) {
					((StoreI) srObject).writeObj(myFirst, 1, "XML");
				}
				SerobjList.add(myFirst);
				
				double val = 20 * rand.nextDouble();
				double myDouble = val;
				double myOtherDouble = val;
				float myFloat = rand.nextFloat();
				short myShort = (short) rand.nextInt(20);
				char myChar = (char) (rand.nextInt(26) + 'z');
				
				mySecond = new MyAllTypesSecond(myDouble, myOtherDouble, myFloat, myShort, myShort, myChar);
				if (val >= 10) {
					((StoreI) srObject).writeObj(mySecond, 2, "XML");
				}
				SerobjList.add(mySecond);
			}
			processor.closeFile();
			//serialization completed
			//Deserialzaton starts
			processor.openFile(filename);
			handler.setFileprocessor(processor);
			System.out.println("Total objects whose values are more than 10");
			((RestoreI) srObject).readObj("XML");
			
			System.out.println("Total objects Created");
			for(SerializableObject obj : SerobjList) {
				System.out.println(obj);
			}
		} else {
			System.out.println("No such mode");
			processor.closeFile();
			System.exit(0);
		}
		processor.closeFile();

	}

}
