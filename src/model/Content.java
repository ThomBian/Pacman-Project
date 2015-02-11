
package model;

/**
 * @author couretn
 */
public enum Content {
    EMPTY(' '), WALL('#'), PAC_GUM('.'), SUPER_PAC_GUM('O'), PACMAN('P'), GHOST('F');

    private char value;

    private Content(char value) {

        this.value = value;
    }

    public char val() {

        return value;
    }

    public static Content fromChar(char value) {

        for (Content c : values()) {
            if (c.val() == value) { return c; }
        }
        throw new IllegalArgumentException("No enum constant matching " + value);
    }

}
