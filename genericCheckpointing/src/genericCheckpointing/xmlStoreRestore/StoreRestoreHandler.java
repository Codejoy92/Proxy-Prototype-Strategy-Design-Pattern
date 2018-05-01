package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Method;
import genericCheckpointing.server.SerStrategy;
import java.lang.reflect.InvocationHandler;
import genericCheckpointing.util.XMLDeserialization;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.XMLSerialization;

public class StoreRestoreHandler implements InvocationHandler {

	FileProcessor fileProcessor = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		SerializableObject obj = new SerializableObject();
		if (method.getName().equals("readObj")) {
			obj = deSerializeData(new XMLDeserialization(fileProcessor));
		}else if (method.getName().equals("writeObj")) {
			serializeData((SerializableObject) args[0], new XMLSerialization(fileProcessor));
		}else {
			obj = null;
		}
		return obj;
	}

	private SerializableObject deSerializeData(SerStrategy strategy) {
		return strategy.processFile();
	}

	private void serializeData(SerializableObject sObject, SerStrategy strategy) {
		strategy.processInput(sObject);
	}

	public void setFileprocessor(FileProcessor processor) {
		fileProcessor = processor;
	}
}
