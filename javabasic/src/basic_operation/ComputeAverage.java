package basic_operation;
import java.util.Scanner;

public class ComputeAverage {
	public static void main(String[] args) {
		//create a Scanner object
		Scanner input = new Scanner(System.in);
		//prompt the user for 3 numbers
		System.out.println("Enter 3 numbers for average: ");
		double number1 = input.nextDouble();
		double number2 = input.nextDouble();
		double number3 = input.nextDouble();
		
		//compute average
		double average = (number1 + number2 + number3) / 3;
		System.out.println("The average of " + number1 + " " + number2 + " " + number3 + " is "
				+ average);
	}
}
