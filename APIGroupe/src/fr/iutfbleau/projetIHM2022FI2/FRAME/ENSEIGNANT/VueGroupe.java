package fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT;

import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

/**
 *  Vue pour afficher la liste des groupes 
 */
public class VueGroupe extends JPanel{

    /**
     * btn pour revenir a l'accueil
     */
    private JButton retour;

    /**
     * tableau qui affcihe la liste des etudiants d'un groupe
     */
    private JTable tableau;
    /**
     * scroll pour le tableau
     */
    private JScrollPane scroll;


    /***
     * @param frame
     * Construit un nouveau panel qui affiche les groupes des étudiants
     */
    public VueGroupe(FrameEns frame){
        this.setBackground(new Color(61,85,134));
        


        String[] titre = {"Nom du Groupe"};
        Queue<String> file = new LinkedList<String>();
        
        Groupe partition = frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

        Iterator<Groupe> itgr = partition.getSousGroupes().iterator();
       
        // Ajout des noms des groupes a la liste
        if(file.isEmpty()){
            while(itgr.hasNext())
            {
                file.add(itgr.next().getName());
            }
        }

        // on transfert les donnees de la liste au tableau
        int size = file.size();
        String [][] data = new String[size][1];
        for(int i = 0; i < size ; i++){
            data[i][0] = file.remove();
        }


        
        this.tableau = new JTable(data,titre){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        
        this.scroll = new JScrollPane(this.tableau);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(gb);
        this.retour = new JButton("Retour");
        
        
        
        JLabel banniere = new JLabel("LISTE DES GROUPES");
        banniere.setForeground(Color.white);
        banniere.setFont(new Font("Arial",2, 19));

 
        // Ajout de retour
        gbc.gridx = 0; 
        gbc.gridy = 0;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0;   
        this.add(this.retour,gbc);

        
        // Ajout de banniere
        gbc.gridx = 1;      
        gbc.gridy = 0;     
        gbc.gridwidth = 1; 
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0;  
        this.add(banniere,gbc);
        
   
        // Ajout de scroll
        gbc.gridx = 1;      
        gbc.gridy = 1;      
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 0.0;   
        gbc.weighty = 1.0;
        gbc.ipadx = 326;
        gbc.ipady = 186;   
        gbc.insets = new Insets(0, 0, 100, 100);   
        this.add(this.scroll,gbc);    
    }


    /***
     * 
     * @return JButton 
     * Retourne le bouton retour qui permet de se diriger vers le menu principal 
     * 
    */
    public JButton getRetour(){
        return this.retour;
    }
}
