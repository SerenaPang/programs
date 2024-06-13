package org.laicode.class19.nestedclassiteratorgenerics;

/**
 * Example of Outer class and Inner Class (Non static nested class)
 */
public class People {
	String name;
	Bike b = new Bike();

	public People(String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println("people name: " + name);
		b.printName();
	}

	class Bike {
		String owner = name; // As inner class, it can access all static and non static member of the outer
								// class
		People p = People.this;

		public void printName() {
			System.out.println("people name: " + name);
			System.out.println("bike name: " + p.name);
		}
	}

	public static void main(String[] args) {
		People p = new People("Thunder cats");
		p.printName();
		// Instance silver owns his bike
		People silver = new People("Silver");
		// instantiate Inner class Object
		People.Bike silversBike = silver.new Bike();
		silversBike.printName();
	}
}
