
public class Options {

	//Gets N if args[0] is not eligible
	public static int getDimensions ( String args[] ) {
		
		String s = args[0];
		if ( Library.is_Digit(s) ) {
			return Integer.parseInt(s);
		}
		
		System.out.println( "The input from array args regarding the dimensions of the puzzle N is not eligible..." );
		while( !Library.is_Digit(s) ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.println( "Choose The Puzzle Dimensions: " );
			System.out.print  ( "Puzzle Size: " );
			s = StdIn.readString();
			System.out.println();
		}
		StdIn.readLine();
		int N = 0;
		N = Integer.parseInt(s);
		while ( N <= 1 ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.println( "Choose The Puzzle Dimensions: " );
			System.out.print  ( "Puzzle Size: " );
			s = StdIn.readString();
			System.out.println();
			while( !Library.is_Digit(s) ) {
				System.out.println( "*******************************" );
				System.out.println( "Input is not an eligible number," );
				System.out.println( "Choose The Puzzle Dimensions: " );
				System.out.print  ( "Puzzle Size: " );
				s = StdIn.readString();
				System.out.println();
			}
			N = Integer.parseInt(s);
		}
		System.out.println();
		return N;
	}
	//Gets d if args[i] is not eligible
	public static int getDisplayType ( String args[] ) {
		
		String s = args[1];
		if ( Library.is_Digit(s) ) {
			return Integer.parseInt(s);
		}
		
		System.out.println( "The input from array args regarding the display type of the puzzle d is not eligible..." );
		while( !Library.is_Digit(s) ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.println( "+-------------------------+" );
			System.out.println( "|0. None                  |" );
			System.out.println( "|1. Text-Based            |" );
			System.out.println( "|2. Graphically           |" );
			System.out.println( "+-------------------------+" );
			System.out.println( "Choose The Display Type: " );
			System.out.print  ( "Display Type: " );
			s = StdIn.readString();
			System.out.println();
		}
		StdIn.readLine();
		int d = 0;
		d = Integer.parseInt(s);
		System.out.println();
		while ( d != 0 && d != 1 && d != 2 ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.println( "+-------------------------+" );
			System.out.println( "|0. None                  |" );
			System.out.println( "|1. Text-Based            |" );
			System.out.println( "|2. Graphically           |" );
			System.out.println( "+-------------------------+" );
			System.out.println( "Choose The Display Type: " );
			System.out.print  ( "Display Type: " );
			s = StdIn.readString();
			System.out.println();
			while( !Library.is_Digit(s) ) {
				System.out.println( "*******************************" );
				System.out.println( "Input is not an eligible number," );
				System.out.println( "+-------------------------+" );
				System.out.println( "|0. None                  |" );
				System.out.println( "|1. Text-Based            |" );
				System.out.println( "|2. Graphically           |" );
				System.out.println( "+-------------------------+" );
				System.out.println( "Choose The Display Type: " );
				System.out.print  ( "Display Type: " );
				s = StdIn.readString();
				System.out.println();
			}
			StdIn.readLine();
			d = Integer.parseInt(s);
			System.out.println();
		}
		System.out.println();
		return d;
	}
	//Gets k
	public static int getDifficulty () {
		
		System.out.print("Give level of difficulty:");
		String s = StdIn.readString();
		System.out.println();
		while(!Library.is_Digit(s)) {
			System.out.println("*******************************");
			System.out.println("Input is not an eligible number,");
			System.out.print("Give level of difficulty:");
			StdIn.readLine();
			s = StdIn.readString();
			System.out.println();
		}
		StdIn.readLine();
		int k = Integer.parseInt(s);
		while( k <= 0 ) {
			System.out.println("*******************************");
			System.out.println("Input is not an eligible number,");
			System.out.print("Give level of difficulty:");
			s = StdIn.readString();
			System.out.println();
			while(!Library.is_Digit(s)) {
				System.out.println("*******************************");
				System.out.println("Input is not an eligible number,");
				System.out.print("Give level of difficulty:");
				StdIn.readLine();
				s = StdIn.readString();
				System.out.println();
			}
			StdIn.readLine();
			k = Integer.parseInt(s);
		}
		
		return k;
	}
	//Gets kmin
	public static int get_kmin () {
		System.out.print( "Dose kmin: " );
		String s = StdIn.readString();
		while( !Library.is_Digit(s) ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.print  ( "Dose kmin: " );
			StdIn.readLine();
			s = StdIn.readString();
			}
		StdIn.readLine();
		int kmin = Integer.parseInt(s);
		while ( kmin <= 0 ) {
			System.out.println( "******************************* " );
			System.out.println( "Input is not an eligible number," );
			System.out.print  ( "Dose kmin: " );
			s = StdIn.readString();
			while( !Library.is_Digit(s) ) {
					System.out.println( "*******************************" );
					System.out.println( "Input is not an eligible number," );
					System.out.print  ( "Dose kmin: " );
					StdIn.readLine();
					s = StdIn.readString();
					}
				kmin = Integer.parseInt(s);
		}
		return kmin;
	}
	//Gets kmax
	public static int get_kmax ( int kmin ) {
		System.out.print( "Dose kmax: " );
		String s = StdIn.readString();
		while( !Library.is_Digit(s) ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.print  ( "Dose kmax: " );
			StdIn.readLine();
			s = StdIn.readString();
		}
		StdIn.readLine();
		int kmax = Integer.parseInt(s);
		while ( kmax <= kmin ) {
			System.out.println( "*******************************" );
			System.out.println( "Input needs to be > kmin" );
			System.out.print  ( "Dose kmax: " );
			s = StdIn.readString();
				while( !Library.is_Digit(s) ) {
					System.out.println( "*******************************" );
					System.out.println( "Input is not an eligible number," );
					System.out.print  ( "Dose kmax: " );
					StdIn.readLine();
					s = StdIn.readString();
				}
				kmax = Integer.parseInt(s);
		} 
		return kmax;
	}
	//Gets p
	public static int get_Plays () {
		System.out.print( "Dose p: " );
		String s = StdIn.readString();
		while( !Library.is_Digit(s) ) {
			System.out.println( "*******************************" );
			System.out.println( "Input is not an eligible number," );
			System.out.print  ( "Dose p: " );
			StdIn.readLine();
			s = StdIn.readString();
		}
		StdIn.readLine();
		int p = Integer.parseInt(s);
		while ( p <= 0 ) {
			System.out.println( "*******************************" );
			System.out.println( "Input needs to be > 0" );
			System.out.print  ( "Dose p: " );
			s = StdIn.readString();
				while( !Library.is_Digit(s) ) {
					System.out.println ( "*******************************" );
					System.out.println ( "Input is not an eligible number," );
					System.out.print   ( "Dose p: " );
					StdIn.readLine();
					s = StdIn.readString();
				}
				p = Integer.parseInt(s);
		}
		return p;
	}
	//Gets q
	public static int get_Tries () {
		System.out.print ( "Dose q: " );
		String s = StdIn.readString();
		while( !Library.is_Digit(s) ) {
			System.out.println ( "*******************************" );
			System.out.println ( "Input is not an eligible number," );
			System.out.print   ( "Dose q: " );
			StdIn.readLine();
			s = StdIn.readString();
		}
		StdIn.readLine();
		int q = Integer.parseInt(s);
		while ( q <= 0 ) {
			System.out.println ( "*******************************" );
			System.out.println ( "Input needs to be > 0" );
			System.out.print   ( "Dose q: " );
			s = StdIn.readString();
				while( !Library.is_Digit(s) ) {
					System.out.println ( "*******************************" );
					System.out.println ( "Input is not an eligible number," );
					System.out.print   ( "Dose q: " );
					StdIn.readLine();
					s = StdIn.readString();
				}
				q = Integer.parseInt(s);
		}
		return q;
	}
	//Gets option for interactive or automative play or to exit
	public static int chooseOption () {
		System.out.println("+-------------------------+");
		System.out.println("|1. Interactive Play      |");
		System.out.println("|2. Automative Play       |");
		System.out.println("|3. Exit                  |");
		System.out.println("+-------------------------+");
		System.out.print("Choose Option:");
		
		String s = StdIn.readString();
		System.out.println();
		while(!Library.is_Digit(s)) {
			System.out.println("Input is not eligible,");
			System.out.println("+-------------------------+");
			System.out.println("|1. Interactive Play      |");
			System.out.println("|2. Automative Play       |");
			System.out.println("|3. Exit                  |");
			System.out.println("+-------------------------+");
			System.out.print("Choose Option:");
			s = StdIn.readString();
			System.out.println();
		}
		StdIn.readLine();
		int option = Integer.parseInt(s);
		while ( option != 1 && option != 2 && option != 3 ) {
			System.out.println("Input is not eligible,");
			System.out.println("+-------------------------+");
			System.out.println("|1. Interactive Play      |");
			System.out.println("|2. Automative Play       |");
			System.out.println("|3. Exit                  |");
			System.out.println("+-------------------------+");
			System.out.print("Choose Option:");
			s = StdIn.readString();
			System.out.println();
			while(!Library.is_Digit(s)) {
				System.out.println("Input is not eligible,");
				System.out.println("+-------------------------+");
				System.out.println("|1. Interactive Play      |");
				System.out.println("|2. Automative Play       |");
				System.out.println("|3. Exit                  |");
				System.out.println("+-------------------------+");
				System.out.print("Choose Option:");
				s = StdIn.readString();
				System.out.println();
			}
			option = Integer.parseInt(s);
		}
		return option;
	}
	//End of game if you choose option 3
	public static void gameStop() {
		System.out.println("You Have Exited The Game!");
	}
	//End credits and statistics
	public static void extraStatistics(int moves, int l, int u, int r, int d, int wrong_Moves) {
		System.out.println();
		System.out.println("+-------------------------+");
		System.out.println("|    Extra Statistics:    |");
		System.out.println("+-------------------------+");
		System.out.println();
		System.out.printf("1.Overall Moves Done: %4d Moves\n", moves);
		System.out.printf("2.Left    Moves Done: %4d Moves\n", l);
		System.out.printf("3.Up      Moves Done: %4d Moves\n", u);
		System.out.printf("4.Right   Moves Done: %4d Moves\n", r);
		System.out.printf("5.Down    Moves Done: %4d Moves\n", d);
		System.out.printf("6.Wrong   Move Tries: %4d Moves\n", wrong_Moves);



	}

}
