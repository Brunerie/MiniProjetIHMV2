package fr.brunerie.projet.application;

import fr.brunerie.projet.ihm.stage.EditionStage;
import fr.brunerie.projet.ihm.stage.NouvellePersonneStage;
import fr.brunerie.projet.metier.entite.Personne;
import fr.brunerie.projet.metier.manager.PersonneManager;
import fr.brunerie.projet.stockage.StockagePersonneDatabase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class accueilController {

    @FXML
    public ListView<Personne> personnes;
    public VBox VboxAccueil;
    public Button btnAccueil;
    public VBox root;
    private StockagePersonneDatabase stockageRessourceStub = new StockagePersonneDatabase();
    private boolean isCTRL = false;

    public void initialize() {
        System.out.println(btnAccueil);
    }

    public void addListerner(){
        this.rafraichirListePersonnes();
        VboxAccueil.setVisible(true);
        root.getChildren().remove(btnAccueil);
        Scene scene = this.VboxAccueil.getScene();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            System.out.println(isCTRL);
            if (event.getCode() == KeyCode.CONTROL) {
                isCTRL = true;
            }
            if (event.getCode() == KeyCode.DELETE){
                try {
                    supprimerPersonne();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.CONTROL) {
                isCTRL = false;
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.N && isCTRL) {
                try {
                    creerNouvellePersonne();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.E && isCTRL) {
                try {
                    editerPersonne();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.R && isCTRL) {
                rafraichirListePersonnes();
            }
        });
    }

    public void creerNouvellePersonne() throws IOException {
        NouvellePersonneStage nouvellePersonneStage = new NouvellePersonneStage();
        this.rafraichirListePersonnes();
    }

    public void editerPersonne() throws IOException {
        EditionStage editionStage = new EditionStage();
        this.rafraichirListePersonnes();
    }

    public void supprimerPersonne() throws IOException {
    Personne personne = personnes.getSelectionModel().getSelectedItem();
        if (personne != null) {
            PersonneManager.getInstance().deletePersonne(personne.getIdPersonne());
            this.rafraichirListePersonnes();
        }
    }

    @FXML
    public void rafraichirListePersonnes() {
        this.personnes.setItems(null);
        this.personnes.setItems(FXCollections.observableArrayList(PersonneManager.getInstance().getPersonnes()));
    }

    @FXML
    public void supprimerPersonneShortcut(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.DELETE) {
            this.supprimerPersonne();
        }
    }

    @FXML
    public void creerNouvellePersonneShortcut(KeyEvent keyEvent) throws IOException {

    }



    public List<Personne> getListePersonnes() {
        return this.stockageRessourceStub.getAll();
    }
}