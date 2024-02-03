package projects.perfumes;
/**
 * This class gets the information from the user and put it to the processor
 * */
public class UserInterface {
	PerfumeDataProcessor dp = new PerfumeDataProcessor();
	/**
	 * This method prints a welcome message to the user
	 * */
	public void printWelcome() {
		System.out.println("============================");
		System.out.println("Welcome to House of Perfume!");
		System.out.println("============================");
	}
	
	/**
	 * gets user input for the perfume
	 * */
	public void getInfo() {
		//dp.infoToMap();
	}
	
	/**
	 * This method display the summary of the ranking of the perfumes
	 * */
	public void displayInfo() {
		//dp.displayMap();
	}
	
	/**
	 * This method prints a goodbye message to the user
	 * */
	public void printGoodBye() {
		System.out.println("============================");
		System.out.println("Thank you for coming to House of Perfume! Goodbye!");
		System.out.println("============================");
	}
}
