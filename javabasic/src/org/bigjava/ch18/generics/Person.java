package org.bigjava.ch18.generics;
/**
 * a person with a name
 * */
public class Person implements Comparable<Person>{
	private String name;
	/**
	 * construct a person object
	 * */
	public Person(String aName) {
		name = aName;
	}
	
	public String toString() {
		return getClass().getName() + "[name=" + name + "]";
	}

	@Override
	public int compareTo(Person other) {
		return name.compareTo(other.name);
	}

}
