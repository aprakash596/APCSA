import java.util.ArrayList;
import java.util.List;

/**
 *	AnagramMaker - Creates a certain number of anagrams based on the the user
 * 	decide. The number of words within the anagram also depends on the user. 
 * 	Furthermore, the anagram is created from an inputted string.
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Aarav Prakash
 *	@since	January 15, 2025
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	private final int TOTAL_ALPHABET = 26;	// total number of letters in the alphabet
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() 
	{
		boolean qIsPressed = false;;
		while(! qIsPressed)
		{
			String word = Prompt.getString("Word(s), name or phrase (q to quit)");
			word = word.toLowerCase();
			word = removeNonLetters(word);
			if(word.equals("q"))
				qIsPressed = true;
			else
			{
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
				numPhrases = 0;
				System.out.println("");
				
				List<String> words = wu.getWords();

				makeAnagram(word, new ArrayList<>(), words);

				System.out.println("");
				System.out.println("Stopped at " + numPhrases + " anagrams"); // accounts for if less anagrams can be made then the inputted phrases
			}
		}
	}

	/**
	 * Runs the recursive process of the anagram creator
	 * @param word		the word to split into possible anagrams
	 * @param anagram	a list of a group of words that make up one anagram
	 * @param words		the dictionary of words that is edited to keep the words that are anagrams
	 */
	public void makeAnagram(String word, List<String> anagram, List <String> words)
	{
		if(numPhrases >= maxPhrases)
			return;
	
		if(word.length() == 0 && anagram.size() == numWords)
		{
			for (int i = 0; i < anagram.size(); i++) 
			{
				if (i > 0)
					System.out.print(" ");
				System.out.print(anagram.get(i));
			}
			System.out.println();
			numPhrases++;
			return;
		}
		else if(anagram.size() >= numWords)
			return;
		{
			int[] letterCount = new int[TOTAL_ALPHABET];
			for (int i = 0; i < word.length(); i++)
				letterCount[word.charAt(i) - 'a']++;

			for(int i = 0; i < words.size(); i++)
			{
				String currentWord = words.get(i);
				int [] compareCount = new int[TOTAL_ALPHABET];

				for (int j = 0; j < currentWord.length(); j++) 
					compareCount[(int)(currentWord.toLowerCase().charAt(j) - 'a')]++;
				
				boolean isAnagram = true;

				for(int j = 0; j < compareCount.length; j++)
				{
					if(isAnagram && compareCount[j] > letterCount[j])
						isAnagram = false;
				}

				if(isAnagram)
				{
					anagram.add(currentWord);

					String newWord = "";
					for (int j = 0; j < TOTAL_ALPHABET; j++) 
					{
						int remainingCount = letterCount[j] - compareCount[j];
						for (int k = 0; k < remainingCount; k++) 
							newWord += (char) (j + 'a');
					}

					List<String> temp = new ArrayList<>(words);
					makeAnagram(newWord, anagram, temp);

					anagram.remove(anagram.size() - 1);
				}
			}
		}
	}

	/**
	 * Removes non letters from the inputted phrase
	 * @param word	the inputted phrase
	 * @return		the inputted phrase without non letters
	 */
	public String removeNonLetters(String word)
	{
		String temp = "";
		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) <= 'z' && word.charAt(i) >= 'a')
				temp += "" + word.charAt(i);
		}
		return temp;
	}

}