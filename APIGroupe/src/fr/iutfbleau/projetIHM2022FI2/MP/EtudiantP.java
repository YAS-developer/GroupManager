package fr.iutfbleau.projetIHM2022FI2.MP;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.BDD.Connexion;

// import fr.iutfbleau.projetIHM2022FI2.;;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class EtudiantP implements Etudiant{


    private Connexion connexion;

    private int id;
    private String nom, prenom;


    /**
     * Constructeur.
     */
    public EtudiantP(String nom, String prenom){
        Objects.requireNonNull(nom,"On ne peut pas créer un étudiant avec un nom null");
        Objects.requireNonNull(prenom,"On ne peut pas créer un étudiant avec un nom null");
        
        this.prenom = prenom;
        this.nom = nom;
        
        try {
            
            this.connexion = Connexion.getInstance();

            PreparedStatement ps = this.connexion.getPrepare();
            ps = this.connexion.getConnection().prepareStatement("INSERT INTO ETUDIANT (NOM, PRENOM) VALUES(?,?)");
            
            ps.setString(1, nom);
            ps.setString(2, prenom);

            ps.executeQuery();

            System.out.println("Etudiant inseré");

            this.connexion.getConnection().close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
    

    public EtudiantP(Etudiant e1){

        try {
            this.connexion = Connexion.getInstance();
            PreparedStatement ps = this.connexion.getPrepare();
            ps = this.connexion.getConnection().prepareStatement("INSERT INTO ETUDIANT (ID_ETUDIANT, NOM, PRENOM) VALUES(?,?,?)");

            ps.setInt(1, e1.getId());
            ps.setString(2, e1.getNom());
            ps.setString(3, e1.getPrenom());

            ps.executeQuery();

            System.out.println("Eleve inseré");

            this.connexion.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * permet de récupérer l'identifiant de l'étudiant.
     * @return l'identifiant.
     */
    public int getId(){
        // try {
        //     this.connexion = new Connexion();
        //     PreparedStatement ps = this.connexion.getPrepare();
        //     ps = this.connexion.getConnection().prepareStatement("INSERT INTO ETUDIANT (ID_ETUDIANT, NOM, PRENOM) VALUES(?,?,?)");

        //     // ps.setInt(1, e1.getId());
        //     // ps.setString(2, e1.getNom());
        //     // ps.setString(3, e1.getPrenom());

        //     ps.executeQuery();

        //     System.out.println("Eleve inseré");

        //     this.connexion.getConnection().close();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        return this.id;
    }

    /**
     * permet de récupérer 
     * @return le nom de l'étudiant.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * permet de récupérer
     * @return le prénom de l'étudiant
     */
    public String getPrenom(){
        return this.prenom;
    }


}
