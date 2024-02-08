package com.mycompany.app;

import java.util.Comparator;
import java.util.Scanner;

/**
 * This class compare the ratings of the perfumes
 * */
public class Rating {
	double ratingScore;

	//move to ui
	/**
	 * This method takes in the likeness rating from the user
	 * */
	public double getScoreFromUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the score of how much you like the perfume lowest(0 - 100)to highest: ");
		double score = input.nextDouble();
		System.out.println(score + " Recorded!");
		return score;
	}
	
	/**
	 * This method returns the rating score of the user for a perfume
	 * */
	public double getRatingScore() {
		return ratingScore;
	}
	
	/**
	 * This class compare the rating score of the perfumes
	 * */
	static class MyComparator implements Comparator<Rating>{
		@Override
		public int compare(Rating perfume1, Rating perfume2) {
			return Double.compare(perfume1.getRatingScore(), perfume2.getRatingScore());
		}	
	}
}
