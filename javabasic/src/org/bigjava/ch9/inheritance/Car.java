package org.bigjava.ch9.inheritance;

/***
 * This class inherits the getNumberOfTires and setNumberOfTires methods
 * */
public class Car extends Vehicle{
	//this instance variable is added to the superclass
	private String licensePlateNumber;
	
	public Car () {		
		//use the public interface to access the instance variable of the superclass
		setNumberOfTires(4); //same a this.setNumberOfTires(4)
		licensePlateNumber = "??????";
	}
	
	//this method overrides a method from the superclass
	public String getDescription() {
		return "A car with license plate " + licensePlateNumber;
	}

	public void seLicensePlateNumber(String newValue) {
		licensePlateNumber = newValue;
	}
}
