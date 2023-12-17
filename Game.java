import java.util.Scanner;
public class Game{
	
	private static boolean playerTurn = true;
	private static boolean compTurn = false;
	private static boolean playerStand = false;
	private static boolean compStand = false;
	private static boolean playerBust = false;
	private static boolean compBust = false;
	private static int pBIndex = 0;
	private static int cBIndex = 0;
	
	public static void GameRun(){
		
		do{
			if(!playerStand){
				playerTurn = true;
				PlayerTurn();
			}
			if(!compStand){
				compTurn = true;
				CompTurn();
			}
		}while(!playerBust && !compBust);
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
		Decks.playerBoard[pBIndex] = AskCard();
		pBIndex++;
		Board.CreateBoard();
		
		while(playerTurn && !playerBust && !playerStand){
			System.out.println("What do you want to do:");
			System.out.println("1: End Turn, 2: Stand, 3: Choose A Card");
			choose = sc.nextInt();
			int sum = 0;
			switch(choose){
				case 1:
					//end turn
					for(Card p: Decks.playerBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							playerBust = true;
							System.out.println("You are bust!");
							//Start a new game
					}
					playerTurn = false;
					break;
				case 2:
					//stand
					for(Card p: Decks.playerBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							playerBust = true;
							System.out.println("You are bust!");
							//Start a new game
					}
					playerTurn = false;
					playerStand = true;
					break;
				case 3:
					//play a card from hand
					System.out.println("Which card you want to choose? 1, 2, 3 or 4");
					choose = sc.nextInt();
					Decks.playerBoard[pBIndex] = Decks.playerHand[choose-1];
					pBIndex++;
					Board.CreateBoard();
					for(Card p: Decks.playerBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							playerBust = true;
							System.out.println("You are bust!");
							//Start a new game
					}
					playerTurn = false;
					break;
			}
		}
	}
	
	public static void CompTurn(){
		Decks.compBoard[cBIndex] = AskCard();
		cBIndex++;
		Board.CreateBoard();
		int choose = 1;
		while(compTurn && !compBust && !compStand){
			
			//An algorithm for computer to choose
			
			int sum = 0;
			switch(choose){
				
				case 1:
					//end turn
					for(Card p: Decks.compBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					break;
				case 2:
					//stand
					for(Card p: Decks.compBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					compStand = true;
					break;
				case 3:
					//play a card from hand
					Decks.compBoard[cBIndex] = Decks.compHand[choose-1];
					cBIndex++;
					Board.CreateBoard();
					for(Card p: Decks.compBoard){
						sum = sum + p.getNumber();
					}
					if(sum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					break;
			}
		}
	}
	
	//check if player bust
	
	//check win conditions
}