/*Sam Hersick
10/20/21
Create useful methods that are universal and would eliminate code in a lot of programs*/

import java.util.Scanner;
import static java.lang.System.*;
import static javax.swing.JOptionPane.*;

public class Useful{
	//scanner
	static Scanner sc = new Scanner(System.in);
	private int a;

	public Useful(){
		a = 10;
	}
	//make sure that a number is greater than a number specificied by the user
	//@param x is the number that we are checking
	//@param y is the number that it must be greater than
	//@return is the new int that meets this requirement
	public static int gThan(int x, int y){
		while(x<= y){
			out.print("Please enter a value greater than "+y+": ");
			x= sc.nextInt();
		}
		return x;
	}

	public static String emptyVal(String x){
		while(x.isEmpty()){
			out.print("\n\tPlease enter a valid string: ");
			x = sc.nextLine();
		}

		return x;
	}

	public static double gThanDouble(double x, double y){
			while(x<= y){
				out.print("Please enter a value greater than "+y+": ");
				x= sc.nextDouble();
			}
			return x;
	}

	public static boolean isPrime(int x){
		boolean y = true;

		if(x == 1)
			y = false;
		else{
		for(int i =2; i <= Math.sqrt(x); i++){
			if(x%i == 0){
				y = false;
			}
		}
		}
		return y;
	}

	public static String yesNoVal(String x){
		while(!(x.equalsIgnoreCase("Yes") || x.equalsIgnoreCase("No"))){
			out.print("\tPlease enter yes or no: ");
			x = sc.nextLine();
		}
		return x;
	}

	public static boolean rerun(){
		int x = showConfirmDialog(null, "Do you want to rerun the program?", "RERUN", YES_NO_OPTION);

		if(x == 0)
			return true;
		else
			return false;
	}

	public int[] splitDig(int x){
		String y = Integer.toString(x);
		int[] dig = new int[y.length()];

		for(int i =0; i < y.length(); i++){
			dig[i] = Integer.valueOf(y.substring(i,i+1));
		}

		return dig;
	}

	public static int valBetween(int x, int l, int u){
		while((x<l) || (x>u)){
			out.print("\n\tPlease enter a value that is greater than "+l+", and less than "+u+": ");
			x = sc.nextInt();
		}

		return x;
	}

	public static double valBetweenDouble(double x, double l, double u){
		while((x<l) || (x>u)){
			out.print("\n\tPlease enter a value that is greater than "+l+", and less than "+u+": ");
			x = sc.nextDouble();
		}

		return x;
	}

	public static String checkForSpace(String x){

		boolean val = false;
		while(!val){
			int checker = 0;

			for(int i = 0; i < x.length(); i++){
				if(Character.isWhitespace(x.charAt(i)))
					checker++;
			}

			if(checker != 1){
			out.print("\tPlease enter a valid name: ");
			x = sc.nextLine();
			}else
			val = true;
		}
		return x;

	}

	//get the amount of triangles that make up the perimeter in the right triangle
	public static int getTriangles(int x){
		int triangles = 0;

		for(int a = 1; a < x; a++){
			for(int b = a; b <x; b++){
				int c = (int)Math.sqrt(Math.pow(a, 2) + Math.pow(b,2));
					if(a+b+c == x && Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c,2))
						triangles++;

			}
		}
		return triangles;
	}

	//-------------------------------------------arrays for arguements-------------------------
	//use the forEach method to get the sum of each column
	public static int[] columnSum(int[][] x){
		int[] cSum = new int[x[0].length];

		for(int i = 0; i < x[0].length; i++){
			for(int c = 0; c <x.length; c++){
				cSum[i] += x[c][i];
			}
		}

		return cSum;
	}

	//get the sum for the columns
	public static double[] columnSumDouble(double[][] x){
		double[] cSum = new double[x[0].length];

		for(int i = 0; i < cSum.length; i++){
			for(int c = 0; c <x.length; c++){
				cSum[i] += x[c][i];
			}
		}

		return cSum;
	}

	//use row-column method
	public static int[] rowSum(int[][] x){
		int[] sums = new int[x.length];

		for(int i = 0; i < x.length; i++){
			for(int c = 0; c <x[i].length; c++){
				sums[i] += x[i][c];
			}
		}

		return sums;
	}

	//sum of the rows in double
	public static double[] rowSumDouble(double[][] x){
		double[] sums = new double[x.length];

		for(int i = 0; i < x.length; i++){
			for(int c = 0; c <x[i].length; c++){
				sums[i] += x[i][c];
			}
		}

		return sums;
	}

	   /*This method will sum the value of the row
	    @param integer array
	    @return int that is the sum of the row*/
		public static int sumArray(int[] a){
			int total = 0;
			for(int i=0; i < a.length; i++){
				total+=a[i];
			}
			return total;
		}


	/*-------------------------Diagonals-------------------------
left top corner		arr[0][0];
right top corner		arr[0][m - 1];
left bottom corner		arr[n - 1][0];
right bottom corner		arr[n - 1][m - 1];*/

	//this method will find the bottomLeft to topRight diagonal of a two dimensional array
	public static int minorDiagonal(int[][] x){
		int sum = 0;
		int y = 0;
		for(int i = x.length-1; i >= 0; i--){
			sum += x[i][y];
			y++;
		}

		return sum;
	}

	//this method will find the bottomRight to topLeft diagonal of a two dimensional array
	public static int majorDiagonal(int[][] x){
		int sum = 0;
		int y = x.length-1;
		for(int i = x.length-1; i >= 0; i--){
			sum += x[i][y];
			y--;
		}

		return sum;
	}

}