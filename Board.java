
public class Board {
	
	private static final int boardSize = 10;
	private String[][] board = new String[boardSize][boardSize];
	
	//initializes a board of boardSize(10 rows & 10 columns)
	public void initializeBoard() {
		
		int val = 1;
		for(int row = 0; row < boardSize; row++) {
			for(int col = 0; col < boardSize; col++) {
				board[row][col] = "" + val;
				val++;
			}
		}
	}
	
	//prints board
	public void printBoard() {
		int row = 0;
		System.out.println("+---+---+---+---+---+---+---+---+---+---+");
		System.out.println("  " + board[row][0] + " | " + board[row][1] + " | " + board[row][2] + " | " + board[row][3] + " | " + board[row][4] + " | " + board[row][5] + " | " + board[row][6] + " | " + board[row][7] + " | " + board[row][8] + " | " + board[row][9] + "| ");
		
		for(row = 1; row <= 8; row++) {
			System.out.println("+---+---+---+---+---+---+---+---+---+---+");
			System.out.println("  " + board[row][0] + "| " + board[row][1] + "| " + board[row][2] + "| " + board[row][3] + "| " + board[row][4] + "| " + board[row][5] + "| " + board[row][6] + "| " + board[row][7] + "| " + board[row][8] + "| " + board[row][9] + "| ");
		}
		
		row = 9;
		System.out.println("+---+---+---+---+---+---+---+---+---+---+");
		System.out.println("  " + board[row][0] + "| " + board[row][1] + "| " + board[row][2] + "| " + board[row][3] + "| " + board[row][4] + "| " + board[row][5] + "| " + board[row][6] + "| " + board[row][7] + "| " + board[row][8] + "|" + board[row][9] + "| ");
		System.out.println("+---+---+---+---+---+---+---+---+---+---+");
	}
	
	
	/**
	 * places the ship mark on chosen spot one at a time
	 * @param spot = coordinate chosen
	 */
	public void placeShip(int spot) {
		if(spot < 10) {
			board[(spot-1)/10][(spot-1)%10] =  ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET;
		}
		else if(spot == 100) {
			board[(spot-1)/10][(spot-1)%10] =  ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET;
		}
		else {
			board[(spot-1)/10][(spot-1)%10] =  ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET;
		}
	}
	
	/**
	 * returns a String of the indicated spot on board
	 * @param spot = coordinate chosen
	 * @return String value at spot
	 */
	public String getSpot(int spot) {
		return board[(spot-1)/10][(spot-1)%10];
	}
	
	/**
	 * checks if the shot fired is a hit or a miss
	 * @param guessCoordinate = coordinate of player's guess
	 * @return String "hit" or "miss"
	 */
	public String hitOrMiss(int guessCoordinate) {
		
		String result = "";
		
		//sets coordinate value to "X" if it was a hit
		if(getSpot(guessCoordinate) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || getSpot(guessCoordinate) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || getSpot(guessCoordinate) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET) {
			if(guessCoordinate < 10) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + "X" + ANSIConstants.ANSI_RESET;
			}
			else if(guessCoordinate == 100) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + " X " + ANSIConstants.ANSI_RESET;
			}
			else {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + "X " + ANSIConstants.ANSI_RESET;
			}
			result = "hit";
		} 
		
		//sets coordinate value to "O" if it was a missed shot
		else {
			if(guessCoordinate < 10) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + "O" + ANSIConstants.ANSI_RESET;
			}
			else if(guessCoordinate == 100) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + " O " + ANSIConstants.ANSI_RESET;
			}
			else {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + "O " + ANSIConstants.ANSI_RESET;
			}
			result = "missed";
		}
		return result;
	}
	
	public void setHitOrMiss(int guessCoordinate, String mark) {
		if(mark == "X") {
			if(guessCoordinate < 10) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + mark + ANSIConstants.ANSI_RESET;
			}
			else if(guessCoordinate == 100) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + (" " + mark + " ") + ANSIConstants.ANSI_RESET;
			}
			else {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_RED + (mark + " ") + ANSIConstants.ANSI_RESET;
			}
		}
		else {
			if(guessCoordinate < 10) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + mark + ANSIConstants.ANSI_RESET;
			}
			else if(guessCoordinate == 100) {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + (" " + mark + " ") + ANSIConstants.ANSI_RESET;
			}
			else {
				board[(guessCoordinate-1)/10][(guessCoordinate-1)%10] = ANSIConstants.ANSI_CYAN + (mark + " ") + ANSIConstants.ANSI_RESET;
			}
		}
		
	}
	
	//if player enters invalid numbers when placing ships, it will reset the coordinates of that ship
	public void resetSpot(int spot) {
		board[(spot-1)/10][(spot-1)%10] = spot + "";
	}
	
}
