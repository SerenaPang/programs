package org.bigjava.ch10.interfaces;

public interface Measurable {
	double getMeasure(); //an abstract method
	
	default boolean smallerThan(Measurable other) {
		return getMeasure() < other.getMeasure();
	}
	
	static double average(Measurable[] objects) // a static method
	{
		double sum = 0;
		for (Measurable obj : objects) {
			sum = sum + obj.getMeasure();
		}
		if (objects.length > 0) {
			return sum / objects.length;
		} else {
			return 0;
		}
	}
}
