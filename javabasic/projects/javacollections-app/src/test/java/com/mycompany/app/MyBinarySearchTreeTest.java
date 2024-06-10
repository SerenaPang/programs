package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.mycompany.app.TreeNode;
import com.mycompany.app.MyBinarySearchTree;

public class MyBinarySearchTreeTest {

	@Test
	public void testInsert() {
		System.out.println("TEST Insert ");
		MyBinarySearchTree tree = new MyBinarySearchTree();

		tree.insert(10);
		tree.insert(5);
		tree.insert(15);
		tree.insert(4);
		tree.insert(8);
		tree.insert(6);
		tree.insert(9);
		tree.insert(16);
		tree.insert(14);
		TreeNode result = tree.insert(11);
		int resultVal = result.getValue();
		System.out.println("result = " + resultVal);
		int left = result.getLeft().getValue();
		int right = result.getRight().getValue();
		assertEquals(5, left);
		assertEquals(15, right);
		assertEquals(10, resultVal);
	}

	@Test
	public void testSearch() {
		System.out.println("TEST Search ");
		MyBinarySearchTree tree = new MyBinarySearchTree();
		tree.insert(1);
		tree.insert(3);
		tree.insert(2);		
		TreeNode result = tree.search(3);
		int resultVal = result.getValue();
		System.out.println("result = " + resultVal);
		assertEquals(3, resultVal);
	}

	@Test
	public void testDelete() {
		System.out.println("TEST Delete ");
		MyBinarySearchTree tree = new MyBinarySearchTree();		
		TreeNode root = tree.insert(3);
		tree.insert(8);
		tree.insert(2);	
		tree.insert(6);
		tree.insert(12);	
		tree.insert(11);
		tree.insert(14);
		tree.insert(9);
		tree.insert(10);
		
		TreeNode result = tree.delete(root, 8);
		int resultVal = result.getValue();
		System.out.println("result = " + resultVal);
		
		TreeNode searchDele = tree.search(8);
		assertEquals(null, searchDele);
		//assertEquals(8, resultVal);
	}
}