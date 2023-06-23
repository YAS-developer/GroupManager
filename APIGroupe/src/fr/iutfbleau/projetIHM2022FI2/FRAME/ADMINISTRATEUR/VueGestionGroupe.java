package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * Vue pour creer, supprimer ou renommer un groupe
*/

public class VueGestionGroupe extends JPanel{


    /**
     * FrameAdmin
    */
    private FrameAdmin frame;

    /**
     * champ de texte pour le nom du groupe creer
    */
    private JTextArea champTxtCreer;

    /**
     * champ de texte pour la taille minimale du groupe qui va etre creer
    */
    private JTextArea champTxtMin;

    /**
     * champ de texte pour la taille maximale du groupe qui va etre creer
    */
    private JTextArea champTxtMax;

    /**
     * btn pour creer un groupe
    */
    private JButton btnCreer;

    /**
     * permet de selectionner le groupe qui va etre supprimer
     */
    private JComboBox<String> groupeSupprimer;

     /**
     * btn pour supprimer un groupe
    */
    private JButton btnSupprimer;

    /**
     * nom du groupe qui va etre supprimer
     */
    private String nomGroupeSupprimer;

    /**
     * permet de selectionner le groupe qui va etre renommer
    */
    private JComboBox<String> groupeRenommer;

    /**
     * ecrire le nouveau du groupe qui va etre renommer
    */
    private JTextArea champTxtRenommer;
    
    /**
     * bouton pour renommer un groupe
     */
    private JButton btnRenommer;

    /**
     * nom du groupe qui va etre renommer
     */
    private String nomGroupeRenommer;

    /**
     * nouveau nom du groupe
     */
    private String nouveauNomGroupe;
    
    /**
     * @param frame {@link FrameAdmin} 
    */
    public VueGestionGroupe(FrameAdmin frame){
        
        this.frame = frame;
        this.setBackground(frame.getColor2());
        this.setLayout(new GridBagLayout());

        JLabel txtCreer = new JLabel("CREER");
        txtCreer.setForeground(Color.WHITE);
        txtCreer.setFont(new Font("Cursive",1,30));

        this.champTxtCreer = new JTextArea("nouveau groupe");
        this.champTxtCreer.setFont(this.frame.getTaillePolice());
        this.champTxtCreer.addMouseListener(new EventAdmin(this.frame));

        this.champTxtMin = new JTextArea("taille minimale");
        this.champTxtMin.setFont(this.frame.getTaillePolice());
        this.champTxtMin.addMouseListener(new EventAdmin(this.frame));
        this.champTxtMax = new JTextArea("taille maximale");
        this.champTxtMax.setFont(this.frame.getTaillePolice());
        this.champTxtMax.addMouseListener(new EventAdmin(this.frame));
        this.btnCreer = new JButton("CREER LE GROUPE");
        this.btnCreer.setFont(this.frame.getTaillePolice());
        this.btnCreer.addActionListener(new EventAdmin(this.frame));

        JLabel txtSupprimer = new JLabel("SUPPRIMER");
        txtSupprimer.setForeground(Color.WHITE);
        txtSupprimer.setFont(new Font("Cursive",1,30));

        this.groupeSupprimer = new JComboBox<>();
        this.groupeSupprimer.addItem("Groupe");
        this.groupeSupprimer.setFont(this.frame.getTaillePolice());
        this.groupeSupprimer.addItemListener(new EventAdmin(this.frame));

        this.btnSupprimer = new JButton("SUPPRIMER LE GROUPE");
        this.btnSupprimer.setFont(this.frame.getTaillePolice());
        this.btnSupprimer.addActionListener(new EventAdmin(this.frame));

        JLabel txtRenommer = new JLabel("RENOMMER");
        txtRenommer.setForeground(Color.WHITE);
        txtRenommer.setFont(new Font("Cursive",1,30));

        this.groupeRenommer = new JComboBox<>();
        this.groupeRenommer.addItem("Groupe");
        this.groupeRenommer.setFont(this.frame.getTaillePolice());
        this.groupeRenommer.addItemListener(new EventAdmin(this.frame));
        
        this.champTxtRenommer = new JTextArea("nouveau nom du groupe");
        this.champTxtRenommer.setFont(this.frame.getTaillePolice());
        this.champTxtRenommer.addMouseListener(new EventAdmin(this.frame));
        this.btnRenommer = new JButton("RENOMMER LE GROUPE");
        this.btnRenommer.setFont(this.frame.getTaillePolice());
        this.btnRenommer.addActionListener(new EventAdmin(this.frame));

        this.refreshGroupe();

        GridBagConstraints gbc = new GridBagConstraints();

        // ajout de txtCreer
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,10,0,0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(txtCreer,gbc);


        // Ajout de champTxtCreer
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,10,0,0);
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        this.add(champTxtCreer,gbc);


        // Ajout de champTxtMin
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.champTxtMin,gbc);
        

        // Ajout de champTxtMax
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.champTxtMax,gbc);

        // Ajout de btnCreer
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnCreer,gbc);
        

        // Ajout de txtSupprimer
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,10,0,0);
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(txtSupprimer,gbc);

        // Ajout de groupeSupprimer
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.ipadx = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(groupeSupprimer,gbc);


        // Ajout de btnSupprimer
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.ipadx = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.btnSupprimer,gbc);
        

        // Ajout de txtRenommer
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,10,0,0);
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(txtRenommer,gbc);

        // Ajout de groupeRenommer
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.ipadx = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.groupeRenommer,gbc);


        // Ajout de champTxtRenommer
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.champTxtRenommer,gbc);

        // Ajout de btnRenommer
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.ipadx = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.btnRenommer,gbc);
    }




    /**
     * Met à jour les données 
     */
    public void refreshGroupe(){

        this.nomGroupeSupprimer = null;
        this.nomGroupeRenommer = null;
        this.nouveauNomGroupe = null;

        //Récupèrer toute la promotion
        Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>();
        
        model.addElement("Groupe");
        model2.addElement("Groupe");
        

        //Parcourt les groupes de la promotion
        for(Groupe g : promo.getSousGroupes()){
            model.addElement(g.getName());
            model2.addElement(g.getName());
        
        }
         
        //Set les JComboBox de la classe
        this.groupeSupprimer.setModel(model);
        this.groupeRenommer.setModel(model2);
    
    }


    /**
     * Renvoie le bouton pour créer un groupe
     * @return le bouton pour créer un groupe 
     */

    public JButton getBtnCreer(){
        return this.btnCreer;
    }

    /**
     * Renvoie champTxtCreer
     * @return champTxtCreer
     */

    public JTextArea getChampTxtCreer(){
        return this.champTxtCreer;
    }

    /**
     * Renvoie champTxtMin
     * @return champTxtMin
     */

    public JTextArea getChampTxtMin(){
        return this.champTxtMin;
    }

    /**
     * Renvoie champTxtMax
     * @return champTxtMax
     */

    public JTextArea getChampTxtMax(){
        return this.champTxtMax;
    }
    
    /**
     * Renvoie le bouton pour supprimer
     * @return le bouton pour supprimer 
     */

    public JButton getBtnSupprimer(){
        return this.btnSupprimer;
    }

    /**
     * Renvoie le nom du groupe à supprimer
     * @return le nom du groupe à supprimer
     */

    public String getNomGroupeSupprimer(){
        return this.nomGroupeSupprimer;
    }

    /**
     * 
     * @param texte {@link String} le nom du groupe 
     */

    public void setNomGroupeSupprimer(String texte){
        this.nomGroupeSupprimer = texte;
    }

    /**
     * Renvoie le bouton pour renommer
     * @return le bouton pour renommer
     */


    public JButton getBtnRenommer(){
        return this.btnRenommer;
    }

    /**
     * Renvoie le champTxtRenommer
     * @return le champTxtRenommer
     */

    public JTextArea getChampTxtRenommer(){
        return this.champTxtRenommer;
    }

    /**
     * Renvoie le nom du groupe à renommer
     * @return le nom du groupe à renommer
     */

    public String getNomGroupeRenommer(){
        return this.nomGroupeRenommer;
    }


    /**
     * Change le nom du groupe à renommer
     * @param texte {@link String} le nom du groupe à renommer
     */
  
    public void setNomGroupeRenommer(String texte){
        this.nomGroupeRenommer = texte;
    }

      /**
     * Renvoie le nouveu nom du groupe à renommer
     * @return le nouveau du group à renommer
     */

    public String getNouveauNomGroupe(){
        return this.nouveauNomGroupe;
    }

      /**
     * Renvoie le groupe à supprimer
     * @return le groupe à supprimer
     */

    public JComboBox<String> getGroupeSupprimer(){
        return this.groupeSupprimer;
    }

      /**
     * Renvoie le groupe à renommer
     * @return le groupe à renommer
     */

    public JComboBox<String> getGroupeRenommer(){
        return this.groupeSupprimer;
    }
}
