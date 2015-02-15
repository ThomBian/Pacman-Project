/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.Content;
import model.Entity;
import model.IModele;
import model.Tile;
import view.Vue;

/**
 * @author Thomas
 *
 */
public class ControllerImpl implements IControleur {
	
	private IModele model;
	private Vue vue;
	
	/**
	 * @param model
	 * @param vue
	 */
	public ControllerImpl(IModele model) {
		super();
		this.model = model;
		this.vue = new Vue(this, model);
		vue.creerVue();
		vue.drawMap(this.model.getBoard());
	}
	
	/* (non-Javadoc)
	 * @see view.IControleur#update()
	 */
	@Override
	public void update() {
	    updatePacman();
	    updateGhosts();
	}

	/**
	 * 
	 */
	private void updateGhosts() {
		ArrayList<Entity> ps = (ArrayList<Entity>) this.model.getPersos();
	    for (int i = 0; i < ps.size(); i++) {
			Entity e = ps.get(i);
			
		}
	}

	/**
	 * 
	 */
	private void updatePacman() {
		Tile tile = this.model.getPacman().getPosition();
		Tile newTilePM = this.model.movePacman();
		this.model.getPacman().setPosition(newTilePM);
		vue.drawPacMan(newTilePM.getX(), newTilePM.getY());
		vue.drawSpace(tile.getX(), tile.getY());
		this.model.updateEntityPosition(this.model.getPacman(), tile);
	}

	/* (non-Javadoc)
	 * @see view.IControleur#restart()
	 */
	@Override
	public void restart() {
		this.model.restartModel();
		this.vue.drawMap(this.model.getBoard());
	}
}
