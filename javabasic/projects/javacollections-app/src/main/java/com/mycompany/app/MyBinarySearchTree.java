package com.mycompany.app;

/**
 * Implementation of a Binary Search Tree and its operations (iterative)
 */
public class MyBinarySearchTree {
	private TreeNode root;

	/**
	 * Insert an element with BST rule: every node to the left of itself is smaller
	 * than itself and every node to the right are greater than itself
	 * 
	 * @return TreeNode the root of the BST that's been inserted
	 */
	public TreeNode insert(int value) {
		TreeNode newNode = new TreeNode(value);
		if (root == null) {
			root = newNode;
			return root;
		}
			//find the spot to add the node
		TreeNode returnRoot = root;
		TreeNode pre = null;
		TreeNode cur = root;
		while (cur != null) {
			pre = cur;
			if (cur.getValue() > value) {				
				cur = cur.getLeft();
			} else {
				cur = cur.getRight();
			}
		}
		//now add the node to the correct slot
		if (pre.getValue() > value) {
			pre.setLeft(newNode);
		} else if(pre.getValue() < value) {
			pre.setRight(newNode);
		}				
		return returnRoot;
	}

	/**
	 * search for an element with target valuein the BST
	 * 
	 * @param target the Node's value to be searched in this BST
	 * @return TreeNode the target TreeNode, null if not exist
	 */
	public TreeNode search(int target) {
		while (root != null && root.getValue() != target) {
			if (root.getValue() < target) {
				root = root.getRight();
			} else {
				root = root.getLeft();
			}		
		}
		return root;
	}

	/**
	 * Delete the element in the BST, and adjust the order of the tree to maintain
	 * BST
	 * 
	 * @param target the Node's value to be deleted in this BST
	 * @return TreeNode the root of the BST tree after deletion
	 */
	public TreeNode delete(int value) {
		
		
		
		return null;
	}
}