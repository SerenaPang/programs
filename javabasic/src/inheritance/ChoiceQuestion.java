package inheritance;

import java.util.ArrayList;

/**
 * the subclass inherits all public methods from the super class. 
 * A subclass can override a superclass method by providing a new implementation.
 * */
public class ChoiceQuestion extends Question{
	//This instance variable is added to the subclass
	private ArrayList<String> choices;
	public ChoiceQuestion() {
		choices = new ArrayList<String>();
	}
	
	public void addChoice(String choice, boolean correct) {
		choices.add(choice);
		if (correct) {
			//covert choices.size() to string
			String choiceString = "" + choices.size();
			setAnswer(choiceString);
		}
	}

	//This methods overrides a method from the superclass
	public void display() {
		//use a reserved word super to call a superclass method
		super.display();
		//display the answer choices
		for (int i = 0; i < choices.size(); i++) {
			int choiceNumber = i + 1;
			System.out.println(choiceNumber + ": " + choices.get(i));
		}
	}
}
