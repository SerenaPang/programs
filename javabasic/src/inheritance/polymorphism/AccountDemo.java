package inheritance.polymorphism;
import java.util.Scanner;

public class AccountDemo {
	/**
	 * This program simulates a bank with checking and savings accounts
	 * */
	public static void main(String[] args) {
		//create accounts
		final int ACCOUNTS_SIZE = 10;
		BankAccount[] accounts = new BankAccount[ACCOUNTS_SIZE];
		for (int i = 0; i < accounts.length/2; i++) {
			accounts[i] = new CheckingAccount();
		}
		
		for (int i = accounts.length / 2; i <accounts.length;i++) {
			SavingAccount account = new SavingAccount();
			account.setInterestRate(0.75);
			accounts[i] = account;
		}
	}
}
