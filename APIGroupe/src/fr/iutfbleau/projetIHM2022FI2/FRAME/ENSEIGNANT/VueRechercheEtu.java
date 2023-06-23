package fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.EventObject;
import java.awt.Dimension;
import java.awt.GridBagConstraints;


/**
 *  Vue pour rechercher un etudiant 
 */
public class VueRechercheEtu extends JPanel{

    /**
    * btn pour revenir a l'accueil
    */
    private JButton btnRetour;

    /**
    * btn pour effectuer une recherche
    */
    private JButton btnRecherche;

     /**
    * champ de texte pour effectuer une recherche
    */
    private JTextField barreRecherche;

    /**
     * tableau qui affiche les resultats de la recherche
     */
    private JTable tableauRecherche;
    
    
    /**
     * 
     * @param frame {@link FrameEns}
     * 
     */
    public VueRechercheEtu(FrameEns frame){
        this.setBackground(frame.getBackgrounColor());
        
        this.btnRetour = new JButton("Retour");
        
        this.barreRecherche = new JTextField("Veuillez saisir le nom d'etudiant");
        this.barreRecherche.addMouseListener(new EventEns(frame));
        this.btnRecherche = new JButton(new ImageIcon("res/img/loupe.png"));
        this.btnRecherche.setOpaque(false);
        this.btnRecherche.setBorderPainted(false);
        this.btnRecherche.setPreferredSize(new Dimension(60, 30));
        this.btnRecherche.addActionListener(new EventEns(frame));

        this.setBackground(frame.getBackgrounColor());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JScrollPane scroll = new JScrollPane();

        this.tableauRecherche = new JTable(){
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        tableauRecherche.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
            },
            new String [] {
                "GROUPE","NOM", "PRENOM"
            }
        ));
        
        scroll.setViewportView(tableauRecherche);

        // Ajout de btnRetour
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnRetour, gbc);

        // Ajout de barreRecherche
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.barreRecherche, gbc);

        // Ajout de btnRecherche
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnRecherche, gbc);
        
        // Ajout de scroll (tableau avec scrollbar)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 326;
        gbc.ipady = 186;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(scroll, gbc);
    }

    /**
     * 
     * @return JTextField
     * Retourne la barre de recherche 
     */ 
    public JTextField getBarreRecherche() {
        return this.barreRecherche;
    }
     
    
    /** 
     * @return JTable
     * Retourne le tableau d'affichage des données 
     */
    public JTable getTableauRecherche(){
        return this.tableauRecherche;
    }

    
    /** 
     * @return String
     * Renvoie le texte de la barre de recherche 
     */
    public String getTextOfRecherche(){
        return this.barreRecherche.getText();
    }

    
    /** 
     * @return JButton
     * Renvoie le buton qui permet de faire une recherche 
     */
    public JButton getBtnRecherche(){
        return this.btnRecherche;
    }

    
    /** 
     * @return JButton
     * Renvoie le buton qui permet de retourner au menu principal 
     */
    public JButton getRetour(){
        return this.btnRetour;
    }
}
