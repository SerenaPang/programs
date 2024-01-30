package org.bigjava.ch15.javacollectionsframework;

public class Event implements Comparable<Event>{
	private double time;
	
	public Event(double eventTime) {
		time = eventTime;
	}
	
	public void process(Simulation sim) {}
	public double getTime() {return time;}
	
	@Override
	public int compareTo(Event other) {
		if (time < other.time) {
			return -1;
		} else if(time > other.time) {
			return 1;
		}else {
			return 0;
		}
	}
}
