package main;
import model.Content;
import model.Entity;
import model.IModel;
import model.ModelImpl;
import controller.ControllerImpl;
import controller.IController;
import strategies.RandomStrategy;
import strategies.ShortPathStrategy;

import java.util.ArrayList;


/**
 * Simulation du programme avec
 * PACMAN : Dijkstra
 * GHOSTS : Dikstra
 * CARTE : petite
 */
public class TestDjikstraPacman {
	
	public static void main(String[] args) {		
		IModel m = new ModelImpl("cartes/normal.map");

        m.getPacman().setStrat(new ShortPathStrategy(Content.SUPER_PAC_GUM));

        ArrayList<Entity> persos = (ArrayList<Entity>) m.getGhosts();
        for (int i = 0; i < persos.size(); ++i)
            persos.get(i).setStrat(new RandomStrategy());

		IController c = new ControllerImpl(m);
	}

    //TODO creer differentes classes de tests, Set les strategies des persos dans cette classe.
}

	
