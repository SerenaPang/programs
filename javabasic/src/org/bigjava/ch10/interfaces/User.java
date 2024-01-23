package org.bigjava.ch10.interfaces;
/**
 * 
 * public class User extends Person implements Named{
	private String username;
	public User(String first, String last) {
		super(first, last);
		username = (first.substring(0, 1) + last).toLowerCase();
	}
 * */
public class User extends Person implements Named
{
	private String username;
	public User(String firstName, String lastName) {
		super(firstName, lastName);
		username = (firstName.substring(0,1) + lastName).toLowerCase();
	}
}
