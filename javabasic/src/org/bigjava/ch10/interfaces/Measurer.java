package org.bigjava.ch10.interfaces;
/**
 * Describe any class whose objects can measure other objects
 * */
public interface Measurer {
	/**
	 * computes the measure of an object
	 * @param anObject the object to be measured
	 * @return the measure
	 * */
	
	double measure(Object anObject);
}
