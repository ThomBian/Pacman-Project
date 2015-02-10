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

}
