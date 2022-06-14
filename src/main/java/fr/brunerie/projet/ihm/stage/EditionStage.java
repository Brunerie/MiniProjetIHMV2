package fr.brunerie.projet.ihm.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditionStage extends Stage{

    public EditionStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fr/brunerie/projet/ihm/view/edition.fxml"));
        Scene scene = new Scene(loader.load(), 600,500);
        this.setScene(scene);
        this.setTitle("Editer Personne");
        this.setResizable(false);
        this.sizeToScene();
        this.showAndWait();
    }
}
