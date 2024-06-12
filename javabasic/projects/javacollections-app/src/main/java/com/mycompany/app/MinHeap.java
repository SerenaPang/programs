package com.mycompany.app;

public class MinHeap() {
	private int[] array;//array.length is the capacity of the min heap
	private int size;//how many elements are in the min heap
	
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("input array can not be null or empty");
		}
		this.array = array;
		size = array.length;
		heapify();
	}
	
	private void heapify() {
		
	}
	
	public MinHeap(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity can not be <= 0");
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == array.length;
	}
	
	private void percolateUp(int index) {
		
	}
	
	private void percolateDown(int index) {
		
	}
	
	public int peek() {
		
	}
	
	public int poll() {
		
	}
	
	public void offer(int element) {
		
	}
	
	public int update(int index, int element) {
		
	}
	
	private void swap(int indexLeft, int indexRight) {
		int temp = array[indexLeft];
		array[indexLeft] = array[indexRight];
		array[indexRight] = temp;
	}
}