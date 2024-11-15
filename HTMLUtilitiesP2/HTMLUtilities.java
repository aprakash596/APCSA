/**
 *	Utilities for handling HTML
 *
 *	@author	Aarav Prakash
 *	@since	November 1, 2024
 */
public class HTMLUtilities 
{
	//	an array containing all of the puncutation
	private final char[] PUNCTUATION = new char[]{'.', ',', ';', ':', '(', 
		')', '?', '!', '=', '&', '~', '+','-'};

	//	will contain strings from multiple lines if needed
	private String previousLines = "";

	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState {NONE, COMMENT, PREFORMAT};

	// the current tokenizer state
	private TokenState state = TokenState.NONE; 

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) 
	{
		// make the size of the array large to start
		String[] result = new String[10000];
		for(int i = 0; i < result.length; i++)
			result[i] = "";

		int resultIndex = 0;
		
		while(str.length() > 0)
		{
			resultIndex = 0;
			String token = "";

			if(state != TokenState.PREFORMAT)
				str = str.trim();

			if(str.indexOf("<!--") == 0)
				state = TokenState.COMMENT;

			if(state == TokenState.COMMENT && str.indexOf("-->") > -1)
			{
				state = TokenState.NONE;
				str = str.substring(str.indexOf("-->") + 3);
			}
			
			if(state != TokenState.COMMENT && str.length() > 0)
			{
				if(str.charAt(0) == '<')
				{
					if(str.indexOf('>') > -1)
						token = tokenizeTag(str);

					if(token.equals("<pre>"))
						state = TokenState.PREFORMAT;
					if(token.equals("</pre>"))
						state = TokenState.NONE;

				}
				else if((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') ||
					(str.charAt(0) >= 'A' && str.charAt(0) <= 'Z'))
				{
					token = tokenizeString(str);
				}
				else if(isPunctuation(str))
					token = "" + str.charAt(0);
				else
					token = tokenizeNumber(str);
			}

			if(state == TokenState.PREFORMAT)
			{
				token = str;
			}

			while(resultIndex < result.length && result[resultIndex].length() > 0)
				resultIndex++;

			if(state != TokenState.COMMENT && token.length() != 0)
			{
				while(resultIndex < result.length && result[resultIndex].length() > 0)
					resultIndex++;

				result[resultIndex] = token;

				str = str.substring(token.length());
			}
			else if(state == TokenState.COMMENT)
				str = "";
		}
		
		String[]placeHolder = result;

		int count = 0;
		while(placeHolder[count].length() > 0)
			count++;

		result = new String[count];

		for(int i = 0; i < count; i++)
			result[i] = placeHolder[i];

		if(result.length > 0)
			return result;
		else
			return null;
	}

	/**
	 * This tokenizes the first number within the string
	 * 
	 * @param str	the HTML string
	 * @return	the first number in the string
	 */
	public String tokenizeNumber(String str)
	{
		String number = "";
		int stringIndex = 0;

		if(str.charAt(stringIndex) == '-') 
		{
			number += str.charAt(stringIndex);
			stringIndex++;
		}

		while(stringIndex < str.length())
		{
			char stringChar = str.charAt(stringIndex);

			if((stringChar >= '0' && stringChar <= '9') || stringChar == '.' || 
				stringChar == 'e')
				number += "" + stringChar;
			else
				stringIndex = str.length();

			stringIndex++;
		}
		return number;
	}
	
	/**
	 * Checks if a string starts with punctuation
	 * 
	 * @param str	the HTML string
	 * @return	if the token at the beginning is punctuation
	 */
	public boolean isPunctuation(String str)
	{
		boolean punctuation = false;
		for(int i = 0; i < PUNCTUATION.length; i++)
		{
			if(str.charAt(0) == PUNCTUATION[i])
			{
				if(PUNCTUATION[i] == '-' && str.length() != 1
					&& (str.charAt(1) >= '0' && str.charAt(1) <= '9'))
					punctuation = false;
				else
					punctuation = true;
			}
		}
		return punctuation;
	}
	
	/**
	 * Tokenizes the first tag in the inputted string
	 * 
	 * @param str	the HTML string
	 * @return	the first tag in the string
	 */
	public String tokenizeTag(String str)
	{
		if(str.indexOf("<!--") != str.indexOf('<'))
			return str.substring(str.indexOf('<'),str.indexOf('>')+1);
		else
			return "";
	}

	/**
	 * Tokenizes a word from the HTML string
	 * 
	 * @param str	the HTML string
	 * @return	the first tokenized string
	 */
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