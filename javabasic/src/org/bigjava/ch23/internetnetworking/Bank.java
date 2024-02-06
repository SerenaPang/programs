package org.bigjava.ch23.internetnetworking;
/**
 * a bank consisting of multiple bank accounts
 * */
public class Bank {
	private BankAccount[] accounts;
	
	/**
	 * constructs a bank account with a given number of accounts
	 * @param size the number of accounts
	 * */
	public Bank(int size) {
		accounts = new BankAccount[size];
		for(int i = 0; i< accounts.length; i++) {
			accounts[i] = new BankAccount();
		}
	}
	
	/**
	 * deposits money into a bank account
	 * @param accountNumber the account number
	 * @param amount the amount to deposit
	 * */
	
	
}
