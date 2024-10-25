/**
 * 	Plays a game of Yahtzee between two players. // make better
 * 
 * 	@author	Aarav Prakash
 * 	@since	October 23, 2024
 */

public class Yahtzee
{

	private int score1, score2;

	private YahtzeePlayer player1, player2;

	private YahtzeeScoreCard scoreCardP1, scoreCardP2;

	private DiceGroup diceP1, diceP2;

	private final int NUMBER_OF_DICE = 5;

	private final int NUMBER_OF_TURNS = 13;

	public Yahtzee()
	{
		score1 = score2 = 0;

		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();

		scoreCardP1 = player1.getScoreCard();
		scoreCardP2 = player2.getScoreCard();

		diceP1 = new DiceGroup();
		diceP2 = new DiceGroup();
	}

	public static void main(String[]args)
	{
		Yahtzee ye = new Yahtzee();
		ye.run();
	}
	
	public void run()
	{
		printHeader();
		
		player1.setName(Prompt.getString("Player 1, please enter your first name"));
		
		player2.setName(Prompt.getString("Player 2, please enter your first name"));

		Prompt.getString("Let's see who will go first. " + player1.getName() + ", please hit enter to roll the dice");

		int startingP1 = 0;
		int startingP2 = 0;

		while(startingP1 == startingP2)
		{
			Prompt.getString("Let's see who will go first. " + player1.getName() + ", please hit enter to roll the dice");

			for(int i = 0; i < NUMBER_OF_DICE; i++)
			{
				diceP1.rollDice();
				startingP1 = diceP1.getTotal();
			}

			Prompt.getString(player2.getName() + ", it's your turn. Please hit enter to roll the dice");

			for(int i = 0; i < NUMBER_OF_DICE; i++)
			{
				diceP2.rollDice();
				startingP2 = diceP2.getTotal();
			}
			
			System.out.print(player1.getName() + ", you rolled a sum of " + startingP1 + ", and");
			System.out.println(player2.getName() + ", you rolled a sum of " + startingP2 + ".");
		}

		if(startingP1 > startingP2)
		{
			System.out.println(player1.getName() + ", since your sum was higher, you'll roll first");
			for(int i = 0; i < NUMBER_OF_TURNS; i++)
			{
				playTurn(player1,scoreCardP1,diceP1,i+1);
				playTurn(player2,scoreCardP2,diceP2,i+1);
			}
		}
		else
		{
			System.out.println(player1.getName() + ", since your sum was higher, you'll roll first");
			for(int i = 0; i < NUMBER_OF_TURNS; i++)
			{
				playTurn(player2,scoreCardP1,diceP1,i+1);
				playTurn(player1,scoreCardP2,diceP2,i+1);
			}
		}
	}

	/**
	 * 
	 * @param scoreCard
	 * @param dices
	 * @param round
	 */
	public void playTurn(YahtzeePlayer player, YahtzeeScoreCard scoreCard, DiceGroup dices, int round)
	{
		scoreCard.printCardHeader();
		scoreCardP1.printPlayerScore(player1);
		scoreCardP2.printPlayerScore(player2);

		System.out.println("Round " + round + " of 13 rounds.");

		Prompt.getString("Betty, it's your turn to play. Please hit enter to roll the dice");

		dices.rollDice();

		dices.printDice();

		System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without");
		System.out.println("spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
		String heldDie = Prompt.getString("(enter -1 if you'd like to end the turn)"); 

		if(! heldDie.equals("-1"))
		{
			dices.rollDice(heldDie);
			dices.printDice();

			System.out.println("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without");
			System.out.println("spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125");
			heldDie = Prompt.getString("(enter -1 if you'd like to end the turn)"); 

			if(! heldDie.equals("-1"))
			{
				dices.rollDice(heldDie);
				dices.printDice();
			}
		}

		scoreCard.printCardHeader();
		scoreCardP1.printPlayerScore(player1);
		scoreCardP2.printPlayerScore(player2);
		scoreCard.printCardFooter();

		int choice = Prompt.getInt(player.getName() + ", now you need to make a choice. "
			+ "Pick a valid integer from the list above",1,13);

		scoreCard.changeScore(choice,dices);
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
