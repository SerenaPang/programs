package basic_operation;
import java.util.Scanner;
/**
 * 从控制台用户读取输入
 * */
public class ComputeAreaWithConsoleInput {
	public static void main(String[] args) {
		//create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//prompt the user to enter a radius
		System.out.println("Enter a number for radius: ");
		double radius = input.nextDouble();
		
		double area = radius * radius * 3.14159;
		System.out.println("The area for the circle of radius" + radius + " is " + area);
	}
}
