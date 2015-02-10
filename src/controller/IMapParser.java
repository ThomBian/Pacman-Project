package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import errors.errorDisplay;
import model.Board;

public interface IMapParser {
	
	public Board parse(String path) throws IOException, FileNotFoundException, errorDisplay;

}
