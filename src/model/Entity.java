/**
 * 
 */
package model;

/**
 * @author couretn
 *
 */
public abstract class Entity {
	
	protected Tile position;

	/**
	 * 
	 */
	public Entity() {
	}

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}

}
