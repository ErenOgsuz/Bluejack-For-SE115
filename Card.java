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
		System.out.println(number + ", " + color);
	}
}