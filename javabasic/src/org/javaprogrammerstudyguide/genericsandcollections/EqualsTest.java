package org.javaprogrammerstudyguide.genericsandcollections;

/**
 * Implementing an equals method
 * */
public class EqualsTest {
	public static void main(String[] args) {
		Moof one = new Moof(8);
		Moof two = new Moof(8);
		if (one.equals(two)) {
			System.out.println("one and two are quals");
		}
	}

}
