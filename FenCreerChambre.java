package Vue;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Modele.AccesDonnees;
import Presentation.*;
import javafx.beans.binding.*;

public class FenCreerChambre extends Stage {
	    // Les composants du Scene Graph
	    private GridPane racine = new GridPane();
	    private HBox zoneBoutons = new HBox();
	    private Label lblNumero = new Label("Numéro de chambre (*) :");
	    private Label lblCategorie = new Label("Catégorie (*) :");
	    private TextField txtNumero = new TextField();
	    private ComboBox<String> cbCategorie = new ComboBox<String>();
	    private Button bnOK = new Button("Créer");
	    private Button bnAnnuler = new Button("Annuler");

	    // Constructeur : initialisation de la fenêtre
	    public FenCreerChambre() {
	        this.setTitle("Créer une chambre");
	        this.sizeToScene();
	        this.setResizable(false);
	        this.setScene(new Scene(creerContenu()));
	    }

	    // Création du contenu de la fenêtre
	    private Parent creerContenu() {
	        BooleanBinding manque = txtNumero.textProperty().isEmpty().or(cbCategorie.getSelectionModel().selectedItemProperty().isNull());
	        bnOK.disableProperty().bind(manque);

	        bnOK.setOnAction(e -> {
	            int numeroChambre = Integer.parseInt(txtNumero.getText());
	            String categorie = cbCategorie.getSelectionModel().getSelectedItem();

	            // Vérifier la validité du numéro de chambre et de la catégorie

	            // Créer la chambre
	            Chambre nouvelleChambre = new Chambre(numeroChambre, categorie);
	            // Ajouter la chambre à la liste des chambres existantes (AccesDonnees)

	            // Afficher un message de succès

	            this.close();
	        });

	        bnAnnuler.setOnAction(e -> {
	            this.close();
	        });

	        zoneBoutons.getChildren().addAll(bnOK, bnAnnuler);
	        zoneBoutons.setSpacing(10);
	        racine.addRow(0, lblNumero, txtNumero);
	        racine.addRow(1, lblCategorie, cbCategorie);
	        racine.add(zoneBoutons, 1, 2);
	        racine.setHgap(10);
	        racine.setVgap(15);
	        racine.setPadding(new Insets(10));
	        return racine;
	    }
	    
	    /*lblNumero.setOnKeyReleased(e ->{
			if (lblNumero.getText().length() >= 5) {
				
				e.consume();
				bnOK.setDisable(true);
				
				// classe Alert permet 
				Alert erreur = new Alert( //il faut renseigner le type qui est erreur
						AlertType.ERROR,
						"Le code postal doit être sur 5 caractères. Veuillez modifier ce champ!", //le message et commenr y remedier
						ButtonType.OK
						);
				
				erreur.setTitle("Code postal : format incorrect");
				
				erreur.showAndWait();//on ettend que l'utilisateur lit le message et d'appuyer sur ok 
			} else {
				bnOK.setDisable(false);
			}
		};*/

	    public void init() {
	        txtNumero.clear();
	        cbCategorie.getItems().clear();
	        // Charger les catégories disponibles depuis AccesDonnees
	        cbCategorie.getItems().addAll("Catégorie 1", "Catégorie 2", "Catégorie 3");
	    }
	}

