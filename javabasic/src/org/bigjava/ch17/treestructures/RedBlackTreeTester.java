package org.bigjava.ch17.treestructures;
import java.util.ArrayList;
import java.util.List;

/**
 * This program tests the red black tree class
 * */
public class RedBlackTreeTester {
	public static void main(String[] args) {
		testFromBook();
		insertionTest("ABCDEFGHIJ");
		System.out.println("All tests passed.");
	}

	private static void removelTest(RedBlackTree t) {
		
	}

	private static Object removalTestTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * inserts all permutations of a string into a red - black tree and checks that it contains the string afterwards
	 * @param letters a string of letters without repetition
	 * */
	private static void insertionTest(String letters) {
		PermutationGenerator gen = new PermutationGenerator(letters);
		for(String perm : gen.getPermutations()) {
			RedBlackTree t = new RedBlackTree();
			for (int i = 0; i < perm.length(); i++) {
				String s = perm.substring(i, i + 1);
				t.add(s);
			}
			assertEquals(letters, t.toString().replace(" ", ""));
		}
	}
	/**
	 * runs simple test from the textbook
	 * */
	private static void testFromBook() {
		RedBlackTree t = new RedBlackTree();
		t.add("D");
		t.add("B");
		t.add("A");
		t.add("C");	
		t.add("F");
		t.add("E");
		t.add("I");
		t.add("G");
		t.add("H");
		t.add("J"); 
		
		t.remove("A");//removing leaf
		t.remove("B");//removing element with one child
		t.remove("F");// removing element with two children
		t.remove("D");//removing root
		assertEquals("C E G H I J", t.toString());
	}

	private static void assertEquals(Object expected, Object actual) {
		if(expected == null && actual != null || !expected.equals(actual)) {
			if (expected == null && actual != null || !expected.equals(actual)) {
				throw new AssertionError("Expected " + expected + "but found " + actual);
			}
		}		
	}
}
