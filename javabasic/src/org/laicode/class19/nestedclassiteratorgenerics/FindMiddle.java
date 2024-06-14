package org.laicode.class19.nestedclassiteratorgenerics;

/**
 * Examples of Generics
 */
public class FindMiddle {
	public static <E> E findMiddle(E[] array) {
		E midElement = null;
		int mid = array.length / 2;
		midElement = array[mid];
		return midElement;
	}

	public static void main(String[] args) {
		String[] strArray = { "Silver", "Diana", "Babycat" };
		Integer[] intArr = { 1, 2, 3 };

		String strMid = findMiddle(strArray);
		int intMid = findMiddle(intArr);

		System.out.println("String mid: " + strMid);
		System.out.println("Integer mid: " + intMid);
	}
}
