package inheritance.polymorphism;
/**
 * A bank account has a balance and a mechanism for applying interest or fees at the end of the month
 * */
public class BankAccount {
	private double balance;
	/**
	 * construct a bank account with zero balance
	 * */
	public BankAccount() {
		balance = 0;
	}
	
	/**
	 * make a deposit into this account
	 * */
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	/**
	 * makes a withdrawal from this account, or charges a penalty if sufficient funds are not available
	 * */
	public void withdraw(double amount) {
		balance = balance - amount;
	}
	
	/**
	 * carries out the end of month processing that is appropriate
	 * */
	public void monthEnd() {
		
	}
	
	public double getBalance() {
		return balance;
	}
	
}
