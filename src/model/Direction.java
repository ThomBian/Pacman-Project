package model;

/**
 * 
 * @author couretn
 *
 */
public enum Direction {
	NORTH(1), SOUTH(3), EAST(2), WEST(0);
	
	private int val;
	
	Direction (int val) {
	    this.val = val;
	}
	
	public int val (){
	    return this.val;
	}
}
