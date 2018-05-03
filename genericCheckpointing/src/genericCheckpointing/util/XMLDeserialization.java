package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.server.SerStrategy;

public class XMLDeserialization implements SerStrategy {
	FileProcessor fileProcessor;
	DeserializeTypes deser;

	public XMLDeserialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}

	@Override
	public void processInput(SerializableObject obj) {
	}

	@Override
	public SerializableObject processFile() {

		SerializableObject object = null;
		String line = "";
		while (line != null) {
			line = fileProcessor.readLine();

			if (line != null && line.trim().equals("<DPSerialization>")) {
					Class<?> className = null;
					line = fileProcessor.readLine();
					do {
						try {
							//Used reflection to create the object depending on the value in the complexType element.
							//Parsed the names of the data members and then invoke the corresponding setX method to set the value for that data member.
							//java reflection is used to invoke method objects
							if (line.contains("myString")) {
								String value = deser.parseLine(line);
								Class[] paramString = new Class[1];	
								paramString[0] = String.class;
								Method method = className.getDeclaredMethod("setMyString", paramString);
								method.invoke(object, value);

							} else if (line.contains("myBool")) {
								boolean value = deser.parseBoolType(line);
								Class[] paramBool = new Class[1];
								paramBool[0] = Boolean.TYPE;
								Method method = className.getDeclaredMethod("setMyBool", paramBool);
								method.invoke(object, value);

							} else if (line.contains("complexType") && !line.trim().equals("</complexType>")) {
								className = Class.forName(deser.parseComplexTag(line));
								object = (SerializableObject) className.newInstance();
							} else if (line.contains("myInt")) {
								int intValue = deser.parseIntType(line);
								Class[] paramInt = new Class[1];
								paramInt[0] = Integer.TYPE;
								Method method = className.getDeclaredMethod("setMyInt",  paramInt);
								method.invoke(object, intValue);

							} else if (line.contains("myOtherInt")) {
								int intValue = deser.parseIntType(line);
								Class[] paramInt = new Class[1];
								paramInt[0] = Integer.TYPE;
								Method method = className.getDeclaredMethod("setMyOtherInt", paramInt);
								method.invoke(object, intValue);

							}

							if (line.contains("myLong")) {
								long value = deser.parseMyLong(line);

								Class[] paramLong = new Class[1];
								paramLong[0] = Long.TYPE;
								Method method = className.getDeclaredMethod("setMyLong", paramLong);
								method.invoke(object, value);

							}

							if (line.contains("myOtherLong")) {
								long value = deser.parseMyLong(line);
								Class[] paramLong = new Class[1];
								paramLong[0] = Long.TYPE;
								Method method = className.getDeclaredMethod("setMyOtherLong", paramLong);
								method.invoke(object, value);

							}

							// MyAllTypesSecond
							if (line.contains("myDouble")) {
								double value = deser.parseDoubleType(line);
								Class[] paramDouble = new Class[1];
								paramDouble[0] = Double.TYPE;
								Method method = className.getDeclaredMethod("setMyDouble", paramDouble);
								method.invoke(object, value);
							}

							if (line.contains("myOtherDouble")) {
								double value = deser.parseDoubleType(line);
								Class[] paramDouble = new Class[1];
								paramDouble[0] = Double.TYPE;
								Method method = className.getDeclaredMethod("setMyOtherDouble", paramDouble);
								method.invoke(object, value);

							}

							if (line.contains("myFloat")) {
								float value = deser.parseFloatType(line);
								Class[] paramFloat = new Class[1];
								paramFloat[0] = Float.TYPE;
								Method method = className.getDeclaredMethod("setMyFloat", paramFloat);
								method.invoke(object, value);

							}

							if (line.contains("myShort")) {
								short value = deser.parseShortType(line);
								Class[] paramShort = new Class[1];
								paramShort[0] = Short.TYPE;
								Method method = className.getDeclaredMethod("setMyShort", paramShort);
								method.invoke(object, value);

							}

							if (line.contains("myChar")) {
								String value = deser.parseLine(line);
								Class[] paramChar = new Class[1];
								paramChar[0] = char.class;
								Method method = className.getDeclaredMethod("setMyChar", paramChar);
								method.invoke(object, value.charAt(0));

							}

						} catch (NoSuchMethodException | IllegalAccessException | SecurityException
								| IllegalArgumentException | ClassNotFoundException | InvocationTargetException
								| InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						line = fileProcessor.readLine();
					} while (!line.trim().equals("</DPSerialization>"));
			}
			break;
		}

		return object;
	}

}
