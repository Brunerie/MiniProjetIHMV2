package fr.brunerie.projet.metier.entite;

public class Personne {
    private int idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;

    public Personne(String nom, String prenom, String adresse, String telephone, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }

    public Personne() {}

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Nom=" + nom + ", Prenom=" + prenom + ", Adresse=" + adresse + ", Mail=" + mail + ", Telephone=" + telephone;
    }
}
