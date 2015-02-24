/**
 * 
 */

package model;

import java.util.ArrayList;
import java.util.List;
import Dijkstra.Vertex;
import Dijkstra.Edge;

/**
 * @author couretn
 */
public class Board {

    private int height, width;

    private Content[][] board;

    public Board(int height, int width) {

        this.height = height;
        this.width = width;
        board = new Content[height][width];
    }

    public void set(int row, int col, Content content) {

        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        board[row][col] = content;
    }

    public Content get(int row, int col) {

        if (row >= height || col >= width) { throw new IllegalArgumentException(); }
        return board[row][col];
    }
    
    /**
     * Verifie si le tableau contient une SUPER_PAC_GUM
     * @return bool true si il en contient une, sinon false
     */
    public boolean hasGotSuperPacGum(){
    	for(int y = 0; y < height; y++)
    		for(int x = 0; x < width; x++)
    			if(board[y][x].val() == Content.SUPER_PAC_GUM.val())
    				return true;
    	return false;
    }

    /**
     * Calcul les tiles adjacentes à celle passé en paramètre
     * @param col y de la case de référence
     * @param row x de la case de référence
     * @return Un tableau de taille 4 correspondant aux contenues des cases adjacentes
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
     * @param vertices Liste correspondant au graphe du board
     * @return target Tableau de taille 2 contenant la position du PACMAN et d'une SUPER_PAC_GUM
     */
    public Vertex calculateGraph(List<Vertex> lTarget){
    	
    	//List = Graph du terrain
		List<Vertex> vertices = new ArrayList<Vertex>();
		Vertex source = null;
    	
    	//On remplit la Liste de Vertex
    	//Et on détermine la position de la source du graph et de l'objectif à atteindre
    	for(int y = 0; y < height; y++){
    		for(int x = 0; x < width; x++){
    			vertices.add(new Vertex(new Tile(board[y][x], x,y)));
    			if(board[y][x].val() == Content.SUPER_PAC_GUM.val())
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
