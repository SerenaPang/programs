package org.bigjava.ch17.treestructures;
import java.util.List;


public class Node {
		public Object data;
		public List<Node> children;
		
		/**
		 * computes the size of the subtree whose root is this node
		 * @return the number of nodes in the subtree
		 * */
		public int size() {
			int sum = 0;
			for (Node child : children) {
				sum = sum + child.size();
			}
			return 1 + sum;
		}
	}