import java.lang.*;
import java.util.*;

public class Chambre {
	private int numero;
	private String categorie; 
	private String etat;
	
	public static final String ETAT_LIBRE = "Libre";
	
	public Chambre(int numero, String categorie) {
		this.numero = numero;
		this.categorie = categorie;
		this.etat = ETAT_LIBRE;	
	}
	
	public void modifierChambre(String categorie) throws ChambreReserveeException {
		if (this.etat.equals(ETAT_LIBRE)) {
			this.categorie = categorie;
		} else {
			throw new ChambreReserveeException();
		}
	}
	
	public String numeroToString() {
		return String.format("%03d", this.numero);
	}
	
	@Override
	public String toString() {
		return "Chambre [numero=" + numeroToString() + ", categorie=" + categorie + ", etat=" + etat + "]";
	}
	
	public int getEtage() {
		return this.numero/100; // returns hundreds digit
	}
	
	public int getNumero() {
		return numero;
	}

	public String getCategorie() {
		return categorie;
	}

	public String getEtat() {
		return etat;
	}

}
