package org.bigjava.ch22.multithreading;
import java.util.concurrent.locks.*;
/**
 * A bank account has a balance that can be changed by deposits and withdrawals
 * */
public class BankAccount {
	private double balance;
	private Lock balanceChangeLock;
	private Condition sufficientFundsCondition;
	
	/**
	 * constructs a bank account with a zero balance
	 * */
	public BankAccount() {
		balance = 0;
		balanceChangeLock = new ReentrantLock();
		sufficientFundsCondition = balanceChangeLock.newCondition();
	}
	
	/**
	 * deposits money into the bank account
	 * @param amount the amount to deposit
	 * */
	public void deposit(double amount) {
		balanceChangeLock.lock();
		try {
			System.out.print("Depositing " + amount);
			double newBalance = balance + amount;
			System.out.println(", new balanc is" + newBalance);
			balance = newBalance;
			sufficientFundsCondition.signalAll();
		}
		finally
		{
			balanceChangeLock.unlock();
		}
	}
	
	/**
	 * Withdraws money from the bank account
	 * @param  amount the amount to withdraw
	 * */
	public void withdraw(double amount) throws InterruptedException{
		balanceChangeLock.lock();
		try {
			while (balance < amount) {
				sufficientFundsCondition.await();
			}
			System.out.println("withdrawing " + amount);
			double newBalance = balance - amount;
			System.out.println(", new balanc is" + newBalance);
			balance = newBalance;
			}
		finally 
		{
			balanceChangeLock.unlock();
		}
	}
	
	/**
	 * gets the current balance of the bank account
	 * @return the current balance
	 * */
	public double getBalance() {
		return balance;
	}

}
