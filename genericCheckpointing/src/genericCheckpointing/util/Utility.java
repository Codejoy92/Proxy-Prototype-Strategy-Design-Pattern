package genericCheckpointing.util;

import java.util.List;
import java.util.Random;

import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;

public class Utility {
	public void validateFile(String[] args) {
		try {
			if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || null == args) {
				System.err.println("Error: invalid arguments");
				System.exit(0);
			}
		} catch (Exception e) {
			System.err.println("File not found");
			System.exit(1);
		}
	}
	

	public void randomValueGenerator(Random rand, List<SerializableObject> serobjList, StoreRestoreI srObject,
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
