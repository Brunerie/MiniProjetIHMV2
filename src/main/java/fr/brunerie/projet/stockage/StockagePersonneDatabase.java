package fr.brunerie.projet.stockage;


import fr.brunerie.projet.metier.entite.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockagePersonneDatabase implements Stockage<Personne> {

    @Override
    public void create(Personne element) {
        try {
            String requete = "INSERT INTO Personne (idPersonne,nom,prenom,adresse,mail,num_tel) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = SQLUtils.connection.prepareStatement(requete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, element.getIdPersonne());
            statement.setString(2, element.getNom());
            statement.setString(3, element.getPrenom());
            statement.setString(4, element.getAdresse());
            statement.setString(5, element.getMail());
            statement.setString(6, element.getTelephone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Personne element) {
        try {
            String requete = "UPDATE Personne SET nom = ?, prenom = ?, adresse = ?, mail = ?, num_tel = ? WHERE idPersonne = ?";
            PreparedStatement statement = SQLUtils.connection.prepareStatement(requete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, element.getNom());
            statement.setString(2, element.getPrenom());
            statement.setString(3, element.getAdresse());
            statement.setString(4, element.getMail());
            statement.setString(5, element.getTelephone());
            statement.setInt(6, element.getIdPersonne());
            statement.executeUpdate();
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            String requete = "DELETE FROM Personne WHERE idPersonne = ?";
            PreparedStatement statement = SQLUtils.connection.prepareStatement(requete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, id);
            statement.executeUpdate();

        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Personne getById(int id) {
        Personne personne = new Personne();
        try {
            String requete = "SELECT * FROM personne WHERE idPersonne = ?";
            PreparedStatement statement = SQLUtils.connection.prepareStatement(requete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            int id1 = result.getInt(1);
            String nom = result.getString(2);
            personne.setIdPersonne(id1);
            personne.setNom(nom);
            personne.setPrenom(result.getString(3));
            personne.setAdresse(result.getString(4));
            personne.setMail(result.getString(5));
            personne.setTelephone(result.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personne;
    }

    @Override
    public List<Personne> getAll() {
        List<Personne> personnes = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Personne";
            PreparedStatement statement = SQLUtils.connection.prepareStatement(requete, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String nom = result.getString(2);
                Personne personne = new Personne();
                personne.setIdPersonne(id);
                personne.setNom(nom);
                personne.setPrenom(result.getString(3));
                personne.setAdresse(result.getString(4));
                personne.setMail(result.getString(5));
                personne.setTelephone(result.getString(6));
                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }
}

