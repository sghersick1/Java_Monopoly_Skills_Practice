/*Sam Hersick
5/3/2022
This will represent the game board in monopoly
*/

//imports
import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class GameBoard{

	//instance variables
	private ArrayList<Player> players;
	private Square[] spots;


	//constructor
	public GameBoard(){
		players = new ArrayList<Player>();
		spots = new Square[40];

		//information and instructions
		out.println("\t\t\t\t\tWelcome to Monopoly\nMonopoly is a game that consists of 2-4 players, each starting with $1500. Each player takes turns rolling\ntwo die and moving that amount.");
		out.println("There are 40 Squares on the board, divided into two sections: properties and speciality squares.");
		out.println("Each property has a color, name, and price. Once you purchase a property you own it until you win or become bankrupt.");
		out.println("If someone lands on a property you own (or vice versa), they will pay YOU a rent fee.");
		out.println("If you own all the properties of one color you can buy a house for any of those properties.");
		out.println("For each house you buy you double your rent cost, you can buy up to 4 houses on one property.");
		out.println("\nfor specialty squares:\n\tChance - you can recieve or lose up to $200");
		out.println("\tTax - you must pay the amount the tax costs");
		out.println("\tFree parking - you just rest on the square.");
		out.println("\tGO - equivalent to a free parkingc (Everytime you pass you collect $200)");
		out.println("------------------------------------------------------------------------------------------------------------------------\n");


		initialize();
		initializePlayers();
	}

	//initialize all of the squares
	private void initialize(){
		spots[0] = new Square("Grey", "GO", 0);
		spots[1] = new Square("Brown", "Mediterranean Av.", 60);
		spots[2] = new Square("Grey", "Chance", 0);
		spots[3] = new Square("Brown", "Baltic Av.", 60);
		spots[4] = new Square("Grey", "Tax", 200);
		spots[5] = new Square("Grey", "Chance", 0);
		spots[6] = new Square("Cyan", "Oriental Av.", 100);
		spots[7] = new Square("Grey", "Chance", 0);
		spots[8] = new Square("Cyan", "Vermont Av.", 100);
		spots[9] = new Square("Cyan", "Connecticut Av.", 120);
		spots[10] = new Square("Grey", "Free Parking", 0);
		spots[11] = new Square("Pink", "St. Charles Place", 140);
		spots[12] = new Square("Grey", "Chance", 0);
		spots[13] = new Square("Pink", "States Av.", 140);
		spots[14] = new Square("Pink", "Virginia Av.", 160);
		spots[15] = new Square("Grey", "Chance", 0);
		spots[16] = new Square("Orange", "St. James Place", 180);
		spots[17] = new Square("Grey", "TAX", 100);
		spots[18] = new Square("Orange", "Tennesse Av.", 180);
		spots[19] = new Square("Orange", "New York Av.", 200);
		spots[20] = new Square("Grey", "Free Parking", 0);
		spots[21] = new Square("Red", "Kentucky Av.", 220);
		spots[22] = new Square("Grey", "Chance", 0);
		spots[23] = new Square("Red", "Indiana Av.", 220);
		spots[24] = new Square("Red", "Illinois Av.", 240);
		spots[25] = new Square("Grey", "Chance", 0);
		spots[26] = new Square("Yellow", "Atlantic Av.", 260);
		spots[27] = new Square("Yellow", "Ventor Av.", 260);
		spots[28] = new Square("Grey", "Chance", 0);
		spots[29] = new Square("Yellow", "Marvin Gardens", 280);
		spots[30] = new Square("Grey", "Free Parking", 0);
		spots[31] = new Square("Green", "Pacific Av.", 300);
		spots[32] = new Square("Green", "North Carolina Av.", 300);
		spots[33] = new Square("Grey", "Chance", 0);
		spots[34] = new Square("Green", "Pennsylvania Av", 320);
		spots[35] = new Square("Grey", "TAX", 200);
		spots[36] = new Square("Grey", "Chance", 0);
		spots[37] = new Square("Blue", "Park Place", 350);
		spots[38] = new Square("Grey", "TAX", 200);
		spots[39] = new Square("Blue", "Board Walk", 400);
	}

	//toString --> print out the game board
	public void printGame(){
		for(int i = 0; i < 40; i++){
			if(i%10 == 0) out.println("\n\n");

			spots[i].print();
		}
	}

	//take a turn
	//@return the player that wins
	public Player play(){

		while(players.size() > 1){ //removes players until there is one player left --> that player is the winner

			for(int i = 0; i < players.size(); i++){ //run through each player
				boolean freeSpot = false;     //if no one owns the spot it is true else it is false
				int prev = players.get(i).getPos();

				//print out player information
				out.printf("\n\n----------------------------------------------\n%20s\n", players.get(i).getName());
				out.printf("%20s\n", "$"+players.get(i).getMoney());
				out.println(players.get(i).roll());
				int position = players.get(i).getPos();

				if(position< prev){  //FOR PASSING GO
					out.println("\nYou passed GO! +$200\n");
					players.get(i).setMoney(players.get(i).getMoney()+200);
				}

				//printBoard(players.get(i));  --> if you wanna show spot on board every round

				//check to see if the player is out of money
				if(players.get(i).getMoney()<= 0){
					out.println("\nYOU ARE BANKRUPT! GOOD GAME BUT YOU ARE OUT!\n");
					players.remove(i);
					i--;
					if(players.size() == 1) return players.get(0);
					continue;
				}

				out.println("\nYou landed on:\n");
				spots[position].printBasic();

				if(!spots[position].getColor().equalsIgnoreCase("Grey")){ //lands on a property

					//check to see if another player owns the property
					if(players.get(i).contains(spots[position])){  //player already has it
						out.println("\nYou already own "+spots[position].getName()+"\n");
					}else{
						for(int c =0; c < players.size(); c++){ //go through all the players to see if someone has it

							if(players.get(c).contains(spots[position])){ //if someone else owns the property
								String tempName = players.get(c).getName()+" owns \""+spots[position].getName()+"\" with "+players.get(c).getProp(players.get(c).getIndex(spots[position])).getHouses()+" house(s).";
								final int RENT = players.get(c).getProp(players.get(c).getIndex(spots[position])).getRent();
								out.println(tempName.toUpperCase());
								out.println("The rent is: "+RENT+"\n");
								out.println("\t- $"+RENT+"\n");
								players.get(i).subMoney(RENT); //take the money from the current player
								players.get(c).addMoney(RENT); //add money to the player whose property it is

								break;
							}else if(c == players.size()-1){
								freeSpot = true;  //no one owns the spot
							}//closes the else if


						}//forLoop

					}//closes the else
				}else{                            //if the square is a speciality
					if(spots[position].getName().equalsIgnoreCase("Tax")){   //if it is tax
						out.println("\nThe tax cost: $"+spots[position].getPrice());
						out.printf("%20s\n", "-$"+spots[position].getPrice());
						players.get(i).setMoney(players.get(i).getMoney() - spots[position].getPrice());
					}else if(spots[position].getName().equalsIgnoreCase("Chance")){  //if it is chance
						int rand = (int)(Math.random()*2); //used to decide positive or negative
						int numChance = rand == 0? -1 * (int)(Math.random()*200) : (int)(Math.random()*200);

						out.println("\nChance result: "+numChance);
						out.printf("%20s\n", (numChance>=0? "+$":"-$")+Math.abs(numChance));
						players.get(i).setMoney(players.get(i).getMoney() + numChance);
					}
				}

				//check to see if the player is out of money if so they are done
				if(players.get(i).getMoney()<= 0){
					out.println("\nYOU ARE BANKRUPT! GOOD GAME BUT YOU ARE OUT!\n");
					players.remove(i);
					i--;
					if(players.size() == 1) return players.get(0);
					continue;
				}

				int opts = 0;
				boolean bHouse = false; //if the user bought a house this round
				while(opts != 1 && bHouse == false){
					out.print("\nMoney: $"+players.get(i).getMoney()+"\nWould you like to:\n1. Continue\n2. See properties\n3. Buy Houses\n4. See the board"+(freeSpot? "\n5. Purchase property" : "")+"\n\n==> ");
					opts = Useful.valBetween(Useful.sc.nextInt(), 1, freeSpot ? 5  : 4);

					if(opts == 3)
						bHouse = houseOptions(players.get(i));  //once they by a house they are done for the round
					else if(opts == 2)
						players.get(i).printProperty();  //see all of their properties
					else if(opts == 4){
						printBoard(players.get(i));   //show the board using dialog box
					}else if(opts == 5){
						if(players.get(i).getMoney()-spots[position].getPrice() <= 0){  //makes sure they have enough money to buy it
							out.println("\nSorry you do not have enough money to purchase this property.\n\n");
						}else{
							players.get(i).buyProperty(spots[position]);
							freeSpot = false;  //the spot is now bought
						}
					}


				}

				Useful.sc.nextLine();
			}//closes the for loop

		}

		return players.get(0);
	}


	//this happens if the user wants to buy a house
	//@param x is the player that wants to buy a house
	//@return boolean is if they bought a house or not
	public boolean houseOptions(Player x){
		ArrayList<Square> houses = x.posHouses();

		if(houses.size() == 0){
			out.println("\n\tThere are no houses that you can buy right now sorry.\n");
			return false;
		}

		out.println("\n1. exit");
		for(int i =2; i <= houses.size()+1; i++){
			out.println(i + ". $" +houses.get(i-2).getHousePrice()+" - "+houses.get(i-2).getName()+" - "+houses.get(i-2).getHouses());
		}
		out.print("==>");

		int choice = Useful.valBetween(Useful.sc.nextInt(), 1, houses.size()+1);

		if(choice == 1) return false;

		for(int i =0; i < x.getProp().size(); i++){
			if(x.getProp().get(i).getName().equalsIgnoreCase(houses.get(choice-2).getName())){
				if(x.getMoney() - x.getProp().get(i).getHousePrice() <= 0){
					out.println("\nSorry you do not have the money to buy a house on this property!\n");
					return false;
				}

				x.houseAction(x.getProp().get(i));
			}
		}

		return true;

	}

	//get the players that are playing
	private void initializePlayers(){
		//for testing
		/*ArrayList<Square> x = new ArrayList<Square>();
		x.add(new Square("Blue", "Board Walk", 400));
		x.add(new Square("Blue", "Park Place", 350));
		x.add(new Square("Cyan", "Oriental Av.", 100));*/

		out.print("How many players are playing(2-4)? ");
		int play = Useful.valBetween(Useful.sc.nextInt(), 2, 4);

		Useful.sc.nextLine();
		out.println();
		for(int i =0; i < play; i++){
			out.print("What is player "+(i+1)+"'s name? ");
			String name = Useful.emptyVal(Useful.sc.nextLine());

			//if(i == 0)	players.add(new Player(name, x));  //FOR TESTING
			//else
			players.add(new Player(name));
		}
	}

	//print out the game board with dialog box
	//@param x is the player you are printing the board for, with their info
	public void printBoard(Player x){
		ImageIcon boardImg = new ImageIcon("board.png");
		showMessageDialog(null, x.getName()+"\nYour spot: "+x.getPos()+"\nProperty:\n"+spots[x.getPos()], "Board", INFORMATION_MESSAGE, boardImg);
	}

	//accessors
	public Square[] getSquares(){return spots;}
	public ArrayList<Player> getPlayer(){return players;}

	//mutators
	public void setSquares(Square[] x){spots = x;}
	public void setPlayer(ArrayList<Player> p){players = p;}

	//main method for testing
	public static void main(String[] args){
		GameBoard g = new GameBoard();
		Player x = g.play();

		out.println("\n\n--------------------------------\nThe winner is: "+x.getName()+" with $"+x.getMoney()+"\n\n\n");


		exit(0);
	}
}