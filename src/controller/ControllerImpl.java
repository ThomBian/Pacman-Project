/**
 * 
 */
package controller;

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
		Tile newTilePM = this.model.deplacePacman();
		vue.drawPacMan(newTilePM.getX(), newTilePM.getY());
	}

	/* (non-Javadoc)
	 * @see view.IControleur#restart()
	 */
	@Override
	public void restart() {
		vue.drawMap(this.model.getInitBoard());
	}
}
