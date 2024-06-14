package org.laicode.class19.nestedclassiteratorgenerics;

/**
 * Examples of Generics
 */

//find the middle element in the array
public class FindMiddle {
	public static <E> E findMiddle(E[] array) {
		E midElement = null;
		int mid = array.length / 2;
		midElement = array[mid];
		return midElement;
	}

	// find the minimum element in the array
	public static <E extends Comparable<E>> E getMin(E[] arr) {
		E min = null;
		if (arr == null || arr.length == 0) {
			return null;
		}
		min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			min = arr[i].compareTo(min) < 0 ? arr[i] : min;
		}
		return min;
	}

	public static void main(String[] args) {
		// test find middle element example
		String[] strArray = { "Silver", "Diana", "Babycat" };
		Integer[] intArr = { 1, 2, 3 };

		String strMid = findMiddle(strArray);
		int intMid = findMiddle(intArr);

		System.out.println("String mid: " + strMid);
		System.out.println("Integer mid: " + intMid);

		// test find minimum element example
		Double[] doubleArr = { 1.1, 1.2, 1.3, 3.3, 4.4 };
		Character[] charArr = { 'W', 'O', 'R', 'L', 'D' };
		Integer m1 = getMin(intArr);
		Double m2 = getMin(doubleArr);
		Character m3 = getMin(charArr);
		System.out.println("intArr min: " + m1);
		System.out.println("doubleArr min: " + m2);
		System.out.println("charArr min: " + m3);
	}
}
