package genericCheckpointing.util;

public class SerializeTypes {
	
	public Object getDataTypeTag(String name, String dataType, String value) {
		String tag = "<" + name + " xsi:type=\"xsd:" + dataType + "\">" + value + "</" + name + ">\n";
		return tag;
	}

	public Object getComplexTypeTag(String input) {
		String tag = "<complexType xsi:type=\"" + input + "\">\n";
		return tag;
	}
}
