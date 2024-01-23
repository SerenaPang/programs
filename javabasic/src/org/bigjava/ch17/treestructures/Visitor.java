package org.bigjava.ch17.treestructures;

/**
 * a visitor whose visit method is called for each visited node during a tree traversal
 * */
public interface Visitor{
	Node root = null;

	/**
	 * This method is called for each visited node
	 * @param data the data of the node
	 * */
	void visit(Object data);
	
	/**
	 * traverses this tree in pre order
	 * @param v the visitor to be invoked at each node
	 * */
	public static void preorder(Visitor v) {
		preorder(root, v);
	}
	
	/**
	 * traverses the tree with a given root in preorder
	 * @param n the root of the tree
	 * @param v the visitor to be invoked at each node
	 * */
	private static void preorder(Node n, Visitor v) {
		if (n == null) {
			return;
		}
		v.visit(n.data);
		for (Node c : n.children) {
			preorder(c, v);
		}
	}
	
	/**
	 * Traverses this tree in postorder
	 * @param v the visitor to be invoked at each node
	 * */
	public static void postorder(Node n, Visitor v) {
		if (n == null) {return;}
		v.visit(n.data);
		for (Node c : n.children) {
			postorder(c, v);
		}
	}
	
	/**
	 * This iterator visits the nodes of a tree in breadth first order
	 * */
	
}



