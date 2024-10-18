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
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils() { }
	
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
	public void printWords (String [] wordList) { }
	
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
				for(int i = 0; i < placeHolder.length; i++)
					multipleBest[i] = placeHolder[i];
				multipleBest.length()
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
			score += scoreTable[word.charAt(i)-65];
		}
		
		return score;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = "1";
		while((letters.length() <= 3 && letters.length() >= 12) && isOnlyLetters(letters))
		{
			letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
			letters = letters.toLowerCase();
		}
		
		loadWords();
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10}; //	pick a random word if have the same score
		String best = bestWord(word,scoreTable);
		
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
	
	/**
	 * 	Checks if the letters string is composed of only letters
	 * 
	 * 	@return if the letters string is composed of only letters
	 */
	public boolean isOnlyLetters(String letters)
	{
		boolean onlyLetters = true;
		for(int i = 0; i < letters.length(); i++)
		{
			char letter = letters.charAt(i);
			if(letter < 'a' || letter > 'z')
				onlyLetters = false;
		}
		return onlyLetters;
	}
}
