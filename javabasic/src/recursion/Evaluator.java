package recursion;
/**
 * A class that computes the value of an arithmetic expression
 * */

public class Evaluator {
	private ExpressionTokenizer tokenizer;
	/**
	 * constructs an evaluator
	 * */
	public Evaluator(String anExpression) {
		tokenizer = new ExpressionTokenizer(anExpression);
	}
	/**
	 * Evaluates the expression
	 * */
	public int getExpressionValue() {
		int value = getTermValue();
		boolean done = false;
		while(!done) 
		{
			String next = tokenizer.peekToken();
			if ("+".equals(next) || "-".equals(next)) 
			{
				tokenizer.nextToken(); // discard + or -
				int value2 = getTermValue();
				if ("+".equals(next)) 
				{
					value = value + value2;
				} else 
				{
					value = value - value2;
				}
			}else 
			{
				done = true;
			}
		}
		return value;
	}
	
	/**
	 * Evaluates the next term found in the expression
	 * */
	private int getTermValue() {
		int value = getFactorValue();
		boolean done = false;
		while (!done) {
			String next = tokenizer.peekToken();
			if ("*".equals(next) || "/".equals(next)) {
				tokenizer.nextToken();
				int value2 = getFactorValue();
				if ("*".equals(next)) {
					value = value * value2;
				} else {
					value = value / value2;
				}
			}
			else {
				done = true;
			}
		}
		return value;
	}
	
	/**
	 * evaluates the next factor found in the expression
	 * */
	private int getFactorValue() {
		int value;
		String next = tokenizer.peekToken();
		if ("(".equals(next)) {
			tokenizer.nextToken();//discard "("
			value = getExpressionValue();
			tokenizer.nextToken();
		}
		else
		{
			value = Integer.parseInt(tokenizer.nextToken());
		}
		return value;
	}
	
}
