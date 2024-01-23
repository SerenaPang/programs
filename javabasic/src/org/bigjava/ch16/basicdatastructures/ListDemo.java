package org.bigjava.ch16.basicdatastructures;

import org.bigjava.ch18.generics.ListIterator;

/**
 * a program that demonstrates the LinkedList class
 * */
public class ListDemo {
	public static void main(String[] args) {
		LinkedList staff = new LinkedList();
		staff.addFirst("Tom");
		staff.addFirst("Romeo");
		staff.addFirst("Harry");
		staff.addFirst("Diana");
		
		// | in the comments indicates the iterator position
		
		java.util.ListIterator iterator = staff.listIterator(); // |DHRT
		iterator.next();//D|HRT
		iterator.next();//DH|RT
		
		//add more elements after second element
		iterator.add("Juliet");//DHJ|RT
		iterator.add("Nina");//DHJN|RT
		iterator.next();//DHJNR|T
		//remove last traversed element
		iterator.remove();//DHJN|T
		
		//print all elements
		iterator = staff.listIterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
	}
}
