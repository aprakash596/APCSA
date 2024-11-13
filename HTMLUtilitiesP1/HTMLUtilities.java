/**
 *	Utilities for handling HTML
 *
 *	@author	Aarav Prakash
 *	@since	
 */
public class HTMLUtilities {
	
	// - is also a part of a word if in the middle
	
	private final char[] PUNCTUATION = new char[]{'.', ',', ';', ':', '(', 
		')', '?', '!', '=', '&', '~', '+','-'};

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		for(int i = 0; i < result.length; i++)
			result[i] = "";
			
		int notEmptyCount = 0;
		
		while(str.length() > 0)
		{
			String token = "";
			str = str.trim();
			
			if(str.charAt(0) == '<')
				token = tokenizeTag(str);
			else if((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') ||
				(str.charAt(0) >= 'A' && str.charAt(0) <= 'Z'))
				token = tokenizeString(str);
			else if(isPunctuation(str))
				token = "" + token.charAt(0);
			else
				token = tokenize
			
			while(result[notEmptyCount].length() > 0)
				notEmptyCount++;
				
			result[notEmptyCount] = token;
			
			str = str.substring(token.length());
			notEmptyCount = 0;
		}
		
		String[]placeholder = result;
		result = new String[notEmptyCount];
		for(int i = 0; i < result.length; i++)
			result[i] = placeholder[i];
		
		
		return result;
	}
	
	public String tokenizeNumber(String str)
	{
		int strIndex = 0;
		char digit = 
		
		while(str.charAt(
	}
	
	public boolean isPunctuation(String str)
	{
		boolean punctuation = false;
		for(int i = 0; i < PUNCTUATION.length; i++)
		{
			if(str.charAt(0) == PUNCTUATION[i])
			{
				if(PUNCTUATION[i] == '-' && str.length() != 1
					&& (str.charAt(1) >= '0' && str.charAt(1) <= '9'))
				punctuation = true;
			}
		}
		
		return punctuation;
	}
	
	public String tokenizeTag(String str)
	{
		return str.substring(str.indexOf('<'),str.indexOf('>')+1);
	}
	
	public String tokenizeString(String str)
	{
		String word = "";
		String lowerStr = str.toLowerCase();
		for(int i = 0; i < str.length(); i++)
		{
			if((lowerStr.charAt(i) >= 'a' && lowerStr.charAt(i) <= 'z')
				|| lowerStr.charAt(i) == '-')
				word += "" + str.charAt(i);
			else
				i = str.length();
		}
		
		return word;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}
