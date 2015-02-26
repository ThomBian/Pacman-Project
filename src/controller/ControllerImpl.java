/**
 * 
 */
package controller;

import ihm.MapIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.Ghost;
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
		this.view = new View(this);
		view.createHCI(this.model.getBoardGameWidth(), this.model.getBoardGameHeight());
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
	    try {
	    	updatePacman();
			updateGhosts();
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mise a jour des fantomes sur l'IHM et dans le modele
	 * @throws MapIndexOutOfBoundsException 
	 */
	private void updateGhosts() throws MapIndexOutOfBoundsException {
		List<Entity> lGhost = (List<Entity>) this.model.getGhosts();
		for(int i = 0; i < lGhost.size(); i++){
			if (lGhost.get(i) instanceof Ghost){
				Ghost g = (Ghost) lGhost.get(i);
				Tile tile = g.getPosition();
				Tile newTilePM = this.model.moveGhost(i);
				g.setPosition(newTilePM);
				view.drawGhost(newTilePM.getX(), newTilePM.getY());
				view.getHci().reset(tile.getX(), tile.getY());
				view.drawChar(g.getLastContent().val(), tile.getX(), tile.getY());
				this.model.updateEntityPosition(g, tile);
			}
		}
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
