/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author	Aarav Prakash
 *  @since	September 27, 2024
*/

public class PegArray 
{


	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;

	// number of pegs in an array
	private final static int PEGS_IN_CODE = 4;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) 
	{
		pegs = new Peg[PEGS_IN_CODE];
		for(int i = 0; i < PEGS_IN_CODE; i++)
		{
			pegs[i] = new Peg();
		}
		exactMatches = 0;
		partialMatches = 0;
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) 
	{
		for(int i = 0; i < PEGS_IN_CODE; i++)
		{
			if(master.getPeg(i).getLetter() == pegs[i].getLetter())
				exactMatches++;
		}
		return exactMatches;
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) 
	{
		for(char i = 'A'; i <= 'F'; i++)
		{
			partialMatches += checkLetter(master, i);
		}
		partialMatches -= exactMatches;
		if(partialMatches < 0)
			partialMatches = 0;
		return partialMatches; 
	}

	private int checkLetter(PegArray master, char letterToCheck)
	{
		int keyCount = 0;
		int guessCount = 0;
		for(int i = 0; i < PEGS_IN_CODE; i++)
		{
			if(master.getPeg(i).getLetter() == letterToCheck)
				keyCount++;
			
			if(pegs[i].getLetter() == letterToCheck)
				guessCount++;
		}
		
		if(guessCount >= keyCount)
			return keyCount;
		else
			return guessCount;
	}


	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
