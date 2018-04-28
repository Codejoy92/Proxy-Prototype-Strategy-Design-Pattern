package genericCheckpointing.util;

import genericCheckpointing.server.Strategy;

public class DeserialisingXML implements Strategy {
	FileProcessor fileProcessor = null;
	Deserialize deserialize = null;

	public DeserialisingXML(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
		deserialize = new Deserialize();
	}

	@Override
	public void processInput(SerializableObject sObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SerializableObject processFile() {
		// TODO Auto-generated method stub
		return null;
	}
}
