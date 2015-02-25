/**
 * 
 */
package model;

import strategies.IStratetgy;

/**
 * @author couretn
 *
 */
public class PacMan extends Entity {

	/**
	 * 
	 */
	public PacMan(Tile tile, IStratetgy s) {
	    this.position = tile;
	    this.ref = Content.PACMAN;
	    this.strat = s;
	}
	
	@Override
	public Tile move(Board gameBoard){
		return strat.move(this.getPosition(), gameBoard);
	}
	
}
