package org.bigjava.ch17.treestructures;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * a tree in which each node has an arbitrary number of children
 * */
public class Tree {
	private static Node root;
	
	class Node {
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
	
	/**
	 * constructs an empty tree
	 * */
	public Tree() 
	{
		root = null;
		
	}
	
	/**
	 * constructs a tree with one node and no children
	 * @param rootData the data for the root
	 * */
	public Tree(Object rootData) {
		root = new Node();
		root.data = rootData;
		//root.children = new ArrayList<>();
		root.children = new LinkedList<>();
	}
	
	/**
	 * adds a subtree as the last child of the root
	 * */
	public void addSubtree(Tree subtree) {
		root.children.add(subtree.root);
	}
	
	/**
	 * computes the size of this tree
	 * @return the number of nodes in the tree
	 * */
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return root.size();
		}
	}
	
	/**
	 * Traverses the tree with a given root in pre order
	 * @param v the visitor to be invoked at each node
	 * */
	public static void preorder(Visitor v) {
		preorder(root, v);
	}
	/**
	 * Traverses the tree with a given root in pre order
	 * @param n the root of the tree
	 * @param v the visitor to be invoked at each node
	 * */
	public static void preorder(Node n, Visitor v) {
		if (n == null) {
			return;
		}
		v.visit(n.data);
		for (Node c : n.children) {
			preorder(c,v);
		}
	}
	
	/**
	 * Traverses the tree with a given root in post order
	 * @param n the root of the tree
	 * @param v the visitor to be invoked at each node
	 * */
	public static void postorder(Node n, Visitor v) {
		if (n == null) {
			return;
		}
		v.visit(n.data);
		for(Node c : n.children) {
			postorder(c,v);
		}
	}
	
	class BreadthFirstIterator implements Iterator{
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
	}

	public Iterator iterator() {
		return new BreadthFirstIterator(root);
	}
}
	
	
	
	
	
	
	
	

