package org.bigjava.ch17.treestructures;

import org.bigjava.ch17.treestructures.Tree.Node;

public class ShortNameCounter implements Visitor{
	//count short names with visitor
		public int counter = 0;
		public void visit(Object data) {
			System.out.println(data);
			if (data.toString().length() <= 5)  {
					counter++;
			}
			System.out.println("Short names: " + counter);
		}
}

