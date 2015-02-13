package view;

import java.awt.Color;

import controller.IControleur;
import model.Board;
import model.Content;
import model.IModele;
import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;
import ihm.Simulable;

public class Vue implements Simulable{
	private IGSimulateur ihm;
	private IControleur controller;
	private IModele modele;
	
	@Override
	public void next() {
		System.out.println("LELELELLEELLELELLELELELELELLE");
		controller.update();
	}

	@Override
	public void restart() {
		controller.restart();		
	}
	
	public Vue(IControleur controleur, IModele modele) {
		this.controller = controleur;
		this.modele = modele;
	}
	
	public void creerVue (){
		setIhm(new IGSimulateur(this.modele.getBoardGameHeight(), this.modele.getBoardGameLength(), this));
	}

	public IGSimulateur getIhm() {
		return ihm;
	}

	public void setIhm(IGSimulateur ihm) {
		this.ihm = ihm;
	}

	public void drawMap(Board board) {
		for (int i = 0; i < board.getHeight(); i++){
			for (int j = 0; j < board.getWidth(); j++) {
				Content cur = board.get(i, j);
				processChar(cur.val(), j, i);
			}
		}
	}

	private void processChar(char val, int col, int row) {
		switch (val) {
		case '#':
			drawWall(col, row);
			break;
		case '.':
			drawPacGomme(col, row);
			break;
		case 'O':
			drawSuperPacGomme(col, row);
			break;
		case 'P':
			drawPacMan(col, row);
			break;
		case 'F':
			drawGhosts(col, row);
			break;
		case ' ':
			drawSpace(col, row);
			break;
		default:
			break;
		}
	}
	
	private void drawWall(int x, int y) {
		try {
			ihm.paintBox(x, y, Color.BLACK);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawSpace(int x, int y) {
		try {
			ihm.paintBox(x, y, Color.WHITE);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawPacGomme(int x, int y) {
		try {
			ihm.paintString(x, y, Color.BLACK, ".");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawSuperPacGomme(int x, int y) {
		try {
			ihm.paintString(x, y, Color.BLACK, "O");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void drawPacMan(int x, int y) {
		try {
			ihm.paintImage(x, y, "images/Pacman.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void drawGhosts(int x, int y) {
		try {
			ihm.paintImage(x, y, "images/Ghost.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}