package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import fr.iutfbleau.projetIHM2022FI2.API.Changement;
import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.MNP.EtudiantNP;
import fr.iutfbleau.projetIHM2022FI2.MNP.GroupeNP;

/**
 * Event pour {@link FrameAdmin}
 */ 
public class EventAdmin implements MouseListener , ItemListener, ActionListener {
    private FrameAdmin frame;

    /**
     * permettant d'effacer le texte de la barre de recherche {@link VuePrincipale}
     */
    private static int nbClickVuePrincipale = 0;

    
    /**
     * permettant d'effacer le texte de la barre de recherche  {@link VueGestionDemande}
     */    
    private static int nbClickVueGestionDemande = 0;

    /**
     * permettant d'effacer le texte d'un champ a remplir {@link VueGestionGroupe}
     */
    private static int nbClickGestionGroupeForCreate = 0;

    /**
     * permettant d'effacer le texte d'un champ a remplir {@link VueGestionGroupe}
     */
    private static int nbClickGestionGroupeForCreate2 = 0;

    /**
     * permettant d'effacer le texte d'un champ a remplir {@link VueGestionGroupe}
     */
    private static int nbClickGestionGroupeForCreate3 = 0;

    /**
     * permettant d'effacer le texte d'un champ a remplir {@link VueGestionGroupe}
     */    
    private static int nbClickGestionGroupeForRename = 0;


    /**
     * 
     * @param frame c'est la FrameAdmin
    */

    public EventAdmin(FrameAdmin frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            
        // BOUTON NAVBAR

        // bouton accueil
        if(e.getSource().equals(this.frame.getBtnAccueil())){
            this.frame.getCard().first(this.frame.getContainer());
            this.frame.getVuePrincipale().refresh();
        }

        // bouton Gestion des groupes
        if(e.getSource().equals(this.frame.getBtnGestionGroupe())){
            this.frame.getCard().show(this.frame.getContainer(), "VueGestionGroupe");
        }

        // bouton Gestion des etudiants
        if(e.getSource().equals(this.frame.getBtnGestionEtudiant())){
            this.frame.getCard().show(this.frame.getContainer(), "VueGestionEtudiant");
        }

        // bouton Gestion des demandes
        if(e.getSource().equals(this.frame.getBtnGestionDemande())){
            this.frame.getCard().show(this.frame.getContainer(), "VueGestionDemande");
        }
        
        // bouton quitter
        if(e.getSource().equals(this.frame.getBtnQuitter())){
          
            String[] choix = {"OUI","NON"};

            int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous sur de vouloir quitter ?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/house.png"),choix,null);

            switch(input){
                case 0 : this.frame.dispose();
                default : break;
            }
            
        }

        // BOUTON FIN NAVBAR

        // BOUTON VuePrincipale  

        // bouton refresh VuePrincipale
        if(e.getSource().equals(this.frame.getVuePrincipale().getBtnRefresh())){
            EventAdmin.nbClickVuePrincipale = 0;
            this.frame.getVuePrincipale().refresh();
            this.frame.getVuePrincipale().getBarreRecherche().setText("Veuillez saisir le nom d'etudiant");
        }

        
        // bouton recherche VuePrincipale
        if(e.getSource().equals(this.frame.getVuePrincipale().getBtnRecherche())){

            // si le texte de la barre de recherche est vide
            if(this.frame.getVuePrincipale().getBarreRecherche().getText().equals("")){
                JOptionPane.showMessageDialog(this.frame,"VEUILLEZ ENTREZ DES CARACTÈRES POUR LA RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
            }else{
                String texte = this.frame.getVuePrincipale().getTextOfRecherche();
                String texteMasjucule = texte.toUpperCase();
                String texteMinuscule = texte.toLowerCase();
                
                
                Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion();
    
                LinkedList<String> resultat = new LinkedList<String>();

                // parcours tout les etudiants
                for(Etudiant etu : promo.getEtudiants()){
                    // si le texte mis par l'admin est egal au debut du nom d'un etudiant
                    if(etu.getNom().startsWith(texte) || etu.getNom().startsWith(texteMinuscule) || etu.getNom().startsWith(texteMasjucule)){
                        String nomGroupeEtu = this.frame.getAcf().getGroupeFactory().getGroupesOfEtudiant(etu).iterator().next().getName();
                        resultat.add(nomGroupeEtu);
                        resultat.add(etu.getNom());
                        resultat.add(etu.getPrenom());
                    }
                }
                // si la liste est vide
                if(resultat.isEmpty()){
                    JOptionPane.showMessageDialog(this.frame,"AUCUN ETUDIANT NE CORRESPOND A VOTRE RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
                }
                else{
                    int size = resultat.size();
                    size = size/3;

                    String[] column = {"GROUPE","NOM", "PRENOM"};
                    String[][] data = new String[size][3];
                    // transfert de la liste a un tableau
                    for(int i = 0; i < size; i++){
                        for(int j = 0; j < 3; j++){
                            data[i][j] =  resultat.remove();
                        }
                    }

                    this.frame.getVuePrincipale().getTableau().setModel(new DefaultTableModel(data,column));
                    EventAdmin.nbClickVuePrincipale=0;
                }
            }   
        }

        // FIN BOUTON VuePrincipale


        // BOUTON VueGestionGroupe


        if(e.getSource().equals(this.frame.getVueGestionGroupe().getBtnCreer())){

            // VERIFICATION DES CHAMPS POUR CREER UN GROUPE

            if(this.frame.getVueGestionGroupe().getChampTxtCreer().getText().equals("nouveau groupe") && this.frame.getVueGestionGroupe().getChampTxtMin().getText().equals("taille minimale") && this.frame.getVueGestionGroupe().getChampTxtMax().getText().equals("taille maximale") || this.frame.getVueGestionGroupe().getChampTxtCreer().getText().equals("") && this.frame.getVueGestionGroupe().getChampTxtMin().getText().equals("") && this.frame.getVueGestionGroupe().getChampTxtMax().getText().equals("")){
                this.frame.getVueGestionGroupe().getChampTxtCreer().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtCreer().setText("Veuillez entrer un nom");
                EventAdmin.nbClickGestionGroupeForCreate=0;

                this.frame.getVueGestionGroupe().getChampTxtMin().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtMin().setText("Veuillez entrer un nombre");
                EventAdmin.nbClickGestionGroupeForCreate2=0;

                this.frame.getVueGestionGroupe().getChampTxtMax().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtMax().setText("Veuillez entrer un nombre");
                EventAdmin.nbClickGestionGroupeForCreate3=0;
            }
            else if(this.frame.getVueGestionGroupe().getChampTxtCreer().getText().equals("") || this.frame.getVueGestionGroupe().getChampTxtCreer().getText().equals("nouveau groupe") || this.frame.getVueGestionGroupe().getChampTxtCreer().getText().equals("Veuillez entrer un nom")){
                this.frame.getVueGestionGroupe().getChampTxtCreer().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtCreer().setText("Veuillez entrer un nom");
                EventAdmin.nbClickGestionGroupeForCreate=0;
            }
            else if(this.frame.getVueGestionGroupe().getChampTxtMin().getText().equals("") || this.frame.getVueGestionGroupe().getChampTxtMin().getText().equals("taille minimale") || this.frame.getVueGestionGroupe().getChampTxtMin().getText().equals("Veuillez entrer un nombre")){
                this.frame.getVueGestionGroupe().getChampTxtMin().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtMin().setText("Veuillez entrer un nombre");
                EventAdmin.nbClickGestionGroupeForCreate2=0;
            }
            else if(this.frame.getVueGestionGroupe().getChampTxtMax().getText().equals("") || this.frame.getVueGestionGroupe().getChampTxtMax().getText().equals("taille maximale") || this.frame.getVueGestionGroupe().getChampTxtMax().getText().equals("Veuillez entrer un nombre")){
                this.frame.getVueGestionGroupe().getChampTxtMax().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtMax().setText("Veuillez entrer un nombre");
                EventAdmin.nbClickGestionGroupeForCreate3=0;
            }
            // FIN VERIFICATION DES CHAMPS POUR CREER UN GROUPE
            else{
                try{
                    int min = Integer.parseInt(this.frame.getVueGestionGroupe().getChampTxtMin().getText());
                    try{
                        int max = Integer.parseInt(this.frame.getVueGestionGroupe().getChampTxtMax().getText());

                        // Gestion des erreurs
                        if(min > max){
                            JOptionPane.showMessageDialog(this.frame, "Veuillez mettre une taille minimale inferieur a la taille maximale",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png")); 
                            this.frame.getVueGestionGroupe().getChampTxtMin().setText("");
                        }
                        else if(min == max){
                            JOptionPane.showMessageDialog(this.frame, "Veuillez mettre une taille minimale et maximale differente",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
                            this.frame.getVueGestionGroupe().getChampTxtMin().setText("");
                            this.frame.getVueGestionGroupe().getChampTxtMax().setText("");
                        }
                        else if(min <= 0){
                            JOptionPane.showMessageDialog(this.frame, "Veuillez mettre une taille minimale supérieur a 0",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
                            this.frame.getVueGestionGroupe().getChampTxtMin().setText("");
                        }
                        else if(max <= 0){
                            JOptionPane.showMessageDialog(this.frame, "Veuillez mettre une taille maximale supérieur a 0",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
                            this.frame.getVueGestionGroupe().getChampTxtMax().setText("");
                        }
                        else{

                            // Creation du groupe

                            String nomGroupeCreer = this.frame.getVueGestionGroupe().getChampTxtCreer().getText();
                            
                            Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

                            promo.addSousGroupe(new GroupeNP(promo, nomGroupeCreer, min, max));
                            JOptionPane.showMessageDialog(this.frame,"Le groupe "+nomGroupeCreer+" a bien été crée.",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/checked.png"));
                            
                            this.frame.getVueGestionGroupe().getChampTxtCreer().setText("nouveau groupe");
                            this.frame.getVueGestionGroupe().getChampTxtMin().setText("taille minimale");
                            this.frame.getVueGestionGroupe().getChampTxtMax().setText("taille maximale");
                            this.frame.getVueGestionGroupe().refreshGroupe();
                            this.frame.getVueGestionEtudiant().refreshAll();

                            EventAdmin.nbClickGestionGroupeForCreate=0;
                            EventAdmin.nbClickGestionGroupeForCreate2=0;
                            EventAdmin.nbClickGestionGroupeForCreate3=0;
                        
                        }
                    }
                    // on verifie dans les deux catch si l'admin n'a pas mis un caractères au lieu d'un chiffre
                    catch(NumberFormatException e1){
                        this.frame.getVueGestionGroupe().getChampTxtMax().setForeground(Color.red);
                        this.frame.getVueGestionGroupe().getChampTxtMax().setText("Veuillez entrer un nombre");
                        EventAdmin.nbClickGestionGroupeForCreate3=0;
                    }
                }   
                catch(NumberFormatException e1){
                    this.frame.getVueGestionGroupe().getChampTxtMin().setForeground(Color.red);
                    this.frame.getVueGestionGroupe().getChampTxtMin().setText("Veuillez entrer un nombre");
                    EventAdmin.nbClickGestionGroupeForCreate2=0;
                    }
                }
            }
        
        
        // CLIQUE SUR BTN SUPPRIMER GROUPE
        if(e.getSource().equals(this.frame.getVueGestionGroupe().getBtnSupprimer())){

            if(this.frame.getVueGestionGroupe().getNomGroupeSupprimer() != null){

                JLabel groupeSupprimer = new JLabel(this.frame.getVueGestionGroupe().getNomGroupeSupprimer());
                groupeSupprimer.setForeground(Color.red);

                Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

                String[] choix = {"OUI","NON"};
                
                int input = JOptionPane.showOptionDialog(this.frame, "etes-vous sur de vouloir supprimer ce groupe : "+groupeSupprimer.getText()+" ?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/supprimer.png"),choix,null);
                
                switch(input){
                    case 0 :{

                        //parcours les sous groupes

                        for(Groupe g : promo.getSousGroupes()){

                            // Si le nom du groupe est egal au groupe selectionne

                            if(g.getName().equals(groupeSupprimer.getText())){

                                // parcours les sous groupes

                                for(Groupe g1 : promo.getSousGroupes()){

                                    // Si le groupe est different du groupe selectionne

                                    if(!g1.equals(g)){

                                        for(Etudiant e1 : g.getEtudiants()){
                                            
                                            // ne voulait pas ajouter l'etudiant marcher avec e1 :(
                                            // g1.addEtudiant(e1);
                                            g1.addEtudiant(new EtudiantNP(e1.getNom(), e1.getPrenom()));
                                        }
                                        break;
                                    }
                                }
                                promo.removeSousGroupe(g);
                                break;
                            }
                        }  
                    
                        JOptionPane.showMessageDialog(this.frame,"Le groupe "+groupeSupprimer.getText()+" a bien ete supprimer",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/checked.png"));
                        this.frame.getVueGestionGroupe().refreshGroupe();
                        this.frame.getVueGestionEtudiant().refreshAll();
                    }  
                    default : break;
                }
            }
            else{
                JOptionPane.showMessageDialog(this.frame,"Veuillez choisir un groupe ",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
            }
        }

        // CLIQUE SUR LE BOUTON RENOMMER GROUPE

        if(e.getSource().equals(this.frame.getVueGestionGroupe().getBtnRenommer())){

            if(this.frame.getVueGestionGroupe().getChampTxtRenommer().getText().equals("") || this.frame.getVueGestionGroupe().getChampTxtRenommer().getText().equals("nouveau nom du groupe") || this.frame.getVueGestionGroupe().getChampTxtRenommer().getText().equals("Veuillez entrer un nom")){
                this.frame.getVueGestionGroupe().getChampTxtRenommer().setForeground(Color.red);
                this.frame.getVueGestionGroupe().getChampTxtRenommer().setText("Veuillez entrer un nom");
                EventAdmin.nbClickGestionGroupeForRename=0;
            }
            else if(this.frame.getVueGestionGroupe().getNomGroupeRenommer() != null){
                String groupeRenommer = this.frame.getVueGestionGroupe().getNomGroupeRenommer();
                String nouveauNomGroupe = this.frame.getVueGestionGroupe().getChampTxtRenommer().getText();
                
                Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

                for(Groupe g : promo.getSousGroupes()){
                    if(groupeRenommer.equals(g.getName())){
                        
                        String[] choix = {"OUI","NON"};
                        int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir renommer le : "+groupeRenommer+" EN : "+nouveauNomGroupe, null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/rename.png"),choix,null);
                
                        switch(input){
                            case 0 :{
                                g.setName(nouveauNomGroupe);
                                JOptionPane.showMessageDialog(this.frame,"Le groupe "+groupeRenommer+" a bien ete renommer : "+nouveauNomGroupe,null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/checked.png"));
                                this.frame.getVueGestionGroupe().refreshGroupe();
                                this.frame.getVueGestionEtudiant().refreshAll();
                                this.frame.getVueGestionGroupe().getChampTxtRenommer().setText("nouveau nom du groupe");
                                EventAdmin.nbClickGestionGroupeForRename=0;
                                break;
                            }
                            default :   this.frame.getVueGestionGroupe().getChampTxtRenommer().setText("nouveau nom du groupe");
                                        EventAdmin.nbClickGestionGroupeForRename=0;
                                        break;
                        }
                        break;
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this.frame,"Veuillez choisir un groupe ",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
            }
            
        }

        // FIN BOUTON RENOMMER

        // FIN BOUTON VueGestionGroupe


        // BOUTON VueGestionEtudiant 

        if(e.getSource().equals(this.frame.getVueGestionEtudiant().getBtnAjoutEtu())){
            if(this.frame.getVueGestionEtudiant().getNomEtudiantAjout().equals("Etudiant") || this.frame.getVueGestionEtudiant().getNomGroupDest2().equals("Groupe") ){
                JOptionPane.showMessageDialog(this.frame,"Veuillez remplir l'integralite de vos coordonnées ",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
            }
        }

        if(e.getSource().equals(this.frame.getVueGestionEtudiant().getBtnDeplacer())){
            if(this.frame.getVueGestionEtudiant().getNomEtudiantDeplacer() != null && this.frame.getVueGestionEtudiant().getNomGroupDest2() != null){
                    
                String nomPrenomEtu = this.frame.getVueGestionEtudiant().getNomEtudiantDeplacer();
                String nomGroupDest2 = this.frame.getVueGestionEtudiant().getNomGroupDest2();
                Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
                
                Groupe groupDest = null;
                
                for(Groupe g : promo.getSousGroupes()){
                    if(g.getName().equals(nomGroupDest2)){
                        groupDest = g;
                    }
                }

                if(groupDest == null){
                    
                }
                else{
                    for(Etudiant etu : promo.getEtudiants()){
                        String nomPrenomTmp = etu.getNom()+" "+etu.getPrenom();
                        if(nomPrenomTmp.equals(nomPrenomEtu)){
                            Groupe groupeOfEtu = this.frame.getAcf().getGroupeFactory().getGroupesOfEtudiant(etu).iterator().next();
                            if(groupeOfEtu.equals(groupDest)){
                                JOptionPane.showMessageDialog(this.frame,"l'etudiant que vous avez selectionner est deja dans ce groupe",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
                            }
                            else{
                                String[] choix = {"OUI","NON"};
                                int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir de deplacer l'eleve :"+nomPrenomTmp+" : du groupe : "+groupeOfEtu.getName()+" au groupe : "+groupDest.getName(), null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/rename.png"),choix,null);
                
                                switch(input){
                                    case 0 :{
                                        groupDest.addEtudiant(etu);
                                        groupeOfEtu.removeEtudiant(etu);
                                        JOptionPane.showMessageDialog(this.frame,"L'etudiant "+nomPrenomTmp+" a ete deplace du groupe : "+groupeOfEtu.getName()+" au groupe : "+groupDest.getName() ,null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/check.png"));
                                        this.frame.getVueGestionEtudiant().refreshAll();
                                        this.frame.getVueGestionGroupe().refreshGroupe();
                                        break;
                                    }
                                    default : break;
                                }
                            }
                        }
                    }    
                }
        
            }
            else{
            JOptionPane.showMessageDialog(this.frame,"Veuillez remplir l'integralité de vos coordonnées ",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
            }          
        }
        //Fin BOUTON VueGestionEtudiant

        //BOUTON Vue Gestion Demande
        if (e.getSource().equals(this.frame.getVueGestionDemande().getValidate())) {

            if (this.frame.getVueGestionDemande().getGroupeInitial() != null && this.frame.getVueGestionDemande().getEtudiant() != null && this.frame.getVueGestionDemande().getGroupeDest() != null) {

                for (Changement c : this.frame.getAcf().getAllChangements()) {
                    
                    if (c.getA().equals(this.frame.getVueGestionDemande().getGroupeInitial()) && c.getEtu().equals(this.frame.getVueGestionDemande().getEtudiant()) && c.getB().equals(this.frame.getVueGestionDemande().getGroupeDest())) {
                        String[] choix = {"OUI","NON"};
                        int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir accepter cette demande?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/supprimer.png"),choix,null);
                        switch(input){
                            case 0 : 
                                this.frame.getAcf().applyChangement(c);
                                this.frame.getAcf().deleteChangement(c);
                                this.frame.getVueGestionDemande().refresh();
                                this.frame.getVuePrincipale().refresh();;
                            default : break;
                        }
                        
                        
                    }
                        
                        
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Veuillez sélectionner une demande à traiter.");
            }
            
            
        }
        // Bouton refresh vueGestionDemande
        if (e.getSource().equals(this.frame.getVueGestionDemande().getBtnRefresh())) {
            this.frame.getVueGestionDemande().refresh();
        }
        // Bouton refuser une demande
        if (e.getSource().equals(this.frame.getVueGestionDemande().getRefuse())) {
            // si l'administrateur a double clique sur un des changements dans la table 
            if (this.frame.getVueGestionDemande().getGroupeInitial() != null && this.frame.getVueGestionDemande().getEtudiant() != null && this.frame.getVueGestionDemande().getGroupeDest() != null) {
                // parcours tout les changements
                for (Changement c : this.frame.getAcf().getAllChangements()) {
                    // trouve le changements que l'administrateur a clique
                    if (c.getA().equals(this.frame.getVueGestionDemande().getGroupeInitial()) && c.getEtu().equals(this.frame.getVueGestionDemande().getEtudiant()) && c.getB().equals(this.frame.getVueGestionDemande().getGroupeDest())) {
                        String[] choix = {"OUI","NON"};
                        int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir refuser cette demande?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/supprimer.png"),choix,null);
                        switch(input){
                            case 0 : 
                                this.frame.getAcf().deleteChangement(c);
                                this.frame.getVueGestionDemande().refresh();
                            default : break;
                        }
                        
                        
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Veuillez sélectionner une demande à traiter.");
            }
            
        }

        if(e.getSource().equals(this.frame.getVueGestionDemande().getBtnRecherche())){
            if(this.frame.getVueGestionDemande().getBarreRecherche().getText().equals("") || this.frame.getVueGestionDemande().getBarreRecherche().getText().equals("Veuillez saisir le nom d'etudiant")){
                JOptionPane.showMessageDialog(this.frame,"VEUILLEZ ENTREZ DES CARACTÈRES POUR LA RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
            }else{
                String texte = this.frame.getVueGestionDemande().getTextOfRecherche();
                String texteMasjucule = texte.toUpperCase();
                String texteMinuscule = texte.toLowerCase();
                
    
                LinkedList<String> resultat = new LinkedList<String>();
                
                for(Changement c : this.frame.getAcf().getAllChangements()){
                    if(c.getEtu().getNom().startsWith(texte) || c.getEtu().getNom().startsWith(texteMinuscule) || c.getEtu().getNom().startsWith(texteMasjucule)){
                        resultat.add(c.getA().getName());
                        resultat.add(c.getEtu().getNom());
                        resultat.add(c.getEtu().getPrenom());
                        resultat.add(c.getB().getName());
                    }
                }

                if(resultat.isEmpty()){
                    JOptionPane.showMessageDialog(this.frame,"AUCUN ETUDIANT NE CORRESPOND A VOTRE RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
                }
                else{
                    int size = resultat.size();
                    size = size/4;

                    String[] column = {"GROUPE INITIAL","NOM", "PRENOM", "GROUPE DESTINATION"};
                    String[][] data = new String[size][4];

                    for(int i = 0; i < size; i++){
                        for(int j = 0; j < 4; j++){
                            data[i][j] =  resultat.remove();
                        }
                    }

                    this.frame.getVueGestionDemande().getTableau().setModel(new DefaultTableModel(data,column));
                    EventAdmin.nbClickVueGestionDemande=0;
                }
            }   
        }
        
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {

        // SUPPRESSION DU TEXTE DES CHAMPS DE TEXTE LORSQUE L'ON CLIQUE 

        if(e.getComponent().equals(this.frame.getVuePrincipale().getBarreRecherche())){
            EventAdmin.nbClickVuePrincipale++;
            if(EventAdmin.nbClickVuePrincipale == 1){
                this.frame.getVuePrincipale().getBarreRecherche().setText("");
            }
        }

        if(e.getComponent().equals(this.frame.getVueGestionDemande().getBarreRecherche())){
            EventAdmin.nbClickVueGestionDemande++;
            if(EventAdmin.nbClickVueGestionDemande == 1){
                this.frame.getVueGestionDemande().getBarreRecherche().setText("");
            }
        }



        if(e.getComponent().equals(this.frame.getVueGestionGroupe().getChampTxtCreer())){
            EventAdmin.nbClickGestionGroupeForCreate++;
            if(EventAdmin.nbClickGestionGroupeForCreate == 1){
                this.frame.getVueGestionGroupe().getChampTxtCreer().setText("");
                this.frame.getVueGestionGroupe().getChampTxtCreer().setForeground(Color.black);
            }
        }

        if(e.getComponent().equals(this.frame.getVueGestionGroupe().getChampTxtMin())){
            EventAdmin.nbClickGestionGroupeForCreate2++;
            if(EventAdmin.nbClickGestionGroupeForCreate2 == 1){
                this.frame.getVueGestionGroupe().getChampTxtMin().setText("");
                this.frame.getVueGestionGroupe().getChampTxtMin().setForeground(Color.black);
            }
        }

        if(e.getComponent().equals(this.frame.getVueGestionGroupe().getChampTxtMax())){
            EventAdmin.nbClickGestionGroupeForCreate3++;
            if(EventAdmin.nbClickGestionGroupeForCreate3 == 1){
                this.frame.getVueGestionGroupe().getChampTxtMax().setText("");
                this.frame.getVueGestionGroupe().getChampTxtMax().setForeground(Color.black);
            }
        }

        if(e.getComponent().equals(this.frame.getVueGestionGroupe().getChampTxtRenommer())){
            EventAdmin.nbClickGestionGroupeForRename++;
            if(EventAdmin.nbClickGestionGroupeForRename == 1){
                this.frame.getVueGestionGroupe().getChampTxtRenommer().setText("");
                this.frame.getVueGestionGroupe().getChampTxtRenommer().setForeground(Color.black);
            }
        }

        //  TABLEAU VueGestionDemande

        if (e.getComponent().equals(this.frame.getVueGestionDemande().getTableau())) {
            //Récupere la ligne selectionnée
            int row = this.frame.getVueGestionDemande().getTableau().getSelectedRow();
        
            //Récuperer le nom du groupe initial
            String grpInitialName = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 0));
            Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
            Groupe grpInitialGroupe;
            
            for (Groupe g : promo.getSousGroupes()) {
                if (g.getName().equals(grpInitialName)) {
                    grpInitialGroupe = g;
                    break;
                }
            }
        
        
            //Récuperer le nom de l'étudiant 
            String etuNom = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 1));
            
            //Récuperer le prénom de l'étudiant 
            String etuPrenom = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 2));
            
            //Récuperer le nom et prenom de l'étudiant 
            String etuFullName = etuNom + " " + etuPrenom;
        
            //Récuperer le nom et le prénom de l'étudiant 
            //Récuperer le nom et le prénom de l'étudiant
            String fullName;
            Etudiant etudiant;

            for (Etudiant etu : promo.getEtudiants()) {
                fullName = etu.getNom() + " " + etu.getPrenom();
                if (fullName.equals(etuFullName)) {
                    etudiant = etu;
                }
            }
        
            //récuperer le nom du groupe destination
            Groupe grpDestGroupe;
            String grpDestName = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 3));

            for (Groupe g : promo.getSousGroupes()) {
                if (g.getName().equals(grpDestName)) {
                    grpDestGroupe = g;
                    break;
                }
            }
        
        }

         //  Fin TABLEAU VueGestionDemande

         if (e.getSource().equals(this.frame.getVueGestionDemande().getValidate())) {

            if (this.frame.getVueGestionDemande().getGroupeInitial() != null && this.frame.getVueGestionDemande().getEtudiant() != null && this.frame.getVueGestionDemande().getGroupeDest() != null) {

                for (Changement c : this.frame.getAcf().getAllChangements()) {
                    
                    if (c.getA().equals(this.frame.getVueGestionDemande().getGroupeInitial()) && c.getEtu().equals(this.frame.getVueGestionDemande().getEtudiant()) && c.getB().equals(this.frame.getVueGestionDemande().getGroupeDest())) {
                        String[] choix = {"OUI","NON"};
                        int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir accepter cette demande?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/supprimer.png"),choix,null);
                        switch(input){
                            case 0 : 
                                this.frame.getAcf().applyChangement(c);
                                this.frame.getAcf().deleteChangement(c);
                                this.frame.getVueGestionDemande().refresh();
                                this.frame.getVuePrincipale().refresh();;
                            default : break;
                        }
                        
                        
                    }
                        
                        
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Veuillez sélectionner une demande à traiter.");
            }
            
            
        }
        if (e.getSource().equals(this.frame.getVueGestionDemande().getBtnRefresh())) {
            this.frame.getVueGestionDemande().refresh();
        }
        if (e.getSource().equals(this.frame.getVueGestionDemande().getRefuse())) {
            if (this.frame.getVueGestionDemande().getGroupeInitial() != null && this.frame.getVueGestionDemande().getEtudiant() != null && this.frame.getVueGestionDemande().getGroupeDest() != null) {

                for (Changement c : this.frame.getAcf().getAllChangements()) {
                    
                    if (c.getA().equals(this.frame.getVueGestionDemande().getGroupeInitial()) && c.getEtu().equals(this.frame.getVueGestionDemande().getEtudiant()) && c.getB().equals(this.frame.getVueGestionDemande().getGroupeDest())) {
                        String[] choix = {"OUI","NON"};
                        int input = JOptionPane.showOptionDialog(this.frame, "Etes-vous de vouloir refuser cette demande?", null,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon("res/img/supprimer.png"),choix,null);
                        switch(input){
                            case 0 : 
                                this.frame.getAcf().deleteChangement(c);
                                this.frame.getVueGestionDemande().refresh();
                            default : break;
                        }
                        
                        
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Veuillez sélectionner une demande à traiter.");
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent().equals(this.frame.getVueGestionDemande().getTableau())) {
            //Récupere la ligne selectionnée
            int row = this.frame.getVueGestionDemande().getTableau().getSelectedRow();

            //Récuperer le nom du groupe initial
            String grpInitialName = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 0));
            Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
            
            for (Groupe g : promo.getSousGroupes()) {
                if (g.getName().equals(grpInitialName)) {
                    this.frame.getVueGestionDemande().setGroupeInitial(g);
                    break;
                }
            }

            //Récuperer le nom et le prénom de l'étudiant selectionné
                //Récuperer le nom de l'étudiant 
            String etuNom = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 1));
                //Récuperer le prénom de l'étudiant 
            String etuPrenom = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 2));
                //Récuperer le nom complet de l'étudiant 
            String etuFullName = etuNom + " " + etuPrenom;

            //Récuperer le nom et le prénom de l'étudiant 
                //Récuperer le nom et le prénom de l'étudiant
                String fullName;
                for (Etudiant etu : promo.getEtudiants()) {
                    fullName = etu.getNom() + " " + etu.getPrenom();
                    if (fullName.equals(etuFullName)) {
                        this.frame.getVueGestionDemande().setEtudiant(etu);
                    }
                }

            //récuperer le nom du groupe destination
            String grpDestName = String.valueOf(this.frame.getVueGestionDemande().getTableau().getModel().getValueAt(row, 3));
            for (Groupe g : promo.getSousGroupes()) {
                if (g.getName().equals(grpDestName)) {
                    this.frame.getVueGestionDemande().setGroupeDest(g);
                    break;
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



    @Override
    public void itemStateChanged(ItemEvent e) {



        if(e.getItem().equals("Groupe")){
            this.frame.getVueGestionGroupe().setNomGroupeSupprimer( null);
            this.frame.getVueGestionGroupe().setNomGroupeRenommer( null);
            this.frame.getVueGestionEtudiant().setNomGroupDest(null);
            this.frame.getVueGestionEtudiant().setNomGroupDest2(null);
        }

        Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
        // parcours les groupes
        for(Groupe g : promo.getSousGroupes()){
            if(e.getItem().equals(g.getName())){
                this.frame.getVueGestionGroupe().setNomGroupeSupprimer((String)e.getItem());
                this.frame.getVueGestionGroupe().setNomGroupeRenommer((String)e.getItem());
                this.frame.getVueGestionEtudiant().setNomGroupDest((String)e.getItem());
                this.frame.getVueGestionEtudiant().setNomGroupDest2((String)e.getItem());
                break;
            }
        }
            



        // parcours les etudiants 
        for(Etudiant etu : promo.getEtudiants()){
            if(e.getItem().equals(etu.getNom()+" "+etu.getPrenom())){
                this.frame.getVueGestionEtudiant().setNomEtudiantAjout((String)e.getItem());
                this.frame.getVueGestionEtudiant().setNomEtudiantDeplacer((String)e.getItem());
                break;
            }
        }
        
    }

}

