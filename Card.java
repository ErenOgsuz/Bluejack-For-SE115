import java.util.Random;
public class Card{
	private int number;
	private String color;
	private String special;
	
	public Card(int a, String b, String c){
		number = a;
		color = b;
		special = c;
	}
	
	/*
	public void setNumber(int x){
		number = x;
	} 
	
	public void setColor(String a){
		color = a;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getColor(){
		return color;
	}*/
	
	public void printCard(){
		if(special == null){
			if(number < 0){
				System.out.println(number + color);
			}else{
				System.out.println("+" + number + color);
			}
			
		}else{
			System.out.println(special);
		}
	}
	
	public void printDeck(){
		if(special == null){
			if(number < 0){
				System.out.print(number + color + " ");
			}else{
				System.out.print("+" + number + color + " ");
			}
			
		}else{
			System.out.print(special + " ");
		}
	}
	
	public static Card[] shuffle(Card[] a){
		Random rnd = new Random();
		Card[] b = a;
		for (int i = 0; i < 100 ; i++){
			int x = rnd.nextInt(a.length);
			int y = rnd.nextInt(a.length);
			Card temp = a[x];
			a[x] = b[y];
			b[y] = temp;
		}
		return a;
	}
	
	public static Card randomCard(){
		Random rnd = new Random();
		int colorInt = rnd.nextInt(4);
		String color = "R";
		switch(colorInt){
				case 0:
					color = "R";
					break;
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
		int minus = rnd.nextInt(2);
		if(minus == 0){
			minus = -1;
		}
		Card rndCard = new Card(minus*(rnd.nextInt(6)+1),color,null);
		return rndCard;
	}
	
	public static Card specialCard(){
		Random rnd = new Random();
		int chance = rnd.nextInt(100)+1;
		int fORd = rnd.nextInt(2);
		Card specCard;
		if(chance <= 20){
			if(fORd == 0){
				//flip card
				specCard = new Card(0,null,"+/-");
			}else{
				//double card
				specCard = new Card(0,null,"x2");
			}
		}else{
			specCard = randomCard();
		}
		return specCard;
	}
}