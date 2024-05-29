package org.laicode.class18.exceptionandunittest;

public class ThrowExceptionTest {
	
	public static void fun() {
		try {
			throw new NullPointerException("demo");
		} catch (NullPointerException e) {
			System.out.println("Caught inside fun()");
			throw e; //re throwing the exception
		}
	}
	
	public static void main(String[] args) {
	 try {
		 fun();
	 }catch (NullPointerException e) {
		 System.out.println("Caught inside main()");
	 }
	}
}
