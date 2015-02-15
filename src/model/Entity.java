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
	protected Content ref;
	
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

}
