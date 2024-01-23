package org.bigjava.ch11.ioexception;

public class InsufficientFundsException extends IllegalArgumentException{
	public InsufficientFundsException() {}
	public InsufficientFundsException(String message) {
		super(message);
	}
}
