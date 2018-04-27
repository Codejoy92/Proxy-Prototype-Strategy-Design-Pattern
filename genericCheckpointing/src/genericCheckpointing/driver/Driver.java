package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.Utility;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	public static void main(String[] args) {
		Utility util= new Utility();
		util.validateFile(args);
		StoreRestoreHandler handler = new StoreRestoreHandler();
		ProxyCreator proxy = new ProxyCreator();
		StoreRestoreI srObject = (StoreRestoreI) proxy.createProxy(
				 new Class[] {
				     StoreI.class, RestoreI.class
				 }, 
				 handler
				 );
		//processing serialization and deserialization
		util.process();
	}

}
