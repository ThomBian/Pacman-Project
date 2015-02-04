package errors;

import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;

import java.awt.Color;

public class errorDisplay extends Exception{
	private String erreur;
	private IGSimulateur ihm;
	
	public errorDisplay(String erreur, IGSimulateur ihm) {
		this.erreur = erreur;
		this.ihm = ihm;
		display();
	}

	private void display() {
		ihm = new IGSimulateur(erreur.length(), 3);
		for (int i = 0; i < erreur.length(); ++i) {
			try {
				ihm.paintString(i, 1, Color.red, String.valueOf(erreur.charAt(i)));
			} catch (MapIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}
}
