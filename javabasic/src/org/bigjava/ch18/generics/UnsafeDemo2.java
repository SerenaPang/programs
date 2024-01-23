package org.bigjava.ch18.generics;
import java.util.ArrayList;
import java.util.Iterator;

public class UnsafeDemo2 {
	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		print(numbers);
		System.out.println("Sum: " + sum(numbers));
		//numbers.add("4"); //compile error
		print(numbers);
		System.out.println("Sum: " + sum(numbers));
	}

	private static int sum(ArrayList<Integer> lst) {
		int sum = 0;
		Iterator<Integer> iter = lst.iterator();
		while (iter.hasNext()) {
			sum = sum + iter.next(); //no cast needed
		}
		return sum;
	}

	private static void print(ArrayList<Integer> lst) {
		Iterator<Integer> iter = lst.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
	}
}
