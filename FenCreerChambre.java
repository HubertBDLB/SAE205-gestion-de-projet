import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.binding.BooleanBinding;


import java.util.ArrayList;

public class FenCreerChambre extends Stage {
    private GridPane racine = new GridPane();
    private HBox zoneBoutons = new HBox();
    private Label lblNumero = new Label("Numéro de chambre (*) :");
    private Label lblCategorie = new Label("Catégorie (*) :");
    private TextField txtNumero = new TextField();
    private ComboBox<String> cbCategorie = new ComboBox<String>();
    private Button bnOK = new Button("Créer");
    private Button bnAnnuler = new Button("Annuler");

    private Chambre chambre;

    public FenCreerChambre() {
        this.setTitle("Créer une chambre");
        this.sizeToScene();
        this.setResizable(false);
        this.setScene(new Scene(creerContenu()));
    }

    private Parent creerContenu() {
        BooleanBinding manque = txtNumero.textProperty().isEmpty().or(cbCategorie.getSelectionModel().selectedItemProperty().isNull());
        bnOK.disableProperty().bind(manque);

        bnOK.setOnAction(e -> {
            int numeroChambre = Integer.parseInt(txtNumero.getText());
            String categorie = cbCategorie.getSelectionModel().getSelectedItem();

            chambre = new Chambre(numeroChambre, categorie);
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

    public void init(ArrayList<Integer> lesNumeros, ArrayList<String> lesCategories) {
        txtNumero.clear();
        cbCategorie.getItems().clear();
        cbCategorie.getItems().addAll(lesCategories);
    }

    public Chambre getChambre() {
        return chambre;
    }
}
