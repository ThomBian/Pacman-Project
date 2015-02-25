/**
 * 
 */
package controller;

import ihm.MapIndexOutOfBoundsException;

import java.util.ArrayList;

import model.Entity;
import model.IModel;
import model.Tile;
import view.View;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 *
 * Implementation du controller
 *
 */
public class ControllerImpl implements IController {

    /**
     * Le modele du MVC
     */
	private IModel model;

    /**
     * La vue du MVC
     */
	private View view;
	
	/**
     * Construteur de controller
     * Permet de creer l'IHM et de dessiner la carte initiale
	 * @param model
	 */
	public ControllerImpl(IModel model) {
		super();
		this.model = model;
		this.view = new View(this, model);
		view.createHCI();
		try {
			view.drawMap(this.model.getBoard());
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

    /**
     * Mis a jour a la reception d'un next envoyé par la vue
     */
	@Override
	public void update() {
	    updatePacman();
	    updateGhosts();
	}

	/**
	 * Mise a jour des fantomes sur l'IHM et dans le modele
	 */
	private void updateGhosts() {
		//TODO mouvement des GHOSTS
	}

	/**
	 * Mise a jour de Pacman sur l'IHM et dans le modele
	 */
	private void updatePacman() {
		Tile tile = this.model.getPacman().getPosition();
		Tile newTilePM = this.model.movePacman();
		this.model.getPacman().setPosition(newTilePM);
		view.drawPacMan(newTilePM.getX(), newTilePM.getY());
		view.drawSpace(tile.getX(), tile.getY());
		this.model.updateEntityPosition(this.model.getPacman(), tile);
	}

    /**
     * Remise a zero du modele et de l'IHM
     */
	@Override
	public void restart() {
		this.model.restartModel();
		try {
			this.view.drawMap(this.model.getBoard());
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
