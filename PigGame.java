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
public class PigGame {
	
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
	
	private void runner()
	{
		boolean gameOver = false;
		int yourTotal = 0;
		Dice dc = new Dice();
		int computerTotal = 0;
		
		while(! gameOver)
		{
			System.out.println("**** USER Turn ****\n");
			yourTotal = turn(yourTotal, dc);
			
			System.out.println("**** COMPUTER Turn ****\n");
			computerTotal = computerTurn(computerTotal, dc);

			gameOver = true;
		}
	}
	private int turn(int totalScore, Dice dc)
	{
		boolean hold = false;
		int turnScore = 0;

		System.out.printf("Your turn score:%6d%s", turnScore, "\n");
		System.out.printf("Your total score:%5d%s", totalScore, "\n");

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
					System.out.printf("\nYour turn score:%6d%s", turnScore, "\n");
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
			System.out.printf("Your total score:%5d%s", totalScore, "\n");
		}
		return totalScore;
	}
	private int computerTurn(int compTotal, Dice dc)
	{
		int compTurnScore = 0;

		System.out.printf("Computer's turn score:%3d%s", compTurnScore, "\n");
		System.out.printf("Computer's total score:%2d%s", compTotal, "\n");
		
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
			else if(compTurnScore >= 20)
			{
				System.out.println("Computer will HOLD");
				compTotal += compTurnScore;
				hold = true;
			}
			System.out.printf("Computer's total score:%2d%s", compTotal, "\n");
		}
		return compTotal;
	}
}
