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
		String key = "";
		while(! allAlphabet && ! lengthThree)
		{
			allAlphabet = true;
			lengthThree = true;
			
			key = Prompt.getString("Please input a word to use as key (letters only)");
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
		int encryptOrDecrypt = Prompt.getInt("Encrypt or decrypts",1,2);
		System.out.println("");

		String inputFileName = "";
		if(encryptOrDecrypt == 1)
			inputFileName = Prompt.getString("Name of file to encrypt");
		else
			inputFileName = Prompt.getString("Name of file to decrypt");
		
		String outputFileName = Prompt.getString("Name of output file");
		
		Scanner inputFile = FileUtils.openToRead(inputFileName);
		PrintWriter outputFile = FileUtils.openToWrite(outputFileName);
		
		while(inputFile.hasNextLine())
		{
			String line = inputFile.nextLine();
			String encryptedLine = encrypter(line, key);
		}
	}

	public String encrypter(String line, String key)
	{
		int keyIndex = 0;
		for(int i = 0; i < line.length(); i++)
		{
			if(keyIndex == key.length())
				keyIndex = 0;
			
			String keyValue = "" + key.charAt(keyIndex);

			String encryptedLetter = "";

			if((int)line.charAt(i) >= 65 || (int)line.charAt(i) <= 90)
				encryptedLetter = upperEncrypt(line.charAt(i), keyValue);
			else if((int)line.charAt(i) >= 97 || (int)line.charAt(i) <= 122)
				encryptedLetter = lowerEncrypt(line.charAt(i), keyValue);
			else
			{
				encryptedLetter = "" + line.charAt(i);
				keyIndex--;
			}
			keyIndex++;
		}
	}

	public String upperEncrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter + 1 == 90)
				letter = 'A';
			else
				letter = (char) ((int) letter + 1);
		}
	}

	public String lowerEncrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter + 1 == 122)
				letter = 'A';
			else
				letter = (char) ((int) letter + 1);
		}
	}

}
