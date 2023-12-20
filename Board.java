public class Board{
	public static void CreateBoard(){
		System.out.println("Score: " + BlueJack.getPlayerName() + " " + Game.GetPlayerWin() + " - "+ "Computer " + Game.GetCompWin());
		System.out.println("---------------------------------------");
		System.out.println("Computer Hand    | " + "X X X X");
		System.out.println("---------------------------------------");
		System.out.print("Computer Board   | " );
		for(Card p: Decks.GetCompBoard()){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.print("Player Board     | " );
		for(Card p: Decks.GetPlayerBoard()){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.print("Player Hand      | "); 
		for(Card p: Decks.GetPlayerHand()){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.println("");
	}
}