package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import errors.errorDisplay;

public class SimulationData {
	private int nbColomns = -1;
	private int nbLines = -1;
	private Character[][] gameBoard; //[line][colomn]
	private static SimulationData INSTANCE = null;

	public static SimulationData getInstance(String map) {
		return INSTANCE == null ? new SimulationData(map) : INSTANCE;
	}
	
	public SimulationData(String map) {
		parse(map);
	}

	private void parse (String map){
		try {
			BufferedReader bf = new BufferedReader(new FileReader(map));
			String readLine = bf.readLine();
			if (readLine != null) {
				while (readLine.charAt(0) == '/') {
					readLine = bf.readLine();
				}
				String[] firstLine = readLine.split(" ");
				nbColomns = Integer.valueOf(firstLine[0]);
				nbLines = Integer.valueOf(firstLine[1]);
				gameBoard = new Character [nbLines][nbColomns];
				int curLine = 0;
				while ((readLine = bf.readLine()) != null) {
					treatLine(readLine, curLine, nbColomns, nbLines);
					curLine++;
				}

				bf.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (errorDisplay e) {
			e.printStackTrace();
		}
	}
	
	private void treatLine(String toTreat, int curLine, int maxCol, int maxLine)
			throws errorDisplay {
		if (toTreat.length() > maxCol) {
			// erreur : acces colonne inexistante
		} else if (curLine >= maxLine) {
			// erreur : acces ligne inexistante
		} else {
			for (int i = 0; i < toTreat.length(); ++i) {
				this.setCase(curLine, i, toTreat.charAt(i));
			}
		}
	}
	
	/**
	 * 
	 * @param x : line
	 * @param y : colomn
	 * @param car
	 */
	public void setCase(int x, int y, Character car) {
		gameBoard[x][y] = car;
	}

	public Character getCase(int x, int y) {
		return gameBoard[x][y];
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("GameBoard < ");
		s.append(nbColomns);
		s.append(':');
		s.append(nbLines);
		s.append("> \n");
		for (int i = 0; i < nbLines; ++i) {
			for (int j = 0; j < nbColomns; ++j) {
				s.append(gameBoard[i][j]);
				s.append(' ');
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public int getNbColomns() {
		return nbColomns;
	}

	public int getNbLines() {
		return nbLines;
	}
	
	public void removeModel (){
		gameBoard = null;
		nbColomns = -1;
		nbLines = -1;
		INSTANCE = null;
	}
}
