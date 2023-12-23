import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
public class Game{
	
	private static Scanner sc = new Scanner(System.in);
	
	private static boolean playerStand = false;
	private static boolean compStand = false;
	private static boolean playerBust = false;
	private static boolean compBust = false;
	private static int playerWinRound = 0;
	private static int compWinRound = 0;
	private static int gameSet = 0;
	private static int pBIndex = 0; //player board index
	private static int cBIndex = 0; //computer board index
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
			System.out.println("-------------New Set Begins------------");
			System.out.println("");
			do{
				if(!playerStand){
					PlayerTurn();
				}
				if(!compStand){
					CompTurn();
				}
			}while((!playerBust) && (!compBust) && !(compStand && playerStand));
			
			if(compBust){
				SetPlayerWin();
				System.out.println("Player won the set");
			}else if(playerBust){
				SetCompWin();
				System.out.println("Computer won the set");
			}else if(playerStand && compStand){
				if(playerSum > compSum){
					SetPlayerWin();
					System.out.println("Player won the set");
				}else if(playerSum==compSum){
					System.out.println("Set is tied");
				}else{
					SetCompWin();
					System.out.println("Computer won the set");
				}
			}else{
				SetCompWin();
				System.out.println("Computer won the set");
			}
			
			compBust = false;
			playerBust = false;
			playerStand = false;
			compStand = false;
			pBIndex = 0;
			cBIndex = 0;
			playerSum = 0;
			compSum = 0;
			gameSet++;
			System.out.println("Set is over");
			System.out.println("");
			for(int i= 0; i< Decks.GetPlayerBoard().length; i++){
				Decks.GetPlayerBoard()[i] = new Card(0,null,null);
			}
			for(int i= 0; i< Decks.GetCompBoard().length; i++){
				Decks.GetCompBoard()[i] = new Card(0,null,null);
			}
		}while(gameSet < 3);
		System.out.println("Game Over");
		System.out.println("Score: " + BlueJack.getPlayerName() + " " + Game.GetPlayerWin() + " - "+ "Computer " + Game.GetCompWin());
		System.out.println("");
		
	}
	
	//ask a card
	public static Card AskCard(){
		if(Decks.GetDeckTop() == Decks.GetDeckBottom()){
			playerStand = true;
			compStand = true;
			gameSet = 3;
		}
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
            }catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please try again.");
                sc.next();
            }catch (Exception ex) {
                System.out.println("Invalid input. Please try again.");
                sc.next();
            }
        }
		System.out.println("");
        return a;
    }
	
	public static void compCheck(){
		compSum = 0;
		for(Card p: Decks.GetCompBoard()){
			compSum = compSum + p.getNumber();
		}
		
		if(compSum > 20){
			compBust = true;
			System.out.println("Computer is bust!");
			System.out.println("");
			//Start a new game
		}else if(compSum == 20){
			compStand = true;
			int checkedCard = 0;
			int colorBlue = 0;
			for(Card p: Decks.GetCompBoard()){
				if(p.getColor() != null){
					checkedCard++;
					if(p.getColor().equals("B")){
						colorBlue++;
					}
				}
			}
			if(checkedCard == colorBlue){
				playerBust = true;
				playerWinRound = 0;
				compWinRound = 2;
				gameSet = 3;
			}
		}else if(cBIndex == 9 && compSum <= 20){
			playerBust = true;
		}
	}
	
	public static void playerCheck(){
		playerSum = 0;
		for(Card p: Decks.GetPlayerBoard()){
			playerSum = playerSum + p.getNumber();
		}
		
		if(playerSum > 20){
			playerBust = true;
			System.out.println("You are bust!");
			System.out.println("");
			//Start a new game
		}else if(playerSum == 20){
			int checkedCard = 0;
			int colorBlue = 0;
			for(Card p: Decks.GetPlayerBoard()){
				if(p.getColor() != null){
					checkedCard++;
					if(p.getColor().equals("B")){
						colorBlue++;
					}
				}
			}
			if(checkedCard == colorBlue){
				compBust = true;
				playerWinRound = 2;
				compWinRound = 0;
				gameSet = 3;
			}
			playerStand = true;
		}else if(pBIndex == 9 && playerSum <= 20){
			compBust = true;
		}
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
					playerCheck();
					break;
				case 2:
					//stand
					playerCheck();
					playerStand = true;
					break;
				case 3:
					//play a card from hand
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
					
					if(Decks.GetPlayerHand()[choose-1].getNumber() == 0 && Decks.GetPlayerHand()[choose-1].getSpecial().equals("x2")){
						if(pBIndex > 0){
							Decks.GetPlayerBoard()[pBIndex-1].setNumber(Decks.GetPlayerBoard()[pBIndex-1].getNumber() * 2);
						}
						Decks.GetPlayerBoard()[pBIndex] = Decks.GetPlayerHand()[choose-1];
						pBIndex++;
					}else if(Decks.GetPlayerHand()[choose-1].getNumber() == 0 && Decks.GetPlayerHand()[choose-1].getSpecial().equals("+/-")){
						if(pBIndex > 0){
							Decks.GetPlayerBoard()[pBIndex-1].setNumber(Decks.GetPlayerBoard()[pBIndex-1].getNumber() * -1);
						}
						Decks.GetPlayerBoard()[pBIndex] = Decks.GetPlayerHand()[choose-1];
						pBIndex++;
					}else{
						Decks.GetPlayerBoard()[pBIndex] = Decks.GetPlayerHand()[choose-1];
						pBIndex++;
					}
					
					Decks.UpdatePlayerHand((choose - 1));
					Board.CreateBoard();
					
					playerCheck();
					break;
			}
	}
	
	public static void CompTurn(){
		System.out.println("Computer's turn:");
		System.out.println("");
		int choose = 2;
		int chooseCard = 0;
		compSum = 0;
		for(Card p: Decks.GetCompBoard()){
			compSum = compSum + p.getNumber();
		}
		
		//An algorithm for computer to choose
		if(playerStand && (playerSum < compSum)){
			choose = 2;
		}else{
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
		}
			
			
		switch(choose){
				case 1:
					//Computer asks for a card
					System.out.println("Computer asks for a card");
					System.out.println("");
					Decks.GetCompBoard()[cBIndex] = AskCard();
					cBIndex++;
					Board.CreateBoard();
					compCheck();
					break;
				case 2:
					//stand
					System.out.println("Computer stands");
					System.out.println("");
					Board.CreateBoard();
					compCheck();
					compStand = true;
					break;
				case 3:
					//An algorithm for computer to choose again
					System.out.println("Computer played a card");
					System.out.println("");
					
					if(Decks.GetCompHand()[chooseCard].getNumber() == 0 && Decks.GetCompHand()[chooseCard].getSpecial().equals("x2")){
						if(cBIndex > 0){
							Decks.GetCompBoard()[cBIndex-1].setNumber(Decks.GetCompBoard()[cBIndex-1].getNumber() * 2);
						}
						Decks.GetCompBoard()[cBIndex] = Decks.GetCompHand()[chooseCard];
						cBIndex++;
					}else if(Decks.GetCompHand()[chooseCard].getNumber() == 0 && Decks.GetCompHand()[chooseCard].getSpecial().equals("+/-")){
						if(cBIndex > 0){
							Decks.GetCompBoard()[cBIndex-1].setNumber(Decks.GetCompBoard()[cBIndex-1].getNumber() * -1);
						}
						Decks.GetCompBoard()[cBIndex] = Decks.GetCompHand()[chooseCard];
						cBIndex++;
					}else{
						Decks.GetCompBoard()[cBIndex] = Decks.GetCompHand()[chooseCard];
						cBIndex++;
					}
					
					Decks.GetCompHand()[chooseCard] = new Card(0,"","");
					Board.CreateBoard();
					
					compCheck();
					break;
		}
		
	}
}