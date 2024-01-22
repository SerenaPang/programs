package inheritance;

public class Vehicle {
	private int numberOfTires;
	public int getNumberOfTires() {
		return numberOfTires;
	}
	
	public void setNumberOfTires(int newValue) {
		numberOfTires = newValue;
	}
	
	public String getDescription() {
		return "A vehicle with " + numberOfTires + "tires";
	}
	
	//overwrite toString
	public String toString() {
		return getClass().getName() + "[ numberOfTires=" + numberOfTires + "]";
	}
}
