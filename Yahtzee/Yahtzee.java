/**
 * 	Plays a game of Yahtzee between two players. // make better
 * 
 * 	@author	Aarav Prakash
 * 	@since	October 23, 2024
 */

public class Yahtzee
{
	public static void main(String[]args)
	{
		Yahtzee ye = new Yahtzee();
		ye.run();
	}
	
	public void run()
	{
		printHeader();
		
		YahtzeePlayer player1 = new YahtzeePlayer();
		player1.setName(Prompt.getString("Player 1, please enter your first name"));
		
		YahtzeePlayer player2 = new YahtzeePlayer();
		player2.setName(Prompt.getString("Player 2, please enter your first name"));
	}
	
	/**
	 * 	Checks who won the game
	 * 
	 * 	@return	1 if player 1 wins, 2 is player 2 wins, 0 if it is a tie
	 */
	public int whoWon(int scoreP1, int scoreP2)
	{
		if(scoreP1 > scoreP2)
			return 1;
		else if(scoreP1 < scoreP2)
			return 2;
		else
			return 0;
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
}
