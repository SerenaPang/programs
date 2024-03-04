package org.bigjava.ch14.sortingsearching;
import java.util.Arrays;

/**
 * This program demonstrates the selection sort algorithm by sorting an array that is filled with random numbers
 * */
public class SelectionSortDemo {
	public static void main(String[] args)
	{
		int[] a = {99, 30, 22, 14, 20, 6};
		System.out.println(Arrays.toString(a));
		SelectionSorter.sort(a);
		System.out.println(Arrays.toString(a));
	}
}
