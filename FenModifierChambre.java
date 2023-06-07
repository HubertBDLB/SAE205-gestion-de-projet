import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class FenModifierChambre extends Stage {
	
	public void FenModifierCHambre() {
		this.setTitle("Formulaire abonnement"); 
		this.setResizable(false);
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		this.sizeToScene();
		this.initModality(Modality.APPLICATION_MODAL);
	}
	
	private Parent creerContenu() {
		return null;
	}
}
