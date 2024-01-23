package org.bigjava.ch11.ioexception;
/**
 * A class to demonstrate a custom exception
 * */
public class CustomExceptions {
	/**
	 * Tests the methods of the BankAccoun class
	 * @param args not used
	 * */
	public static void main(String[] args) {
		BankAccount harrysChecking = new BankAccount();
		harrysChecking.deposit(2000);
		harrysChecking.deposit(5000);
		//The last statement is not executed since an exception has terminated the program
		System.out.println(harrysChecking.getBalance());
	}
}
