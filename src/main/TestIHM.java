package main;
import model.IModele;
import model.ModeleImpl;
import controller.ControllerImpl;
import controller.IControleur;

public class TestIHM {
	
	public static void main(String[] args) {		
		IModele m = new ModeleImpl("cartes/small.map");
		IControleur c = new ControllerImpl(m);
	}
}

	
