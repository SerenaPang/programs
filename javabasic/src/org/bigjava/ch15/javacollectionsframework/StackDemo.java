package org.bigjava.ch15.javacollectionsframework;
import java.util.Stack;

/**
 * This program simulates an undo stack. Note that operations must be in the opposite order in which they are first issued
 * */
public class StackDemo {
	public static void main(String[] args) 
	{
		Stack<String> commands = new Stack<>();
		commands.push("Insert 'Hello'");
		commands.push("Insert ','");
		commands.push("Insert ' '");
		commands.push("Insert 'World'");
		commands.push("Insert '?'");
		commands.push("Insert '?'");
		commands.push("Insert '!'");
		
		//now we undo the last four commands
		for (int i = 1; i <= 7; i++) {
			String command = commands.pop();
			System.out.println("Undo " + command);
		} 
	}
}
