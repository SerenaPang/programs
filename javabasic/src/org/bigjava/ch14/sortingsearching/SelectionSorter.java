package org.bigjava.ch14.sortingsearching;

/**
 * The sort method of this class sorts an array, using the selection sort algorithm 
 * */
public class SelectionSorter {
	/**
	 * Sorts an array, using selection sort
	 * @param a the array to sort
	 * */
	public static void sort(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
		{
			int minPos = minimumPosition(a, i);
			swap(a, minPos, i);
		}
		
	}

	/**
	 * finds the minimum element in the unsorted region and swap it to the sorted region
	 * @param a the array to sort
	 * @param from the first position in a to compare
	 * @return the position of the smallest element in the range a[from] ... a[a.length - 1]
	 * */
	private static int minimumPosition(int[] a, int from) {
		 int minPos = from;
		 for (int i = from + 1; i < a.length; i++) 
		 {
			 if (a[i] < a[minPos])
			 {
				 minPos = i;
			 }
		 }
		return minPos;
	}	

	private static void swap(int[] a, int minPos, int i) {
		int x = a[minPos];
		a[minPos] = a[i];
		a[i] = x;
	}
}
