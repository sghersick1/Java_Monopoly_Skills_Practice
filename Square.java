/*Sam Hersick
5/3/2022
This will represent a square in monopoly
each square is different and some squares are unique like tax, go, chance, and free parking
these unique squares will be given the color grey for easy coding
*/

//imports
import java.text.DecimalFormat;
import static java.lang.System.*;

public class Square{

	//instance variables
	private String name;
	private String color;
	private int price;
	private int rent;
	private int houses;
	private int housePrice;



	//constructor
	public Square(String color, String name, int price){
		this.name = name;
		this.color = color;
		this.price = price;
		rent = (int)Math.pow((price*0.1),1.4);
		housePrice = (int)(0.5*price);
	}

	//accesors
	public String getColor(){return color;}
	public String getName(){return name;}
	public int getPrice(){return price;}
	public int getRent(){return rent;}
	public int getHouses(){return houses;}
	public int getHousePrice(){return housePrice;}

	//mutators
	public void setColor(String color){this.color = color;}
	public void setNate(String name){this.name = name;}
	public void setPrice(int price){this.price = price;}
	public void setRent(int rent){this.rent = rent;}
	public void setHouses(int houses){this.houses = houses;}
	public void setHousePrice(int housePrice){this.housePrice = housePrice;}

	//print the card
	public void print(){
		out.printf("------------------------------\n");

		// print the color
		printHelp(color);
		out.println();
		out.printf("|----------------------------|\n");
		out.printf("|%29s\n", "|");

		//print the name
		printHelp(name);
		out.println();

		//print the price
		if(color.equalsIgnoreCase("Grey")&&!name.equalsIgnoreCase("Tax")){  //DO NOT print the price if it is a "grey" card
			out.printf("|%29s\n", "|");
		}else{
			printHelp(String.valueOf(price)); //prints the price similar to the rest of the information
			out.println();
		}

		out.printf("|%29s\n", "|");
		out.printf("------------------------------\n");

	}

	//help print a variable
	//@param x is the String that you want to print in the center
	public void printHelp(String x){
		//print out the variable in the correct format
		//this will use format to center the word

		out.printf("|%14s%s", x.substring(0,x.length()/2),x.substring(x.length()/2));
		for(int i = 0; i < 15-(x.length()%2 == 0?x.length()/2 +1:x.length()/2+2); i++)
			out.print(" ");
		out.print("|");
	}

	//override the equals
	//@param x is another square that it is compared to
	//@return boolean is if the names are the same or not
	public boolean equals(Square x){
		//returns true if the squares have the same name
		return name.equalsIgnoreCase(x.getName());
	}

	//print basic information about the square
	public void printBasic(){
		out.printf("%20s\n", name);

		if(!color.equalsIgnoreCase("Grey")){
			out.printf("%20s\n", color.toUpperCase());
			out.printf("%20d\n", price);
		}
	}

	//add a house and change the price of rent
	public void addHouse(){
		houses++;
		rent = rent*2; //each house you add will double the price of rent
	}

	//toString
	public String toString(){
		DecimalFormat mon = new DecimalFormat("$#,###");
		return name+"\n"+(color.equalsIgnoreCase("Grey") ? "" : color.toUpperCase()+"\n"+mon.format(price));
	}
}