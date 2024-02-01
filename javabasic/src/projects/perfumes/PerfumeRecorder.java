package projects.perfumes;
import java.util.Scanner;

/**
 * This class takes the user input of the number of perfumes, names, how much they like it.
 * and display a summary for the perfume.
 * */
public class PerfumeRecorder {
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
	public String getName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the perfume: ");
		String name = input.nextLine();
		System.out.println(name + " Recorded!");
		return name;
	}
	
	/**
	 * This method takes in the likeness score from the user
	 * */
	public double getScore() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the score of how much you like the perfume lowest(0 - 100)to highest: ");
		double score = input.nextDouble();
		System.out.println(score + " Recorded!");
		return score;
	}
}
