package projects.perfumes;
import java.util.Scanner;

/**
 * This class takes the user input of the number of perfumes, names, how much they like it.
 * and display a summary for the perfume.
 * */
public class Perfume {
	String name;
	Rating rating;
	double ratingScore;
	
	/**
	 * This method gets the number of perfumes to record
	 * */
	public int getNumber() {
		//gets the number of perfume user wants to put
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter how many perfumes you want to record: ");
		int numbers = input.nextInt();
		System.out.println("Ready to record " + numbers + " of perfumes now");
		return numbers;	
	}
	
	/**
	 * This method takes in the names of the perfume, 
	 * */
	public String getPerfumeName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the perfume: ");
		String name = input.nextLine();
		System.out.println(name + " Recorded!");
		return name;
	}
	
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
	
	/**
	 * This method takes in the likeness score from the user
	 * */
//	public double getRatingScore() {
//		return rating.getRatingScore();
//	}
	
	/**
	 * This method sets the rating of the perfume
	 * */
	public void setRatingScore(double rating) {
		this.rating.ratingScore = rating;
	}	
	
	public void setRating(double ratingScore) {
		this.ratingScore = ratingScore;
	}	
	
	public double getRating() {
		return ratingScore;
	}
	
}
