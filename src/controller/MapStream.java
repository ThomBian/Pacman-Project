/**
 * 
 */

package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Board;
import model.SimulationData;

/**
 * @author Nathanael COURET
 */
public final class MapStream {

    private String path;

    /**
     * 
     * @param path
     */
    public MapStream(String path) {

        if (path == null || path.equals("")) { throw new NullPointerException(
            "invalid path given, cannot be null or empty"); }
        this.path = path;
    }
    /**
     * 
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public SimulationData load()
        throws IOException, FileNotFoundException {

        SimulationData model = new SimulationData(path);
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine();
        if (line == null) {
            reader.close();
            throw new IllegalStateException("error reading the map: empty file");
        }
        while (line.charAt(0) == '/') {
            line = reader.readLine();
        }
        String[] dims = line.split(" ");
        Board board = new Board(Integer.valueOf(dims[0]), Integer.valueOf(dims[1]));
        for (int i = 0; (line = reader.readLine()) != null; i++) {
            treatLine(line, i, board);
        }
        reader.close();
        return model;
    }

    private void treatLine(String toTreat, int curLine, Board board) {

        for (int i = 0; i < toTreat.length(); ++i) {
            board.set(curLine, i, toTreat.charAt(i));
        }
    }
}
