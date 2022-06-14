package fr.brunerie.projet;

import fr.brunerie.projet.ihm.stage.AccueilStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new AccueilStage();
    }

    public static void main(String[] args) {
        launch();
    }
}
