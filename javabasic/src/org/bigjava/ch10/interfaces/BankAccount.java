package org.bigjava.ch10.interfaces;
/**
 * A bank account has a balance that can be changed by deposits and withdrawals
 * */
public class BankAccount implements Measurable{
	private double balance;
	
	//construct a bank account with a zero balance
	public BankAccount() {
		balance = 0;
	}
	
	//constructs a bank account with a given balance
	public BankAccount(double initialBalance) {
		balance = initialBalance;
	}
	
	//deposits money into the bank account
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	//withdraws money from the bank account
	public void withdraw(double amount) {
		balance = balance - amount;
	}
	
	//gets the current balance of the bank account
	public double getBalance() {
		return balance;
	}

	@Override
	public double getMeasure() {
		return balance;
	}
	
	public int compareTo(Object otherObject)
	{
		BankAccount other = (BankAccount) otherObject;
		if (balance < other.balance) {return -1;}
		if (balance > other.balance) {return 1;}
		return 0;
	}
}
