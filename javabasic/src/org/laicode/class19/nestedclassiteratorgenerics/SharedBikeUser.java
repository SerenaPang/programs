package org.laicode.class19.nestedclassiteratorgenerics;
/**
 * Example of Outer class and Static Inner Class (static nested class)
 * Static member belongs to class, non static member belongs to instance
 * */
public class SharedBikeUser {
	String name;
	public SharedBikeUser(String name) {
		this.name = name;
	}

	/**
	 * This is a static inner class.
	 * */
	static class Bike{
		//it can only visit the static member, it can not visit the non static member
		//String owner = name //compile error 
		String key;
	}
}
