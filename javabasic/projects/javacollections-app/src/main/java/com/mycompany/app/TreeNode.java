package com.mycompany.app;

public class TreeNode{
	 protected int value;
	 protected TreeNode left;
	 protected TreeNode right;
	
	public TreeNode(int val) {
		this.value = val;
		this.left = null;
		this.right = null;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setLeft(TreeNode node) {
		this.left = node;
	}
	
	public TreeNode getLeft() {
		return left;
	}
	
	public void setRight(TreeNode node) {
		this.right = node;
	}
	
	public TreeNode getRight() {
		return right;
	}	
}