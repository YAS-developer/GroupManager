package fr.iutfbleau.projetIHM2022FI2.MP;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.BDD.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AbstractChangementFactoryP implements AbstractChangementFactory {

    private AbstractGroupeFactory agf;
    private Connexion connexion;
    private HashMap<Integer,Changement> brainGroupe;
    private ResultSet rS ;
    private int rU;
    private AbstractChangementFactory acf;


    

    //Constructeur qui permet de prendre un abstractGroupeFactory non persistant
    public AbstractChangementFactoryP(AbstractChangementFactory acf){

        this.acf = acf;
        // Objects.requireNonNull(agf,"On ne peut pas créer une usine à changement dont l'usine à groupe parternaire est null");
        // this.brainGroupe = new HashMap<Integer,Changement>();   

        
        
    }

    public AbstractChangementFactoryP() {
        try {
            
            PreparedStatement ptsmt = this.connexion.getPrepare();
            ptsmt = this.connexion.getConnection().prepareStatement("SELECT id FROM CHANGEMENT");
            rS = ptsmt.executeQuery();

            while(rS.next()){
                // brainGroupe.put()
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
        }
    }

    /**
     * permet de récupérer l'usine abstraite pour les groupes qui fonctionne en tandem avec cette usine abstraite
     * @return cette usine abstraite pour les groupes
     */
    public AbstractGroupeFactory getGroupeFactory(){
        return this.agf;
    }
    
    /**
     * permet de récupérer les changements 
     * @return l'ensemble de tous les changements en attente
     */
    public Set<Changement> getAllChangements(){
        
        throw new IllegalArgumentException("a faire");
    }

    /**
     * permet de mettre en oeuvre un changement connu de l'usine abstraite.
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalStateException si le changement n'a pas de sens en l'état actuel (e.g. étudiant pas dans le groupe de départ a, groupe b inconnu, groupe a inconnu, etc).
     * @throws java.lang.IllegalArgumentException si inconnu de l'usine abstraite
     */
    public void applyChangement(Changement c){
        
    }


    /**
     * permet de supprimer un changement connu de l'usine abstraite.
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si inconnu de l'usine abstraite
     */
    public void deleteChangement(Changement c){
        
    }

    /**
     * permet d'ajouter un nouveau changement.
     *
     * @param A groupe actuel
     * @param B groupe demandé
     * @param e étudiant concerné par le changement
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si les groupes ou l'étudiant ne sont pas connus de la factory partenaire, ou e n'appartient pas à A ou A et B ne sont pas frères dans l'arbre des groupes.
     *        
     */
    
     public void createChangement(Groupe A, Etudiant e, Groupe B){
        
        Objects.requireNonNull(A,"Le groupe d'origine ne peut pas être null");
        Objects.requireNonNull(B,"Le groupe d'arrivée ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");
        
        ChangementP changP =  new ChangementP(e.getId(),A.getId(),B.getId());        
    }



    public AbstractChangementFactory getAcfofAcf(){
        return this.acf;
    }
}
