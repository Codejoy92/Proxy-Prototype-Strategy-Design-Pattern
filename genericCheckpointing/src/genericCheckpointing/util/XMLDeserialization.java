package genericCheckpointing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.server.SerStrategy;

public class XMLDeserialization implements SerStrategy {
	FileProcessor fileProcessor;

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
						if (line.contains("complexType") && !line.trim().equals("</complexType>")) {
							className = Class.forName(parseComplexTag(line));
							object = (SerializableObject) className.newInstance();
						} else if (line.contains("myString")) {
							String value = parseLine(line);
							Method method = className.getDeclaredMethod("setMyString", String.class);
							method.invoke(object, value);
						} else if (line.contains("myBool")) {
							boolean value = parseBoolType(line);
							Method method = className.getDeclaredMethod("setMyBool", Boolean.TYPE);
							method.invoke(object, value);
						} else if (line.contains("myInt")) {
							int intValue = parseIntType(line);
							Method method = className.getDeclaredMethod("setMyInt", Integer.TYPE);
							method.invoke(object, intValue);
						} else if (line.contains("myOtherInt")) {
							int intValue = parseIntType(line);
							Method method = className.getDeclaredMethod("setMyOtherInt", Integer.TYPE);
							method.invoke(object, intValue);
						}else if (line.contains("myLong")) {
							long value = parseMyLong(line);
							Method method = className.getDeclaredMethod("setMyLong", Long.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myOtherLong")) {
							long value = parseMyLong(line);
							Method method = className.getDeclaredMethod("setMyOtherLong", Long.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myDouble")) {
							double value = parseDoubleType(line);
							Method method = className.getDeclaredMethod("setMyDouble", Double.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myOtherDouble")) {
							double value = parseDoubleType(line);
							Method method = className.getDeclaredMethod("setMyOtherDouble", Double.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myFloat")) {
							float value = parseFloatType(line);
							Method method = className.getDeclaredMethod("setMyFloat", Float.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myShort")) {
							short value = parseShortType(line);
							Method method = className.getDeclaredMethod("setMyShort", Short.TYPE);
							method.invoke(object, value);
						}else if (line.contains("myChar")) {
							String value = parseLine(line);
							Method method = className.getDeclaredMethod("setMyChar", char.class);
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

	public int parseIntType(String str) {
		String value = parseLine(str);
		return Integer.parseInt(value);
	}

	public long parseMyLong(String str) {
		return Long.parseLong(parseLine(str));
	}

	public boolean parseBoolType(String str) {
		return Boolean.parseBoolean(parseLine(str));
	}

	public double parseDoubleType(String str) {
		return Double.parseDouble(parseLine(str));
	}

	public float parseFloatType(String str) {
		return Float.parseFloat(parseLine(str));
	}

	public short parseShortType(String str) {
		return Short.parseShort(parseLine(str));
	}

	/**
	 * https://alvinalexander.com/blog/post/java/how-extract-html-tag-string-regex-pattern-matcher-group
	 * 
	 * @param parseLine
	 * @return
	 */
	public String parseLine(String parseLine) {
		Pattern pattern = Pattern.compile(">.*<");
		Matcher matcher = pattern.matcher(parseLine);
		matcher.find();
		return parseLine.substring(matcher.start() + 1, matcher.end() - 1);
	}

	public String parseComplexTag(String line) {

		Pattern pattern = Pattern.compile("genericCheckpointing.*(First|Second)");
		Matcher matcher = pattern.matcher(line);
		matcher.find();
		return line.substring(matcher.start(), matcher.end());
	}
}
