package org.bigjava.ch11.ioexception;
/**
 * a bank account has a balance that can be changed by deposits and withdrawals
 * */
public class BankAccount {
	private double balance;
	/**
	 * constructs a bank account with a zero balance
	 * */
	public BankAccount() {
		balance = 0;
	}
	
	/**
	 * constructs a bank account with a given balance
	 * @param initialBalance the initial balance
	 * */
	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}
	
	/**
	 * deposits money into the bank account
	 * @param amount the amount to deposit
	 * */
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	/**
	 * Withdraws money from the bank account
	 * @param amount the amount to withdraw
	 * */
	public void withdraw(double amount) {
		if (amount > balance) {
			throw new IllegalArgumentException("Amount exceeds balance");
		}
		balance = balance - amount;
	}
	
	/**
	 * Gets the current balance of the bank account
	 * @return the current balance
	 * */
	public double getBalance() {
		return balance;
	}
}
