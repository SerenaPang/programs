package recursion;
/**
 * palindrome class with helper method
 * */
public class PalindromesHelper {
	public static boolean isPalindrome(String text) {
		return isPalindrome(text, 0 , text.length() - 1);
	}
	
	public static  boolean isPalindrome(String text, int start, int end) {
		//separate case for substring of length 0 and 1
		if (start >= end) 
		{
			return true;
		}else {
			//get first and last characters, converted to lower case
			char first = Character.toLowerCase(text.charAt(start));
			char last = Character.toLowerCase(text.charAt(end));
			if (Character.isLetter(first) && Character.isLetter(last)) 
			{
				if (first == last) {
					//test substring that doesn't contain the matching letters
					return isPalindrome(text, start + 1, end - 1);
				}
				else 
				{
					return false;
				}
			}
			else if (!Character.isLetter(last)) 
			{
				return isPalindrome(text, start, end - 1);
			} 
			else
			{
				return isPalindrome(text, start + 1, end);
			}
		}
	}
}
