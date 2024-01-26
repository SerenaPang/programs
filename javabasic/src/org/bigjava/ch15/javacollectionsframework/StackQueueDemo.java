package org.bigjava.ch15.javacollectionsframework;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This program demonstrates stacks, queues, and priority queues
 * */
public class StackQueueDemo {
	public static void main(String[] args) {
		System.out.println("Stack: ");
		Stack<String> s = new Stack<>();
		s.push("Tom");
		s.push("Diana");
		s.push("Harry");
		while (s.size() > 0) {
			System.out.println(s.pop());
		}
		
		System.out.println("Priority Queue: ");
		PriorityQueue<String> pq = new PriorityQueue<>();
		pq.add("Tom");
		pq.add("Diana");
		pq.add("Harry");
		while (pq.size() > 0) {
			System.out.println(pq.remove());
		}
	}
}
