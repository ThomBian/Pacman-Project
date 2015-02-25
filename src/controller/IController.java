package controller;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * Controlleur dans le modele MVC de notre application
 * Fait le lien entre la View et le Model
 */

public interface IController {

    /**
     * mis a jour de la vue et du model
     */
	void update();

    /**
     * remet a zero le modele et la vue
     */
	void restart();

}
