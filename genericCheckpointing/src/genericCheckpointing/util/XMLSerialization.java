package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.server.SerStrategy;

public class XMLSerialization implements SerStrategy {
	FileProcessor fileProcessor;
	SerializeTypes ser;
	/**
	 *This is a paramterized constructor which takes in FileProcessor object 
	 *its used for reading the content of file line by line
	 *@param fileProcessorIn fileProcessorIn
	 *
	 */
	public XMLSerialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}
	/**
	 *This method is declared in serStrategy which is used during serialization
	 *This method takes the random generated values and class descriptions
	 *and creates xml file out of it
	 *
	 */
	@Override
	public void processInput(SerializableObject obj) {
		StringBuilder builder = new StringBuilder();
		ser = new SerializeTypes();
		Class<?> cls = obj.getClass();
		builder.append("<DPSerialization>");
		builder.append(System.getProperty("line.separator"));
		builder.append(ser.getComplexTypeTag(cls.getName()));
		builder.append(System.getProperty("line.separator"));
		for (Field field : cls.getDeclaredFields()) {
			try {
				if (String.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(ser.getDataTypeTag(field.getName(), "string", invokeRet.toString()));
					builder.append(System.getProperty("line.separator"));
				} else if (boolean.class == field.getType()) {
					Method getterMethod = cls.getMethod("is" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(ser.getDataTypeTag(field.getName(), "boolean", invokeRet.toString()));
					builder.append(System.getProperty("line.separator"));
				} else if (int.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Integer.parseInt(invokeRet.toString()) >= 10) {
						builder.append(ser.getDataTypeTag(field.getName(), "int", invokeRet.toString()));
						builder.append(System.getProperty("line.separator"));
					}
				} else if (long.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Long.parseLong(invokeRet.toString()) >= 10) {
						builder.append(ser.getDataTypeTag(field.getName(), "long", invokeRet.toString()));
						builder.append(System.getProperty("line.separator"));
					}
				} else if (float.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(ser.getDataTypeTag(field.getName(), "float", invokeRet.toString()));
					builder.append(System.getProperty("line.separator"));
				} else if (double.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Double.parseDouble(invokeRet.toString()) >= 10) {
						builder.append(ser.getDataTypeTag(field.getName(), "double", invokeRet.toString()));
						builder.append(System.getProperty("line.separator"));
					}
				} else if (char.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(ser.getDataTypeTag(field.getName(), "char", invokeRet.toString()));
					builder.append(System.getProperty("line.separator"));
				} else if (short.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(ser.getDataTypeTag(field.getName(), "short", invokeRet.toString()));
					builder.append(System.getProperty("line.separator"));
				}
			} catch (IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		builder.append("</complexType>");
		builder.append(System.getProperty("line.separator"));
		builder.append("</DPSerialization>");
		builder.append(System.getProperty("line.separator"));
		fileProcessor.writeToFile(builder.toString());
	}
	/**
	 *This method is declared in serStrategy which is used during Deserialization
	 *this method parses the given xml format and creates object out of it using reflection
	 *@return null
	 */
	@Override
	public SerializableObject processInputDeser() {
		return null;
	}

}
