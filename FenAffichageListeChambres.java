import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ArrayList;

public class FenAffichageListeChambres extends Stage {
    private TableView<Chambre> tableChambres;
    private ObservableList<Chambre> lesChambres;
    
    Button bnAjouter = new Button("Ajouter");
    Button bnModifier = new Button("Modifier");
    Button bnSupprimer = new Button("Supprimer");
    Button bnFermer = new Button("Fermer");
    
    private MenuItem optionAjouter		= new MenuItem("Ajouter");
	private MenuItem optionModifier		= new MenuItem("Modifier");
	private MenuItem optionSupprimer	= new MenuItem("Supprimer");

    public FenAffichageListeChambres() {
        this.setTitle("Liste des chambres");
        this.setScene(new Scene(creerContenu()));        
		this.setResizable(true);
		this.setMinWidth(420);
		this.setMinHeight(300);
		this.sizeToScene();
    }

    @SuppressWarnings("unchecked")
	private Parent creerContenu() {
        AnchorPane racine = new AnchorPane();
        
        ContextMenu menu = new ContextMenu(
				optionAjouter,
				new SeparatorMenuItem(),
				optionModifier,
				new SeparatorMenuItem(),
				optionSupprimer
		);

        tableChambres = new TableView<>();
        TableColumn<Chambre, Integer> colNumero = new TableColumn<>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        TableColumn<Chambre, String> colCategorie = new TableColumn<>("Catégorie");
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        tableChambres.getColumns().addAll(colNumero, colCategorie);
        tableChambres.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableChambres.setContextMenu(menu);


        
        
        BooleanBinding rien = Bindings.equal(tableChambres.getSelectionModel().selectedIndexProperty(), -1);
        
        bnAjouter.setPrefWidth(100);
        bnAjouter.setOnAction(e -> {
            Main.ouvrirNouvelleChambre();
        });
        optionAjouter.setOnAction(e -> {
			Main.ouvrirNouvelleChambre();
		});

        	
        bnModifier.setPrefWidth(100);
		bnModifier.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
		optionModifier.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
        
        bnSupprimer.setPrefWidth(100);
        bnSupprimer.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
        optionSupprimer.disableProperty().bind(Bindings.when(rien).then(true).otherwise(false));
        bnSupprimer.setOnAction(e -> {
            Chambre selectedChambre = tableChambres.getSelectionModel().getSelectedItem();
            if (selectedChambre != null) {
                Alert confirmation = new Alert(AlertType.CONFIRMATION);
                confirmation.setTitle("Suppression de chambre");
                confirmation.setHeaderText("Confirmation de suppression");
                confirmation.setContentText("Voulez-vous vraiment supprimer la chambre numéro " + selectedChambre.getNumero() + " ?");
                Optional<ButtonType> result = confirmation.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    lesChambres.remove(selectedChambre);
                }
            }
        });
		
		
		bnFermer.setPrefWidth(100);
        bnFermer.setOnAction(e -> {
            this.close();
        }); 

        AnchorPane.setTopAnchor(tableChambres, 10.0);
		AnchorPane.setLeftAnchor(tableChambres, 10.0);
		AnchorPane.setRightAnchor(tableChambres, 120.0);
		AnchorPane.setBottomAnchor(tableChambres, 10.0);
		AnchorPane.setTopAnchor(bnAjouter, 30.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setTopAnchor(bnModifier, 80.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setTopAnchor(bnSupprimer, 130.0);
		AnchorPane.setRightAnchor(bnSupprimer, 10.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		racine.getChildren().addAll(tableChambres, bnAjouter, bnModifier, bnSupprimer, bnFermer);
		return racine;
		}

    public void init(ArrayList<Integer> listeChambres) {
    	 ArrayList<String> categories = AccesDonnees.getLesCategories();
    	    ArrayList<Chambre> chambres = new ArrayList<>();
    	    for (int i = 0; i < listeChambres.size(); i++) {
    	        int numero = listeChambres.get(i);
    	        String categorie = categories.get(i); // Get the corresponding category
    	        chambres.add(new Chambre(numero, categorie));
    	    }
    	    lesChambres = FXCollections.observableArrayList(chambres);
    	    tableChambres.setItems(lesChambres);
    }
    
    public void ajouterChambre(Chambre c) {
		lesChambres.add(c);
	}
    
    public void supprimerChambre(Chambre e) {
		lesChambres.remove(e);
	}
}
