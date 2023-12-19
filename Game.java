import java.util.Scanner;
public class Game{
	
	private static boolean playerTurn = true;
	private static boolean compTurn = false;
	private static boolean playerStand = false;
	private static boolean compStand = false;
	private static boolean playerBust = false;
	private static boolean compBust = false;
	private static boolean playerWin = false;
	private static boolean compWin = false;
	private static int playerWinRound = 0;
	private static int compWinRound = 1;
	private static int gameSet = 0;
	private static int pBIndex = 0;
	private static int cBIndex = 0;
	private static int playerSum = 0;
	private static int compSum = 0;
	
	public static void GameRun(){
		
		do{
			System.out.println("Yeni set başlıyor");
			do{
			if(!playerStand){
				playerTurn = true;
				PlayerTurn();
			}
			if(!compStand){
				compTurn = true;
				CompTurn();
			}
			}while((!playerBust) && (!compBust) && (!playerWin) && (!compWin));
			
			if(compBust){
				playerWinRound ++;
				System.out.println("Player won the set");
			}else if(playerBust){
				compWinRound ++;
				System.out.println("Computer won the set");
			}else if(playerWin){
				playerWinRound ++;
				System.out.println("Player won the set");
			}else if(compWin){
				compWinRound ++;
				System.out.println("Computer won the set");
			}
			compBust = false;
			playerBust = false;
			playerWin = false;
			compWin = false;
			playerStand = false;
			compStand = false;
			gameSet++;
			System.out.println("Set bitti");
			for(int i= 0; i< Decks.playerBoard.length; i++){
				Decks.playerBoard[i] = new Card(0,null,null);
			}
			for(int i= 0; i< Decks.compBoard.length; i++){
				Decks.compBoard[i] = new Card(0,null,null);
			}
		}while(gameSet < 4);
		System.out.println("Oyun bitti");
		
	}
	
	//ask a card
	public static Card AskCard(){
		Card asked = Decks.Deck[Decks.deckTop];
		Decks.deckTop--;
		return asked;
	}
	
	//player chooses (end turn, stand, play a card from hand)
	public static void PlayerTurn(){
		System.out.println("Oyuncunun turu");
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
						compWin = true;
						//Start a new game
					}else if(playerSum == 20){
						playerWin = true;
						playerStand = true;
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
						compWin = true;
						//Start a new game
					}else if(playerSum == 20){
						playerWin = true;
						playerStand = true;
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
						compWin = true;
						//Start a new game
					}else if(playerSum == 20){
						playerWin = true;
						playerStand = true;
					}
					playerTurn = false;
					break;
			}
		}
	}
	
	public static void CompTurn(){
		System.out.println("Computer's turn");
		Decks.compBoard[cBIndex] = AskCard();
		cBIndex++;
		Board.CreateBoard();
		int choose = 2;
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
								continue;
							}
						}else{
							continue;
						}
					}
				}
			}else if(compSum == 20){
				choose = 2;
			}else{
				choose = 2;
			}
			
			switch(choose){
				case 1:
					//end turn
					if(compSum > 20){
						compBust = true;
						System.out.println("Computer is bust!");
						playerWin = true;
						//Start a new game
					}else if(compSum == 20){
						compWin = true;
						compStand = true;
					}
					compTurn = false;
					break;
				case 2:
					//stand
					if(compSum > 20){
						compBust = true;
						System.out.println("Computer is bust!");
						playerWin = true;
						//Start a new game
					}else if(compSum == 20){
						compWin = true;
						compStand = true;
					}
					compTurn = false;
					break;
				case 3:
					//An algorithm for computer to choose again
					System.out.println("Computer played a card");
					Decks.compBoard[cBIndex] = Decks.compHand[chooseCard];
					Decks.compHand[chooseCard] = new Card(0,"","");
					cBIndex++;
					Board.CreateBoard();
					compSum = 0;
					for(Card p: Decks.compBoard){
						compSum = compSum + p.getNumber();
					}
					if(compSum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							playerWin = true;
							//Start a new game
					}else if(compSum == 20){
						compWin = true;
						compStand = true;
					}
					compTurn = false;
					break;
			}
		}
	}
}