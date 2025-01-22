import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author	Aarav Prakash
 *	@since	October 18, 2024
 */

public class WordUtilities
{
	private String [] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "randomWords.txt";
	
	/* Constructor */
	public WordUtilities() { }

	/**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () 
	{ 
		Scanner input = FileUtils.openToRead(WORD_FILE);
		words = new String[1];
		words[0] = input.next();
		while(input.hasNext())
		{
			String[]placeHolder = words;
			words = new String[placeHolder.length+1];
			for(int i = 0; i < placeHolder.length; i++)
			{
				words[i] = placeHolder[i];
			}
			words[placeHolder.length] = input.next();
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{
		String[]foundWords = new String[1];
		for(int i = 0; i < words.length; i++)
		{
			String word = words[i];
			if(isWordMatch(word, letters))
			{
				foundWords[foundWords.length-1] = word;
				String[]placeHolder = foundWords;
				foundWords = new String[placeHolder.length+1];
				for(int j = 0; j < placeHolder.length; j++)
					foundWords[j] = placeHolder[j];
			}
		}
		
		if(foundWords[foundWords.length-1] == null)
		{
			String[]placeHolder = foundWords;
			foundWords = new String[foundWords.length-1];
			for(int i = 0; i < foundWords.length; i++)
				foundWords[i] = placeHolder[i];
		}
		
		return foundWords;
	}
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	 public boolean isWordMatch (String word, String letters) 
	 {
		 for(int a = 0; a < word.length(); a++)
		 {
			 char c = word.charAt(a);
			 if(letters.indexOf(c) > -1)
				 letters = letters.substring(0, letters.indexOf(c))
							 + letters.substring(letters.indexOf(c) + 1);
			 else
		 		 return false;
		 }
		
		 return true;
	 }
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) 
	{ 
		for(int i = 0; i < wordList.length; i++)
		{
			if(i != 0 && i%5 == 0)
				System.out.print("\n");
			System.out.printf("%-15s", wordList[i]);
		}
		System.out.println("");
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		String best = wordList[0];
		int bestScore = getScore(wordList[0], scoreTable);
		String[]multipleBest = new String[1];
		
		for(int i = 1; i < wordList.length; i++)
		{
			int score = getScore(wordList[i], scoreTable);
			if(score > bestScore)
			{
				best = wordList[i];
				bestScore = score;
				multipleBest = new String[1];
				multipleBest[0] = best;
			}
			else if(score == bestScore)
			{
				String[]placeHolder = multipleBest;
				multipleBest = new String[placeHolder.length+1];
				for(int j = 0; j < placeHolder.length; j++)
					multipleBest[j] = placeHolder[j];
				multipleBest[multipleBest.length-1] = wordList[i];
			}
		}
		
		if(multipleBest.length > 1)
		{
			best = wordList[(int)(Math.random()*(multipleBest.length-1))];
		}
		
		return best;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int score = 0;
		for(int i = 0; i < word.length(); i++)
		{
			score += scoreTable[(int)word.charAt(i) - 97];
		}
		
		return score;
	}
}
