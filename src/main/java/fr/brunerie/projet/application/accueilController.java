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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.List;

public class accueilController {

    @FXML
    public ListView<Personne> personnes;
    public VBox VboxAccueil;
    public Button btnAccueil;
    public VBox root;
    public Label carnetAdresse;
    public Label lancerAppli;
    private StockagePersonneDatabase stockageRessourceStub = new StockagePersonneDatabase();
    private boolean isCTRL = false;

    public void initialize() {
        System.out.println(btnAccueil);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        carnetAdresse.setFont(new Font(50));
        lancerAppli.setFont(new Font(20));
    }

    public void addListerner(){
        VBox VboxAccueil = new VBox();
        VboxAccueil.setAlignment(javafx.geometry.Pos.CENTER);
        VboxAccueil.setSpacing(10);

        Label label = new Label("Accueil");
        VboxAccueil.getChildren().add(label);

        this.personnes = new ListView<>();
        VBox.setVgrow(this.personnes, javafx.scene.layout.Priority.ALWAYS);
        VboxAccueil.getChildren().add(this.personnes);

        HBox hbox = new HBox();
        hbox.setSpacing(15);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        Button btnNouvellePersonne = new Button("Nouveau");
        btnNouvellePersonne.setOnAction(event -> {
            try {
                creerNouvellePersonne();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button btnEditerPersonne = new Button("Editer");
        btnEditerPersonne.setOnAction(event -> {
            try {
                editerPersonne();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button btnSupprimerPersonne = new Button("Supprimer");
        btnSupprimerPersonne.setOnAction(event -> {
            try {
                supprimerPersonne();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button btnRafraichir = new Button("Rafraichir");
        btnRafraichir.setOnAction(event -> {
            rafraichirListePersonnes();
        });

        hbox.setPadding(new javafx.geometry.Insets(0, 0, 10, 0));
        hbox.getChildren().addAll(btnNouvellePersonne, btnEditerPersonne, btnSupprimerPersonne, btnRafraichir);

        VboxAccueil.getChildren().add(hbox);
        root.getChildren().add(VboxAccueil);


        root.getChildren().remove(btnAccueil);
        Scene scene = VboxAccueil.getScene();
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

        this.rafraichirListePersonnes();
    }

    public void creerNouvellePersonne() throws IOException {
        NouvellePersonneStage nouvellePersonneStage = new NouvellePersonneStage();
        this.rafraichirListePersonnes();
    }

    public void editerPersonne() throws IOException {
        if (this.personnes.getSelectionModel().getSelectedItem() != null) {
            Personne personne = this.personnes.getSelectionModel().getSelectedItem();
            System.out.println(personne.getIdPersonne());
            EditionStage editionStage = new EditionStage(personne.getIdPersonne());
            this.rafraichirListePersonnes();
        }
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
