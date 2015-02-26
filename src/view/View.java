package view;

import java.awt.Color;

import controller.IController;
import model.Board;
import model.Content;
import model.IModel;
import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;
import ihm.Simulable;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * Vue du MVC de lapplication
 * Affiche les informations du model reues grace au controlleur
 *
 */

public class View implements Simulable {

    /**
     * Permet de creer l'interface graphique et de dessiner
     * hci : human computer interface
     */
    private IGSimulateur hci;

    /**
     * Controlleur du MVC
     */
	private IController controller;
	
	@Override
	public void next() {
		controller.update();
	}

	@Override
	public void restart() {
		controller.restart();		
	}

    /**
     * Constructeur d'une vue
     * @param controleur
     */
	public View(IController controleur) {
		this.controller = controleur;
	}

    /**
     * crée un simulateur et l'associe a la vue
     */
	public void createHCI(int width, int height){
		setHci(new IGSimulateur(width, height, this));
	}

    /**
     * dessine un plateau de jeu en fonction du paramètre
     * @param board :
     *          tableau a deux dimensions avec le contenu du fichier carte
     * @throws MapIndexOutOfBoundsException
     */
	public void drawMap(Board board) throws MapIndexOutOfBoundsException {
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				Content cur = board.get(i, j);
				hci.reset(j, i);
				drawChar(cur.val(), j, i);
			}
		}
	}

    /**
     * dessine un caractere sur le simulateur
     * @param val : le caractere a dessiner
     * @param col : la colonne (x)
     * @param row : la ligne  (y)
     */
	public void drawChar(char val, int col, int row) {
		switch (val) {
		case '#':
			drawWall(col, row);
			break;
		case '.':
			drawPacGum(col, row);
			break;
		case 'O':
			drawSuperPacGum(col, row);
			break;
		case 'P':
			drawPacMan(col, row);
			break;
		case 'F':
			drawGhost(col, row);
			break;
		case ' ':
			drawSpace(col, row);
			break;
		default:
			break;
		}
	}

    /**
     * dessine un mur
     * @param x
     * @param y
     */
	private void drawWall(int x, int y) {
		try {
			hci.paintBox(x, y, Color.BLACK);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * dessine un espace : une case sans contenu
     * @param x
     * @param y
     */
	public void drawSpace(int x, int y) {
		try {
			hci.paintBox(x, y, Color.WHITE);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * dessine une Pac gomme
     * @param x
     * @param y
     */
	private void drawPacGum(int x, int y) {
		try {
			hci.paintString(x, y, Color.BLACK, ".");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * dessine une super pac gomme
     * @param x
     * @param y
     */
	private void drawSuperPacGum(int x, int y) {
		try {
			hci.paintString(x, y, Color.BLACK, "O");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * dessine Pacman
     * @param x
     * @param y
     */
	public void drawPacMan(int x, int y) {
		try {
			hci.paintImage(x, y, "images/Pacman.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * dessine un fantome
     * @param x
     * @param y
     */
	public void drawGhost(int x, int y) {
		try {
			hci.paintImage(x, y, "images/Ghost.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}


    /* MODIFIEURS ET ACCESSEURS */

    public IGSimulateur getHci() {
        return hci;
    }

    public void setHci(IGSimulateur hci) {
        this.hci = hci;
    }
}