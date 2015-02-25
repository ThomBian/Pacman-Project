package errors;

/**
 * Created by Thomas on 25/02/15.
 */
public class MapAccessError extends Error {
    private int rowErr;
    private int colErr;
    private int heightMax;
    private int widthMax;

    public MapAccessError (int r, int col, int h, int w){
        rowErr = r;
        colErr = col;
        heightMax = h;
        widthMax = w;
    }

    @Override
    public String toString() {
        return "MapAccessError{" +
                "rowErr=" + rowErr +
                ", colErr=" + colErr +
                ", heightMax=" + heightMax +
                ", widthMax=" + widthMax +
                '}';
    }
}
