package model;

import java.util.Collection;



public interface IModele {
	
	/**
	 * Initialise le model
	 * @param map
	 */
	public void init(String map);
	
	
	/**
	 * clear the model
	 */
	public void remove();
	
	/**
	 * 
	 * @return
	 */
	public Tile movePacman();
	
	/**
	 * 
	 * @param idx
	 * @return
	 */
	public Tile moveGhost(int idx);
	
	/* Setter and getter */
	public int getBoardGameHeight();
	public int getBoardGameWidth();
	public Board getBoard();
	public Board getInitBoard();
	public PacMan getPacman();
	public Collection<Entity> getPersos();
	public String getMap();
	/**
	 * update the model data
	 * @param e
	 * @param t
	 */
	public void updateEntityPosition(Entity e, Tile t);
	
	public void restartModel();
}
