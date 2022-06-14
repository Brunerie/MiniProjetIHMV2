package fr.brunerie.projet.ihm.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NouvellePersonneStage extends Stage {

    public NouvellePersonneStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fr/brunerie/projet/ihm/view/ajoutContact.fxml"));
        Scene scene = new Scene(loader.load(), 600,500);
        this.setScene(scene);
        this.setTitle("Nouvelle Personne");
        this.setResizable(false);
        this.sizeToScene();
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }
}
