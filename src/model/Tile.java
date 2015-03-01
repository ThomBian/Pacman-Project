/**
 * 
 */
package model;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 *
 * Represente une case sur le plateau de jeu
 *
 */
public class Tile {
    /**
     * Contenu de la case
     * @see model.Content
     */
	private Content content;

    /**
     * coordonnée x de la case : n° de colonne
     */
	private int x;

    /**
     * coordonnée y de la case : n° de ligne
     */
	private int y;
	
	/**
	 * Constructeur de case vide (DEFAUT)
	 */
	public Tile() {
		content = Content.EMPTY;
	}
	
	/**
     * Constructeur de case
	 * @param content
	 * @param x
	 * @param y
	 */
	public Tile(Content content, int x, int y) {
		super();
		this.content = content;
		this.x = x;
		this.y = y;
	}

    /* ACCESSEURS ET MODIFIEURS */

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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Tile))
            return false;
        Tile other = (Tile) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

}
