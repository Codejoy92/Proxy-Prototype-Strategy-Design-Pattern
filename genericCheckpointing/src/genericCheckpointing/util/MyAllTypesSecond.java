package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {

	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private short myOtherShortT;
	private char myCharT;
	
	public MyAllTypesSecond(){
		myDoubleT = 0.0;
		myOtherDoubleT = 0.0;
		myFloatT = 0;
		myShortT = 0;
		myCharT = ' ';
	}
	
	public MyAllTypesSecond(double myDoubleIn, double myOtherDoubleIn, float myFloatIn, short myShortIn, short myOtherShortIn, char myCharIn) {
		myDoubleT = myDoubleIn;
		myOtherDoubleT = myOtherDoubleIn;
		myFloatT = myFloatIn;
		myShortT = myShortIn;
		myOtherShortT = myOtherShortIn;
		myCharT = myCharIn;
	}

	
	public double getmyDoubleT() {
		return myDoubleT;
	}

	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public double getmyOtherDoubleT() {
		return myOtherDoubleT;
	}

	public void setmyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}

	public float getmyFloatT() {
		return myFloatT;
	}

	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public short getmyShortT() {
		return myShortT;
	}

	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	public short getmyOtherShortT() {
		return myOtherShortT;
	}

	public void setmyOtherShortT(short myOtherShortT) {
		this.myOtherShortT = myOtherShortT;
	}

	public char getmyCharT() {
		return myCharT;
	}

	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += Double.hashCode(myDoubleT);
		result += Double.hashCode(myOtherDoubleT);
		result += Float.hashCode(myFloatT); 
		result += myShortT;
		result += myOtherShortT;
		result += Character.hashCode(myCharT);
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
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (Double.doubleToLongBits(myOtherDoubleT) != Double.doubleToLongBits(other.myOtherDoubleT))
			return false;
		if (myOtherShortT != other.myOtherShortT)
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myDouble=" + myDoubleT + ", myOtherDouble=" + myOtherDoubleT + ", myFloat=" + myFloatT
				+ ", myShort=" + myShortT + ", myOtherShort=" + myOtherShortT + ", myChar=" + myCharT + "]";
	}
	
	

}
