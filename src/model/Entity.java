/**
 * 
 */
package model;

import strategies.IStratetgy;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 *
 * represente un personnage du jeu : un pacman ou un fantome
 *
 */
public class Entity {
    /**
     * case courante du personnage
     */
	protected Tile position;

    /**
     * contenu representant le personnage
     * permet de differencier un pacman d'un fantome
     */
	protected Content ref;

    /**
     * strategie de deplacement du personnage
     */
	private IStratetgy strat;

    /**
     * constructeur de personnage
     * @param position
     * @param ctn
     */
    public Entity (Tile position, Content ctn){
        this.position = position;
        this.ref = ctn;
    }

    /*ACCESSEURS ET MODIFIEURS */

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
	
	public Content getRef(){
		return ref;
	}
	
	public Tile move(Board gameBoard) {
        assert strat != null;
        return this.strat.move(position, gameBoard);
    }

    public void setRef(Content ref) {
        this.ref = ref;
    }

    public IStratetgy getStrat() {
        return strat;
    }

    public void setStrat(IStratetgy strat) {
        this.strat = strat;
    }
}
