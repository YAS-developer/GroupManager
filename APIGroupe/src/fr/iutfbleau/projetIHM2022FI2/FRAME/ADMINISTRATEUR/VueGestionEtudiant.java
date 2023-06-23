package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;

import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * Vue permettant de d'ajouter un etudiant ou de le deplacer
 */ 

public class VueGestionEtudiant extends JPanel{
    /**
     * frame de l'admin
     */
    private FrameAdmin frame;
    /**
     *  permet de selectionner un etudiant 
     */ 
    private JComboBox<String> comboAjoutEtu;

    /**
     * permet de selectionner  le groupe de destination
     */ 
    private JComboBox<String> comboGroupeDest;
    
    
    /**
    *  boutton pour ajouter d'un etudiant  
    */ 
    private JButton btnAjoutEtu;
    
    /**
     * l'etudiant a 
     */ 
    private String nomEtudiantAjout;

    /**
     * le groupe destination 
     */
    private String nomGroupeDest;

    /**
     * permet de selectionner un groupe destination
     */
    private JComboBox<String> comboGroupeDest2;

    /**
     * permet de selectionner un etudiant 
     */
    private JComboBox<String> comboEtudiants;

    /**
     * bouton pour deplacer d'un etudiant  
     */
    private JButton btnDeplacer;

    /**
     * nom de l'etudiant qui va etre deplacer
     */
    private String nomEtudiantDeplacer;
    /**
     * nom du groupe destination
     */
    private String nomGroupeDest2;


    /**
     * police des titre de la vue
     */
    private final Font titreFont = new Font("Cursive",1,25);

    /**
     * @param frame
     * Construit un nouveau panel pour la gestion des etudiants
     */
    public VueGestionEtudiant(FrameAdmin frame){
        super();
        this.frame = frame;
        this.setBackground(frame.getColor2());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel labelAjouter = new JLabel("AJOUTER UN ETUDIANT");
        labelAjouter.setForeground(Color.white);
        labelAjouter.setFont(titreFont);

        this.comboAjoutEtu = new JComboBox<String>();
        this.comboGroupeDest = new JComboBox<String>();

        this.btnAjoutEtu = new JButton("AJOUTER");
        this.btnAjoutEtu.addActionListener(new EventAdmin(frame));

        JLabel labelDeplacer = new JLabel("DEPLACER UN ETUDIANT");
        labelDeplacer.setFont(titreFont);
        labelDeplacer.setForeground(Color.white);
        this.comboEtudiants = new JComboBox<String>();
        this.comboEtudiants.addItemListener(new EventAdmin(frame));
        this.comboGroupeDest2 = new JComboBox<String>();
        this.comboGroupeDest2.addItemListener(new EventAdmin(frame));
        this.btnDeplacer = new JButton("Valider");

        this.btnDeplacer.addActionListener(new EventAdmin(frame));


        this.refreshAll();

        //Ajouter le labelAjouter à la fenètre
        gbc.gridx = 0;      
        gbc.gridy = 0;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NORTH;    
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 20, 0, 0);
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0;   
        this.add(labelAjouter, gbc);


        // Ajout de la comboAjoutEtu
        gbc.gridx = 0;      
        gbc.gridy = 1;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.ipadx = 100;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;   
        this.add(this.comboAjoutEtu, gbc);
        
        //Ajout de comboGroupeDest
        gbc.gridx = 1;      
        gbc.gridy = 1;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.CENTER;    
        gbc.anchor = GridBagConstraints.EAST;
        gbc.ipadx = 100;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(comboGroupeDest, gbc);


        // Ajout de btnAjoutEtu
        gbc.gridx = 2;      
        gbc.gridy = 1;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;    
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(btnAjoutEtu, gbc);
        
        // Ajout de labelDeplacer
        gbc.gridx = 0;      
        gbc.gridy = 3;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(labelDeplacer, gbc);


        // Ajout de comboEtudiants
        gbc.gridx = 0;      
        gbc.gridy = 4;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 100;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;    
        this.add(comboEtudiants, gbc);


        // Ajout de comboGroupeDest2
        gbc.gridx = 1;      
        gbc.gridy = 4;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 100;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.comboGroupeDest2, gbc);


        // Ajout de btnDeplacer
        gbc.gridx = 2;      
        gbc.gridy = 4;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;    
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.btnDeplacer, gbc);

    }

    /**
     *  refresh les JCombox 
     */
    public void refreshAll() {

        this.nomEtudiantAjout = null;
        this.nomGroupeDest = null;

        this.nomEtudiantDeplacer = null;
        this.nomGroupeDest2 = null;

        Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> model4 = new DefaultComboBoxModel<String>();
        
        model.addElement("Etudiant");
        model3.addElement("Etudiant");



        for(Etudiant e : this.frame.getAcf().getGroupeFactory().getPromotion().getEtudiants()){
            model.addElement(e.getNom()+" "+e.getPrenom());
            model3.addElement(e.getNom()+" "+e.getPrenom());
        }

        model2.addElement("Groupe");
        model4.addElement("Groupe");

        
        for(Groupe g : promo.getSousGroupes()){
            model2.addElement(g.getName());
            model4.addElement(g.getName());
        }
        
 

        this.comboAjoutEtu.setModel(model);
        
        this.comboGroupeDest.setModel(model2);
        
        this.comboEtudiants.setModel(model3);
        this.comboGroupeDest2.setModel(model4);
        
    }




    /**
     * Renvoie le bouton pour ajouter un étudiant
     * @return le bouton pour ajouter un étudiant
     */
    public JButton getBtnAjoutEtu(){
        return this.btnAjoutEtu;
    }

    /**
     * Renvoie le nom de l'étudiant à ajouter
     * @return le nom de l'étudiant à ajouter
     */
    public String getNomEtudiantAjout(){
        return this.nomEtudiantAjout;
    }


    /**
     * Renvoie le nom du groupe destination
     * @return le nom du groupe destination
     */
    public String getNomGroupeDest(){
        return this.nomGroupeDest;
    }


    /**
     * Change le nom de l'étudiant à ajouter
     * @param texte {@link String}
     */
    public void setNomEtudiantAjout(String texte){
        this.nomEtudiantAjout = texte;
    }


    /**
     * Change le nom du groupe destination
     * @param texte {@link String}
     */
    public void setNomGroupDest(String texte){
        this.nomGroupeDest = texte;
    }





    /**
     * Renvoie le bouton déplacer 
     * @return le bouton déplacer
     */
    public JButton getBtnDeplacer(){
        return btnDeplacer;
    }


    /**
     * Renvoie le nom de l'étudiant à déplacer
     * @return le nom de l'étudiant à déplacer
     */
    public String getNomEtudiantDeplacer(){
        return this.nomEtudiantDeplacer;
    }

    /**
     * Renvoie le nom du groupe destination 
     * @return le nom du groupe destinantion
     */
    public String getNomGroupDest2(){
        return this.nomGroupeDest2;
    }


    /**
     * Changer le nom de l'étudiant à déplacer 
     * @param texte {@link String}
     */
    public void setNomEtudiantDeplacer(String texte){
        this.nomEtudiantDeplacer = texte;
    }

    /**
     * Change le nom du groupe destination 
     * @param texte {@link String}
     */
    public void setNomGroupDest2(String texte){
        this.nomGroupeDest2 = texte;
    }
}
