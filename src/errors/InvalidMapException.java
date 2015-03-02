/**
 * 
 */

package errors;

/**
 * <p>
 * Exception lancée lorsqu'une erreur relative à la carte intervient.
 * 
 * @author Nathanael COURET
 */
public class InvalidMapException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6046706857853452325L;

    /**
     * 
     */
    public InvalidMapException() {

    }

    /**
     * @param message
     */
    public InvalidMapException(String message) {

        super(message);
    }

    /**
     * @param cause
     */
    public InvalidMapException(Throwable cause) {

        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidMapException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public InvalidMapException(
        String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <p>
     * Exception lancée lorsque tous les chemins de la carte ne sont pas
     * connectés.
     */
    public static final InvalidMapException notConnectedPathException =
                                                                          new InvalidMapException(
                                                                              "Carte invalide: certains chemins sont inaccessibles.");

    /**
     * <p>
     * Exception lancée lorsque le parsing de la carte rencontre une erreur.
     */
    public static final InvalidMapException mapParseException(Exception cause) {

        return new InvalidMapException("erreur lors de la lecture de la carte", cause);
    }

}
