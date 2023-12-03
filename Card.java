public class Card{
	private int number;
	private String color;
	
	public void setNumber(int a){
		number = a;
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