package org.bigjava.ch9.inheritance;

public class CarDemo {
	public static void print(Car c) {
		System.out.println(c.getDescription());
		System.out.println("Tires: " + c.getNumberOfTires());
	}
	
	public static void main(String[] args) {
		Car aPlainCar = new Car();
		print(aPlainCar);
		
		Car aLimo = new Car();
		aLimo.seLicensePlateNumber("WOOHOO");
		aLimo.setNumberOfTires(8);
		print(aLimo);
	}

}
