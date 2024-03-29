package org.bigjava.ch10.interfaces;

public class MeasurableTester {
	public static void testSmallerThan() {
		Measurable[] countries = new Measurable[3];
		countries[0] = new Country("Uruguay", 176220);
		countries[1] = new Country("Thailand", 513120);
		countries[2] = new Country("Belgium", 30510);
		
		System.out.println("Uruguay is smaller than Thailand: " + countries[0].smallerThan(countries[1]));
		System.out.println("Expected: true");
		System.out.println("Uruguay is smaller than Belgium: " + countries[0].smallerThan(countries[2]));
		System.out.println("Expected: false");
	}
	
	//calling the static average method with an array of BankAccount objects
	public static void testAverage() {
		Measurable[] accounts = new Measurable[3];
		accounts[0] = new BankAccount(0);
		accounts[1] = new BankAccount(10000);
		accounts[2] = new BankAccount(2000);
		
		double averageBalance = Measurable.average(accounts);
		System.out.println("Average balance: " + averageBalance);
		System.out.println("Expected: 4000");
		
		//calling the static average method with an array of Country objects
		
		Measurable[] countries = new Measurable[3];
		countries[0] = new Country("Uruguay", 176220);
		countries[1] = new Country("Thailand", 513120);
		countries[2] = new Country("Belgium", 30510);
		
		double averageArea = Measurable.average(countries);
		System.out.println("Average area: " + averageArea);
		System.out.println("Expected: 239950");
	}
}
