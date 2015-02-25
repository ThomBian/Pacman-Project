/**
 * 
 */

package model;

import java.util.ArrayList;
import java.util.List;
import Dijkstra.Vertex;
import Dijkstra.Edge;
import errors.MapAccessError;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 * object representant un plateau de jeu / simulation
 */
public class Board {

    /**
     * hauteur et largeur du plateau
     */
    private int height, width;

    /**
     * tableau deux dimensions [hauteur][largeur]
     */
    private Content[][] board;

    /**
     * constructeur de plateau
     * @param height
     * @param width
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        board = new Content[height][width];
    }

    /**
     * affecte un contenu a la colonne et la ligne passees en
     * parametre
     * @param row
     * @param col
     * @param content
     */
    public void set(int row, int col, Content content) {
        if (row >= height || col >= width) { throw new MapAccessError(row, col, height, width); }
        board[row][col] = content;
    }

    /**
     * recupere le contenu a la colonne et la ligne passees en parametre
     * @param row
     * @param col
     * @return
     */
    public Content get(int row, int col) {
        if (row >= height || col >= width) { throw new MapAccessError(row, col, height, width); }
        return board[row][col];
    }
    
    /**
     * Verifie si le tableau contient un content objectif
     * @return bool true si il en contient un, sinon false
     */
    public boolean hasGotContent(Content obj){
    	for(int y = 0; y < height; y++)
    		for(int x = 0; x < width; x++)
    			if(board[y][x].val() == obj.val())
    				return true;
    	return false;
    }

    /**
     * Calcul les cases adjacentes à celle passee en parametre
     * @param col y de la case de référence
     * @param row x de la case de référence
     * @return Un tableau de taille 4 correspondant aux contenus des cases adjacentes
     */
    public Content[] getSurrounding(int col, int row) {
        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        Content[] tab = new Content[4]; // gauche/haut/droite/bas
        tab[Direction.WEST.val()] = col > 0 ? board[row][col - 1] : null;
        tab[Direction.NORTH.val()] = row > 0 ? board[row - 1][col] : null;
        tab[Direction.EAST.val()] = col < width - 1 ? board[row][col + 1] : null;
        tab[Direction.SOUTH.val()] = row < height - 1 ? board[row + 1][col] : null;
        return tab;
    }
    
    /**
     * Calcul du graphe pour l'algorithme de Djkstra
     * @return target Tableau de taille 2 contenant la position du PACMAN et d'une SUPER_PAC_GUM
     */
    public Vertex calculateGraph(List<Vertex> lTarget, Content obj){
    	//List = Graph du terrain
		List<Vertex> vertices = new ArrayList<Vertex>();
		Vertex source = null;
    	
    	//On remplit la Liste de Vertex
    	//Et on détermine la position de la source du graph et de l'objectif à atteindre
    	for(int y = 0; y < height; y++){
    		for(int x = 0; x < width; x++){
    			vertices.add(new Vertex(new Tile(board[y][x], x,y)));
    			if(board[y][x].val() == obj.val())
    				lTarget.add(vertices.get(vertices.size()-1));
    			if(board[y][x].val() == Content.PACMAN.val())
    				source = vertices.get(vertices.size()-1);
    		}
    	}

    	//On crée les liens entre les différente différentes cases
    	//C'est-à-dire les chemins possible depuis une position (x;y)
    	for(int y = 0; y < height; y++){
    		for(int x = 0; x < width; x++){
    			//Si je ne suis pas un mur des chemins existent
    			if(vertices.get(x+y*(height)).tile.getContent().val() != Content.WALL.val()){
	    			Content[] tab = getSurrounding(x, y);
	    			List<Edge> lEd = new ArrayList<Edge>();
	    			for(int i = 0; i < tab.length; i++){
	    				//Si l'un de mes voisins n'est pas un mur ou un fantome un chemin existe entre lui et moi
	    				if(tab[i] != null && tab[i].val() != Content.WALL.val()  
	    						&& tab[i].val() != Content.GHOST.val()){
	    					int X = 0; 
	    					int Y = 0;
	    					switch (i) {
							case 0:
								X = x-1; Y = y;
								break;
								
							case 1:
								X = x; Y = y-1;
								break;
								
							case 2:
								X = x+1; Y = y;
								break;
								
							case 3:
								X = x; Y = y+1;
	
							default:
								break;
							}
	    					//Je l'ajoute dans ma liste de possibilité de déplacement
	    					lEd.add(new Edge(vertices.get(X+Y*(height)),1));
	    				}
	    			}
	    			if(!lEd.isEmpty()){
	    				vertices.get(x+y*(height)).adjacencies = lEd.toArray(new Edge[lEd.size()]);
	    			}
    			}
    		}
    	}
    	return source;
    }

    /* ACCESSEURS ET MODIFIEURS */

    public Content[][] getBoard() {
        return board;
    }

    public void setBoard(Content[][] board) {
        this.board = board;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
