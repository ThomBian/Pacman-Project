/**
 * 
 */
package model;

/**
 * @author couretn
 *
 */
public class PacMan extends Entity {

	private Tile tile;

    /**
	 * 
	 */
	public PacMan(Tile tile) {
	    this.tile = tile;
	}

    
    /**
     * @return the tile
     */
    public Tile getTile() {
    
        return tile;
    }


    
    /**
     * @param tile the tile to set
     */
    public void setTile(Tile tile) {
    
        this.tile = tile;
    }
	
	
}
