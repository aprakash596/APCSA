/**
 * 	// add later
 * 
 * 	@author	Aarav Prakash
 * 	@since	October 23, 2024
 */

public class YahtzeeScoreCard
{
	private int[]scoreCard;

	public YahtzeeScoreCard()
	{
		scoreCard = new int[13];
		for(int i = 0; i < scoreCard.length; i++)
			scoreCard[i] = -1; // -1 means there should be no score
	}

	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	/**
	 *  Print the scorecard footer when the player makes a choice
	 */
	public void printCardFooter() {
		System.out.println("\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6    7    8    9  " +
						" 10   11   12   13 \n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	public int getScore(int choice)
	{
		return scoreCard[choice-1];
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) 
	{
		if(choice >= 1 && choice <= 6)
			numberScore(choice,dg);
		else if(choice == 7)
			threeOfAKind(dg);
		else if(choice == 8)
			fourOfAKind(dg);
		else if(choice == 9)
			fullHouse(dg);
		else if(choice == 10)
			smallStraight(dg);
		else if(choice == 11)
			largeStraight(dg);
		else if(choice == 12)
			chance(dg);
		else if(choice == 13)
			yahtzeeScore(dg);
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) 
	{
		Dice[]die = dg.getDie();
		int score = 0;
		for(int i = 0; i < die.length; i++)
		{
			if(die[i].getValue() == choice)
				score += choice;
		}
		scoreCard[choice-1] = score;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) 
	{
		
	}
	
	public void fourOfAKind(DiceGroup dg) {}
	
	public void fullHouse(DiceGroup dg) {}
	
	public void smallStraight(DiceGroup dg) {}
	
	public void largeStraight(DiceGroup dg) {}
	
	public void chance(DiceGroup dg) {}
	
	public void yahtzeeScore(DiceGroup dg) {}

}
