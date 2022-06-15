package fr.brunerie.projet.metier.manager;

import fr.brunerie.projet.metier.entite.Personne;
import fr.brunerie.projet.stockage.StockagePersonneDatabase;

import java.util.List;

public class PersonneManager {
    private StockagePersonneDatabase stockageRessourceStub = new StockagePersonneDatabase();

    private final static PersonneManager INSTANCE = new PersonneManager();

    private PersonneManager() {
    }

    public static PersonneManager getInstance() {
        return INSTANCE;
    }

    public void createPersonne(String nom, String prenom, String adresse, String telephone, String mail) {
        Personne personne = new Personne(nom, prenom, adresse, mail,telephone);
        this.stockageRessourceStub.create(personne);
    }

    public void updatePersonne(int id, String nom, String prenom, String adresse, String mail, String telephone) {
        Personne personne = this.stockageRessourceStub.getById(id);
        personne.setNom(nom);
        personne.setPrenom(prenom);
        personne.setAdresse(adresse);
        personne.setTelephone(telephone);
        personne.setMail(mail);
        this.stockageRessourceStub.update(personne);
    }

    public void deletePersonne(int id) {
        this.stockageRessourceStub.deleteById(id);
    }

    public Personne getPersonne(int id) {
        return this.stockageRessourceStub.getById(id);
    }

    public List<Personne> getPersonnes() {
        return this.stockageRessourceStub.getAll();
    }
}
