package org.bigjava.ch18.generics;
/**
 * a student with a name and a major
 * */
public class Student extends Person{
	private String major;
	
	/**
	 * constructs a Student object
	 * */
	public Student(String aName, String aMajor) {
		super(aName);
		major = aMajor;
	}
	
	public String toString() {
		return super.toString() + "[major=" + major + "]";
	}

}
