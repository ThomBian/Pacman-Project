/**
 * 
 */

package errors;

/**
 * @author Nathanael COURET
 */
public class GameEndedInterrupt extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2125424662876001793L;

    /**
     * @param message
     */
    public GameEndedInterrupt(boolean won) {

        super("Partie terminée! " + (won ? "Pacman a " : "les fantomes ont ") + "gagné" +
            (won ? "" : "s"));
    }

}
