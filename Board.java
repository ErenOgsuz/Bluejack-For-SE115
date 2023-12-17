public class Board{
	public static void CreateBoard(){
		System.out.println("-----------------------------------");
		System.out.println("Computer Hand    | " + "X X X X");
		System.out.println("-----------------------------------");
		System.out.println("Computer Board   | " );
		System.out.println("-----------------------------------");
		System.out.println("Player Board     | " );
		System.out.println("-----------------------------------");
		System.out.print("Player Hand      | "); 
		for(Card p: Decks.playerHand){
			p.printDeck();
		}
		System.out.println("");
		System.out.println("-----------------------------------");
	}
}