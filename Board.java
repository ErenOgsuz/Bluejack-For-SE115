public class Board{
	public static void CreateBoard(){
		System.out.println("---------------------------------------");
		System.out.println("Computer Hand    | " + "X X X X");
		System.out.println("---------------------------------------");
		System.out.print("Computer Board   | " );
		for(Card p: Decks.compBoard){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.print("Player Board     | " );
		for(Card p: Decks.playerBoard){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.print("Player Hand      | "); 
		for(Card p: Decks.playerHand){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
	}
}