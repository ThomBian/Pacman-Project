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
	protected char ref;
	
	public Entity() {
	}

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
	
	public char getRef(){
		return ref;
	}

}
