package org.bigjava.ch9.inheritance;
import java.util.Scanner;

public class QuestionDemo1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Question q = new Question();
		q.setText("Who was the inventor of Java?");
		q.setAnswer("James Gosling");
		
		q.display();
		System.out.print("You answer: ");
		String response = in.nextLine();
		System.out.println(q.checkAnswer(response));
	}
}
