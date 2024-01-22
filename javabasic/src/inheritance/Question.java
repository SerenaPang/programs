package inheritance;

/**
 * This is a super class for subclasses: multiple choice questions, numeric questions...
 * A question with text and answer*/
public class Question {
	private String text;
	private String answer;
	
	public Question() 
	{
		text = "";
		answer = "";
	}
	
	public void setText(String questionText) {
		text = questionText;
	}
	
	public void setAnswer(String correctResponse) {
		answer = correctResponse;
	}
	
	public boolean checkAnswer(String response) {
		return response.equals(answer);
	}
	
	public void display() {
		System.out.println(text);
	}
	
}
