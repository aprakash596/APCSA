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
 *	@author
 *	@version
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	
	//	an array containing all of the puncutation
	private final char[] PUNCTUATION = new char[]{'.', ',', ';', ':', '(', 
		')', '?', '!', '=', '&', '~', '+','-'};
		
	private enum TagState {REGULAR, BOLD, ITALIC, NOPRINT};
	
	private TagState state = TagState.NOPRINT; 

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
	
	public void run(String[]args) 
	{
		Scanner input = null;
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
		
		
		/*
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
		*/
	}
	
	public void readTokenFile(String fileName)
	{
		input = FileUtils.openToRead(fileName);
		
		int tokenCount = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			String []tokenizedLine = util.tokenizeHTMLString(line);
			for(int i = 0; i < tokenizedLine.length; i++)
				tokens[tokenCount + i] = tokenizedLine[i];
			
			tokenCount += tokenizedLine.length;
		}
		
		input.close();
	}
	
	public void removeEmpty()
	{
		String[]placeHolder = tokens;

		int count = 0;
		while(placeHolder[count].length() > 0)
			count++;

		tokens = new String[count];

		for(int i = 0; i < count; i++)
			tokens[i] = placeHolder[i];
	}
	
	public void printTokens()
	{
		for(int i = 0; i < tokens.length; i++)
		{
			String token = tokens[i];
			if(token.indexOf('<') > -1 && token.indexOf('>') > -1)
			{
				token = token.toLowerCase();
				if(token.equals("<html>") && token[i].equalsIgoreCase("<body>"))
					state = TagState.REGULAR;
				else if(token.equals("</body>") && token[i].equalsIgoreCase("</html>"))
					state = TagState.NOPRINT;
				else if(tagState != TagState.NOPRINT)
				{
					if(token.equals("<b>")
						state = TagState.BOLD;
					if(token.equals("<\b>") || token.equals("<\i>"))
						state = TagState.REGULAR;
					if(token.equals("<i>"))
						state = TagState.ITALIC
				}
			}
		}
	}
}
