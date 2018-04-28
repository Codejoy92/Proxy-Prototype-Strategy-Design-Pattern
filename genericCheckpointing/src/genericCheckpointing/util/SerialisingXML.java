package genericCheckpointing.util;

import genericCheckpointing.server.Strategy;

public class SerialisingXML implements Strategy{
	FileProcessor fileProcessor;
	Serialize serialize;
	public SerialisingXML(FileProcessor fileProcessorIn) {
		fileProcessor = fileProcessorIn;
		serialize = new Serialize();
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
