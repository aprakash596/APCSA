import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author	Aarav Prakash
 *	@since	November 18, 2023
 */
public class HTMLRender 
{
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	
	//	an array containing all of the puncutation
	private final char[] PUNCTUATION = new char[]{'.', ',', ';', ':', '(', 
		')', '?', '!', '=', '&', '~', '+','-'};
		
	//	states for different printing methods
	private enum TagState {REGULAR, BOLD, ITALIC, NOPRINT, HEADING1, HEADING2,
        HEADING3, HEADING4, HEADING5, HEADING6, PREFORMAT};
	
	//	an array of tokens to check if a font state has ended
    private final String[]ENDING_TOKENS = new String[]{"</b>","</i>","</h1>",
        "</h2>","</h3>","</h4>","</h5>","</h6>","</pre>"};
	
	//	the program cannot print without having the html and body tag enclose the file
	private TagState state = TagState.NOPRINT; 

	//	keeps track of the number of characters for wrapping
    private int lineCount = 0;

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	private HTMLUtilities util;			// HTMLUtilities used in render
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		
		util = new HTMLUtilities();
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	/**
	 * 	Runs the program and all necessary methods to run it
	 */
	public void run(String[]args) 
	{
		String fileName = "";
		if (args.length > 0)
			fileName = args[0];
		else {
			System.out.println("Usage: java HTMLRender <htmlFileName>");
			System.exit(0);
		}
		
		readTokenFile(fileName);
		removeEmpty();
		printTokens();
	}
	
	/**
	 * 	Tokenize the HTML file
	 * 
	 * 	@param	fileName 	the name of the inputted HTML file
	 */
	public void readTokenFile(String fileName)
	{
		Scanner input = FileUtils.openToRead(fileName);
		
		int tokenCount = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			String []tokenizedLine = util.tokenizeHTMLString(line);
            if(tokenizedLine == null)
                tokenizedLine = new String[]{""};
                for(int i = 0; i < tokenizedLine.length; i++)
				    tokens[tokenCount + i] = tokenizedLine[i];

                tokenCount += tokenizedLine.length;
		}
		
		input.close();
	}
	
	/**
	 * 	Removes all empty tokens from the tokenized array
	 */
	public void removeEmpty()
	{
		String[]placeHolder = tokens;
		int count = 0;
		while(placeHolder[count] != null)
			count++;
		tokens = new String[count];
		for(int i = 0; i < count; i++)
			tokens[i] = placeHolder[i];
	}
	
	/**
	 * 	Changes the state to utilize the different print methods and
	 * 	calls the printer to print the non-tag tokens
	 */
	public void printTokens()
	{
		for(int i = 0; i < tokens.length; i++)
		{
			String token = tokens[i];
			if(token.indexOf('<') > -1 && token.indexOf('>') > -1)
			{
				token = token.toLowerCase();
				if(token.equals("<html>") && tokens[i+1].equalsIgnoreCase("<body>"))
					state = TagState.REGULAR;
				else if(token.equals("</body>") && tokens[i+1].equalsIgnoreCase("</html>"))
					state = TagState.NOPRINT;
				else if(state != TagState.NOPRINT)
				{
					if(token.equals("<p>"))
					{
						if(lineCount > 0)
							browser.println();
						browser.println();
						lineCount = 0;
					}
					else if(token.equals("</p>"))
					{
						browser.println();
						browser.println();
						lineCount = 0;
					}
					else if(token.equals("<b>"))
						state = TagState.BOLD;
					else if(token.equals("<i>"))
						state = TagState.ITALIC;
					else if(token.equals("<hr>"))
						browser.printHorizontalRule();
					else if(token.indexOf("h") > -1 && token.indexOf("/") == -1)
					{
						lineCount = 0;
						browser.println();
						browser.println();
						if(token.equals("<h1>"))
							state = TagState.HEADING1;
						else if(token.equals("<h2>"))
							state = TagState.HEADING2;
						else if(token.equals("<h3>"))
							state = TagState.HEADING3;
						else if(token.equals("<h4>"))
							state = TagState.HEADING4;
						else if(token.equals("<h5>"))
							state = TagState.HEADING5;
						else if(token.equals("<h6>"))
							state = TagState.HEADING6;
					}
                    else if(token.equals("<pre>"))
                        state = TagState.PREFORMAT;
                    else if(token.equals("<br>"))
					{
						lineCount = 0;
                        browser.printBreak();
					}
                    else if(token.equals("<q>"))
                        printer(" \"");
					else if(token.equals("</q>"))
						printer("\"");
                    else if(isRegular(token))
						state = TagState.REGULAR;
				}
			}
            else 
            {
                if(token.equals("\n"))
				{
					browser.println();
					lineCount = 0;
				}
                else if(isPunctuation(token))
				{	
					lineCount++;
                    printer(token);
				}
                else if(state != TagState.PREFORMAT)
				{
					if(token.length() != 0 && isOverLimit(token.length()))
					{
						printer("\n");
						lineCount = token.length();
					}
					else
						printer("");
					
					printer(token);
				}
				else if(state == TagState.PREFORMAT)
				{
					printer(token);
					printer("\n");
				}
            }
		}
	}


	/**
	 * 	Sets the font state to regular if a tag that ends a special font
	 * 	is encountered
	 * 	
	 * 	@param	token	an HTML tag
	 * 	@return	if the tag is an ending tag for a special font
	 */
    private boolean isRegular(String token)
    {
        for(int i = 0; i < ENDING_TOKENS.length; i++)
        {
            if(token.equals(ENDING_TOKENS[i]))
                return true;
        }
        return false;
    }

	/**
	 * 	Checks if the inputted token is punctuation
	 * 
	 * 	@param	token	a non-tag token
	 * 	@return	if the token is punctuation
	 */
    private boolean isPunctuation(String token)
    {
        boolean punctuation = false;
		for(int i = 0; i < PUNCTUATION.length; i++)
		{
			if(token.length() != 0 &&token.charAt(0) == PUNCTUATION[i])
			{
				if(PUNCTUATION[i] == '-' && token.length() > 1
					&& (token.charAt(1) >= '0' && token.charAt(1) <= '9'))
					punctuation = false;
				else
					punctuation = true;
			}
		}
		return punctuation;
    }

	/**
	 * 	Prints str based on the current font state
	 * 	
	 * 	@param	str		the current non-tag token
	 */
    private void printer(String str)
    {
		if(str.equals("\n"))
		{
			browser.println();
			lineCount = 0;
		}
        if(state == TagState.REGULAR)
            browser.print(str);
        else if(state == TagState.BOLD)
            browser.printBold(str);
        else if(state == TagState.ITALIC)
            browser.printItalic(str);
        else if(state == TagState.HEADING1)
            browser.printHeading1(str);
        else if(state == TagState.HEADING2)
            browser.printHeading2(str);
        else if(state == TagState.HEADING3)
            browser.printHeading3(str);
        else if(state == TagState.HEADING4)
            browser.printHeading4(str);
        else if(state == TagState.HEADING5)
            browser.printHeading5(str);
        else if(state == TagState.HEADING6)
            browser.printHeading6(str);
        else if(state == TagState.PREFORMAT)
		{
            browser.printPreformattedText(str);
			browser.println();
			lineCount = 0;
		}
    }

	/**
	 * 	Checks if the line goes over the specified limit for certain fonts
	 * 	to wrap the text
	 * 
	 * 	@param	tokenLength		the length of the token being printed
	 * 	@return	if the token goes over the limit
	 */
    private boolean isOverLimit(int tokenLength)
    {
		boolean addNewLine;

		int limit = 80;
        
		if(state == TagState.HEADING1)
			limit = 40;
		else if(state == TagState.HEADING2)
			limit = 50;
		else if(state == TagState.HEADING3)
			limit = 60;
		else if(state == TagState.HEADING5)
			limit = 100;
		else if(state == TagState.HEADING6)
			limit = 120;
		
		if(state != TagState.PREFORMAT)
		{
			if(lineCount + tokenLength > limit)
			{
				lineCount = tokenLength;
				addNewLine = true;
			}
			else
			{
				lineCount += tokenLength;
				addNewLine = false;
			}
		}
		else
		{
			lineCount = 0;
			addNewLine = true;
		}

		if(! addNewLine && lineCount != 0)
		{
			printer(" ");
			lineCount++;
		}

		return addNewLine;
    }
}
