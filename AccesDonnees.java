

import java.util.ArrayList;


public class AccesDonnees {
	static private ArrayList<Chambre> 	lesChambres = new ArrayList<Chambre>();
	
	static public void connexion() { 
		lesChambres.add(new Chambre(61, "Petite suite"));
		lesChambres.add(new Chambre(71, "Chambre économique"));
		lesChambres.add(new Chambre(32, "Chambre standard"));
		lesChambres.add(new Chambre(42, "Chambre standard"));
		lesChambres.add(new Chambre(44, "Chambre supérieure"));
		lesChambres.add(new Chambre(84, "Suite supérieure"));
			}


	static public ArrayList<Integer> getLesChambres() {

		ArrayList<Integer> lesNum = new ArrayList<Integer>();
		for(int i=0 ; i<lesChambres.size() ; i++) {
			lesNum.add(lesChambres.get(i).getNumero());
		}
		return lesNum;
	}

	static public ArrayList<String> getLesCategories() {
	    ArrayList<String> lesCategories = new ArrayList<>();
	    for (Chambre chambre : lesChambres) {
	        lesCategories.add(chambre.getCategorie());
	    }
	    return lesCategories;
	}
	

	static public void creerChambre(Chambre c) {
		lesChambres.add(c);
	}
	static public void supprimerChambre(Chambre c) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesChambres.size()) {
			if (lesChambres.get(i).getNumero()==c.getNumero()){
				lesChambres.remove(i);
				trouve = true;
			}
			i++;
		}
	}
	static public void modifierChambre(Chambre c) {
		boolean trouve = false;
		int i=0;
		while (!trouve && i<lesChambres.size()) {
			if (lesChambres.get(i).getNumero()==c.getNumero()){
				lesChambres.set(i, c);
				trouve = true;
			}
			i++;
		}
	}
}
