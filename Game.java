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
	private static int playerSum = 0;
	private static int compSum = 0;
	
	public static void GameRun(){
		
		do{
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
			if(compBust){
				playerWin ++;
			}else if(playerBust){
				computerWin ++;
			}
			gameSet++;
		}while(gameSet < 4);
		
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
			switch(choose){
				case 1:
					//end turn
					playerSum = 0;
					for(Card p: Decks.playerBoard){
						playerSum = playerSum + p.getNumber();
					}
					if(playerSum > 20){
							playerBust = true;
							System.out.println("You are bust!");
							//Start a new game
					}
					playerTurn = false;
					break;
				case 2:
					//stand
					playerSum = 0;
					for(Card p: Decks.playerBoard){
						playerSum = playerSum + p.getNumber();
					}
					if(playerSum > 20){
							playerBust = true;
							System.out.println("You are bust!");
							//Start a new game
					}
					playerTurn = false;
					playerStand = true;
					break;
				case 3:
					//play a card from hand
					playerSum = 0;
					System.out.println("Which card you want to choose? 1, 2, 3 or 4");
					choose = sc.nextInt();
					Decks.playerBoard[pBIndex] = Decks.playerHand[choose-1];
					pBIndex++;
					Board.CreateBoard();
					for(Card p: Decks.playerBoard){
						playerSum = playerSum + p.getNumber();
					}
					if(playerSum > 20){
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
		int chooseCard = 0;
		compSum = 0;
		for(Card p: Decks.compBoard){
			compSum = compSum + p.getNumber();
		}
		
		while(compTurn && !compBust && !compStand){
			
			//An algorithm for computer to choose
			if(compSum < 15){
				choose = 1;
			}else if((compSum >= 15 && compSum < 20)|| compSum > 20){
				for(int i= 0; i < Decks.compHand.length; i++){
					if(Decks.compHand[i].getNumber() + compSum == 20){
						//use this card
						choose = 3;
						chooseCard = i;
					}else{
						if(Decks.compHand[i].getNumber() == 0){
							if(Decks.compHand[i].getSpecial().equals("x2")){
								if(compSum + Decks.compBoard[cBIndex-1].getNumber() == 20){
									//use this card
									choose = 3;
									chooseCard = i;
								}
							}else if(Decks.compHand[i].getSpecial().equals("+/-")){
								if(compSum + (Decks.compBoard[cBIndex-1].getNumber() * -2) == 20){
									//use this card
									choose = 3;
									chooseCard = i;
								}
							}else{
								choose = 2;
							}
						}else{
							choose = 2;
						}
					}
				}
			}else if(compSum == 20){
				choose = 2;
			}else{
				choose = 1;
			}
			
			switch(choose){
				case 1:
					//end turn
					if(compSum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					break;
				case 2:
					//stand
					if(compSum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					compStand = true;
					break;
				case 3:
					//An algorithm for computer to choose again
					
					Decks.compBoard[cBIndex] = Decks.compHand[chooseCard];
					Decks.compHand[chooseCard] = null;
					cBIndex++;
					Board.CreateBoard();
					compSum = 0;
					for(Card p: Decks.compBoard){
						compSum = compSum + p.getNumber();
					}
					if(compSum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							//Start a new game
					}
					compTurn = false;
					break;
			}
		}
	}
}