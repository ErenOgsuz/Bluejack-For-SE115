import java.util.Random;
public class Decks{
	public static void ConstructDecks(){
		
		Random rnd = new Random();
		Card[] Deck = new Card[40];
		String color = "R";
		int counter = 0;
		int colorCount = 0;
		
		int deckTop = 39;
		int deckBottom = 0;
		
		Card[] playerDeck = new Card[10];
		Card[] compDeck = new Card[10];
		
		Card[] playerHand = new Card[4];
		Card[] compHand = new Card[4];
		
		//Create game deck
		for(int i = 1; i <= 4; i++){
			for(int j = 1; j<= 10; j++){
				Deck[counter] = new Card(j,color,null);
				//Deck[counter].printCard();
				counter++;
			}
			colorCount++;
			switch(colorCount){
				case 1: 
					color = "G";
					break;
				case 2:
					color = "Y";
					break;
				case 3:
					color = "B";
					break;
			}
		}
		
		Card.shuffle(Deck);
		
		/*for (Card p : Deck){
			p.printCard();
		}*/
		
		//Create player and computer deck
		for(int i = 0; i < 10; i++){
			if(i<5){
				playerDeck[i] = Deck[deckBottom]; 
				compDeck[i] = Deck[deckTop];
				deckTop--;
				deckBottom++;
			}else if(i<8){
				playerDeck[i] = Card.randomCard();
				compDeck[i] = Card.randomCard();
			}else{
				playerDeck[i] = Card.specialCard();
				compDeck[i] = Card.specialCard();
			}
		}
		
		/*
		for (Card p : playerDeck){
			p.printDeck();
		}
		System.out.println();
		
		for (Card p : compDeck){
			p.printDeck();
		}
		System.out.println();*/
		
		//Creates player and computer decks
		for(int i = 0; i < 4; i++){
			int indexp = 0;
			int indexc = 0;
			
			do{
				indexp = rnd.nextInt(10);
			}while(playerDeck[indexp] == null);
			
			do{
				indexc = rnd.nextInt(10);
			}while(compDeck[indexc] == null);
			
			if(playerDeck[indexp] != null){
				playerHand[i] = playerDeck[indexp];
				playerDeck[indexp] = null;
			}
			
			if(compDeck[indexc] != null){
				compHand[i] = compDeck[indexc];
				compDeck[indexc] = null;
			}
		}
		
		/*
		for (Card p : playerHand){
			p.printDeck();
		}
		System.out.println();
		
		for (Card p : compHand){
			p.printDeck();
		}
		System.out.println();*/
	}
	
}