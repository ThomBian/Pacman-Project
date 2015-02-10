/**
 * 
 */
package model;

/**
 * @author couretn
 *
 */
public class Board {
	
	private int height, width;
	
	private Character[][] board;

	/**
	 * 
	 */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		board = new Character[height][width];
	}
	
	public void set(int row, int col, char data) {
		if(row >= height || col >= width) {
			throw new IllegalArgumentException();
		}
		board[row][col] = data;
	}

	public Character[][] getBoard() {
		return board;
	}

	public void setBoard(Character[][] board) {
		this.board = board;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
