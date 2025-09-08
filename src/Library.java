import java.util.Scanner;
import java.awt.Font;

public class Library {

	//Interactive play option
	public static void Interactive_Play ( int N, int d ) {
		
		Scanner scan       = new Scanner ( System.in );
		boolean is_Exiting = false;
		 
		
		System.out.println ( "*******************************" );	
		int k = Options.getDifficulty();								//get k
		
		
		int [][] puzzle          = initializePuzzle( N );				//creates a NxN sorted puzzle
		int [][] shuffled_puzzle = copy_Array( puzzle, N );
		for ( int i = 1; i <= k; i++ )									//shuffles puzzle k times
			shuffled_puzzle = shufflePuzzle ( shuffled_puzzle, N );
		while( isSolution ( shuffled_puzzle, N, puzzle ) ) {				//if after the shuffling, the puzzle is sorted right then
			shuffled_puzzle = copy_Array( puzzle, N );						//re-do the shuffle from the start
			for ( int i = 1; i <= k; i++ )									
				shuffled_puzzle = shufflePuzzle ( shuffled_puzzle, N );
		}
		puzzle = initializePuzzle ( N );
		
		switch ( d ) {													//displays puzzle according to d
		case 1:
			System.out.println( "*******************************" );	
			displayPuzzle     ( shuffled_puzzle, N );
			System.out.println();
			break;
		case 2:
			displayPuzzleGraph( shuffled_puzzle, N );
		}
		
		int left  = 0;
		int up    = 0;
		int right = 0;
		int down  = 0;
		int moves_So_Far = 1;
		int wrong_Moves  = 0;
		
		while ( !isSolution ( shuffled_puzzle, N, puzzle ) && !is_Exiting ) {		//while puzzle is not sorted 
			char move = getUserCommand( moves_So_Far );								//get move command
			int row   = find_row      ( shuffled_puzzle, N );						
			int col   = find_col      ( shuffled_puzzle, N );
			
			while ( isValidMove ( move, row, col, N ) == false ) {					//repeat until user inputs a right move
				wrong_Moves++;
				wrongMoveIndicator( move, row, col, N );
				switch ( d ) {
				case 1:
					System.out.println( "*******************************" );	
					displayPuzzle     ( shuffled_puzzle, N );
					System.out.println();
					break;
				case 2:
					displayPuzzleGraph( shuffled_puzzle, N );
				}
				move = getUserCommand ( moves_So_Far );
			}
			
			switch ( move ) {								//counts how many move where mode
			case 'l': left++;
				break;
			case 'u': up++;
				break;
			case 'r': right++;
				break;
			case 'd': down++;
				break;
			}
			
			if( move == 'e' ) {								//checks if user wants to quit the game
				is_Exiting = true;
				moves_So_Far--;
			}
			else {
				shuffled_puzzle = play ( shuffled_puzzle, move, N, row, col );  //initiating move
				
				switch ( d ) {
				case 1:			
					System.out.println( "*******************************" );	
					displayPuzzle     ( shuffled_puzzle, N );
					System.out.println();
					break;
				case 2:
					displayPuzzleGraph( shuffled_puzzle, N );
				}

			}
			moves_So_Far++;
		}
		
		if ( is_Exiting ) 					//if user exited the game 
			Options.gameStop();
		else								//if not the he beat the puzzle
			System.out.println      ( "Congratulations, puzzle solved!!" );
			Options.extraStatistics ( moves_So_Far, left, up, right, down, wrong_Moves );
		
		scan.close();
	}
	//Automative play option
	public static void Automative_Play(int N, int d) {
			Scanner scan = new Scanner ( System.in );
			
			System.out.println( "*******************************" );	
			
			int [][] puzzle          = initializePuzzle ( N );
			int [][] shuffled_puzzle = initializePuzzle ( N );
					
			int kmin = Options.get_kmin ();					//get kmin, kmax, p for each k and q for each p of each k
			int kmax = Options.get_kmax ( kmin );
			int p    = Options.get_Plays();
			int q    = Options.get_Tries();
			

			
			int       range                   = kmax - kmin + 1;
			double [] Avg_Success_per_Diff    = new double[range];
			double [] Sum_of_Success_per_Diff = new double[range];
			double [] Avg_moves_per_Diff      = new double[range];
			double [] sum_of_moves_per_Diff   = new double[range];
			int       success                 = 0;
			
			
			for ( int i = kmin; i <= kmax; i++ ) {				//for every difficulty
				
				shuffled_puzzle = initializePuzzle ( N );							//create and shuffle puzzle
				shuffled_puzzle = initializePuzzleK( shuffled_puzzle, N, i );		//same for every p of a difficulty
				while ( isSolution ( shuffled_puzzle, N, puzzle ) ) {
					shuffled_puzzle = initializePuzzle ( N );
					shuffled_puzzle = initializePuzzleK( shuffled_puzzle, N, i );
				}
				Sum_of_Success_per_Diff [i-kmin] = 0;
				sum_of_moves_per_Diff   [i-kmin] = 0;
				success = 0;

				for ( int j = 1; j <= p; j++ ) {				//for every play of every difficulty
					
					int [][] copy_puzzle = copy_Array ( shuffled_puzzle, N );			//create an array that is shuffled the same for each difficulty
					int      tries = 0;
					
					while( tries <= q && !isSolution ( copy_puzzle, N, puzzle ) ) {		//while puzzle is not sorted and still has tries(q)
						switch ( d ) {													//display puzzle according to d
						case 1:
							System.out.println( "*******************************" );	
							displayPuzzle( shuffled_puzzle, N );
							System.out.println();
							break;
						case 2:
							displayPuzzleGraph ( shuffled_puzzle, N );
							break;
						}
						int row   = find_row   ( copy_puzzle, N );						//makes a random eligible move
						int col   = find_col   ( copy_puzzle, N );
						char move = moveChance ( row, col, N );
						copy_puzzle = play ( copy_puzzle, move, N, row, col );
						tries++;
					}
					if( isSolution ( copy_puzzle, N, puzzle ) ) {			//if while ended and puzzle is sorted then add statistics
						Sum_of_Success_per_Diff [i-kmin]++;
						sum_of_moves_per_Diff   [i-kmin]+= tries;
						success++;
					}	
					
				}
				if( success == 0 ) {
					Avg_Success_per_Diff [i-kmin] = 0.0;
					Avg_moves_per_Diff   [i-kmin] = 0.0;
				}
				else {
					Avg_Success_per_Diff [i-kmin] = (double) ( Sum_of_Success_per_Diff [i-kmin] / p * 100.0 );
					Avg_moves_per_Diff   [i-kmin] = (double) ( sum_of_moves_per_Diff   [i-kmin] / success );	
				}
			}
			
			
			for ( int i = kmin; i <= kmax; i++ ) {				//display of statistics after all turns of all plays of all difficulties
				if ( Avg_Success_per_Diff [i-kmin] == 0.0 )
					System.out.printf ( "Gia k= %d ekane meso oro kiniseon Nan kai nikise 0.00 %% fores", i );
				else
					System.out.printf ( "Gia k= %d ekane meso oro kiniseon %.2f kai nikise %.2f %% fores", i, Avg_moves_per_Diff[i-kmin], Avg_Success_per_Diff[i-kmin] );
				System.out.println();
			}
			scan.close();
			
		}
	//Initialises puzzle in correct order
	private static int [][] initializePuzzle ( int dimensions ) {
			
		int [][] puzzle = new int [dimensions][dimensions];
		int      number = 1;
			
		for ( int i = 0; i < dimensions; i++ )
			for( int j = 0; j < dimensions; j++ ) {
				if( !( ( i == dimensions - 1 ) && ( j == dimensions - 1 ) ) ) {
					puzzle[i][j] = number;
					number++;
				}
				else
					puzzle[i][j] = 0;
			}
		return puzzle;
	}
	//Shuffles the puzzle one time
	private static int [][] shufflePuzzle ( int [][] puzzle, int d ) {
		int [][] shuffled_puzzle = puzzle;
			
		boolean is_Shuffled = false;
		for( int i = 0; i < d && !is_Shuffled; i++ ) {
			for( int j = 0; j < d && !is_Shuffled; j++ ) {
				if( shuffled_puzzle[i][j] == 0 ) {						//find 0 in the puzzle
					is_Shuffled     = true;								//shuffle once
					char   move     = moveChance( i, j, d );			//find the move of 0 (l,u,r,d)
					shuffled_puzzle = moveBlank( puzzle, move, i, j );	//make the move
				}
			}
		}
		
		return shuffled_puzzle;
	}
	//Shuffles a puzzle k times for automative option
	private static int [][] initializePuzzleK ( int [][] puzzle, int d, int k ) {
		puzzle = initializePuzzle(d);
		for( int times = 1; times <= k; times++ )
			puzzle = shufflePuzzle (puzzle, d);	
		return puzzle;
	}
	//copy array to another
	private static int [][] copy_Array ( int [][] puzzle1, int d ) {
		int [][] puzzle2 = new int [d][d];
		for( int i = 0; i < d; i++ )
			for( int j = 0; j < d; j++ )
				puzzle2[i][j] = puzzle1[i][j];
	return puzzle2;
	}	
	//how each background character is displayed compared to puzzle's size for text-based
	private static void  printPuzzleBackround ( int digits, char ch ) {
		switch ( digits ) {
		case 1:
			System.out.printf( "%1c", ch );
			break;
		case 2:
			System.out.printf( "%2c", ch );
			break;
		case 3:
			System.out.printf( "%3c", ch );
			break;
		case 4:
			System.out.printf( "%4c", ch );
			break;
		case 5:
			System.out.printf( "%5c", ch );
			break;
		case 6:
			System.out.printf( "%6d", ch );
			break;
		case 7:
			System.out.printf( "%7d", ch );
			break;
		case 8:
			System.out.printf( "%8d", ch );
			break;
		case 9:
			System.out.printf( "%9d", ch );
			break;
		case 10:
			System.out.printf( "%10d", ch );
			break;
		}
	}
	//how each number is displayed compared to puzzle's size for text-based
	private static void  printPuzzlePiece ( int digits, int piece ) {
		switch ( digits ) {
		case 1:
			System.out.printf( "%1d", piece );
			break;
		case 2:
			System.out.printf( "%2d", piece );
			break;
		case 3:
			System.out.printf( "%3d", piece );
			break;
		case 4:
			System.out.printf( "%4d", piece );
			break;
		case 5:
			System.out.printf( "%5d", piece );
			break;
		case 6:
			System.out.printf( "%6d", piece );
			break;
		case 7:
			System.out.printf( "%7d", piece );
			break;
		case 8:
			System.out.printf( "%8d", piece );
			break;
		case 9:
			System.out.printf( "%9d", piece );
			break;
		case 10:
			System.out.printf( "%10d", piece );
			break;
		}	
	}
	//text-based display
	private static void displayPuzzle ( int [][] puzzle, int d ) {
		
		int max    		 = findMax ( puzzle, d );		//find biggest number of puzzle 
		int digits 		 = findNumberSize ( max );		//find how many digits max number has
		int row          = 0;							//in order to use printf for organized display
		int col          = 0;
		boolean real_Row = false;
		
		for ( int i = 0; i <= d*2; i++ ) {
			col      = 0;
			real_Row = false;
			for ( int j = 0; j <= d*6; j++ ) {
				if( i == 0 || i%2 == 0 ) {
					if( j == 0 || j%6 ==0 )
						printPuzzleBackround ( digits, '+' );
					else
						printPuzzleBackround ( digits, '-' );
				}
				else {
					if( j == 0 || j%6 == 0 )
						printPuzzleBackround ( digits, '!' );
					else if( ( j != 0 ) && ( j%6 != 0 ) && ( j%3 == 0 ) ) {
						printPuzzlePiece ( digits, puzzle[row][col] );
						real_Row = true;								//finds if numbers where included in the display
						col++;
					}
					else
						printPuzzleBackround( digits, ' ' );
				}
			}
			System.out.println();
			if ( real_Row )												//if numbers were diplayed then change row
				row++;
		}
		
		
	}
	//for output
	private static void printMoveTurn ( int move ) {
		if ( move == 1 )
			System.out.print( move + "st" );
		else if ( move == 2 )
			System.out.print( move + "nd" );
		else if ( move == 3 )
			System.out.print( move + "rd" );
		else
			System.out.print( move + "th" );
	}
	//finds if a move is not eligible
	private static void wrongMoveIndicator ( int move, int row, int col, int N ) {
		switch ( move ) {
		case 'l':
			if ( col == 0 )
				System.out.println( "You are not allowed to go left" );
			break;
		case 'r':
			if( col == N - 1 )
				System.out.println( "You are not allowed to go right" );
			break;
		case 'u':
			if( row == 0 )
				System.out.println( "You are not allowed to go up" );
			break;
		case 'd':
			if( row == N - 1 )
				System.out.println( "You are not allowed to go down" );
			break;
		}
	}	
	//finds biggest number of puzzle
	private static int findMax ( int [][] puzzle, int d ) {
		int max = Integer.MIN_VALUE;
		for ( int i = 0; i < d; i++ )
			for ( int j = 0; j < d; j++ )
				if ( max < puzzle[i][j])
					max = puzzle[i][j];
		return max;
	}
	//finds a number's size (ex. 112 has a size of 3 digits)
	private static int findNumberSize ( int number ) {
		int pl = 0;
		while ( number / 10 > 0 ) {
			pl++;
			number = number / 10;
		}
		pl++;
		return pl;
	}
	//select charactes font and size compared to how big the puzzle is for graphical display
	private static void selectFont( int digit, int dimensions ) {
		switch( digit ) {
		case 1:
			Font f1 = new Font ( "Arial", Font.BOLD, 30 );
			StdDraw.setFont( f1 );
			break;
		case 2:
			Font f2 = new Font ( "Arial", Font.BOLD, 16 );
			StdDraw.setFont( f2 );
			break;
		case 3:
			if ( dimensions >= 28) {
				Font f3 = new Font ( "Arial", Font.BOLD, 9 );
				StdDraw.setFont( f3 );
				break;
			}
			else if ( dimensions > 24 && dimensions < 28 ) {
				Font f3 = new Font ( "Arial", Font.BOLD, 10 );
				StdDraw.setFont( f3 );
				break;
			}
			else {
				Font f3 = new Font ( "Arial", Font.BOLD, 12 );
				StdDraw.setFont( f3 );
				break;
			}	
		case 4:
			Font f4 = new Font ( "Arial", Font.PLAIN, 8 );
			StdDraw.setFont( f4 );
			break;
		case 5:
			Font f5 = new Font ( "Arial", Font.PLAIN, 4 );
			StdDraw.setFont( f5 );
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			Font f = new Font ( "Arial", Font.PLAIN, 1 );
			StdDraw.setFont( f );
			break;
		
		}
		
	}
	//finds whether puzzle is solved or not
	private static boolean isSolution ( int [][] shuffled_puzzle, int d, int [][] puzzle ) {
		for ( int i = 0; i < d; i++ )
			for ( int j = 0; j < d; j++ )
				if ( shuffled_puzzle[i][j] != puzzle[i][j] )
					return false;
		return true;
	}
	//finds if a character is a number
	public static boolean is_Digit ( String st ) {
		for ( int i = 0; i < st.length(); i++ ) {
			if ( !Character.isDigit ( st.charAt(i) ) )
				return false;
		}
		return true;
	}
	//finds if a String is a character
	private static boolean is_Char ( String st ) {
		if( st.length() == 1 && Character.isLetter ( st.charAt(0) ) )
			return true;
		return false;
	}
	//creates a random eligible move for shuffling or for the automative option
	private static char moveChance ( int row, int col, int d ) {
		double chance = 0.0;
		char   move   = ' ';
		chance        = Math.random();

		if ( row > 0 && row < d - 1 && col > 0 && col < d - 1 ) {
			if ( chance < 0.25 )
				move = 'l';
			else if ( chance < 0.50 )
				move = 'u';
			else if ( chance < 0.75 ) 
				move = 'r';
			else
				move = 'd';			
		}
		else if ( row > 0 && row < d - 1 && col == d - 1 ) {
			if ( chance < 0.3 )
				move = 'l';
			else if ( chance < 0.6 )
				move = 'u';
			else
				move = 'd';	
		}
		else if ( row > 0 && row < d - 1 && col == 0 ) {
			if ( chance < 0.3 )
				move = 'u';
			else if ( chance < 0.6 )
				move = 'r';
			else
				move = 'd';	
		}
		else if ( row == 0 && col > 0 && col < d - 1 ) {
			if ( chance < 0.3 )
				move = 'l';
			else if ( chance < 0.6 )
				move = 'r';
			else
				move = 'd';	
		}
		else if ( row == d-1 && col > 0 && col < d - 1 ) {
			if ( chance < 0.3 )
				move = 'l';
			else if ( chance < 0.6 )
				move = 'u';
			else
				move = 'r';	
		}
		else if ( row == 0 && col == 0 ) {
			if ( chance < 0.5 )
				move = 'r';
			else
				move = 'd';	
		}
		else if ( row == 0 && col == d - 1 ) {
			if ( chance < 0.5 )
				move = 'l';
			else
				move = 'd';	
		}
		else if ( row == d - 1 && col == 0 ) {
			if ( chance < 0.5 )
				move = 'u';
			else
				move = 'r';	
		}
		else if ( row == d - 1 && col == d - 1 ) {
			if ( chance < 0.5 )
				move = 'l';
			else
				move = 'u';	
		}
		return move;
	}
	//initiates the move of number 0 left, up, right, or down
	private static int [][] moveBlank ( int [][] puzzle, char move, int row, int col ) {
		int [][] shuffled_puzzle = puzzle;
		int      temp;
		switch ( move ) {
		case 'l':
			temp                        = shuffled_puzzle[row][col];
			shuffled_puzzle[row][col]   = shuffled_puzzle[row][col-1];
			shuffled_puzzle[row][col-1] = temp;
			break;
		case 'u': 
			temp                        = shuffled_puzzle[row][col];
			shuffled_puzzle[row][col]   = shuffled_puzzle[row-1][col];
			shuffled_puzzle[row-1][col] = temp;
			break;
		case 'r': 
			temp                        = shuffled_puzzle[row][col];
			shuffled_puzzle[row][col]   = shuffled_puzzle[row][col+1];
			shuffled_puzzle[row][col+1] = temp;
			break;
		case 'd': 
			temp                        = shuffled_puzzle[row][col];
			shuffled_puzzle[row][col]   = shuffled_puzzle[row+1][col];
			shuffled_puzzle[row+1][col] = temp;
			break;
		}
		return shuffled_puzzle;
	}
	//move input for interactive option
	private static char getUserCommand ( int moves ) {
		System.out.println( "*******************************" );	
		System.out.print  ( "Give " );
		printMoveTurn     ( moves );
		System.out.print  ( " Move:" );
		String s = StdIn.readString();
		System.out.println();
		while( !is_Char(s) ) {													//if move command is not a char the is wrong, re-enter command
			System.out.println( "*******************************" );	
			System.out.println( "Input is not eligible," );
			System.out.print  ( "Give " );
			printMoveTurn     ( moves );
			System.out.print  ( " Move:" );
			StdIn.readLine();
			s = StdIn.readString();
			System.out.println();
		}
		char move = s.charAt(0);
		while ( move != 'l' && move != 'u' && move != 'r' && move != 'd' && move != 'e' ){		//if move command is not l,u,r,d,or e then re-enter command
			System.out.println( "*******************************" );	
			System.out.println( "Input is not eligible," );
			System.out.print  ( "Give " );
			printMoveTurn     ( moves );
			System.out.print  ( " Move:" );
			StdIn.readLine();
			s = StdIn.readString();
			System.out.println();
			while( !is_Char(s) ) {
				System.out.println( "*******************************" );	
				System.out.println( "Input is not eligible," );
				System.out.print  ( "Give " );
				printMoveTurn     ( moves );
				System.out.print  ( " Move:" );
				StdIn.readLine();
				s = StdIn.readString();
				System.out.println();
			}
			StdIn.readLine();
			move = s.charAt(0);
		}
		return move;
	}
	//play function initiates the command and uses move_Blank function to move 0 accordingly
	private static int [][] play ( int [][] puzzle, char move, int d, int row, int col ) {
		int [][] moved_puzzle = puzzle;
		
		moved_puzzle = moveBlank ( moved_puzzle, move, row, col );
		
		return moved_puzzle;
	}
	//finds row that 0 is located
	private static int find_row ( int [][] puzzle, int d ) {
		for( int i = 0; i < d; i++ ) {
			for( int j = 0; j < d; j++ ) {
				if( puzzle[i][j] == 0 ) {
					return i;
				}
			}
		}
		return 0;
	}
	//finds col that 0 is located
	private static int find_col ( int [][] puzzle, int d ) {
		for( int i = 0; i < d; i++ ) {
			for( int j = 0; j < d; j++ ) {
				if( puzzle[i][j] == 0 ) {
					return j;
				}
			}
		}
		return 0;
	}
	//finds whether the move the user has input is valid or not
	private static boolean isValidMove ( char move, int row, int col, int d ) {
		switch ( move ) {
		case 'l':
			if ( col == 0 )
				return false;
			break;
		case 'r':
			if( col == d - 1 )
				return false;
			break;
		case 'u':
			if( row == 0 )
				return false;
			break;
		case 'd':
			if( row == d - 1 )
				return false;
			break;
		}
		return true;
	}
	//display the graph for graphical option
	public static void displayPuzzleGraph ( int [][] puzzle, int d ) {
		
		int digits = findNumberSize( findMax ( puzzle, d ) );	//finds digits of biggest number of puzzle to find font
		
		StdDraw.setCanvasSize(700, 700);						//canvas size
		int row = 0;
		int col = 0;		
		StdDraw.setXscale(0, d+1);								//x and y scales
		StdDraw.setYscale(0, d+1);
		StdDraw.picture((d+1)/2.0, (d+1)/2.0, "N_Puzzle.png");	//background picture
		for(int j=d; j>=1; j--) {
			for(int i=1; i<=d; i++) {	
				selectFont ( digits, d );						//find font of numbers displayed
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.setPenRadius(0.005);
				StdDraw.square(i, j, 0.5);						//draws white squares
				StdDraw.setPenColor ( StdDraw.WHITE );
				StdDraw.text  ( i, j, ""+puzzle[row][col] );	//writes numbers with white text inside squares
				col++;
			}
		System.out.println();
		row++;
		col=0;
		}
				
	}
	
	}
