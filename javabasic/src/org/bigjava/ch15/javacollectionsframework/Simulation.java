package org.bigjava.ch15.javacollectionsframework;
import java.util.PriorityQueue;

public class Simulation {
	private PriorityQueue<Event> eventQueue;
	private double currentTime;
	
	/**
	 * constructs a discrete event simulation.
	 * */
	public Simulation() {
		eventQueue = new PriorityQueue();
	}
	
	public double getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * compute exponentially distributed random numbers.
	 * @param mean the mean of the number sequence
	 * @return a random number
	 * */
	public static double expdist(double mean) {
		return -mean * Math.log(1 - Math.random());		
	}

	/**
	 * Adds an event to the event queue
	 *@param evt the event to add
	 * */
	public void addEvent(Event event) {
		eventQueue.add(event);
	}
	
	/**
	 * displays intermediate results after each event
	 * */
	public void display() {}
	
	/**
	 * displays summary results after the end of the simulation 
	 * */
	public void displaySummary() {}
	
	public void run(double startTime, double endTime) {
		currentTime = startTime;
		while (eventQueue.size() > 0 && currentTime <= endTime) {
			Event event = eventQueue.remove();
			currentTime = event.getTime();
			event.process(this);
			display();
		}
		displaySummary();
	}

}
