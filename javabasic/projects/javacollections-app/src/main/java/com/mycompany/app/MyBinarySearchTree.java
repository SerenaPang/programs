package com.mycompany.app;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import com.mycompany.app.TreeNode;
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
		// find the spot to add the node
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
		// now add the node to the correct slot
		if (pre.getValue() > value) {
			pre.setLeft(newNode);
		} else if (pre.getValue() < value) {
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
	public TreeNode delete(TreeNode root, int target) {
		// TreeNode ruturnRoot = root;
		if (root == null) {
			return null;
		}
		// find target node
		if (root.value > target) {
			root.left = delete(root.left, target);
			return root; // until root.value == target, it will return root
		} else if (root.value < target) {
			root.right = delete(root.right, target);
			return root;
		}
		// case 1 & case 2
		if (root.left == null) {
			return root.right;
		} // case 3
		else if (root.right == null) {
			return root.left;
		}
		// guarantee root.left and root.right both are not null
		// case 4.1 root's right child's left is null, means right is the smallest node
		if (root.right.left == null) {
			root.right.left = root.left;
			return root.right;
		}
		// case 4.2 root's right child's left is not null, means left subtree has the
		// smallest node
		// step 1: find and delete the smallest node
		TreeNode smallest = deleteSmallest(root.right);
		// step 2: connect the smallest node with root.left and root.right
		smallest.left = root.left;
		smallest.right = root.right;
		// step 3: return the smallest node
		return smallest;
	}

	private TreeNode deleteSmallest(TreeNode cur) {
		TreeNode pre = cur;
		cur = cur.left;
		while (cur.left != null) {
			pre = cur;
			cur = cur.left;
		}
		pre.left = pre.left.right;// which is the smallest node's right child
		return cur;
	}

	/**
	 * Pre order traversal iteratively traverse a tree (root, left right)
	 */
	public List<TreeNode> preOrder() {
		if (root == null) {
			return null;
		}
		// Use a stack to add the root and add to a list, then add the right and left
		// node
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		List nodeList = new ArrayList<TreeNode>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			// add the element to the list
			// Integer curVal = cur.value;
			// System.out.print(" " + curVal +" ");
			nodeList.add(cur);
			// put the right subtree aside on the stack, so it will be the last to poll
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			// when poll from the stack, the left subtree would be the first to poll
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
		return nodeList;
	}

	/**
	 * In order traversal iteratively traverse a tree (left, root, right)
	 */
	public int[] inOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		List nodeList = new ArrayList<Integer>();
		// helperNode tells wheather the node is the root with all the left subtree
		// nodes added
		TreeNode helperNode = root;

		while (helperNode != null || !stack.isEmpty()) {
			if (helperNode != null) {// means there are left nodes, we need to continue traverse
				stack.offerFirst(helperNode);
				helperNode = helperNode.left;
			} else {// if the helper == null then the current stack top is the node with all left
					// subtree printed, now pop it and print it, then go to the right
				helperNode = stack.pollFirst();
				nodeList.add(helperNode);
				helperNode = helperNode.right;
			}
		}
		int size = nodeList.size() - 1;
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = (int) nodeList.get(i);
		}
		return result;
	}

	/**
	 * In order traversal iteratively traverse a tree (left, right, root)
	 */
	public int[] postOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		List nodeList = new ArrayList<Integer>();

		TreeNode pre = null;
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode current = stack.peekFirst();
			//// if pre is null -> going down (left subtree has priority)
			if (pre == null || current == pre.left || current == pre.right) {
				// if pre is current's parent -> going down (left subtree has priority)
				if (current.left != null) {
					stack.offerFirst(current.left);
				} else if (current.right != null) {
					stack.offerFirst(current.right);
				} else {
					// current's left and right are null means we visited all nodes from left to
					// right and root are visited
					nodeList.add(current);
					stack.pollFirst();
				}
			} // left or right tree is finished, from bottom goes up
			else if (pre == current.left)// from left subtree -> left subtree finished, going cur.right
			{
				if (current.right != null) {
					stack.offerFirst(current.right);
				} else {
					nodeList.add(current);
					stack.pollFirst();
				}
			} else {// from right subtree -> right subtree finished, pop cur, going up
				nodeList.add(current);
				stack.pollFirst();
			}
			pre = current;
		}

		int size = nodeList.size() - 1;
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = (int) nodeList.get(i);
		}
		return result;
	}
}
