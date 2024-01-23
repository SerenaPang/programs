package org.bigjava.ch9.inheritance;
/**
 * An employee has a name and a mechanism for computing weekly pay
 * */
public class Employee {
	private String name;
	/**
	 * construct an employee with an empty name
	 * */
	public Employee() {
		name = "";
	}
	
	/**
	 * sets the name of this employee
	 * */
	public void setName(String employeeName) {
		name = employeeName;
	}
	
	/**
	 * gets the name of this employee
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * computes the pay for the given number of hours
	 * */
	public double weeklyPay(int hoursWorked) {
		return 0;
	}
}
