
package model;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 * Enumeration du contenu possible d'une case
 */
public enum Content {
    EMPTY(' '), WALL('#'), PAC_GUM('.'), SUPER_PAC_GUM('O'), PACMAN('P'), GHOST('F');

    /**
     * caractere de l'enum
     */
    private char value;

    /**
     * constructeur d'un content
     * @param value
     */
    private Content(char value) {
        this.value = value;
    }

    /**
     * accesseur de value
     * @return la valeur d'un content
     */
    public char val() {
        return value;
    }

    /**
     * retourne un contenu en fonction d'un caractère
     * @param value
     * @return le content associe au parametre
     */
    public static Content fromChar(char value) {
        for (Content c : values()) {
            if (c.val() == value) { return c; }
        }
        throw new IllegalArgumentException("No enum constant matching " + value);
    }

}
