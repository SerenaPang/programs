package org.bigjava.ch17.treestructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstIterator implements Iterator{
	private Node root;
	private Queue<Node> q;
	public BreadthFirstIterator(Node root) 
	{
		q = new LinkedList<>();
		if (root != null) {q.add(root);}
	}

	@Override
	public boolean hasNext() {
		return q.size() > 0;
	}

	@Override
	public Object next() {
		Node n = q.remove();
		for (Node c : n.children) {
			q.add(c);
		}
		return n.data;
	}
	
	public Iterator iterator() {
		return new BreadthFirstIterator(root);
	}
}
