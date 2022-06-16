package fr.brunerie.projet.application;

import fr.brunerie.projet.metier.entite.Personne;
import fr.brunerie.projet.metier.manager.PersonneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class editionController {


    private int idPersonne;

    @FXML
    public TextField nomPersonneField;
    public TextField prenomPersonneField;
    public TextField telPersonneField;
    public TextField emailPersonneField;
    public TextField adressePersonneField;
    public Button btnValider;

    public void initialize() {
        Platform.runLater(() -> {
            Personne personne = PersonneManager.getInstance().getPersonne(this.idPersonne);
            this.nomPersonneField.setText(personne.getNom());
            this.prenomPersonneField.setText(personne.getPrenom());
            this.adressePersonneField.setText(personne.getAdresse());
            this.emailPersonneField.setText(personne.getMail());
            this.telPersonneField.setText(personne.getTelephone());
        });
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void editerPersonne(){
        String nom = nomPersonneField.getText();
        String prenom = prenomPersonneField.getText();
        String tel = telPersonneField.getText();
        String email = emailPersonneField.getText();
        String adresse = adressePersonneField.getText();
        if(!nom.equals("") && !prenom.equals("") && !tel.equals("") && !email.equals("")){
            if (email.matches("^(.+)@(.+)$")) {
                if(tel.matches("(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}")){
                    PersonneManager.getInstance().updatePersonne(idPersonne, nom, prenom,adresse, email, tel);
                    Stage stage = (Stage) btnValider.getScene().getWindow();
                    stage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Numero de telephone invalide");
                    alert.setContentText("Veuillez entrer un numero de telephone valide");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Email invalide");
                alert.setContentText("Veuillez entrer un email valide");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
    }

    @FXML
    public void validerFormulaire(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            this.editerPersonne();
        }
    }
}
