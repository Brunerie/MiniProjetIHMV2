package fr.brunerie.projet.ihm.stage;

import fr.brunerie.projet.application.editionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditionStage extends Stage{

    public EditionStage(int idPersonne) throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fr/brunerie/projet/ihm/view/edition.fxml"));
        Scene scene = new Scene(loader.load(), 600,500);
        editionController controller = loader.getController();
        controller.setIdPersonne(idPersonne);
        this.setScene(scene);
        this.setTitle("Editer Personne");
        this.setResizable(false);
        this.sizeToScene();
        this.initModality(Modality.APPLICATION_MODAL);
        this.showAndWait();
    }
}
