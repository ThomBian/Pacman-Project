/**
 * 
 */
package model;

import strategies.IStratetgy;

/**
 * @author couretn
 *
 */
public abstract class Entity {
	
	protected Tile position;
	protected Content ref;
	protected IStratetgy strat;
	
	public Entity() {
	}

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
	
	public Content getRef(){
		return ref;
	}
	
	public abstract Tile move(Board gameBoard);

}
