package fr.brunerie.projet.application;

import fr.brunerie.projet.metier.manager.PersonneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ajoutContactController {

    public TextField nomContactField;
    public TextField prenomContactField;
    public TextField telContactField;
    public TextField emailContactField;
    public TextField adresseContactField;
    public Button btnValider;

    @FXML


    public void creerContact(){
        String nom = nomContactField.getText();
        String prenom = prenomContactField.getText();
        String tel = telContactField.getText();
        String email = emailContactField.getText();
        String adresse = adresseContactField.getText();
        if(!nom.equals("") && !prenom.equals("") && !tel.equals("") && !email.equals("") && !adresse.equals("")){
            PersonneManager.getInstance().createPersonne(nom, prenom, adresse, email,tel);
            Stage stage = (Stage) btnValider.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void validerFormulaire(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            this.creerContact();
        }
    }
}
