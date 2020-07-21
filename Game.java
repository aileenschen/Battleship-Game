/**
 * @authors Aileen Chen, Elizabeth Tan, Elaine Ton
 * Group 8 (Team Ship Commanders)
 */

import java.util.*;

public class Game {

	public static boolean winner = false;
	public static boolean tie = false;
	public static int turn = 1;
	public static int guess;
	//number of ships player 1 has sunk
	public static int numberSunk1;
	//number of ships player 2 has sunk
	public static int numberSunk2;
	public static int ready;
	public static Ship[] player1Ships = new Ship[5];
	public static Ship[] player2Ships = new Ship[5];
	static Scanner keyboard = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		
		//create and initialize player 1's board
		Board board1 = new Board();
		board1.initializeBoard();
		
		//create and initialize player 1's guessing board
		Board board2 = new Board();
		board2.initializeBoard();
		
		//create and initialize player 2's board
		Board board3 = new Board();
		board3.initializeBoard();
		
		//create and initialize player 2's guessing board
		Board board4 = new Board();
		board4.initializeBoard();
		

		
		
		
		System.out.println("Welcome to Battleship!" + "\n");
		
		//present board to player 1
		System.out.println("Player 1, here is your board to place your ships." + "\n");
		board1.printBoard();
		System.out.println();
		
		//player 1 places their 5 ships
		setPlayer1Ships(board1, player1Ships);
		
		//player 1 is done placing all their ships
		System.out.println("You have placed all your ships!" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
		
		
		
		//present board to player 2
		System.out.println("Player 2, here is your board to place your ships." + "\n");
		board3.printBoard();
		System.out.println();
		
		//player 2 places their 5 ships
		setPlayer2Ships(board3, player2Ships);
		
		//player 2 is done placing all their ships
		System.out.println("You have placed all your ships!" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
		
		
		
		
		
		//game setup finished, game now begins
		do {
			
			//player 1's turn to guess
			if (turn % 2 == 1) {
				
				System.out.println("\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
				System.out.println("Player 1, here is your board." + "\n");
				board1.printBoard();
				
				System.out.println("\n" + "Player 1, here is your guessing board." + "\n");
				board2.printBoard();
				
				System.out.print("\n" + "Choose a spot to guess (1-100): ");
				guess = keyboard.nextInt();
				
				//checks if shot fired was a hit or miss
				String result = board3.hitOrMiss(guess);
				if(result.equals("hit")) {
					//changes hitCoordinate boolean array to true at the coordinate of the guess
					for (int i = 0; i < player2Ships.length; i++) {
						for (int j = 0; j < player2Ships[i].getLength(); j++) {
							player2Ships[i].isHit(guess);
						}
					}
					//change player 1's guessing board to "X" at the coordinate of the guess
					board2.setHitOrMiss(guess, "X");
				}
				else {
					//change player 1's guessing board to "O" at the coordinate of the guess
					board2.setHitOrMiss(guess, "O");
				}
				
				
				System.out.println("\n" + "You " + result + " Player 2's ship.");
				System.out.println();
				
				//checks if any of player 2's ships was sunk
				boolean result1 = false;
				numberSunk1 = 0;
				for(int i = 0; i < player2Ships.length; i++) {
					result1 = player2Ships[i].isSunk();
					if(result1 == true) {
						numberSunk1++;
					}
				}
				System.out.println("You have sunk " + numberSunk1 + " of Player 2's ships." + "\n");
				
				//reprints player 1's guessing board
				board2.printBoard();
				System.out.println("\n" + "\n");
				
				//getting ready for player 1's turn
				do {
					System.out.print("Player 2, are you ready? Type in \"0\" to continue: ");
					ready = keyboard.nextInt();
					if(ready == 0) {
						turn++;
						continue;
					}
				} while(ready != 0);
				
			}
			
			//player 2's turn to guess
			else {
				System.out.println("\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
				System.out.println("Player 2, here is your board." + "\n");
				board3.printBoard();
				
				System.out.println("\n" + "Player 2, here is your guessing board." + "\n");
				board4.printBoard();
				
				System.out.print("\n" + "Choose a spot to guess (1-100): ");
				guess = keyboard.nextInt();
				
				//checks if shot fired was a hit or miss
				String result = board1.hitOrMiss(guess);
				if(result.equals("hit")) {
					//changes hitCoordinate boolean array to true at the coordinate of the guess
					for (int i = 0; i < player1Ships.length; i++) {
						for (int j = 0; j < player1Ships[i].getLength(); j++) {
							player1Ships[i].isHit(guess);
						}
					}
					//change player 2's guessing board to "X" at the coordinate of the guess
					board4.setHitOrMiss(guess, "X");
				}
				else {
					//change player 2's guessing board to "O" at the coordinate of the guess
					board4.setHitOrMiss(guess, "O");
				}
				
				System.out.println("\n" + "You " + result + " Player 1's ship.");
				System.out.println();
				
				//checks if any of player 1's ships was sunk
				boolean result2 = false;
				numberSunk2 = 0;
				for(int i = 0; i < player1Ships.length; i++) {
					result2 = player1Ships[i].isSunk();
					if(result2 == true) {
						numberSunk2++;
					}
				}
				System.out.println("You have sunk " + numberSunk2 + " of Player 1's ships." + "\n");
				
				//reprints player 2's guessing board
				board4.printBoard();
				System.out.println("\n" + "\n");
				
				//check for tie
				if(numberSunk1 == 5 && numberSunk2 == 5) {
					System.out.println("It's a tie!");
					tie = true;
					System.exit(0);
				}
				//check if player 1 has won
				else if(numberSunk2 != 5 && numberSunk1 == 5) {
					System.out.println("Congratulations Player 1, you win!");
					winner = true;
					System.exit(0);
				}
				//check if player 2 has won
				else if(numberSunk1 != 5 && numberSunk2 == 5) {
					System.out.println("Congratulations Player 2, you win!");
					winner = true;
					System.exit(0);
				}
				
				//getting ready for player 1's turn
				do {
					System.out.print("Player 1, are you ready? Type in \"0\" to continue: ");
					ready = keyboard.nextInt();
					if(ready == 0) {
						turn++;
						continue;
					}
				} while(ready != 0);
			}
			
		} while (winner == false && tie == false);
		
		
		
		
			
		
		
	}
	
	
	
	
	public static void setPlayer1Ships(Board board1, Ship[] ships) {
		
		//player 1 chooses spots for their Carrier
		System.out.println("Player 1, you will be choosing 5 consecutive (horizontal or vertical) unfilled spots for your Carrier in numerical order.");
		int[] carrier1Array = {0, 0, 0, 0, 0};
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(carrier1Array[i - 1] - spot) != 1 && Math.abs(carrier1Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Carrier
				for (int j = 0; j < i; j++) {
					board1.resetSpot(carrier1Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board1.placeShip(spot);
				carrier1Array[i] = spot;
			}
		}
		
		//creates Ship object for player 1's Carrier
		Ship carrier1 = new Ship(carrier1Array, carrier1Array.length);
		ships[0] = carrier1;
		System.out.println();
		board1.printBoard();
		System.out.println();
		
		
		
		//player 1 chooses spots for their Battleship
		System.out.println("Player 1, you will be choosing 4 consecutive (horizontal or vertical) unfilled spots for your Battleship in numerical order.");
		int[] battleship1Array = {0, 0, 0, 0};
		for(int i = 0; i < 4; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(battleship1Array[i - 1] - spot) != 1 && Math.abs(battleship1Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Battleship
				for (int j = 0; j < i; j++) {
					board1.resetSpot(battleship1Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				//places ship in chosen spot & adds it to the ship's coordinate list
				board1.placeShip(spot);
				battleship1Array[i] = spot;
			}
		}
		
		//creates Ship object for player 1's Battleship
		Ship battleship1 = new Ship(battleship1Array, battleship1Array.length);
		ships[1] = battleship1;
		System.out.println();
		board1.printBoard();
		System.out.println();
		
		
		
		//player 1 chooses spots for their Cruiser
		System.out.println("Player 1, you will be choosing 3 consecutive (horizontal or vertical) unfilled spots for your Cruiser in numerical order.");
		int[] cruiser1Array = {0, 0, 0};
		for(int i = 0; i < 3; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(cruiser1Array[i - 1] - spot) != 1 && Math.abs(cruiser1Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Cruiser
				for (int j = 0; j < i; j++) {
					board1.resetSpot(cruiser1Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board1.placeShip(spot);
				cruiser1Array[i] = spot;
			}
		}
		
		//creates Ship object for player 1's Cruiser
		Ship cruiser1 = new Ship(cruiser1Array, cruiser1Array.length);
		ships[2] = cruiser1;
		System.out.println();
		board1.printBoard();
		System.out.println();
		
		
		
		//player 1 chooses spots for their Submarine
		System.out.println("Player 1, you will be choosing 3 consecutive (horizontal or vertical) unfilled spots for your Submarine in numerical order.");
		int[] submarine1Array = {0, 0, 0};
		for(int i = 0; i < 3; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(submarine1Array[i - 1] - spot) != 1 && Math.abs(submarine1Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Submarine
				for (int j = 0; j < i; j++) {
					board1.resetSpot(submarine1Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board1.placeShip(spot);
				submarine1Array[i] = spot;
			}
		}
		
		//creates Ship object for player 1's Submarine
		Ship submarine1 = new Ship(submarine1Array, submarine1Array.length);
		ships[3] = submarine1;
		System.out.println();
		board1.printBoard();
		System.out.println();
		
		
		
		//player 1 chooses spots for their Destroyer
		System.out.println("Player 1, you will be choosing 2 consecutive (horizontal or vertical) unfilled spots for your Destroyer in numerical order.");
		int[] destroyer1Array = {0, 0};
		for(int i = 0; i < 2; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board1.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(destroyer1Array[i - 1] - spot) != 1 && Math.abs(destroyer1Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Destroyer
				for (int j = 0; j < i; j++) {
					board1.resetSpot(destroyer1Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board1.placeShip(spot);
				destroyer1Array[i] = spot;
			}
		}
		
		//creates Ship object for player 1's Destroyer
		Ship destroyer1 = new Ship(destroyer1Array, destroyer1Array.length);
		ships[4] = destroyer1;
		System.out.println();
		board1.printBoard();
		System.out.println();
		
	}

	public static void setPlayer2Ships(Board board3, Ship[] ships) {
		
		//player 2 chooses spots for their Carrier
		System.out.println("Player 2, you will be choosing 5 consecutive (horizontal or vertical) unfilled spots for your Carrier in numerical order.");
		int[] carrier2Array = {0, 0, 0, 0, 0};
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(carrier2Array[i - 1] - spot) != 1 && Math.abs(carrier2Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Carrier
				for (int j = 0; j < i; j++) {
					board3.resetSpot(carrier2Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board3.placeShip(spot);
				carrier2Array[i] = spot;
			}
		}
		
		//creates Ship object for player 2's Carrier
		Ship carrier2 = new Ship(carrier2Array, carrier2Array.length);
		ships[0] = carrier2;
		System.out.println();
		board3.printBoard();
		System.out.println();
		
		
		
		//player 2 chooses spots for their Battleship
		System.out.println("Player 2, you will be choosing 4 consecutive (horizontal or vertical) unfilled spots for your Battleship in numerical order.");
		int[] battleship2Array = {0, 0, 0, 0};
		for(int i = 0; i < 4; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(battleship2Array[i - 1] - spot) != 1 && Math.abs(battleship2Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Battleship
				for (int j = 0; j < i; j++) {
					board3.resetSpot(battleship2Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board3.placeShip(spot);
				battleship2Array[i] = spot;
			}
		}
		
		//creates Ship object for player 2's Battleship
		Ship battleship2 = new Ship(battleship2Array, battleship2Array.length);
		ships[1] = battleship2;
		System.out.println();
		board3.printBoard();
		System.out.println();
		
		
		
		//player 2 chooses spots for their Cruiser
		System.out.println("Player 2, you will be choosing 3 consecutive (horizontal or vertical) unfilled spots for your Cruiser in numerical order.");
		int[] cruiser2Array = {0, 0, 0};
		for(int i = 0; i < 3; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(cruiser2Array[i - 1] - spot) != 1 && Math.abs(cruiser2Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Cruiser
				for (int j = 0; j < i; j++) {
					board3.resetSpot(cruiser2Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board3.placeShip(spot);
				cruiser2Array[i] = spot;
			}
		}
		
		//creates Ship object for player 2's Cruiser
		Ship cruiser2 = new Ship(cruiser2Array, cruiser2Array.length);
		ships[2] = cruiser2;
		System.out.println();
		board3.printBoard();
		System.out.println();
		
		
		
		//player 2 chooses spots for their Submarine
		System.out.println("Player 2, you will be choosing 3 consecutive (horizontal or vertical) unfilled spots for your Submarine in numerical order.");
		int[] submarine2Array = {0, 0, 0};
		for(int i = 0; i < 3; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(submarine2Array[i - 1] - spot) != 1 && Math.abs(submarine2Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Submarine
				for (int j = 0; j < i; j++) {
					board3.resetSpot(submarine2Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board3.placeShip(spot);
				submarine2Array[i] = spot;
			}
		}
		
		//creates Ship object for player 2's Submarine
		Ship submarine2 = new Ship(submarine2Array, submarine2Array.length);
		ships[3] = submarine2;
		System.out.println();
		board3.printBoard();
		System.out.println();
		
		
		
		//player 2 chooses spots for their Destroyer
		System.out.println("Player 2, you will be choosing 2 consecutive (horizontal or vertical) unfilled spots for your Destroyer in numerical order.");
		int[] destroyer2Array = {0, 0};
		for(int i = 0; i < 2; i++) {
			System.out.print("Enter a spot: ");
			int spot = keyboard.nextInt();
			//checks if spot is between 1-100, if it's not filled already, and if they're consecutive horizontally or vertically
			if (spot < 1 || spot > 100 || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S" + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + " S " + ANSIConstants.ANSI_RESET || board3.getSpot(spot) == ANSIConstants.ANSI_PURPLE + "S " + ANSIConstants.ANSI_RESET || (i > 0 && (Math.abs(destroyer2Array[i - 1] - spot) != 1 && Math.abs(destroyer2Array[i - 1] - spot) != 10))) {
				System.out.println("\n" + "Error, please re-enter your spots." + "\n");
				//resets all spots of their Destroyer
				for (int j = 0; j < i; j++) {
					board3.resetSpot(destroyer2Array[j]);
				}
				//resets index to 0
				i = -1;
			} else {
				board3.placeShip(spot);
				destroyer2Array[i] = spot;
			}
		}
		
		//creates Ship object for player 2's Destroyer
		Ship destroyer2 = new Ship(destroyer2Array, destroyer2Array.length);
		ships[4] = destroyer2;
		System.out.println();
		board3.printBoard();
		System.out.println();
		
	}
	
	
}
