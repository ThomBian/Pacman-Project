package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.Board;
import model.SimulationData;
import errors.ErrorDisplay;

public enum MapParserImpl implements IMapParser {

	DEFAULT {

		@Override
		public SimulationData parse(String path) throws IOException,
				FileNotFoundException, ErrorDisplay {

			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			if (line != null) {
				while (line.charAt(0) == '/') {
					line = reader.readLine();
				}
				SimulationData model = new SimulationData(path);
				String[] dimensions = line.split("");
				Board board = new Board(Integer.valueOf(dimensions[0]),
						Integer.valueOf(dimensions[1]));
				for (int i = 0; (line = reader.readLine()) != null; i++) {
					treatLine(line, i, board);
				}
				reader.close();
				model.setGameBoard(board);
				model.setNbColomns(board.getWidth());
				model.setNbLines(board.getHeight());
				return model;
			}
			reader.close();
			throw new IllegalStateException("Unexpected EOF in file " + path);
		}

		private void treatLine(String toTreat, int curLine, Board board) {
			for (int i = 0; i < toTreat.length(); ++i) {
				board.set(curLine, i, toTreat.charAt(i));
			}
		}

	}

}
