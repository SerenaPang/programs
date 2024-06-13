package org.laicode.class19.nestedclassiteratorgenerics;

/**
 * Example of Outer class and Static Inner Class (static nested class) Static
 * member belongs to class, non static member belongs to instance
 */
public class SharedBikeUser {
	String name;

	public SharedBikeUser(String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println("name: " + name);
	}

	/**
	 * This is a static inner class.
	 */
	static class Bike {
		// it can only visit the static member, it can not visit the non static member
		// String owner = name //compile error
		String key;

		public void lock() {
			System.out.println("Lock the bike now...");
		}
	}

	public static void main(String[] args) {
		SharedBikeUser.Bike sb1 = new SharedBikeUser.Bike();
		sb1.lock();
		SharedBikeUser.Bike sb2 = new SharedBikeUser.Bike();
		SharedBikeUser.Bike sb3 = new SharedBikeUser.Bike();
		SharedBikeUser.Bike sb4 = new SharedBikeUser.Bike();
		SharedBikeUser.Bike sb5 = new SharedBikeUser.Bike();
		SharedBikeUser diana = new SharedBikeUser("Diana");
		diana.printName();
	}
}
