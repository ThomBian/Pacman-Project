package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.SimulationData;
import errors.ErrorDisplay;

public interface IMapParser {
	
	public SimulationData parse(String path) throws IOException, FileNotFoundException, ErrorDisplay;

}
