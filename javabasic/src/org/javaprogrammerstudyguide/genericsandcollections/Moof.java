package org.javaprogrammerstudyguide.genericsandcollections;

/**
 * Implementing an equals method
 * */
public class Moof {
	private int moofValue;
	Moof(int val) {
		moofValue = val;
	}
	
	public int getMoofValue() {
		return moofValue;
	}
	
	public boolean equals(Object o) {
		//make sure it's the same object type and content if the same
		if ((o instanceof Moof) && ((Moof)o).getMoofValue() == this.moofValue) {
			return true;
		} else {
			return false;
		}
	}
}
