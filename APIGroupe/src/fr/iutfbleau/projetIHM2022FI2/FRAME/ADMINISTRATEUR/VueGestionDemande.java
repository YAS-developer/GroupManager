package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;
import fr.iutfbleau.projetIHM2022FI2.API.*;


import java.util.EventObject;
import java.util.LinkedList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagConstraints;


import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

/**
 *  Vue pour la gestion des changements
 */ 

public class VueGestionDemande extends JPanel{

    /**
     * Tableau pour afficher les changements
     */ 
    private JTable tableau;

    /**
     * {@link FrameAdmin} 
     */ 
    private FrameAdmin frame;

    /**
     * Bouton pour actualiser le tableau
     */ 
    private JButton btnRefresh;

    /**
     * bouton pour effectuer une recherche
     */
    private JButton btnRecherche;

    /**
     * Bouton pour accepter un changement
     */
    private JButton btnValidate;

    /**
     * Bouton pour refuser un changement
     */
    private JButton btnRefuse;


    /** 
     * groupe de l'etudiant
    */
    Groupe grpInitialGroupe;

    
    /** 
     * l'etudiant
    */
    Etudiant etudiant;

    /**
     * groupe ou souhaite aller l'etudiant 
     */
    Groupe grpDestGroupe;

    /**
     *  barre de recherche
     */
    private JTextField barreRecherche;
    
   
    /**
     * @param frame FrameAdmin
     */
    public VueGestionDemande (FrameAdmin frame){
        
        this.frame = frame;
        this.setBackground(this.frame.getColor2());
        this.btnRefresh = new JButton();
        this.tableau = new JTable();
        
        // permet de ne pas modifier le tableau 
        this.tableau = new JTable(){
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        this.tableau.setFont(this.frame.getTaillePolice());
        
        this.refresh();
        this.btnRefresh = new JButton(new ImageIcon("res/img/refresh.png"));
        this.barreRecherche = new JTextField("Veuillez saisir le nom d'etudiant");
        this.barreRecherche.setFont(this.frame.getTaillePolice());
        this.btnRecherche = new JButton(new ImageIcon("res/img/loupe.png"));
        this.btnValidate = new JButton("Valider");
        this.btnValidate.setFont(this.frame.getTaillePolice());
        this.btnValidate.setBackground(Color.GREEN);
        this.btnRefuse = new JButton("Refuser");
        this.btnRefuse.setFont(this.frame.getTaillePolice());
        this.btnRefuse.setBackground(Color.RED);
        this.btnRefuse.setForeground(Color.white);

        //Ajouter des Action/Mouse Listener aux éléments
        this.btnRefresh.addActionListener(new EventAdmin(frame));
        this.barreRecherche.addMouseListener(new EventAdmin(frame));
        this.btnRecherche.addActionListener(new EventAdmin(frame));
        this.btnValidate.addActionListener(new EventAdmin(frame));
        this.btnRefuse.addActionListener(new EventAdmin(frame));
        this.tableau.addMouseListener(new EventAdmin(frame));

        
        //Ajouter un JScrollPane au tableau
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(this.tableau);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //AJouter le bouton refresh à la fenètre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 20, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnRefresh, gbc);

        //Ajouter la barre de recherche à la fenètre
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.ipady = 15;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.barreRecherche, gbc);

        //Ajouter le bouton recherche à la fenètre
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.btnRecherche, gbc);

        //Ajouter le tableau à la fenètre
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 326;
        gbc.ipady = 186;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(scroll, gbc);

        //Ajouter le bouton valider à la fenètre
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 100;
        gbc.ipady = 40;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets= new Insets(10, 0,0,0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnValidate, gbc);

        //Ajouter le bouton refuser à la fenètre
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 100;
        gbc.ipady = 40;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets= new Insets(10, 0,0,0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnRefuse, gbc);
    }   



    /**
     * Mettre à jour les données du tableau de changements
     */
    public void refresh(){

        //Récupère tous les changements
        Iterator<Changement> itrCh = this.frame.getAcf().getAllChangements().iterator();

        Changement c;
        LinkedList<String> resultat = new LinkedList<String>();

        while(itrCh.hasNext()){
            c = itrCh.next();
            
            //Ajouter le nom du groupe initial
            resultat.add(c.getA().getName());

            //Ajouter le nom de l'étudiant
            resultat.add(c.getEtu().getNom());

            //Ajouter le prénom de l'étudiant
            resultat.add(c.getEtu().getPrenom());

            //Ajouter le nom du groupe destination
            resultat.add(c.getB().getName());

        }

        int size = resultat.size();
            size = size/4;

        String[] column = {"GROUPE INITIAL","NOM", "PRENOM", "GROUPE DESTINATION"};
        String[][] data = new String[size][4];

        //Rajouter les résultats au tableau
        for(int i = 0; i < size; i++){
            for(int j = 0; j < 4; j++){
                data[i][j] =  resultat.remove();
            }
        }

        this.tableau.setModel(new DefaultTableModel(data, column));
    }

    /**
     * Renvoie le bouton pour valider une demande
     * @return le bouton pour valider une demande
     */
    public JButton getValidate() {
        return this.btnValidate;
    }

    /**
     *Renvoie le bouton pour refuser une demande
     * @return le bouton pour refuser une demande
     */
    public JButton getRefuse() {
        return this.btnRefuse;
    }

    
    /**
     * Renvoie le tableau des résultats de recherche
     * @return le tableau des résultats de recherche
     */
    public JTable getTableau(){
        return this.tableau;
    }

    /**
     * Renvoie le groupe initial
     * @return Le groupe initial
     */
    public Groupe getGroupeInitial() {
        return this.grpInitialGroupe;
    }

    /**
     * Change le groupe initial
     * @param g groupe inital {@link Groupe} 
     */
    public void setGroupeInitial(Groupe g) {
        this.grpInitialGroupe = g;
    }

    /**
     * Renvoie l'étudiant qui demande le changement 
     * @return l'étudiant qui demande le changement
     */
    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    /**
     * Changer l'étudiant qui demande un changement
     * @param etu etudiant {@link Etudiant}
     */
    public void setEtudiant(Etudiant etu) {
        this.etudiant = etu;
    }

    /**
     * Renvoie le groupe destination
     * @return le groupe destination
     */
    public Groupe getGroupeDest() {
        return this.grpDestGroupe;
    }

    /**
     * Changer le groupe destination
     * @param g groupe destination {@link Groupe}
     */
    public void setGroupeDest(Groupe g) {
        this.grpDestGroupe = g;
    }

    /**
     * Renvoie le bouton pour mettre à jour les résultats affichés 
     * @return le bouton pour mettre à jour les résultats affichés
     */
    public JButton getBtnRefresh(){
        return this.btnRefresh;
    }


    /**
     * Renvoie le bouton pour faire des recherches
     * @return le bouton pour faire des recherches
     */
    public JButton getBtnRecherche(){
        return this.btnRecherche;
    }

    /**
     * Renvoie le contenu de la barre de recherche
     * @return le contenu de la barre de recherche
     */
    public String getTextOfRecherche(){
        return this.barreRecherche.getText();
    }

    /**
     * Renvoie la barre de recherche
     * @return la barre de recherche
     */
    public JTextField getBarreRecherche(){
        return this.barreRecherche;
    }
}