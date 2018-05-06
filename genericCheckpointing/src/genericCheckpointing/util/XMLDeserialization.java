package genericCheckpointing.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import genericCheckpointing.server.SerStrategy;

public class XMLDeserialization implements SerStrategy {
	FileProcessor fileProcessor;
	DeserializeTypes deser;
	List<SerializableObject> serReadObj = new ArrayList<SerializableObject>();
	/**
	 *This is a paramterized constructor which takes in FileProcessor object 
	 *its used for reading the content of file line by line
	 *@param fileProcessorIn fileProcessorIn
	 *
	 */
	public XMLDeserialization(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
	}
	/**
	 *This is a default constructor 
	 */
	public XMLDeserialization() {
	}
	/**
	 *This method is declared in serStrategy which is used during serialization
	 *@param obj obj
	 */
	@Override
	public void processInput(SerializableObject obj) {
	}

	/**
	 *This method is declared in serStrategy which is used during Deserialization
	 *this method parses the given xml format and creates object out of it using reflection
	 *@return object
	 */
	@Override
	public SerializableObject processInputDeser() {
		deser = new DeserializeTypes();
		SerializableObject object = null;
		String line;
		String[] splited = null;
		line = fileProcessor.readLine();
		Class<?> className = null;
				while (line != null){
					try {
						// Used reflection to create the object depending on the value in the
						// complexType element.
						// Parsed the names of the data members and then invoke the corresponding setX
						// method to set the value for that data member.
						// java reflection is used to invoke method objects
						if (line.trim().equals("</complexType>")) {
							System.out.println(object);
							serReadObj.add(object);
						}else if (line.contains("complexType") && !line.trim().equals("</complexType>")) {
							className = Class.forName(deser.parseComplexTag(line));
							object = (SerializableObject) className.newInstance();
						}else if(line.contains("xsd")) {
							String value = deser.parseUnknown(line);
							if(value.contains("int")) {
								int value1 = deser.parseIntType(line);
								Class[] param = new Class[1];
								param[0] = Integer.TYPE;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
								
							}
							else if (value.contains("char")) {
								String value1 = deser.parseLine(line);
								Class[] param = new Class[1];
								param[0] = char.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1.charAt(0));
								splited = null;
							}
							else if (value.contains("short")) {
								short value1 = deser.parseShortType(line);
								Class[] param = new Class[1];
								param[0] = short.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							else if (value.contains("long")) {
								long value1 = deser.parseMyLong(line);
								Class[] param = new Class[1];
								param[0] = long.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							else if (value.contains("double")) {
								double value1 = deser.parseDoubleType(line);
								Class[] param = new Class[1];
								param[0] = double.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							else if (value.contains("float")) {
								float value1 = deser.parseFloatType(line);
								Class[] param = new Class[1];
								param[0] = float.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							else if (value.contains("boolean")) {
								boolean value1 = deser.parseBoolType(line);
								Class[] param = new Class[1];
								param[0] = boolean.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							else if (value.contains("string")) {
								String value1 = deser.parseLine(line);
								Class[] param = new Class[1];
								param[0] = String.class;
								splited = line.split(" ");
								Method method = className.getDeclaredMethod("set"+splited[0].substring(1), param);
								method.invoke(object, value1);
								splited = null;
							}
							
						}
					}catch(NoSuchMethodException e){
						System.err.println("no setter with name " + "set"+splited[0].substring(1) + " found" );
					}
					catch (IllegalAccessException | SecurityException
							| IllegalArgumentException | ClassNotFoundException | InvocationTargetException
							| InstantiationException e) {
						e.printStackTrace();
					}
					line = fileProcessor.readLine();
				} 
				fileProcessor.serReadObj.addAll(serReadObj);
		return object;
	}
	/**
	 * This method gets the list and returns it
	 * @return serReadObj
	 * 
	 */
	public List<SerializableObject> getSerReadObj() {
		return serReadObj;
	}
	
}
