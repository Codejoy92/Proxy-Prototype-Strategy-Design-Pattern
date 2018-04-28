package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Method;
import genericCheckpointing.server.Strategy;
import java.lang.reflect.InvocationHandler;
import genericCheckpointing.util.DeserialisingXML;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerialisingXML;

public class StoreRestoreHandler implements InvocationHandler {

	FileProcessor fileProcessor = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		SerializableObject obj = new SerializableObject();
		if (method.getName().equals("readObj")) {
			obj = dataDeSerialization(new DeserialisingXML(fileProcessor));
		}else if (method.getName().equals("writeObj")) {
			dataSerialization((SerializableObject) args[0], new SerialisingXML(fileProcessor));
		}else {
			obj = null;
		}
		return obj;
	}

	private SerializableObject dataDeSerialization(Strategy strategy) {
		return strategy.processFile();
	}

	private void dataSerialization(SerializableObject sObject, Strategy strategy) {
		strategy.processInput(sObject);
	}

	public void setFileprocessor(FileProcessor processor) {
		fileProcessor = processor;
	}
}
