/**
 *	The game of Pig.
 *	A game between a user and the computer, in which there are two actions,
 * 	roll and hold. By rolling, you can add to your turn's total and by
 * 	holding, you are able to collect the turn's total. If you roll a 2 or
 * 	6, the roll is added to the turn's total. If you roll a 1, then 
 * 	you lose your turn and 0 is added instead of the turn's total.
 *
 *	@author Aarav Prakash	
 *	@since 	September 13, 2024
 */
public class PigGame 
{
	/**
	 *  The main method is used to call the methods to print the introduction
	 * 	and run the rest of the program.
	 */
	public static void main(String[]args)
	{
		PigGame pg = new PigGame();
		pg.printIntroduction();
		pg.runner();
	}
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	/**
	 * 	Sets up the PigGame and checks if user will play or look at 
	 * 	statistics.
	 */
	private void runner()
	{
		boolean gameOver = false;
		int yourTotal = 0;
		Dice dc = new Dice();
		int computerTotal = 0;

		char playOrStat = Prompt.getChar("Play game or statistics (p or s)");
		
		if(playOrStat == 'p')
		{
			while(! gameOver)
			{
				//System.out.println("**** USER Turn ****\n");
				yourTotal = turn(yourTotal, dc);
				
				//System.out.println("**** COMPUTER Turn ****\n");
				computerTotal = computerTurn(computerTotal, dc);
				
				if(yourTotal >= 100)
				{
					System.out.println("Your total score: " + yourTotal + "\n");
					System.out.println("Congratulations!!! YOU WON!!\n");
					System.out.println("Thanks for playing the Pig Game!!!");
				}

				gameOver = true;
			}
		}
		else if (playOrStat == 's');
		{
			System.out.println("\nRun statistical analysis - \"Hold at 20\"\n");
			
			int turnCount = Prompt.getInt("Number of turns", 1000, 1000000);
			
			
			double count0 = 0;
			double count20 = 0;
			double count21 = 0;
			double count22 = 0;
			double count23 = 0;
			double count24 = 0;
			double count25 = 0;
			
			
			for(int i = 0; i < turnCount; i++)
			{
				int oneTurnCount = computerStats(dc);
				if(oneTurnCount == 0)
					count0++;
				if(oneTurnCount == 20)
					count20++;
				if(oneTurnCount == 21)
					count21++;
				if(oneTurnCount == 22)
					count22++;
				if(oneTurnCount == 23)
					count23++;
				if(oneTurnCount == 24)
					count24++;
				if(oneTurnCount == 25)
					count25++;
			}
			System.out.println("\nScore\tEstimated Probability");
			System.out.printf("0 \t%.5f\n", (count0/((double)turnCount)));
			System.out.printf("20\t%.5f\n", (count20/((double)turnCount)));
			System.out.printf("21\t%.5f\n", (count21/((double)turnCount)));
			System.out.printf("22\t%.5f\n", (count22/((double)turnCount)));
			System.out.printf("23\t%.5f\n", (count23/((double)turnCount)));
			System.out.printf("24\t%.5f\n", (count24/((double)turnCount)));
			System.out.printf("25\t%.5f\n\n", (count25/((double)turnCount)));
		}
	}
	/**
	 * 	Does one turn for the user, which means that it runs until the 
	 * 	user holds or rolls a 1.
	 * 	@param  totalScore		total score of the player before the turn
	 * 	@param 	dc				holds an instance of the Dice class
	 * 	@return					returns new total score of the user after a turn
	 */
	private int turn(int totalScore, Dice dc)
	{
		boolean hold = false;
		int turnScore = 0;

		System.out.printf("Your turn score:   %d%s", turnScore, "\n");
		System.out.printf("Your total score:  %d%s", totalScore, "\n");

		while(! hold)
		{
			char move = Prompt.getChar("(r)oll or (h)old ");
			
			if(move == 'r')
			{
				System.out.println("\nYou ROLL");
				int rollValue = dc.roll();
				dc.printDice();

				if(rollValue != 1)
				{
					turnScore += dc.getValue();
					System.out.printf("\nYour turn score:  %d%s", turnScore, "\n");
				}
				else
				{
					System.out.println("You LOSE your turn");
					hold = true;
				}
			}
			else if(move == 'h')
			{
				System.out.println("You HOLD");
				totalScore += turnScore;
				hold = true;
			}
			System.out.printf("Your total score:  %d%s", totalScore, "\n");
		}
		return totalScore;
	}
	/**
	 * 	Runs a turn for the computer, in which the computer holds when
	 * 	their turn score reaches 20 or their total score reaches 100
	 * 	@param compTotal		the computer's total score before the turn
	 * 	@param dc				holds an instance of the Dice class
	 * 	@return 				the computer's total score after the turn
	 */
	private int computerTurn(int compTotal, Dice dc)
	{
		int compTurnScore = 0;

		System.out.printf("Computer's turn score:   %d%s", compTurnScore, "\n");
		System.out.printf("Computer's total score:  %d%s", compTotal, "\n");
		
		boolean hold = false;
		while(! hold)
		{
			String move = Prompt.getString("Press enter for computer turn");

			if(compTurnScore < 20)
			{
				System.out.println("Computer will ROLL");
				int rollValue = dc.roll();
				dc.printDice();

				if(rollValue != 1)
				{
					compTurnScore += dc.getValue();
					System.out.printf("\nComputer's turn score:%3d%s", compTurnScore, "\n");
				}
				else
				{
					System.out.println("Computer loses turn.");
					hold = true;
				}
			}
			else if(compTurnScore >= 20 || (compTurnScore + compTotal) >= 100)
			{
				System.out.println("Computer will HOLD");
				compTotal += compTurnScore;
				hold = true;
			}
			System.out.printf("Computer's total score:%2d%s", compTotal, "\n");
		}
		return compTotal;
	}
	/**
	 * 	Runs a turn in calculating the statistics for the user. It is similar
	 * 	to the method computerTurn(int compTotal, Dice dc) but it does
	 * 	not print anything and returns the turn score instead of the total.
	 * 	@param dc				holds an instance of the Dice class
	 * 	@return 				the computer's turn score
	 */
	public int computerStats(Dice dc)
	{
		int compTurnScore = 0;
		
		boolean hold = false;
		
		while(! hold)
		{
			if(compTurnScore < 20)
			{
				int rollValue = dc.roll();

				if(rollValue != 1)
					compTurnScore += dc.getValue();
				else
				{
					compTurnScore = 0;
					hold = true;
				}
			}
			else if(compTurnScore >= 20)
				hold = true;
		}
		return compTurnScore;
	}
}