package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import com.mycompany.app.ListNode;
import com.mycompany.app.MyStack;

/**
 * Unit test for MyStack in java collections App.
 */
public class MyStackTest {

	@Test
	public void testSize() {
		System.out.println("TEST Size ");
		MyStack stack = new MyStack();

		int result = stack.size();
		System.out.println("Size = " + result);
		assertEquals(0, result);
	}

	@Test
	public void testEmpty() {
		System.out.println("TEST Empty ");
		MyStack stack = new MyStack();
		int size = stack.size();
		boolean result = stack.empty();
		System.out.println("size = " + result);
		assertTrue(result);
	}

	@Test
	public void testEmpty2() {
		System.out.println("TEST Empty ");
		MyStack stack2 = new MyStack();
		stack2.addFirst(1);
		stack2.addFirst(2);
		int size2 = stack2.size();
		boolean result2 = stack2.empty();
		System.out.println("size2 = " + result2);
		assertFalse(result2);
	}

	@Test
	public void testPeekFirst() {
		System.out.println("TEST PeekFirst ");
		MyStack stack = new MyStack();
		stack.addFirst(1);
		stack.addFirst(2);
		stack.addFirst(3);
		ListNode result = stack.addFirst(4);
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(4, resultVal);
	}

	@Test
	public void testAddFirst() {
		System.out.println("TEST AddFirst ");
		MyStack stack = new MyStack();
		stack.addFirst(1);
		ListNode result = stack.addFirst(2);
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(2, resultVal);
	}

	@Test
	public void testRemoveFirst() {
		System.out.println("TEST RemoveFirst ");
		MyStack stack = new MyStack();
		stack.addFirst(1);
		stack.addFirst(2);
		stack.addFirst(3);
		stack.addFirst(4);
		ListNode result = stack.removeFirst();
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(3, resultVal);
	}
}