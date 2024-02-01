package projects.perfumes;
import java.util.Scanner;

/**
 * This class demonstrate how perfume recorder works
 * */
public class PerfumeRecorderDemo {
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.printWelcome();
			
		boolean again = true;
		while(again == true) {
			System.out.println("Enter [a] to add a new perfume; Enter [b] to display the ranking; Enter [c] to exit: ");
			Scanner in = new Scanner(System.in);
			String choices = in.next();
			
			switch(choices) {
				case "a":  ui.getInfo();
				break;
			
				case "b" : ui.displayInfo();
				break;	
				
				case "c" :
					ui.printGoodBye();
					again = false;
					break;
			}
		}
	}
}
