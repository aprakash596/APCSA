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
		
		while(! gameOver)
		{
			System.out.println("**** USER Turn ****\n");
			yourTotal = turn(yourTotal, dc);
			
			gameOver = true;
		}
	}
	private int turn(int totalScore, Dice dc)
	{
		boolean hold = false;
		int turnScore = 0;
		while(! hold)
		{
			System.out.println("Your turn score: " + turnScore);
			System.out.println("Your total score: " + totalScore + "\n");
			
			char move = Prompt.getChar("(r)oll or (h)old -> ");
			
			if(move == 'r')
			{
				int rollValue = dc.roll();
				dc.printDice();
			}
			
			hold = true;
		}
		return totalScore;
	}
}
