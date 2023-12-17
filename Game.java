import java.util.Scanner;
public class Game{
	
	private static boolean playerTurn = false;
	private static boolean compTurn = false;
	private static boolean playerStand = false;
	private static boolean compStand = false;
	
	public static void GameRun(){
		int pBIndex = 0;
		//Board asks for a card
		Decks.playerBoard[pBIndex] = AskCard();
		pBIndex++;
		
		Board.CreateBoard();
	}
	
	//ask a card
	public static Card AskCard(){
		Card asked = Decks.Deck[Decks.deckTop];
		Decks.deckTop--;
		return asked;
	}
	
	//player chooses (end turn, stand, play a card from hand)
	public static void PlayerTurn(){
		Scanner sc = new Scanner(System.in);
		int choose = 0;
		while(playerTurn){
			System.out.println("What do you want to do:");
			System.out.println("1: End Turn, 2: Stand, 3: Choose A Card");
			choose = sc.nextInt();
			switch(choose){
				case 1:
					//end turn
					playerTurn = false;
					break;
				case 2:
					//stand
					playerTurn = false;
					playerStand = true;
					break;
				case 3:
					//play a card from hand
					System.out.println("Which card you want to choose? 1, 2, 3 or 4");
					break;
			}
		}
	}
	
	//check if player bust
	
	//check win conditions
}