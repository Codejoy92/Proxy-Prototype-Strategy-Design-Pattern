package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {

	private double myDouble;
	private double myOtherDouble;
	private float myFloat;
	private short myShort;
	private short myOtherShort;
	private char myChar;
	
	public MyAllTypesSecond(){
		myDouble = 0.0;
		myOtherDouble = 0.0;
		myFloat = 0;
		myShort = 0;
		myChar = ' ';
	}
	
	public MyAllTypesSecond(double myDoubleIn, double myOtherDoubleIn, float myFloatIn, short myShortIn, short myOtherShortIn, char myCharIn) {
		myDouble = myDoubleIn;
		myOtherDouble = myOtherDoubleIn;
		myFloat = myFloatIn;
		myShort = myShortIn;
		myOtherShort = myOtherShortIn;
		myChar = myCharIn;
	}

	public double getmyDouble() {
		return myDouble;
	}

	public void setmyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public double getmyOtherDouble() {
		return myOtherDouble;
	}

	public void setmyOtherDouble(double myOtherDouble) {
		this.myOtherDouble = myOtherDouble;
	}

	public float getmyFloat() {
		return myFloat;
	}

	public void setmyFloat(float myFloat) {
		this.myFloat = myFloat;
	}

	public short getmyShort() {
		return myShort;
	}

	public void setmyShort(short myShort) {
		this.myShort = myShort;
	}

	public short getmyOtherShort() {
		return myOtherShort;
	}

	public void setmyOtherShort(short myOtherShort) {
		this.myOtherShort = myOtherShort;
	}

	public char getmyChar() {
		return myChar;
	}

	public void setmyChar(char myChar) {
		this.myChar = myChar;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += Double.hashCode(myDouble);
		result += Double.hashCode(myOtherDouble);
		result += Float.hashCode(myFloat); 
		result += myShort;
		result += myOtherShort;
		result += Character.hashCode(myChar);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myChar != other.myChar)
			return false;
		if (Double.doubleToLongBits(myDouble) != Double.doubleToLongBits(other.myDouble))
			return false;
		if (Float.floatToIntBits(myFloat) != Float.floatToIntBits(other.myFloat))
			return false;
		if (Double.doubleToLongBits(myOtherDouble) != Double.doubleToLongBits(other.myOtherDouble))
			return false;
		if (myOtherShort != other.myOtherShort)
			return false;
		if (myShort != other.myShort)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myDouble=" + myDouble + ", myOtherDouble=" + myOtherDouble + ", myFloat=" + myFloat
				+ ", myShort=" + myShort + ", myOtherShort=" + myOtherShort + ", myChar=" + myChar + "]";
	}
	
	

}
