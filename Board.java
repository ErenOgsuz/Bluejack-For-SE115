public class Board{
	public static void CreateBoard(){
		System.out.println("Score: " + BlueJack.getPlayerName() + " " + Game.GetPlayerWin() + " - "+ "Computer " + Game.GetCompWin());
		System.out.println("-----------------------------------------------------------");
		System.out.print("Computer Hand    | ");
		for(Card p: Decks.GetCompHand()){
			if(p.getNumber() == 0 && p.getSpecial().equals("")){
				System.out.print("O ");
			}else{
				System.out.print("X ");
			}
		}
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Computer Board   | " );
		for(Card p: Decks.GetCompBoard()){
			p.printDeck();
		}
		int compSum = 0;
		for(Card p: Decks.GetCompBoard()){
			compSum = compSum + p.getNumber();
		}
		System.out.print("= " + compSum);
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Player Board     | " );
		for(Card p: Decks.GetPlayerBoard()){
			p.printDeck();
		}
		int playerSum = 0;
		for(Card p: Decks.GetPlayerBoard()){
			playerSum = playerSum + p.getNumber();
		}
		System.out.print("= " + playerSum);
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Player Hand      | "); 
		for(Card p: Decks.GetPlayerHand()){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.println("");
	}
}