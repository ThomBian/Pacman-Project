package model;

import java.util.Collection;

/**
 * Interface du modele dans le MVC de l'application
 * Stocke les données liées à la carte et à la simulation
 * Manipule ces données
 */

public interface IModel {
	
	/**
	 * Initialise le modele
	 * @param map
	 */
	public void init(String map);
	
	
	/**
	 * detruit le modele
     * vide le plateau de jeu
     * vide la liste de fantome
     * detruit pacman
	 */
	public void remove();
	
	/**
	 * deplace pacman
	 * @return la nouvelle position de pacman
	 */
	public Tile movePacman();
	
	/**
	 * deplace le fantome d'indice en parametre
	 * @param idx dans la liste des fantomes
	 * @return la nouvelle position du fantome
	 */
	public Tile moveGhost(int idx);

    /**
     * mets a jour le tableau a deux dimensions representant le jeu
     * @param e le personnage concerné
     * @param t la case concernée
     */
    public void updateEntityPosition(Entity e, Tile t);

    /**
     * reinitialise le modele
     */
    public void restartModel();

	/* ACCESSEURS ET MODIFIEURS */
	public int getBoardGameHeight();
	public int getBoardGameWidth();
	public Board getBoard();
	public Entity getPacman();
	public Collection<Entity> getGhosts();
	public String getMap();
}
