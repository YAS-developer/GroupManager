package fr.iutfbleau.projetIHM2022FI2.MP;
import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.BDD.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 * Une demande de changement de groupe
 * concerne un étudiant, qui est dans un groupe A et veut aller dans un groupe B.
 * 
 * Implémentation non persistante fournie avec l'API.
 */

public class ChangementP implements Changement {

    //auto-incrément des changements
    private static int nextId=0;

    private int id;
    private Groupe a,b;
    private Etudiant etu;
    private Connexion cnx;
    private ResultSet rSet;

    public ChangementP(int id_etu, int id_gp_depart, int id_gp_arrive){
        Objects.requireNonNull(a,"On ne peut pas créer un changement avec un groupe à quitter null");
        Objects.requireNonNull(b,"On ne peut pas créer un changement avec un groupe à rejoindre null");
        Objects.requireNonNull(etu,"On ne peut pas créer un changement concernant un étudiant null");

        // this.id=++this.nextId;
        this.a=a;
        this.b=b;
        this.etu=etu;

        try {
            this.cnx = Connexion.getInstance();
            PreparedStatement ps = this.cnx.getPrepare();
            ps = this.cnx.getConnection().prepareStatement("INSERT INTO changement(ID_ETUDIANT, ID1_GROUPE_DEPART, ID2_GROUPE_ARRIVE, STATUS) VALUES(?,?,?,?)");
            
            ps.setInt(1,id_etu);                  
            ps.setInt(2, id_gp_depart);
            ps.setInt(3, id_gp_arrive);
            ps.setString(4, "En attente");
            ps.executeUpdate();
            this.cnx.getConnection().close();
            this.cnx.getPrepare().close();
            ps.close();
            
            System.out.println("changement crée.");
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    
    /**
     * permet de récupérer l'identifiant du changement (référence interne sans intérêt irl).
     * @return l'identifiant.
     */
    public int getId(){
        // try {
        //     this.cnx = new Connexion();
        //     PreparedStatement ps = this.cnx.getPrepare();
        //     ps = this.cnx.getConnection().prepareStatement("SELECT ID FROM changement WHERE ETUDIANT=? AND ID1_GROUPE_DEPART=? AND ID2_GROUPE_DEPART=?");
        //     ps.setString(1,this.etu.getNom()+" "+this.etu.getPrenom());
        //     ps.setString(2, this.a.getName());
        //     ps.setString(3, this.b.getName());
        //     ps.setInt(4, 0);
            
        //     rSet = ps.executeQuery();
            
        //     this.id = rSet.getInt(1);

        //     System.out.println("changement crée.");
        // } catch (Exception e) {
        //     System.out.println(e.getCause());
        // }
        
        return this.id;
    }

    /**
     * permet de récupérer le groupe de depart
     * @return ce groupe.
     */
    public Groupe getA(){
        return this.a;
    }

    /**
     * permet de récupérer le groupe d'arrivée
     * @return ce groupe.
     */
    public Groupe getB(){
        return this.b;
    }

    /**
     * permet de récupérer l'étudiant demandant le changement
     * @return cet étudiant
     */
    public Etudiant getEtu(){
        return this.etu;
    }

}
