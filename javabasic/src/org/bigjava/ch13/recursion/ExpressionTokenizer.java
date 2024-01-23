package org.bigjava.ch13.recursion;
/**
 * this class breaks up a string describing an expression into tokens: numbers, parentheses, and operators
 * */
public class ExpressionTokenizer {
	private String input;
	private int start; //the start of current token
	private int end; // The position after the end of the current token
	
	public ExpressionTokenizer(String anInputExpression) {
		input = anInputExpression;
		start = 0;
		end = 0;
		nextToken(); //find the first token
	}

	/**
	 * peeks at the next token without consuming it
	 * */
	public String peekToken() {
		if (start >= input.length())
		{return null;}
		else 
		{return input.substring(start, end);}
	}

	/**
	 * Gets the next token and moves the tokenizer to the following token
	 * */
	public String nextToken() {
		String r = peekToken();
		start = end;
		if (start >= input.length()) {
			return r;
		}
		if (Character.isDigit(input.charAt(start))) {
			end = start + 1;
			while (end < input.length() && Character.isDigit(input.charAt(end))) 
			{
				end++;
			}
		}
		else
		{
			end = start + 1;
		}
		return r;
	}

}
