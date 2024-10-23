/**
 *  This program defines the Dice class. A Dice is 6 sides by default, but can
 *  be overridden as n-sided. It keeps track of the number of rolls and
 *  the last roll value.
 *
 *  @author Mr Greenstein
 */
 
//do not edit

public class Dice {
	private int sides, rollCount, rollValue;
	
	/*	 @param s	The number of sides for die */
	public Dice (int s) {
		sides = s;
		rollCount = rollValue = 0;
	}
	
	/*	No-args constructor - default 6 sides */
	public Dice ( ) {
		this(6);
	}
	
	/**
	 *  One roll of the die
	 *
	 *  @return		The value of the rolled die
	 */
	public int roll ( ) {
		rollCount++;
		rollValue = (int)(Math.random()*sides) + 1;
		return rollValue;
	}
	
	/**	@return		number of rolls taken by this dice */
	public int numRolls ( ) { return rollCount; }
	
	/**	@return		the last rolled value */
	public int getValue ( ) { return rollValue; }
}






