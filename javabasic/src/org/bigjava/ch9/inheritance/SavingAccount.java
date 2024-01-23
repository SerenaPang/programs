package org.bigjava.ch9.inheritance;
/**
 * A savings account earns interest on the minimum balance
 * */
public class SavingAccount extends BankAccount{
	private double interestRate;
	private double minBalance;
	
	/**
	 * construct a savings account with a zero balance
	 * */
	public SavingAccount() {
		interestRate = 0;
		minBalance = 0;
	}
	/**
	 * sets the interest rate for this account
	 * */
	public void setInterestRate(double rate) {
		interestRate = rate;
	}
	
	public void withdraw(double amount) {
		super.withdraw(amount);
		double balance = getBalance();
		if(balance < minBalance){
			minBalance = balance;
		}
	}
	
	public void monthEnd() {
		double interest = minBalance * interestRate / 100;
		deposit(interest);
		minBalance = getBalance();
	}
}
