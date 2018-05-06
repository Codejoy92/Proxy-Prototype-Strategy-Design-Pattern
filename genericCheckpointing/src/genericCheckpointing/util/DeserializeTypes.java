package genericCheckpointing.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeserializeTypes {
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
	
	public String parseUnknown(String line) {
		Pattern pattern = Pattern.compile("\\\"(.*)\\\"");
		Matcher matcher = pattern.matcher(line);
		matcher.find();
		return line.substring(matcher.start(), matcher.end());
		
	}
	public float parseFloatType(String str) {
		return Float.parseFloat(parseLine(str));
	}

	public short parseShortType(String str) {
		return Short.parseShort(parseLine(str));
	}
	public int parseIntType(String str) {
		return Integer.parseInt(parseLine(str));
	}

	public long parseMyLong(String str) {
		return Long.parseLong(parseLine(str));
	}

	public boolean parseBoolType(String str) {
		return Boolean.parseBoolean(parseLine(str));
	}

	public double parseDoubleType(String str) {
		return  Double.parseDouble(parseLine(str));
	}

}
