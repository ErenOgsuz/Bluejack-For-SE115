public class BlueJack{
	public static void main(String[] args){
		Card[] Deck = new Card[40];
		String color = "R";
		int counter = 0;
		int colorCount = 0;
		
		for(int i = 1; i <= 4; i++){
			for(int j = 1; j<= 10; j++){
				Deck[counter] = new Card(j,color);
				Deck[counter].printCard();
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
		
		for (Card p : Deck){
			p.printCard();
		}
	}
}