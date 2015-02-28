package main;

import java.util.ArrayList;

import model.Content;
import model.Entity;
import model.IModel;
import model.ModelImpl;
import strategies.RandomStrategy;
import strategies.ShortPathStrategy;
import controller.ControllerImpl;
import controller.IController;

/**
 * Simulation du programme avec
 * PACMAN : Dijkstra
 * GHOSTS : Radom
 * CARTE : Djikstra_3
 */

public class TestDjikstraPacman3 {
	public static void main(String[] args) {		
		IModel m = new ModelImpl("cartes/Djikstra_3.map");

		
        m.getPacman().setStrat(new ShortPathStrategy(Content.SUPER_PAC_GUM));
        
        ArrayList<Entity> persos = (ArrayList<Entity>) m.getGhosts();
        for (int i = 0; i < persos.size(); ++i)
            persos.get(i).setStrat(new RandomStrategy());

		IController c = new ControllerImpl(m);
	}
}
