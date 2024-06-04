package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import com.mycompany.app.ListNode;
import com.mycompany.app.MyLinkedList;

/**
 * Unit test for java collections App.
 */
public class AppTest 
{
    /**
     * Test length method for linked list
     */
    @Test
    public void testLength()
    {
    	System.out.println("TEST LENGTH ");
    	ListNode head = new ListNode(0);
    	MyLinkedList myList = new MyLinkedList(head);
    	myList.appendTail(1);
    	myList.appendTail(2);
    	myList.appendTail(3);
    	int result = myList.length();
    	System.out.println("LENGTH = " + result);
    	assertEquals(4, result);
    }
    
    /**
     * Test length method for linked list
     */
    @Test
    public void testGetLength()
    {
    	ListNode head = null;
    	MyLinkedList myList = new MyLinkedList(head);
    	head = myList.appendTail(1);
    	System.out.println("TEST GET LENGTH ");	
    	myList.appendTail(2);
    	myList.appendTail(3);
    	myList.appendTail(4);
    	int result = myList.getLength();
    	System.out.println("LENGTH = " + result);
    	assertEquals(4, result);
    }
    /**
     * Test get method 
     * */
    @Test
    public void testGet()
    {
    	System.out.println("TEST GET: ");
    	ListNode head = null;
    	MyLinkedList myList = new MyLinkedList(head);
    	head = myList.appendTail(1);
    	myList.appendTail(2);
    	myList.appendTail(3);
    	myList.appendTail(4);
    	ListNode result = myList.get(head, 3);
    	int value = result.value;
    	System.out.println("Index 3 = " + value);
    	assertEquals(4, value);
    }
    
    /**
     * Test append tail
     * */
    @Test
    public void testAppendTail()
    {
    	System.out.println("TEST APPEND TAIL ");
     	ListNode head = new ListNode(0);
    	MyLinkedList myList = new MyLinkedList(head);
    	myList.appendTail(1);
    	myList.appendTail(2);
    	myList.appendTail(3);
    	int[] expectedArray = new int[4];
    	int index = 0;
    	while (head != null) {
    		int value = head.value;
    		expectedArray[index] = value;
    		index++;
    		head = head.next;
    	}
    	int[] resultArray = {0, 1, 2, 3};
    	assertArrayEquals(expectedArray, resultArray);
    }
    
    /**
     * Test append head
     * */
    @Test
    public void testAppendHead()
    {
    	System.out.println("TEST APPEND HEAD ");
    	ListNode head = null;
    	MyLinkedList myList = new MyLinkedList(head);
    	myList.appendHead(1);  
    	myList.appendHead(2);
    	ListNode nHead = myList.appendHead(3);
    	int[] resultArray = new int[3];
    	
    	int len = myList.getLength();
    	System.out.println("!!!len in append head: " + len);
    	for(int i = 0; i < len; i++) {
    		int value = nHead.value;
    		resultArray[i] = value;
    		nHead = nHead.next;
    	}	
    	for (int i = 0; i < resultArray.length; i++) {
    		System.out.println("val: " + resultArray[i]);
    	}
    	int[] expectedArray = {3,2,1};
    	assertArrayEquals(expectedArray, resultArray);
    }
    
    /**
     * Test remove 
     * */
    @Test
    public void testRemove()
    {
//    	ListNode head = new ListNode(0);
//    	ListNode node1 = new ListNode(1);
//    	ListNode node2 = new ListNode(2);
//    	ListNode node3 = new ListNode(3);
//    
//    	head.next = node1;
//    	node1.next = node2;
//    	node2.next = node3;
//   
//    	MyLinkedList myList = new MyLinkedList(head);   	
//    	myList.remove(head, 2);
//    	
//    	ListNode head2 = new ListNode(0);
//    	ListNode n1 = new ListNode(1);
//    	ListNode n3 = new ListNode(3);   	
//  	
//    	head2.next = n1;
//    	n1.next = n3;
//    	
//    	assertEquals(head, head2);
    }
    
}
