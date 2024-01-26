package org.bigjava.ch17.treestructures;
/**
 * This class encapsulates a work order with a priority
 * */
public class WorkOrder implements Comparable{
	private int priority;
	private String description;
	
	/**
	 * constructs a work order with a given priority and description
	 * @param a priority the priority of this work order
	 * @param aDescription the description of this work order
	 * */
	public WorkOrder(int aPriority, String aDescription) {
		priority = aPriority;
		description = aDescription;
	}
	
	public String toString() {
		return "priority=" + priority + ". description=" + description;
	}
	
	@Override
	public int compareTo(Object otherObject) {
		WorkOrder other = (WorkOrder) otherObject;
		if (priority < other.priority) {
			return -1;
		}
		if (priority > other.priority) {
			return 1;
		}
		return 0;
	}
}
