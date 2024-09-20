import java.util.Scanner;
import java.io.PrintWriter;


/**
 *	MVCipher - Add your description here !!!!!!!!!!!!!!!!!!!
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author Aarav Prakash
 *	@since	September 20, 2024
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() 
	{
		//checking: diff (cat Macbeth.txt) (cat decrypted.txt) for the school computer
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		boolean allAlphabet = false;
		boolean lengthThree = false;
		while(! allAlphabet && ! lengthThree)
		{
			allAlphabet = true;
			lengthThree = true;
			
			String key = Prompt.getString("Please input a word to use as key (letters only)");
			key = key.toUpperCase();
			
			for(int i = 0; i < key.length(); i++)
			{
				if(key.charAt(i) < (char)65 || key.charAt(i) > 90)
					allAlphabet = false;
				if(key.length() < 3)
					lengthThree = false;
			}
		}
		
		System.out.println("");
		/* Prompt for encrypt or decrypt */
		
		int encryptOrDecrypt = Prompt.getInt("Encrypt or decrypt (1,2)",1,2);
		System.out.println("");

		String inputFileName = "";
		if(encryptOrDecrypt == 1)
			inputFileName = Prompt.getString("Name of file to encrypt");
		else
			inputFileName = Prompt.getString("Name of file to decrypt");
		
		String outputFileName = Prompt.getString("Name of output file");
		
		Scanner inputFile = FileUtils.openToRead(inputFileName);
		Printwriter outputFile = FileUtils.openToWrite(outputFileName);
		
		String[]inputLines = reader(inputFile);
		
		
		
		
		/* Don't forget to close your output file */
	}
	
	public String[] reader(Scanner inputFile)
	{
		String[]inputLines = new String[1];
		boolean firstRead = false;
		while(inputFile.hasNextLine())
		{
			if(! firstRead)
				inputLines[0] = inputFile.nextLine();
			else
			{
				String[]placeholder = inputLines;
				inputLines = new String[placeHolder.length()+1];
				for(int i = 0; i < inputLines.length
			}
		}
	}
	
}
