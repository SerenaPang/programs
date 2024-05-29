package org.laicode.class18.exceptionandunittest;

public class ThrowsExceptionTest {
	public static void fun() throws IllegalAccessException, ClassNotFoundException {
		System.out.println("inside fun");
		throw new IllegalAccessException("demo");
	}

	public static void main(String[] args) {
		// fun();
		try {
			fun();
			//IllegalAccessException | ClassNotFoundException
		} catch (IllegalAccessException e) {
			System.out.println("caught inside main");
		}catch (ClassNotFoundException e) {
			System.out.println("caught ClassNotFoundException");
		}catch (Exception e) {
			System.out.println("caught Exception");
		}
	}
}
