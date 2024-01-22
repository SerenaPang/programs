package tree_structures;
import basic_data_structures.Node;

/**
 * a binary tree in which each node has two children
 * */
public class BinaryTree {
	private Node root;
	
	/**
	 * constructs an empty tree
	 * */
	public BinaryTree() {
		root = null;
	}
	
	/**
	 * constructs a tree with one node and no children
	 * @param rootData the data for the root
	 * */
	public BinaryTree(Object rootData) {
		root = new Node();
		root.data = rootData;
		root.left = null;
		root.right = null;
	}
	
	/**
	 * constructs a binary tree
	 * @param rootData the data for the root
	 * @param left the left subtree
	 * @param right the right subtree
	 * */
	public BinaryTree(Object rootData, BinaryTree left, BinaryTree right) {
		root = new Node();
		root.data = rootData;
		root.left = left.root;
		root.right = right.root;
	}
	
	class Node{
		public Object data;
		public Node left;
		public Node right;
	}
	
	/**
	 * returns the height of the subtree whose root is the given node
	 * @param n a node or null
	 * @return the height of the subtree, or 0 if n is null
	 * */
	private static int height(Node n) {
		if (n == null) {
			return 0;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}		
	}
	
	/**
	 * returns the height of this tree
	 * @return the height
	 * */
	public int height() {
		return height(root);
	}
	
	/**
	 * checks whether this tree is empty
	 * @return true is this tree is empty
	 * */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * gets the left subtree of this tree
	 * @return the left child of the root
	 * */
	public BinaryTree left() {
		BinaryTree result = new BinaryTree();
		result.root = root.left;
		return result;
	}
	
	/**
	 * gets the right subtree of this tree
	 * @return the right child of the root
	 * */
	public BinaryTree right() {
		BinaryTree result = new BinaryTree();
		result.root = root.right;
		return result;
	}

	public char[] data() {
		// TODO Auto-generated method stub
		return null;
	}
}
