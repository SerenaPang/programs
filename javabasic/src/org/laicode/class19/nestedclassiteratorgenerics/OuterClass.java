package org.laicode.class19.nestedclassiteratorgenerics;

/**
 * Examples of Nested Class
 * */
public class OuterClass {
	private static String msg1 = "Static Message";
	private String msg2 = "Non Static Message";

	/**
	 * a static inner class: only static members of Outer class is directly
	 * accessible in nested static class
	 */
	public static class NestedStaticClass {

		public void printMessage() {
			// msg1 is a static variable
			System.out.println("Message from nested static class: " + msg1);
			// compile error: msg2 is a non static variable
			// System.out.println("Message from nested static class: " + msg2);
		}
	}

	/**
	 * a non static inner class: both static and non static members of Outer class
	 * are accessible in this nested class
	 */
	public class InnerClass {
		public void display() {
			System.out.println("Message from nested static class: " + msg1);
			System.out.println("Message from nested static class: " + msg2);
		}

		/**
		 * Also, you can not declare a static method in here, it can only be declared in
		 * a static class or top level class
		 */
		// public static void print1() {}

		public OuterClass getOuterClass() {
			return OuterClass.this;
		}
	}

	// how to create instance of inner static class and instance of non static inner
	// class
	public static void main(String[] args) {
		// create instance of inner static class
		OuterClass.NestedStaticClass nestedStatic = new OuterClass.NestedStaticClass();
		// call non static method of inner static class
		nestedStatic.printMessage();

		// create instance of non static inner class
		// first create an outer class instance
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();

		// we can also combined into one step
		OuterClass.InnerClass inner2 = new OuterClass().new InnerClass();

		// call inner class method
		inner.display();
		inner2.display();
	}
}
