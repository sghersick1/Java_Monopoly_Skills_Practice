/*Sam Hersick
5/3/2022
This will represent a player in monopoly
*/

//imports
import java.util.ArrayList;
import static java.lang.System.*;

public class Player{

	//instance variables
	private String name;
	private int money;
	private ArrayList<Square> prop = new ArrayList<Square>(); //this is the properties the player owns.
	private int pos;


	//constructor
	public Player(String name){
		this.name = name;
		money = 1500;
	}

	public Player(String name, ArrayList<Square> prop){
		this.name = name;
		money = 1500;
		this.prop = prop;
	}

	//overloading
	public Player(String name, int money){
		this.name = name;
		this.money = money;
	}

	//accessors
	public String getName(){return name;}
	public int getMoney(){return money;}
	public int getPos(){return pos;}
	public ArrayList<Square> getProp(){return prop;}

	//mutators
	public void setName(String name){this.name = name;}
	public void setMoney(int money){this.money = money;}
	public void setPos(int pos){this.pos = pos;}
	public void setProp(ArrayList<Square> prop){this.prop = prop;}

	//take away money
	//@param x is the amount of money you want to subtract
	public void subMoney(int x){
		money -= x;
	}

	//add money
	//@param x is the amount you want to add
	public void addMoney(int x){
		money+= x;
	}

	//see if the Player owns a property
	//@param Square is a square the user tests to see if this player owns it
	//@return true if player owns it false otherwise
	public boolean contains(Square test){
		return getIndex(test) >= 0;
	}

	//take a turn
	//@return is a string saying what the die rolled
	public String roll(){
		int roll1 = (int)(Math.random()*6)+1;
		int roll2 = (int)(Math.random()*6)+1;
		int prev = pos;

		pos +=roll1 + roll2;
		pos %=40;

		return "die 1: "+roll1+"\ndie 2: "+roll2+"\nroll: "+(roll1+roll2);
	}

	//buy a property
	//@param x is the square the user wants to buy
	public void buyProperty(Square x){
		prop.add(x);
		money -=x.getPrice();
		out.println("\n\t\tsuccessful purchase of: "+x.getName()+"\n");
	}

	//prints out all of the players properties
	public void printProperty(){

		if(prop.size() == 0){ //if they don't have any properties
			out.println("\n\tYou do not own any properties currently\n");
			return;
		}

		out.println("\n\n");
		for(Square x: prop)
			x.print();
		out.println("\n");
	}

	//checck which properties you can buy houses for
	//@param ArrayList<Square> is all the squares that the user can buy houses for
	public ArrayList<Square> posHouses(){
		ArrayList<Square> pos = new ArrayList<Square>();

		String[] col = {"Brown", "Cyan", "Pink", "Orange", "Red", "Yellow", "Green", "Blue"};
		int[] correspond = new int[col.length];

		for(Square x: prop){
			for(int i =0; i < col.length; i++){

				//out.println(x.getColor()+" "+col[i]+ " "+x.getColor().equalsIgnoreCase(col[i]));  --> for testing
				if(x.getColor().equalsIgnoreCase(col[i])) correspond[i]++;
			}
		}

		for(int i=0; i < col.length; i++){
			//out.println(correspond[i]+ " "+col[i]); ----> for testing
			if((i == 0 && correspond[i] == 2)||(i == 7 && correspond[i] == 2)||correspond[i] == 3){
				for(Square x: prop){
					if(x.getHouses()<4 && x.getColor().equalsIgnoreCase(col[i])){ //can't own more than 4 houses on one property
						//out.println(x+" "+col[i]); ---> for testing
						pos.add(x);
					}

				}
			}
		}

		return pos;
	}

	//return the index of a property that they own
	//@param x is a square on the board
	//@return is the index of that square
	public int getIndex(Square x){
		for(int i =0; i < prop.size(); i++){
			if(x.equals(prop.get(i))) return i;
		}

		return -1; //Player doesn't know property
	}

	//return the prop at a int index
	//@param x is the index of the square the person wants
	//@return is the square at the index of x
	public Square getProp(int x){
		return prop.get(x);
	}

	//add house to their property
	public void houseAction(Square x){
		prop.get(getIndex(x)).addHouse();
		money -= prop.get(getIndex(x)).getHousePrice();
	}

	//toString method
	public String toString(){
		return name+" - $"+money+" - "+pos+" - "+prop.size()+"(properties)";
	}
}