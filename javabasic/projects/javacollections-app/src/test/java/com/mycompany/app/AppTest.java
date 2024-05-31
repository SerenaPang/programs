package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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
    	ListNode head = new ListNode(0);
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	head.next = node1;
    	node1.next = node2;
    	node2.next = node3;
    	MyLinkedList myList = new MyLinkedList(head);
    	assertEquals(4, myList.length(head));
    }
    
    /**
     * Test get method 
     * */
    @Test
    public void testGet()
    {
    	ListNode head = new ListNode(0);
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	head.next = node1;
    	node1.next = node2;
    	node2.next = node3;
    	MyLinkedList myList = new MyLinkedList(head);
    	
    	assertEquals(node3, myList.get(head, 3));
    }
    
    /**
     * Test append tail
     * */
    
    /**
     * Test append head
     * */
    
    /**
     * Test remove 
     * */
    
}
