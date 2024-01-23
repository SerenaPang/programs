package org.bigjava.ch10.interfaces;

public class Data {
/**
 * Computers the average of the measures of the given objects
 * @param objects and array of objects
 * @param meas the measurer for the objects
 * @return the average of the measures
 * */
	public static double average(Object[] objects, Measurer meas) {
		double sum = 0;
		for (Object obj : objects) {
			sum = sum + meas.measure(obj);
		}
		if (objects.length > 0) {
			return sum / objects.length;
		} else {
			return 0;
		}
	}
}
