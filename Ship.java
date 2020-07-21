
public class Ship {
	
	private int[] coordinates;
	private int length;
	
	//array corresponding to each coordinate hit
	public boolean[] hitCoordinates;
	
	/**
	 * initializes all indexes of hitCoordinate boolean array to false
	 * @param coordinates = coordinates of player's specified ship
	 * @param length = length of that type of ship
	 */
	public Ship(int[] coordinates, int length) {
		this.coordinates = coordinates;
		this.length = length;
		
		hitCoordinates = new boolean[length];
		
		for(int i = 0; i < length; i++) {
			hitCoordinates[i] = false;
		}
	}
	
	/**
	 * checks each index of player's ship's boolean array to see if it's sunk
	 * if all elements in the array is equal to true, then the ship is sunk
	 * @return true if ship is sunk
	 * @return false otherwise
	 */
	public boolean isSunk() {
		for(int i = 0; i < length; i++) {
			if(hitCoordinates[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * if a ship is hit, it changes the index of the hitCoordinate boolean array to true
	 * @param spot = coordinate of player's guess
	 * @return true if ship is hit
	 * @return false otherwise
	 */
	public boolean isHit(int spot) {
		for(int i = 0; i < length; i++) {
			if(coordinates[i] == spot) {
				hitCoordinates[i] = true;
				return true;
			}
		}
		return false;
	}
	
	public int getLength() {
		return length;
	}
	
}
