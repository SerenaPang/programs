package ioexception;
/**
 * A class to demonstrate an exception
 * */
public class ThrowingExceptions {
/**
 * Tests the methods of the BankAccount class
 * @param args not used
 * */
	public static void main(String[] args) {
		BankAccount harrysChecking = new BankAccount();
		harrysChecking.deposit(2000);
		harrysChecking.deposit(5000);
		//the last statement is not executed since an exception has terminated the program
		System.out.println(harrysChecking.getBalance());
	}
}
