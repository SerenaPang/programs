package org.corejava.ch4.inheritanceandreflection;

import java.util.Objects;

public class Item {
	private String description;
	private double price;
	
	public boolean equals(Object otherObject) {
		//use == test to see if the objects are identical(comparing address not content by default)
		if (this == otherObject) {
			return true;
		}
		//must return false if the parameter is null
		if(otherObject == null) {
			return false;
		}
		//check that otherObjectis an Item
		if (getClass() != otherObject.getClass()) {
			return false;
		}
		//test whether the instance variables have identical values
		Item other = (Item) otherObject;
		//Object.equals(description, other.description) && 
		return price == other.price;	
	}

	public int hashCode() {
		return Objects.hash(description, price);
	}
}
