import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher is a programs that can encrypt or decrypt a given file with
 * 	an inputted String. The program matches the characters in the string
 * 	with each letter in the file, cycling through them. Then, the position
 * 	of the letter in the alphabet is the amount that each letter in the 
 * 	file is shifted. The encryption only applies to letters. The opposite
 * 	of this process is the decryption.
 *	
 *	@author Aarav Prakash
 *	@since	September 20, 2024
 */
public class MVCipher 
{		
	/** Constructor */
	public MVCipher() { }
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	This method prompts the user for a string with only letters that is
	 * 	at least 3 letters long, the input file name, and the output file name.
	 * 	It opens the files and adds the encrypted or decrypted letters to
	 * 	the output file and then closes the file. It also calls the methods
	 * 	to encrypt and decrypt the input file.
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
		int encryptOrDecrypt = Prompt.getInt("Encrypt or decrypt?",1,2);
		System.out.println("");

		String inputFileName = "";
		if(encryptOrDecrypt == 1)
			inputFileName = Prompt.getString("Name of file to encrypt");
		else
			inputFileName = Prompt.getString("Name of file to decrypt");
		
		String outputFileName = Prompt.getString("Name of output file");
		
		Scanner inputFile = FileUtils.openToRead(inputFileName);
		PrintWriter outputFile = FileUtils.openToWrite(outputFileName);
		
		if(encryptOrDecrypt == 1)
		{
			while(inputFile.hasNextLine())
			{
				String line = inputFile.nextLine();
				String encryptedLine = encrypter(line, key);
				outputFile.println("" + encryptedLine);
			}
			System.out.println("\nThe encrypted file " + outputFileName + " has been"
					+ " created using the keyword -> " + key + "\n");
		}
		else
		{
			while(inputFile.hasNextLine())
			{
				String line = inputFile.nextLine();
				String decryptedLine = decrypter(line, key);
				outputFile.println("" + decryptedLine);
			}
			System.out.println("\nThe decrypted file " + outputFileName + " has been"
					+ " created using the keyword -> " + key + "\n");
		}
		outputFile.close();
	}
	
	/**
	 *	This method decrypts the string using a for loop to cycle each 
	 * 	individual letter.
	 * 
	 * 	@param		line	a line from the input file to decrypt
	 * 	@param		key		the key string used to decrypt
	 * 	@return				the decrypted line
	 */
	public String decrypter(String line, String key)
	{
		int keyIndex = 0;
		String decryptedLine = "";
		for(int i = 0; i < line.length(); i++)
		{
			if(keyIndex == key.length())
				keyIndex = 0;
			
			String keyValue = "" + key.charAt(keyIndex);

			String decryptedLetter = "";

			if((int)line.charAt(i) >= 65 && (int)line.charAt(i) <= 90)
				decryptedLetter = upperDecrypt(line.charAt(i), keyValue);
			else if((int)line.charAt(i) >= 97 && (int)line.charAt(i) <= 122)
				decryptedLetter = lowerDecrypt(line.charAt(i), keyValue);
			else
			{
				decryptedLetter = "" + line.charAt(i);
				keyIndex--;
			}
			decryptedLine += "" + decryptedLetter;
			keyIndex++;
		}
		return decryptedLine;
	}
	
	/**
	 * 	This method decrypts an inputted uppercase letter.
	 * 
	 * 	@param		letter		an uppercase letter from a line of the file
	 * 	@param		keyValue	the corresponding letter from the key string to letter
	 * 	@return 				a shifted uppercase letter
	 */
	public String upperDecrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter - 1 == 64)
				letter = 'Z';
			else
				letter = (char) ((int) letter - 1);
		}
		return "" + letter;
	}

	/**
	 * 	This method decrypts an inputted lowercase letter.
	 * 
	 * 	@param		letter		an lowercase letter from a line of the file
	 * 	@param		keyValue	the corresponding letter from the key string to letter
	 * 	@return 				a shifted lowercase letter
	 */
	public String lowerDecrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter - 1 == 96)
				letter = 'z';
			else
				letter = (char) ((int) letter - 1);
		}
		return "" + letter;
	}

	/**
	 *	This method encrypts the string using a for loop to cycle each 
	 * 	individual letter.
	 * 
	 * 	@param		line	a line from the input file to encrypt
	 * 	@param		key		the key string used to encrypt
	 * 	@return				the encrypted line
	 */
	public String encrypter(String line, String key)
	{
		int keyIndex = 0;
		String encryptedLine = "";
		for(int i = 0; i < line.length(); i++)
		{
			if(keyIndex == key.length())
				keyIndex = 0;
			
			String keyValue = "" + key.charAt(keyIndex);

			String encryptedLetter = "";

			if((int)line.charAt(i) >= 65 && (int)line.charAt(i) <= 90)
				encryptedLetter = upperEncrypt(line.charAt(i), keyValue);
			else if((int)line.charAt(i) >= 97 && (int)line.charAt(i) <= 122)
				encryptedLetter = lowerEncrypt(line.charAt(i), keyValue);
			else
			{
				encryptedLetter = "" + line.charAt(i);
				keyIndex--;
			}
			encryptedLine += "" + encryptedLetter;
			keyIndex++;
		}
		return encryptedLine;
	}

	/**
	 * 	This method encrypts an inputted uppercase letter.
	 * 
	 * 	@param		letter		an uppercase letter from a line of the file
	 * 	@param		keyValue	the corresponding letter from the key string to letter
	 * 	@return 				a shifted uppercase letter
	 */
	public String upperEncrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter + 1 == 91)
				letter = 'A';
			else
				letter = (char) ((int) letter + 1);
		}
		return "" + letter;
	}

	/**
	 * 	This method encrypts an inputted lowercase letter.
	 * 
	 * 	@param		letter		an lowercase letter from a line of the file
	 * 	@param		keyValue	the corresponding letter from the key string to letter
	 * 	@return 				a shifted lowercase letter
	 */
	public String lowerEncrypt(char letter, String keyValue)
	{
		int shiftValue = ((int) keyValue.charAt(0)) - ((int) 'A') + 1;
		for(int i = 1; i <= shiftValue; i++)
		{
			if((int)letter + 1 == 123)
				letter = 'a';
			else
				letter = (char) ((int) letter + 1);
		}
		return "" + letter;
	}

}