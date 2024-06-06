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
		TreeNode returnRoot = root;
		if (root == null) {
			root = newNode;
			return root;
		}
			//find the spot to add the node
		TreeNode pre = null;
		while (root != null) {
			if (root.getValue() > value) {
				pre = root;
				root = root.getLeft();
			} else {
				pre = root;
				root = root.getRight();
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
		if (target == root.getValue()) {
			return root;
		} else if (target > root.getValue()) {
			// start find the right of the root
			root = root.getRight();
			while (root != null) {
				if (root.getValue() == target) {
					return root;
				} else if (root.getValue() > target) {
					root = root.getLeft();
				} else {
					root = root.getRight();
				}
			}
		} else {
			// start find the left of the root
			root = root.getLeft();
			while (root != null) {
				if (root.getValue() == target) {
					return root;
				} else if (root.getValue() < target) {
					root = root.getRight();
				} else {
					root = root.getLeft();
				}
			}
		}
		return null;
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