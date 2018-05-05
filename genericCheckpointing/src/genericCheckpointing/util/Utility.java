package genericCheckpointing.util;

public class Utility {
	public void validateFile(String[] args) {
		try {
			if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || null == args) {
				System.err.println("Error: invalid arguments");
				System.exit(0);
			}
		} catch (Exception e) {
			System.err.println("File not found");
			System.exit(1);
		}
	}
}
