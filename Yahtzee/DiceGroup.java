/**
 * 	// add later
 * 
 * 	@author	Aarav Prakash
 * 	@since	October 23, 2024
 */


public class DiceGroup {
	
	private Dice [] die;	// the array of dice
	
	private final int NUM_DICE = 5;	// number of dice
	
	// Create the seven different line images of a die
	private String [] line = {	" _______ ",
								"|       |",
								"| O   O |",
								"|   O   |",
								"|     O |",
								"| O     |",
								"|_______|" };
	
	/*	you complete */
	public DiceGroup() { }
	
	/**	you complete */
	public void rollDice() { }
	
	/**	Hold the dice in the rawHold and roll the rest.
	 *	For example: If rawHold is "421", then hold die 1, 2, and 4, and
	 *	roll 3 and 5.
	 *	@param rawHold		the string of dice to hold
	 *
	 *	you complete
	 */
	public void rollDice(String rawHold) { }
	
	/**	getters - you complete */
	
	/**	@return the total value of the DiceGroup - you complete */
	public int getTotal() { }
	
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	private void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 6; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	private void printDiceLine(int value) {
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	private int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
}
