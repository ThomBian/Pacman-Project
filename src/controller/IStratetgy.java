package controller;

import model.Board;
import model.Tile;


public interface IStratetgy {
    
    public Tile move(Tile pos, Board board);

}
