package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;

import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;


import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;


/**
 * Vue principal de {@link FrameAdmin}
 */
public class VuePrincipale extends JPanel{

    /**
     * tableau qui affiche toute la promo
     */
    private JTable tableau;
    /**
     * {@link FrameAdmin}
     */
    private FrameAdmin frame;

    /**
     * btn pour refresh le tableau
     */
    private JButton btnRefresh;

    /**
     * permet de taper un texte  pour la recherche 
     */
    private JTextField barreRecherche;

    /**
     * permet d'effectuer une recherche
     */
    private JButton btnRecherche;
    
   /**
    * @param frame FrameAdmin
    */
    public VuePrincipale(FrameAdmin frame){
        
        this.frame = frame;
        this.setBackground(this.frame.getColor2());
        this.btnRefresh = new JButton();
        this.tableau = new JTable();
        
        //Rend les éléments du tableau impossibles à modifier à partir de l'application
        this.tableau = new JTable(){
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        this.tableau.setFont(this.frame.getTaillePolice());
        
        //Affiche les données
        this.refresh();

        this.btnRefresh = new JButton(new ImageIcon("res/img/refresh.png"));
        this.barreRecherche = new JTextField("Veuillez saisir le nom d'etudiant");
        this.barreRecherche.setFont(this.frame.getTaillePolice());
        this.btnRecherche = new JButton(new ImageIcon("res/img/loupe.png"));



        this.btnRefresh.addActionListener(new EventAdmin(frame));
        this.barreRecherche.addMouseListener(new EventAdmin(frame));
        this.btnRecherche.addActionListener(new EventAdmin(frame));
        
        
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(this.tableau);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Ajoute le bouton refresh à la fenetre
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
        //

        //Ajoute la barre de recherche à la fenentre
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.ipady = 15;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.barreRecherche, gbc);
        //

        //Ajoute le bouton qui permet de faire des recherches à la fenetre
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.btnRecherche, gbc);
        //

        //Ajoute la table à la fenetre 
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 326;
        gbc.ipady = 186;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets= new Insets(0, 0,50,0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(scroll, gbc);
        //
    }   

    /**
     * Met à jour les données affichées
     */
    public void refresh(){

        //Recupère toute la promotion
        Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
        //

        //Ajoute le nom du groupe, le nom de l'étudiant et son prénom à la table
        LinkedList<String> resultat = new LinkedList<String>();
        for(Groupe groupe : promo.getSousGroupes()){
            String nomGroupe = groupe.getName();
            for(Etudiant e : groupe.getEtudiants()){
                resultat.add(nomGroupe);
                resultat.add(e.getNom());
                resultat.add(e.getPrenom());
            }
            
        }

        int size = resultat.size();
            size = size/3;

        String[] column = {"GROUPE","NOM", "PRENOM"};
        String[][] data = new String[size][3];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < 3; j++){
                data[i][j] =  resultat.remove();
            }
        }

        this.tableau.setModel(new DefaultTableModel(data, column));
        //
    }

    /**
     * Renvoie le tableau des données
     * @return le tableau des données
     */
    public JTable getTableau(){
        return this.tableau;
    }

    /**
     * Renvoie le bouton pour mettre à jour les boutons affichées
     * @return le bouton pour mettre à jour les données affichées
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
     * @return la barra de recherche 
     */
    public JTextField getBarreRecherche(){
        return this.barreRecherche;
    }
}
