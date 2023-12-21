import java.util.Scanner;
import java.util.InputMismatchException;
public class Game{
	
	private static Scanner sc = new Scanner(System.in);
	
	private static boolean playerTurn = true;
	private static boolean compTurn = false;
	private static boolean playerStand = false;
	private static boolean compStand = false;
	private static boolean playerBust = false;
	private static boolean compBust = false;
	private static boolean playerWin = false;
	private static boolean compWin = false;
	private static int playerWinRound = 0;
	private static int compWinRound = 0;
	private static int gameSet = 0;
	private static int pBIndex = 0;
	private static int cBIndex = 0;
	private static int playerSum = 0;
	private static int compSum = 0;
	
	public static int GetPlayerWin(){
		return playerWinRound;
	}
	
	public static int GetCompWin(){
		return compWinRound;
	}
	
	public static void SetPlayerWin(){
		playerWinRound++;
	}
	
	public static void SetCompWin(){
		compWinRound++;
	}
	
	public static void GameRun(){
		
		do{
			System.out.println("New Set Begins");
			System.out.println("");
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
			
			if(playerStand && compStand){
				if(playerSum > compSum){
					playerWin = true;
				}else{
					compWin = true;
				}
			}
			
			if(compBust){
				SetPlayerWin();
				System.out.println("Player won the set");
			}else if(playerBust){
				SetCompWin();
				System.out.println("Computer won the set");
			}else if(playerWin){
				SetPlayerWin();
				System.out.println("Player won the set");
			}else if(compWin){
				SetCompWin();
				System.out.println("Computer won the set");
			}
			compBust = false;
			playerBust = false;
			playerWin = false;
			compWin = false;
			playerStand = false;
			compStand = false;
			pBIndex = 0;
			cBIndex = 0;
			playerSum = 0;
			compSum = 0;
			gameSet++;
			System.out.println("Set is over");
			for(int i= 0; i< Decks.GetPlayerBoard().length; i++){
				Decks.GetPlayerBoard()[i] = new Card(0,null,null);
			}
			for(int i= 0; i< Decks.GetCompBoard().length; i++){
				Decks.GetCompBoard()[i] = new Card(0,null,null);
			}
		}while(gameSet < 3);
		System.out.println("Game Over");
		
	}
	
	//ask a card
	public static Card AskCard(){
		Card asked = Decks.GetDeck()[Decks.GetDeckTop()];
		Decks.SetDeckTop(-1);
		return asked;
	}
	
	public static int Choose(int max) {
        int a = 0;

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
					a = sc.nextInt();
					if(a>0 && a<= max){
						break;
					}else{
						System.out.println("Invalid input. Please try again.");
					}
                } else {
                    System.out.println("Invalid input. Please try again.");
                    sc.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.next();
            }
        }
		System.out.println("");
        return a;
    }
	
	//player chooses (end turn, stand, play a card from hand)
	public static void PlayerTurn(){
		System.out.println("Player's turn:");
		System.out.println("");
		int choose = 0;
		
		Board.CreateBoard();
		
		System.out.println("What do you want to do:");
		System.out.println("1: Want A Card, 2: Stand, 3: Play A Card");
		
		choose = Choose(3);
			
			switch(choose){
				case 1:
					//Want a card from dealer
					Decks.GetPlayerBoard()[pBIndex] = AskCard();
					pBIndex++;
					Board.CreateBoard();
					playerSum = 0;
					for(Card p: Decks.GetPlayerBoard()){
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
					for(Card p: Decks.GetPlayerBoard()){
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
					int cardsInHand = 4;
					int cardsNotInHand = 0;
					System.out.println("Which card you want to choose?");
					for(Card p: Decks.GetPlayerHand()){
							if(p.getNumber() == 0 && p.getSpecial() == null){
								cardsNotInHand++;
							}
					}
					for(int i = 0; i < (cardsInHand - cardsNotInHand); i++){
						System.out.print((i +1) + ")"); 
						Decks.GetPlayerHand()[i].printDeck();
						System.out.print(" ");
					}
					System.out.println("");
					choose = Choose((cardsInHand - cardsNotInHand));
					Decks.GetPlayerBoard()[pBIndex] = Decks.GetPlayerHand()[choose-1];
					Decks.UpdatePlayerHand((choose - 1));
					pBIndex++;
					Board.CreateBoard();
					for(Card p: Decks.GetPlayerBoard()){
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
	
	public static void CompTurn(){
		System.out.println("Computer's turn:");
		System.out.println("");
		
		Board.CreateBoard();
		int choose = 2;
		int chooseCard = 0;
		compSum = 0;
		for(Card p: Decks.GetCompBoard()){
			compSum = compSum + p.getNumber();
		}
		
			//An algorithm for computer to choose
			if(compSum < 15){
				choose = 1;
			}else if((compSum >= 15 && compSum < 20)|| compSum > 20){
				for(int i= 0; i < Decks.GetCompHand().length; i++){
					if(Decks.GetCompHand()[i].getNumber() + compSum == 20){
						//use this card
						choose = 3;
						chooseCard = i;
					}else{
						if(Decks.GetCompHand()[i].getNumber() == 0){
							if(Decks.GetCompHand()[i].getSpecial().equals("x2")){
								if(compSum + Decks.GetCompBoard()[cBIndex-1].getNumber() == 20){
									//use this card
									choose = 3;
									chooseCard = i;
								}
							}else if(Decks.GetCompHand()[i].getSpecial().equals("+/-")){
								if(compSum + (Decks.GetCompBoard()[cBIndex-1].getNumber() * -2) == 20){
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
					//Computer asks for a card
					System.out.println("Computer asks for a card");
					System.out.println("");
					Decks.GetCompBoard()[cBIndex] = AskCard();
					cBIndex++;
					Board.CreateBoard();
					if(compSum > 20){
						compBust = true;
						System.out.println("Computer is bust!");
						System.out.println("");
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
					System.out.println("Computer stands");
					System.out.println("");
					if(compSum > 20){
						compBust = true;
						System.out.println("Computer is bust!");
						System.out.println("");
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
					System.out.println("");
					Decks.GetCompBoard()[cBIndex] = Decks.GetCompHand()[chooseCard];
					Decks.GetCompHand()[chooseCard] = new Card(0,"","");
					cBIndex++;
					Board.CreateBoard();
					compSum = 0;
					for(Card p: Decks.GetCompBoard()){
						compSum = compSum + p.getNumber();
					}
					if(compSum > 20){
							compBust = true;
							System.out.println("Computer is bust!");
							System.out.println("");
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