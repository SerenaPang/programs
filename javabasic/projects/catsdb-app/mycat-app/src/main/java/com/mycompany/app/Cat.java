package com.mycompany.app;
/**
 * A cat class 
 * */
public class Cat {
	private int catId;
	private String name;
	
	public Cat(int id, String name) {
		this.catId = id;
		this.name = name;
	}
	
	/**
	 * Gets the id of the cat
	 * @return id
	 * */
	public int getId() {
		return catId;
	}
	
	/**
	 * Gets the name of the cat
	 * @return name
	 * */
	public String getName() {
		return name;
	}
}
