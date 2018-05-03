package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericCheckpointing.server.SerStrategy;

public class XMLSerialization implements SerStrategy{
	FileProcessor fileProcessor;
	public XMLSerialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}
	@Override
	public void processInput(SerializableObject obj) {
		StringBuilder builder = new StringBuilder();

		Class<?> cls = obj.getClass();
		builder.append("Serialization starts\n");
		builder.append(getComplexTypeTag(cls.getName()));
		for (Field field : cls.getDeclaredFields()) {

			try {
				if (String.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(getDataTypeTag(field.getName(), "string", invokeRet.toString()));
				} else if (boolean.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(getDataTypeTag(field.getName(), "boolean", invokeRet.toString()));
				} else if ( int.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Integer.parseInt(invokeRet.toString()) >= 10)
						builder.append(getDataTypeTag(field.getName(), "int", invokeRet.toString()));
				} else if (long.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Long.parseLong(invokeRet.toString())>= 10)
						builder.append(getDataTypeTag(field.getName(), "long", invokeRet.toString()));
				} else if (float.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(getDataTypeTag(field.getName(), "float", invokeRet.toString()));
				} else if (double.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					if (Double.parseDouble(invokeRet.toString()) >= 10)
						builder.append(getDataTypeTag(field.getName(), "double", invokeRet.toString()));
				} else if (char.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(getDataTypeTag(field.getName(), "char", invokeRet.toString()));
				} else if (short.class == field.getType()) {
					Method getterMethod = cls.getMethod("get" + field.getName());
					Object invokeRet = getterMethod.invoke(obj);
					builder.append(getDataTypeTag(field.getName(), "short", invokeRet.toString()));
				}

			} catch (IllegalArgumentException |NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fileProcessor.writeToFile(builder.toString());
	}
	
	public Object getDataTypeTag(String name, String dataType, String value) {
		String tag = "<" + name + " xsi:type=\"xsd:" + dataType + "\">" + value + "</" + name + ">\n";
		return tag;
	}

	public Object getComplexTypeTag(String input) {
		String tag = "<complexType xsi:type=\"" + input + "\">\n";
		return tag;
	}
	@Override
	public SerializableObject processFile() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
