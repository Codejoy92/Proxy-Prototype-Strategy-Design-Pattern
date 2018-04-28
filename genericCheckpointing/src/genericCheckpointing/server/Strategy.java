package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

public interface Strategy {
	 void processInput(SerializableObject sObject);
	 SerializableObject processFile();
}
