package org.bigjava.ch17.treestructures;
import java.util.Iterator;

import org.bigjava.ch17.treestructures.Tree.Node;
/**
 * This program demonstrates tree traversal
 * */
public class TraversalDemo {
	public static void main(String[] args) {
		Tree t1 = new Tree("Anne");
		Tree t2 = new Tree("Peter");
		t1.addSubtree(t2);
		Tree t3 = new Tree("Zara");
		t1.addSubtree(t3);
		Tree t4 = new Tree("Savannah");
		t2.addSubtree(t4);
		
		
		
		ShortNameCounter v = new ShortNameCounter();
		t1.preorder(v);
		System.out.println("Short Names: " + v.counter);
		//count short names with iterator
		Iterator iter = t1.iterator();
		int counter = 0;
		while (iter.hasNext()) {
			Object data = iter.next();
			System.out.println(data);
			if (data.toString().length() <= 5) {
				counter++;
			}
		}
		System.out.println("Short Names: " + counter);
	}
}
