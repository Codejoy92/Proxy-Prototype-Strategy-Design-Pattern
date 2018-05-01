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
		
		MyAllTypesFirst myFirst;
		MyAllTypesSecond mySecond;
		String mode = args[0];
		String filename = args[2];
		processor.openFile(filename);
		int noOfObjects = Integer.parseInt(args[1]);
		
		// processing serialization and deserialization
		if (mode.equals("deser")) {
			XMLDeserialization deserialisingXML = new XMLDeserialization(processor);
			for (int i = 0; i < noOfObjects; i++)
				SerobjList.add(deserialisingXML.processFile());
			for (SerializableObject obj : SerobjList)
				System.out.println(obj);

		} else if (mode.equals("serdeser")) {
			processor.openFile(filename);
			handler.setFileprocessor(processor);

			for (int i = 0; i < noOfObjects; i++) {
				int value = rand.nextInt(20);
				int myInt = value;
				int myOtherInt = value;
				long myLong = (long) value;
				long myOtherLong = (long) value;
				String myString = "Design patterns " + i;
				boolean myBool = Math.random() < 0.5;

				if (value < 10) {
					myFirst = new MyAllTypesFirst(myString, myBool);
				}
				else {
					myFirst = new MyAllTypesFirst(myInt, myOtherInt, myLong, myOtherLong, myString, myBool);
				}
				double val = rand.nextDouble();
				double myDouble = val;
				double myOtherDouble = val;
				float myFloat = rand.nextFloat();
				short myShort = (short) rand.nextInt(20);
				char myChar = (char) (rand.nextInt(26) + 'z');

				if (val < 10) {
					mySecond = new MyAllTypesSecond(myFloat, myShort, myChar);
				}
				else {
					mySecond = new MyAllTypesSecond(myDouble, myOtherDouble, myFloat, myShort, myChar);
				}
				SerobjList.add(myFirst);
				SerobjList.add(mySecond);

				((StoreI) srObject).writeObj(myFirst,1, "XML");
				((StoreI) srObject).writeObj(mySecond,2, "XML");

			}

		} else {
			System.out.println("No such mode");
			processor.closeFile();
			System.exit(0);
		}
		processor.closeFile();

	}

}
