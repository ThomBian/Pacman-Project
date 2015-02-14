/**
 * 
 */
package model;

/**
 * @author couretn
 *
 */
public class PacMan extends Entity {

	/**
	 * 
	 */
	public PacMan(Tile tile) {
	    this.position = tile;
	    this.ref = Content.PACMAN.val();
	}
	
}
