/**
 *	Plays the game of MasterMind.
 *	<Describe the game here>
 *	@author	Aarav Prakash
 *	@since	September 27, 2024
 */

public class MasterMind 
{

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F
											
	/**
	 * 	Constructor
	 */
	public MasterMind()
	{
		PegArray pa = new PegArray(PEGS_IN_CODE);
		guesses = new PegArray[10];
	}
	
	/**
	 * 	The main method
	 */									
	public static void main(String[]args)
	{
		MasterMind mm = new MasterMind();
		//mm.printIntroduction();
		mm.run();
	}
	
	public void run()
	{
		master = setMasterCode();
		/*for(int i = 0; i < guesses.length; i++)
		{
			guesses[i]
		}*/
		//printBoard();
	}
	
	/**
	 * 	sets the master code
	 * 	@return 	an array for the master code
	 */
	public PegArray setMasterCode()
	{
		
	}

	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}

/*
|   10   |       |       |       |       |   0      0    |
+--------+-------+-------+-------+-------+---------------+

Guess 4

Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right -> afaf
+--------+-------+-------+-------+-------+---------------+
| MASTER |  ***  |  ***  |  ***  |  ***  | Exact Partial |
|        +-------+-------+-------+-------+               |
| GUESS  +-------+-------+-------+-------+---------------|
|    1   |   A   |   B   |   C   |   D   |   1      0    |
|        +-------+-------+-------+-------+---------------|
|    2   |   A   |   E   |   E   |   F   |   2      0    |
|        +-------+-------+-------+-------+---------------|
|    3   |   A   |   F   |   F   |   A   |   2      2    |
|        +-------+-------+-------+-------+---------------|
|    4   |   A   |   F   |   A   |   F   |   2      2    |
|        +-------+-------+-------+-------+---------------|
|    5   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    6   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    7   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    8   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    9   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|   10   |       |       |       |       |   0      0    |
+--------+-------+-------+-------+-------+---------------+

Guess 5

Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right -> aaff
+--------+-------+-------+-------+-------+---------------+
| MASTER |   A   |   A   |   F   |   F   | Exact Partial |
|        +-------+-------+-------+-------+               |
| GUESS  +-------+-------+-------+-------+---------------|
|    1   |   A   |   B   |   C   |   D   |   1      0    |
|        +-------+-------+-------+-------+---------------|
|    2   |   A   |   E   |   E   |   F   |   2      0    |
|        +-------+-------+-------+-------+---------------|
|    3   |   A   |   F   |   F   |   A   |   2      2    |
|        +-------+-------+-------+-------+---------------|
|    4   |   A   |   F   |   A   |   F   |   2      2    |
|        +-------+-------+-------+-------+---------------|
|    5   |   A   |   A   |   F   |   F   |   4      0    |
|        +-------+-------+-------+-------+---------------|
|    6   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    7   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    8   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    9   |       |       |       |       |   0      0    |
|        +-------+-------+-------+-------+---------------|
|   10   |       |       |       |       |   0      0    |
+--------+-------+-------+-------+-------+---------------+

Nice work! You found the master code in 5 guesses.
// has an empty line underneath
*/
/*
Guess 10

Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right -> aaaa
+--------+-------+-------+-------+-------+---------------+
| MASTER |   E   |   D   |   C   |   E   | Exact Partial |
|        +-------+-------+-------+-------+               |
| GUESS  +-------+-------+-------+-------+---------------|
|    1   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    2   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    3   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    4   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    5   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    6   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    7   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    8   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|    9   |   A   |   A   |   A   |   A   |   0      0    |
|        +-------+-------+-------+-------+---------------|
|   10   |   A   |   A   |   A   |   A   |   0      0    |
+--------+-------+-------+-------+-------+---------------+
Oops. You were unable to find the solution in 10 guesses. //no empty line underneath
*/

