package com.mycompany.app;

/**
 * This class takes the user input of the number of perfumes, names, how much they like it.
 * and display a summary for the perfume.
 * */
public class Perfume {
	String name;
	double ratingScore;

	/**
	 * This method takes in the names of the perfume, 
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * This method sets the name of the perfume
	 * */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRating(double ratingScore) {
		this.ratingScore = ratingScore;
	}	
	
	public double getRating() {
		return ratingScore;
	}
}
