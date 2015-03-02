package main;

import java.util.ArrayList;

import model.Entity;
import model.Ghost;
import model.IModel;
import model.ModelImpl;
import strategies.MiniMaxStrategy;
import strategies.RandomStrategy;
import controller.ControllerImpl;
import controller.IController;
import errors.InvalidMapException;

public class TestMiniMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
            IModel m = new ModelImpl("cartes/small.map");
          //ATTENTION : Un seul ghost est préférable
            ArrayList<Entity> ghosts = (ArrayList<Entity>) m.getGhosts();
            Ghost g = (Ghost) ghosts.get(0);
            m.getPacman().setStrat(new MiniMaxStrategy(true, m.getPacman(), g));

            g.setStrat(new RandomStrategy());

            @SuppressWarnings("unused")
            IController c = new ControllerImpl(m);
        } catch (InvalidMapException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

		
	}

}
