package com.mycompany.app;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import java.util.ArrayList;

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
	public TreeNode delete(int target) {
		//TreeNode ruturnRoot = root;
		if (root == null || root.value == target) {
			return null;
		}
		//step 1: Find the node to be deleted
		TreeNode pre = null;
		while (root != null && root.value != target) {
			if (root.value < target) {
				pre = root;
				root = root.right;
			} else {
				pre = root;
				root = root.left;
			}		
		}		
		//now root.value == target, and pre == root's parent
		//step 2:find replaced node in different cases 
		//case 1: the node to be deleted had no children
		if (root.left == null && root.right == null) {
			pre.right = null;
			return pre;
		}//case 2: the node to be deleted had no left children
		else if (root.left == null && root.right != null) {
			pre.right = root.right;
			return pre;
		}//case 3: the node to be deleted had no right children
		else if (root.left != null && root.right == null) {
			pre.right = root.left;
			return pre;
		} //case 4: the node to be deleted has both right and left children
		else {
			//case 4.1:the right child does not have left children
			if (root.right.left == null) {//itself is the smallest
				root.right.left = root.left; //the new node has to take over the deleted node's left child
				pre.right = root.right; //replace the node
				return pre.right;
			}//case 4.2:the right child have left children
			else {//find smallest in the left subtree, and move it up to replace the target node
				//find smallest node
				TreeNode previousNode = null;
				TreeNode smallest = root;
				while (smallest != null && smallest.left != null) {
					previousNode = smallest;
					smallest = smallest.left;
				}
				//The parent of the smallest node has to take over the right child of the smallest node
				previousNode.left = smallest.right;
				//The smallest node goes up to replace the deleted node and take over the left and right child of the delted node
				smallest.right = root.right;				
				smallest.left = root.left;
				pre.right = smallest;
				return pre.right;
			}
		}
	}
	
	private TreeNode findSmallest(TreeNode root) {
		while (root != null && root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	/**
	 * Pre order traversal iteratively traverse a tree (root, left right)
	 * */
	public int[] preOrder(TreeNode root) {	
		if (root == null) {
			return null;
		}
		//Use a stack to push and pop element in the tree
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		List nodeList = new ArrayList<Integer>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			Integer val = (Integer)cur.value;
			nodeList.add(cur);
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
		int size = nodeList.size() - 1;
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = (int)nodeList.get(i);
		}
		return result;
	}		
}
