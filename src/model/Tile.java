/**
 * 
 */
package model;

/**
 * @author couretn
 *
 */
public class Tile {
	
	protected Content content;
	protected int x;
	protected int y;
	
	/**
	 * 
	 */
	public Tile() {
		content = Content.EMPTY;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void setX(int i) {
		this.x = i;
	}
	
	public void setY (int y){
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
