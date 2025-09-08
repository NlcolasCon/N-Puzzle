import java.util.Scanner;
public class Client {
	
	/**
	* Author: Nicolas Constantinou
	* Written: 12/10/2023
	* Last updated: 27/10/2023
	* 
	* The program, is a game that lets the user try and 
	* beat a puzzle. The user sets the difficulty and tries
	* to solve it.
	* The program also, can receive a number of difficulties
	* plays an tries for each play form the user and try 
	* to solve the puzzle by itself using random eligible moves.
	* The puzzle can be displayed text based, graphically or not displayed
	* at all.
	*
	*/
	
	//Main menu
	public static void main (String args[]) {
		
		Scanner scan = new Scanner (System.in);
		
		int N = Options.getDimensions(args);
		
		int d = Options.getDisplayType(args);
		
		int option = Options.chooseOption();
		
		switch (option) {
		case 1:
			Library.Interactive_Play(N, d);
			break;
		case 2:
			Library.Automative_Play(N, d);
			break;
		case 3:
			Options.gameStop();
			break;
		}
		
		scan.close();
	}
	
	
}
