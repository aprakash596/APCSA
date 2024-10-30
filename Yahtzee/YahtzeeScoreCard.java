/**
 * 	Sets the scores for the scorecard in the game Yahtzee
 * 
 * 	@author	Aarav Prakash
 * 	@since	October 23, 2024
 */

public class YahtzeeScoreCard
{
	private int[]scoreCard;

	private final int[]SCORE_INDICES = new int[]{0,1,2,3,4,5,6,7,
		8,9,10,11,12,13};
	
	private final int HIGHEST_ROLL = 6;

	/**
	 * 	Sets up scoreCard values as 0
	 */
	public YahtzeeScoreCard()
	{
		scoreCard = new int[14];
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
		System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9  " +
						" 10   11   12   13 \n");
		System.out.println("");
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

	/**
	 * 	Returns the score of the choice that the player made
	 * 	after rolling the die.
	 * 
	 * 	@param choice 	the choice that the player made
	 * 	@return			the score of the choice
	 */
	public int getScore(int choice)
	{
		return scoreCard[choice];
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 * 	@return    if the choice has already been picked by the player
	 */
	public boolean changeScore(int choice, DiceGroup dg) 
	{
		boolean alreadyPicked = false;

		if(choice >= 1 && choice <= 6 && scoreCard[SCORE_INDICES[choice]] == -1)
			numberScore(choice,dg);
		else if(choice == 7 && scoreCard[SCORE_INDICES[7]] == -1)
			threeOfAKind(dg);
		else if(choice == 8 && scoreCard[SCORE_INDICES[8]] == -1)
			fourOfAKind(dg);
		else if(choice == 9 && scoreCard[SCORE_INDICES[9]] == -1)
			fullHouse(dg);
		else if(choice == 10 && scoreCard[SCORE_INDICES[10]] == -1)
			smallStraight(dg);
		else if(choice == 11 && scoreCard[SCORE_INDICES[11]] == -1)
			largeStraight(dg);
		else if(choice == 12 && scoreCard[SCORE_INDICES[12]] == -1)
			chance(dg);
		else if(choice == 13 && scoreCard[SCORE_INDICES[13]] == -1)
			yahtzeeScore(dg);
		else
			alreadyPicked = true;

		return alreadyPicked;
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
		scoreCard[choice] = score;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) 
	{
		int score = 0;
		Dice[]die = dg.getDie();
		for(int i = 0; i <= HIGHEST_ROLL; i++)
		{
			int count = 0;
			for(int j = 0; j < die.length; j++)
			{
				if(die[j].getValue() == i)
					count++;
			}
			if(count >= 3)
				score = 3 * i;
		}
		scoreCard[SCORE_INDICES[7]] = score;
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) 
	{
		int score = 0;
		Dice[]die = dg.getDie();
		for(int i = 0; i <= HIGHEST_ROLL; i++)
		{
			int count = 0;
			for(int j = 0; j < die.length; j++)
			{
				if(die[j].getValue() == i)
					count++;
			}
			if(count >= 4)
				score = 4 * i;
		}
		scoreCard[SCORE_INDICES[8]] = score;
	}
	
	/**
	 *	Updates the scorecard for Full House choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) 
	{
		boolean threeKind = false;
		boolean twoPair = false;
		Dice[]die = dg.getDie();

		for(int i = 0; i <= HIGHEST_ROLL; i++)
		{
			int count = 0;
			for(int j = 0; j < die.length; j++)
			{
				if(die[j].getValue() == i)
					count++;
			}
			if(count == 3)
				threeKind = true;
			else if(count == 2);
				twoPair = true;
		}
		if(threeKind && twoPair)
			scoreCard[SCORE_INDICES[9]] = 25;
		else
			scoreCard[SCORE_INDICES[9]] = 0;
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void smallStraight(DiceGroup dg) 
	{
		Dice[]die = dg.getDie();
		int[]count = new int[HIGHEST_ROLL];

		for(int i = 0; i < die.length; i++)
			count[die[i].getValue() - 1]++;

		int straightCount = 0;
		for(int i = 0; i < HIGHEST_ROLL; i++)
		{
			if(count[i] >= 1)
				straightCount++;
			else if(straightCount < 4)
				straightCount = 0;
		}

		if(straightCount >= 4)
			scoreCard[SCORE_INDICES[10]] = 30;
		else
			scoreCard[SCORE_INDICES[10]] = 0;
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void largeStraight(DiceGroup dg) 
	{
		boolean isStraight = true;
		Dice[]die = dg.getDie();
		int[]count = new int[HIGHEST_ROLL];

		for(int i = 0; i < die.length; i++)
			count[die[i].getValue() - 1]++;


		//	checks for one of each of 2-5 since must be in a large straight
		for(int i = 1; i < HIGHEST_ROLL-1; i++)
		{
			if(count[i] != 1)
				isStraight = false;
		}

		if(isStraight)
			scoreCard[SCORE_INDICES[11]] = 40;
		else
			scoreCard[SCORE_INDICES[11]] = 0;
	}

	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void chance(DiceGroup dg) 
	{
		int score = 0;
		Dice[]die = dg.getDie();

		for(int i = 0; i < die.length; i++)
			score += die[i].getValue();

		scoreCard[SCORE_INDICES[12]] = score;
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void yahtzeeScore(DiceGroup dg) 
	{
		boolean isYahtzee = true;
		Dice[]die = dg.getDie();
		int yahtzeeNum = die[0].getValue();

		for(int i = 1; i < die.length; i++)
		{
			if(die[i].getValue() != yahtzeeNum)
				isYahtzee = false;
		}

		if(isYahtzee)
			scoreCard[SCORE_INDICES[13]] = 50;
		else
			scoreCard[SCORE_INDICES[13]] = 0;
	}

}
