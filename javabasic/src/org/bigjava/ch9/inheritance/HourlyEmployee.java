package org.bigjava.ch9.inheritance;

/**
 * An hourly employee id paid for every hour worked
 * */
public class HourlyEmployee extends Employee{
	private double hourlyWage;
	
	/**
	 * constructs an hourly employee with a given name and weekly wage
	 * */
	public HourlyEmployee(String name, double wage) {
		setName(name);
		hourlyWage = wage;
	}
	
	public double weeklyPay(int hoursWorked) {
		double pay = hoursWorked * hourlyWage;
		if (hoursWorked > 40) {
			//add overtime
			pay = pay + ((hoursWorked - 40) * 0.5) * hourlyWage;
		}
		return pay;
	}
}
