package org.bigjava.ch10.interfaces;
/**
 * This class demonstrates what happens when a class inherits a method that conflicts with a default method from an
 * interface.
 * 
 * The User class inherits he name method from its superclass Person
 * AND it inherits a default name method from the Named interface
 * Which one is called when you invoke it? Because of the "classes win " rule, it is the one that is inherited 
 * from the superclass 
 * */
public class ConflictingDefaultMethodsTester {
	public static void main(String[] args) {
		Named user = new User("Amy", "Smith");
		System.out.println("Named: " + user.name());
		System.out.println("Expected: Amy Smith"); //from the class User, not (NONE)which is from the interface name
	}
}
