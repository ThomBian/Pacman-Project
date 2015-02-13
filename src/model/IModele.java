package model;



public interface IModele {
	
	public void initialiser(String map);
	public void restart();
	public void detruire();
	public Tile deplacePacman();
	public void deplaceGhost();
	public int getBoardGameHeight();
	public int getBoardGameLength();
	public Board getBoard();
	public Board getInitBoard();
}
