import javafx.application.*;
import javafx.stage.*;

public class Main extends Application {
	
	public void start(Stage primaryStage) throws InterruptedException {
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
		//primaryStage = new FenModifierChambre();
		//primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
}
