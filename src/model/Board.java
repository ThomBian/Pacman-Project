/**
 * 
 */

package model;

/**
 * @author couretn
 */
public class Board {

    private int height, width;

    private Content[][] board;

    /**
	 * 
	 */
    public Board(int height, int width) {

        this.height = height;
        this.width = width;
        board = new Content[height][width];
    }

    public void set(int row, int col, char data) {

        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        board[row][col] = Content.fromChar(data);
    }

    public Content get(int row, int col) {

        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        return board[row][col];
    }
    

    public Content[] getSurrounding(int row, int col) {

        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        Content[] tab = new Content[4]; // gauche/haut/droite/bas
        tab[Direction.WEST.val()] = col > 0 ? board[row][col - 1] : null;
        tab[Direction.NORTH.val()] = row > 0 ? board[row - 1][col] : null;
        tab[Direction.EAST.val()] = col < width - 1 ? board[row][col + 1] : null;
        tab[Direction.SOUTH.val()] = row < height - 1 ? board[row + 1][col] : null;
        return tab;
    }

    public Content[][] getBoard() {

        return board;
    }

    public void setBoard(Content[][] board) {

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
