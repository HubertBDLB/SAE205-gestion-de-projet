import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    static private FenCreerChambre fNouvChambre = new FenCreerChambre();
    static private FenAffichageListeChambres fListeChambres = new FenAffichageListeChambres();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws InterruptedException {
    	AccesDonnees.connexion();
    	fListeChambres.init(AccesDonnees.getLesChambres());
    	fNouvChambre.initModality(Modality.APPLICATION_MODAL);
    	primaryStage = fListeChambres;
    	((FenAffichageListeChambres) primaryStage).init(AccesDonnees.getLesChambres());
		primaryStage.show();
    	
        Chambre c1 = new Chambre(46, "Anglais"); // Important, pas de 0 au début
        System.out.println(c1);
        System.out.println(c1.getNumero());



        // Modifier
        try {
            c1.modifierChambre("Espagnol");
        } catch (ChambreReserveeException e) {
            System.out.println(e);
        }

        System.out.println(c1);
    }

    // gestion des fenêtres
    static public void ouvrirNouvelleChambre() {
    	fNouvChambre.init(AccesDonnees.getLesChambres(), AccesDonnees.getLesCategories());
        fNouvChambre.showAndWait(); 
        Chambre nouvelleChambre = fNouvChambre.getChambre(); // Get the new chambre from the dialog
        if (nouvelleChambre != null) {
            fListeChambres.ajouterChambre(nouvelleChambre); // Add the new chambre to the list
            AccesDonnees.creerChambre(nouvelleChambre); // Add the new chambre to the data source
        }
    }
    
    
    static public void modifierEmploye(Chambre c) {
		//fListeChambres.modifierChambre(c);
		AccesDonnees.modifierChambre(c);	
	}
    
    static public void supprimerEmploye(Chambre c) {
		fListeChambres.supprimerChambre(c);
		AccesDonnees.supprimerChambre(c);
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
