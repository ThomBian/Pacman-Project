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

}
