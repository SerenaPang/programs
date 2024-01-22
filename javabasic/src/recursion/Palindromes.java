package recursion;

public class Palindromes {
	/**
	 * Tests whether a text is a palindrome
	 * */
	public static boolean isPalindrome(String text) {
		int length = text.length();
		//separate case for shortest strings
		if(length <= 1) {
			return true;
		} else {
			//get first and last characters, converted to lowercase
			char first = Character.toLowerCase(text.charAt(0));
			char last = Character.toLowerCase(text.charAt(length - 1));
			
			if (Character.isLetter(first) && Character.isLetter(last)) {
				//both are letters
				if (first == last) {
					//remove both first and last character
					String shorter = text.substring(1, length - 1);
					return isPalindrome(shorter);
				}
				else {
					return false;
				}
			}
			else if (!Character.isLetter(last)) {
				//remove last character
				String shorter = text.substring(0, length - 1);
				return isPalindrome(shorter);
			}
			else
			{
				//remove first character
				String shorter = text.substring(1);
				return isPalindrome(shorter);
			}
		}
	}
	
	public static void main(String[] args) {
		String sentence1 = "Madam, I'm Adam!";
		System.out.println(sentence1);
		System.out.println("Palindrome: " + isPalindrome(sentence1));
		String sentence2 = "Sir, I'm Eve!";
		System.out.println(sentence2);
		System.out.println("Palindrome: " + isPalindrome(sentence2));
	}
}
