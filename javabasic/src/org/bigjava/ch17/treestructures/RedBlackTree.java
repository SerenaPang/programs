package org.bigjava.ch17.treestructures;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a reb-black tree whose nodes hold objects that implement the Comparable interface
 * */
public class RedBlackTree {
	static Node root; //package access, for testing 
	static final int BLACK = 1;
	static final int RED = 0;
	private static final int NEGATIVE_RED = -1;
	private static final int DOUBLE_BLACK = 2;
	
	/**
	 * constructs an empty tree
	 * */
	public RedBlackTree() {
		root = null;
	}
	
	/**
	 * Insert a new node into the tree
	 * @param obj the object to insert
	 * */
	public void add(Comparable obj) {
		Node newNode = new Node();
		newNode.data = obj;
		newNode.left = null;
		newNode.right = null;
		if (root == null) {
			root = newNode;
		} else {
			root.addNode(newNode);
		}
		fixAfterAdd(newNode);
	}
	
	/**
	 * Tries to find an object in the tree
	 * @param obj the object to find
	 * @return true if the object is contained in the tree
	 * */
	public boolean find(Comparable obj) {
		Node current = root;
		while(current != null) {
			int d = current.data.compareTo(obj);
			if (d == 0) {
				return true;
			} else if (d > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}
	
	/**
	 * Tries to remove an object from the tree. Does nothing if the object is not contained in the tree
	 * @param obj the object to remove
	 * */
	public void remove(Comparable obj) {
		//find node to be removed 
		Node toBeRemoved = root;
		boolean found = false;
		while (!found && toBeRemoved != null) {
			int d = toBeRemoved.data.compareTo(obj);
			if (d == 0) {
				found = true;
			} else {
				if (d > 0)
				{
					toBeRemoved = toBeRemoved.left;
				}else {
					toBeRemoved = toBeRemoved.right;
				}
			}
			if (!found ) {
				return;
			}
			//toBeRemoved contains obj
			//if one of the children is empty, use the other
			if (tobeRemoved.left == null || toBeRemoved.right == null) {
				Node newChild;
				if (tobeRemoved.left == null) {
					newChild = toBeRemoved.right;
				} else {
					newChild = toBeRemoved.left;
				}
				fixBeforeRemove(toBeRemoved);
				replaceWith(toBeRemoved, newChild);
				return;
			}
			
			//neither subtree is empty
			//find smallest element of the right subtree
			Node smallest = toBeRemoved.right;
			while (smallest.left != null) {
				smallest = smallest.left;
			}
			//smallest contains smallest child in right subtree
			//move contents, unlink child
			toBeRemoved.data = smallest.data;
			fixBeforeRemoved(smallest);
			replaceWith(smallest, smallest.right);
		}
	}
	
	
	/**
	 * Yields the contents of the tree in sorted order
	 * @return all data items traversed in in order, with a space after each item
	 * */
	public String toString() {
		return toString(root);
	}
	
	/**
	 * Yields the contents of the subtree with the given root in sorted order
	 * @param parent the root of the subtree
	 * @return all data items traversed in in order, with a space after each item
	 * */
	private static String toString(Node parent) {
		if (parent == null) {
			return "";
		}
		return toString(parent.left) + parent.data + " " + toString(parent.right);
	}
	
	/**
	 * a node of a red-black tree stores a data item and references of the child nodes to the left and to the right.
	 * The color is the "cost" of passing the node; 1 for black or 0 for red
	 * temporarily, it may be set to 2 or -1
	 * */
	static class Node {
		public Comparable data;
		public Node left;
		public Node right;
		public Node parent;
		public int color;
	
	
	/**
	 * constructs a red node with no data
	 * */
	public Node() {}
	
	/**
	 * sets the left child and updates its parent reference
	 * @param child the new left child
	 * */
	public void setLeftChild(Node child) {
		left = child;
		if (child != null) {
			child.parent = this;
		}
	}
	
	/**
	 * sets the right child and updates its parent reference
	 * @param child the new right child
	 * */
	public void setRightChild(Node child) {
		right = child;
		if (child != null) {
			child.parent = this;
		}
	}
	
	/**
	 * inserts a new node as a descendant of this node
	 * @param newNode the node to insert
	 * */
	public void addNode(Node newNode) {
		int comp = newNode.data.compareTo(data);
		if (comp < 0) {
			if (left == null) {
				left = newNode;
				left.parent = this;
			} else {
				left.addNode(newNode);
			}
		}
		else if (comp > 0) {
			if (right == null) {
				right = newNode;
				right.parent = this;
			}
			else {
				right.addNode(newNode);
			}
		}
	}
	
	/**
	 * updates the parent's and replacement node's links when this node is replaced
	 * also updates the root reference if it is replaced 
	 * @param toBeReplaced the node that is to be replaced
	 * @param replacement the node that replaces that node
	 * */
	private void replaceWith(Node toBeReplaced, Node replacement) {
		if (toBeReplaced.parent == null) {
			replacement.parent = null;
			root = replacement;
		}
		else if (toBeReplaced == toBeReplaced.parent.left) {
			toBeReplaced.parent.setLeftChild(replacement);
		} else {
			toBeReplaced.parent.setRightChild(replacement);
		}
	}
	
	/**
	 * restores the tree to a red-black tree after a node has been added
	 * @param newNode the node that has been added
	 * */
	private void fixAfterAdd(Node newNode) {
		if(newNode.parent == null) {
			newNode.color = BLACK;
		} else
		{
			newNode.color = RED;
			if (newNode.parent.color == RED) {
				fixDoubleRed(newNode);
			}
		}
	}
	
	
	/**
	 * fixes the tree so that it is a red -black tree after a node has been removed
	 * @param toBeRemoved the node that it to be removed 
	 * */
	private void fixBeforeRemove(Node toBeRemoved) {
		if (toBeRemoved.color == RED) {
			return;
		}
		if (toBeRemoved.left != null || toBeRemoved.right != null) { //it is not a leaf
			//color the child black
			if (toBeRemoved.left == null) {
				toBeRemoved.right.color = BLACK;
			}else {
				toBeRemoved.left.color = BLACK;
			}
		} else {
			bubbleUp(toBeRemoved.parent);
		}
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
}
