package fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.EventObject;
import java.util.Iterator;



/**
 * Vue pour afficher l'etudiant d'un groupe 
*/
public class VueEtuparGroupe extends JPanel{

    /**
     *  selection un groupe  
     */
    private JComboBox<String> groupe;
    /**
     *  scroll pour le tableau 
     */
    private JScrollPane jScrollPane1;

    /**
     *  btn retour a l'accueil
     */
    private JButton retour;

    /**
     * tableau affiche les etudiants par groupe
     */
    private JTable tableau;

    /**
     * Texte de la vue 
     */
    private JLabel titre;


    /***
     * 
     * @param f {@link FrameEns}
     * 
     */
    public VueEtuparGroupe(FrameEns f) {

        this.setBackground(f.getBackgrounColor());
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gb);

        this.titre = new JLabel("LISTE DES ETUDIANTS EN FONCTION DU GROUPE");
        this.titre.setForeground(Color.white);
        this.titre.setFont(new Font("Arial",2, 19));

        retour = new JButton("Retour");
        groupe = new JComboBox<>();
        jScrollPane1 = new JScrollPane();

        tableau = new JTable(){
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        tableau.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
            },
            new String [] {
                "GROUPE","NOM", "PRENOM"
            }
        ));
        
        jScrollPane1.setViewportView(tableau);
        
        
       
        //Ajout du titre
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.ipady = 19;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(6, 42, 0, 0);
        this.add(titre, gbc);

        // Ajout de retour
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new java.awt.Insets(12, 6, 0, 0);
        this.add(retour, gbc);

        
    
        Groupe partition = f.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

        Iterator<Groupe> itgr = partition.getSousGroupes().iterator();
        groupe.addItem("Selectionner un groupe");
        // parcours tout les groupe de la promo
        while(itgr.hasNext()){
            // ajout des noms des groupes au JComboBox
            groupe.addItem(itgr.next().getName());
        }
        groupe.addItemListener(new EventEns(f));


        // Ajout de groupe
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipadx = 39;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(57, 28, 0, 0);
        this.add(groupe, gbc);

        
        // Ajout de jScrollPane1 (tableau avec un scroll)
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 326;
        gbc.ipady = 186;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(57, 28, 11, 43);
        this.add(jScrollPane1, gbc);
    }        
    
    
    /** 
     * @param combo
     * Change la comboBox qui contient les groupes
     */
    public void setItem(JComboBox<String> combo){
        this.groupe = combo;
    }

    /***
     * 
     * @return JButton 
     * Retourne le  bouton retour qui permet d'aller au menu prinicipale de la classe {@link FrameEns}
     * 
    */
    public JButton getRetour(){
        return this.retour;
    }

    
    /** 
     * @return JTable
     * Retourne un tableau qui contient la liste des groupes etudiants
     */
    public JTable getTableau(){
        return this.tableau;
    }
}