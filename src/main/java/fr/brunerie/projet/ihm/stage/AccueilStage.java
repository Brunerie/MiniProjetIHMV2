package fr.brunerie.projet.ihm.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilStage extends Stage {

    public AccueilStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fr/brunerie/projet/ihm/view/accueil.fxml"));
        Scene scene = new Scene(loader.load(), 800,500);
        this.setScene(scene);
        this.setTitle("Carnet d'adresse");
        this.setResizable(false);
        this.sizeToScene();
        this.show();
    }
}
