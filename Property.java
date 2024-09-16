/*Sam Hersick
5/9/22
this is for properties - "is-a" Square
*/

//imports

public class Property extends Square{
	//instance variables
	private int rent;
	private int houses;
	private int housePrice;


	public Property(String color, String name, int price){
		super(color, name, price);
		rent = rent = (int)Math.pow((price*0.1),1.4);
		housePrice = (int)(super.getPrice()*0.5);
	}

	public int getRent(){return rent;}
	public void setRent(int rent){this.rent = rent;}
}