import java.util.Random;
public class Card{
	private int number;
	private String color;
	
	public Card(int a, String b){
		number = a;
		color = b;
	}
	
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
	}
	
	public void printCard(){
		System.out.println(number + color);
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
}