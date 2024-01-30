package org.bigjava.ch15.javacollectionsframework;
/**
 * a bank customer
 * */
public class Customer {
	private double arrivalTime;
	/**
	 * constructs a customer
	 * @param the time at which the customer entered the bank
	 * */
	public Customer(double time) {arrivalTime = time;}
	
	/**
	 * gets the time at which the customer entered the bank
	 * @return the arrival time
	 * */
	double getArrivalTime() {return arrivalTime;}
}
