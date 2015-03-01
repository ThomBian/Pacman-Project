
package main;

import controller.ControllerImpl;
import controller.IController;
import model.Content;
import model.Entity;
import model.IModel;
import model.ModelImpl;
import strategies.RandomStrategy;
import strategies.ShortPathStrategy;

import java.util.ArrayList;

import errors.InvalidMapException;

/**
 * @authors Bianchini - Couret - Taboulot - Valette Pacman : RANDOM Ghosts :
 *          RANDOM map : djikstra_3
 */
public class TestRandomAll {

    public static void main(String[] args) {

        try {
            IModel m = new ModelImpl("cartes/djikstra_3.map");

            m.getPacman().setStrat(new RandomStrategy());

            ArrayList<Entity> persos = (ArrayList<Entity>) m.getGhosts();
            for (int i = 0; i < persos.size(); ++i)
                persos.get(i).setStrat(new RandomStrategy());

            IController c = new ControllerImpl(m);
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }
    }
}
