package controller;

import errors.GameEndedInterrupt;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * Controller dans le modele MVC de notre application
 * Fait le lien entre la View et le Model
 */

public interface IController {

    /**
     * mis a jour de la vue et du model
     */
	void update() throws GameEndedInterrupt;

    /**
     * remet a zero le modele et la vue
     */
	void restart();

}
