package fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

import fr.iutfbleau.projetIHM2022FI2.API.AbstractChangementFactory;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

/**
 *  Frame de l'administrateur 
 */

public class FrameAdmin extends JFrame {

    /**
     * Vue Principal de la Frame
     */
    private VuePrincipale vp;

    /**
     * Vue Gestion Groupe de la Frame
     */
    private VueGestionGroupe vgg;

    /**
     * Vue Gestion Etudiant de la Frame
     */
    private VueGestionEtudiant vge;

    /**
     * Vue Gestion Demande de la Frame
     */
    private VueGestionDemande vgd;

    /**
     * CardLayout de la Frame
     */
    private CardLayout card;

    /**
     * container de la Frame
     */
    private JPanel container;

    /**
     * navBar de la Frame
     */
    private JPanel navBar;

    /**
     *  btnAccueil de la Frame
     */
    private JButton btnAccueil;
    
     /**
     *  btnGestionGroupe de la Frame
     */
    private JButton btnGestionGroupe ;

    /**
     *  btnGestionEtudiant de la Frame
     */
    private JButton btnGestionEtudiant;

    /**
     *  btnGestionDemande de la Frame
     */
    private JButton btnGestionDemande;

    /**
     *  btnGestionDemande de la Frame
     */
    private JButton btnQuitter;

    /**
     * Couleur de fond 1 
     */
    private final Color bgColor = new Color(98,0,238);

    /**
     * Couleur de fond 2 
     */
    private final Color color2 = new Color(55,0,79);

    /**
     * taille de la police 
     */
    private final Font taillePolice = new Font("Cursive",1,13);

    /**
     * {@link AbstractChangementFactory}
     */
    private AbstractChangementFactory acf;

    /**
     * 
     * @param acf 
     */
    public FrameAdmin(AbstractChangementFactory acf) {
        this.setTitle("Administrateur");
        this.acf = acf;
        this.vp = new VuePrincipale(this);
        this.vgg = new VueGestionGroupe(this);
        this.vge = new VueGestionEtudiant(this);
        this.vgd = new VueGestionDemande(this);
        this.card = new CardLayout();
        this.container = new JPanel();
        this.navBar = new JPanel();
        
        this.btnAccueil = new JButton(null, new ImageIcon("res/img/house.png"));
        this.btnAccueil.setFont(taillePolice);
        this.btnAccueil.setForeground(this.color2);
        this.btnAccueil.setBackground(Color.WHITE);
        this.btnAccueil.setOpaque(true);

        this.btnGestionGroupe = new JButton("GESTION DES GROUPES");
        this.btnGestionGroupe.setFont(taillePolice);        
        this.btnGestionEtudiant = new JButton("GESTION DES ETUDIANTS");
        this.btnGestionEtudiant.setFont(taillePolice);            
        this.btnGestionDemande = new JButton("GESTION DES DEMANDES");
        this.btnGestionDemande.setFont(taillePolice); 
        this.btnQuitter = new JButton("QUITTER");
        this.btnQuitter.setFont(taillePolice); 

        this.btnAccueil.addActionListener(new EventAdmin(this));
        this.btnGestionGroupe.addActionListener(new EventAdmin(this));
        this.btnGestionDemande.addActionListener(new EventAdmin(this));
        this.btnGestionEtudiant.addActionListener(new EventAdmin(this));
        this.btnQuitter.addActionListener(new EventAdmin(this));


        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        this.navBar.setLayout(gbl);
        this.container.setLayout(this.card);

        this.container.add("Accueil",this.vp);
        this.container.add("VueGestionGroupe",this.vgg);
        this.container.add("VueGestionEtudiant",this.vge);
        this.container.add("VueGestionDemande", this.vgd);

        this.navBar.setBackground(this.bgColor);
        this.container.setBackground(Color.RED);

        /*Configuration des buttons*/

        //Ajout btnAccueil
        gbc.gridx = 0;     
        gbc.gridy = 0;      
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0;  
        gbc.insets = new Insets(10, 10, 50, 10);   
        this.navBar.add(this.btnAccueil,gbc);


        // Ajout btnGestionGroupe
        gbc.gridx = 0;       
        gbc.gridy = 1;      
        gbc.gridwidth = 1; 
        gbc.gridheight = 1;  
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;  
        gbc.ipadx = 100;
        gbc.ipady = 20;
        this.navBar.add(this.btnGestionGroupe,gbc);

        // Ajout btnGestionEtudiant
        gbc.gridx = 0;     
        gbc.gridy = 2;      
        gbc.gridwidth = 1;  
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.ipadx = 100;
        gbc.ipady = 20;
        this.navBar.add(this.btnGestionEtudiant,gbc);

        // Ajout btnGestionDemande
        gbc.gridx = 0;      
        gbc.gridy = 3;      
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0;  
        gbc.ipadx = 100;
        gbc.ipady = 20;
        gbc.insets = new Insets(0, 10, 60, 10);    
        this.navBar.add(this.btnGestionDemande,gbc);

        
        // Ajout btnQuitter
        gbc.gridx = 0;      
        gbc.gridy = 4;     
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.weightx = 1.0;  
        gbc.weighty = 0.0;  
        gbc.ipadx = 100;
        gbc.ipady = 20;
        gbc.insets = new Insets(0, 10, 60, 10);  
        this.navBar.add(this.btnQuitter,gbc);


        //Ajout de la navbar à la frame
        this.add(this.navBar,BorderLayout.WEST);
        // Ajout du container a la frame
        this.add(this.container,BorderLayout.CENTER);
        
        ConfigFrame();
    }
    
    /***
     * Configure la fenêtre 
     * */ 

    public void ConfigFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
    }

    /**
     * Renvoie le cardLayout
     * @return le cardLayout
     */
    public CardLayout getCard(){
        return this.card;
    }


    /**
     * Renvoie la vue principale
     * @return la vue principale 
     */
    public VuePrincipale getVuePrincipale(){
        return this.vp;
    }

    /**
     * Renvoie la vue gestion des groupes
     * @return la vue gestion des groupes
     */
    public VueGestionGroupe getVueGestionGroupe(){
        return this.vgg;
    }

    /**
     * Renvoie la vue gestion des étudiants
     * @return la vue gestion des étudiants 
     */
    public VueGestionEtudiant getVueGestionEtudiant(){
        return this.vge;
    }

    /**
     * Renvoie la vue gestion de demande  
     * @return la vue gestion de demande
     */
    public VueGestionDemande getVueGestionDemande(){
        return this.vgd;
    }

    

    /**
     * Renvoie l'usine abstraite des changements
     * @return acf {@link AbstractChangementFactory}
     */
    public AbstractChangementFactory getAcf(){
        return this.acf;
    }

    /**
     * Renvoie une couleur
     * @return une couleur 
     */
    public Color getColor2(){
        return this.color2;
    }

    /**
     * Renvoie la font
     * @return la font
     */
    public Font getTaillePolice(){
        return this.taillePolice;
    }
    
    
    /**
     * Renvoie le container
     * @return le container
     */
    public JPanel getContainer(){
        return this.container;
    }

    /**
     * Renvoie le bouton pour afficher la vue principale
     * @return le bouton pour afficher la vue principale
     */
    public JButton getBtnAccueil(){
        return this.btnAccueil;
    }

    /**
     * Renvoie le bouton pour afficher la vue gestion groupe
     * @return le bouton pour afficher la vue gestion groupe
     */
    public JButton getBtnGestionGroupe(){
        return this.btnGestionGroupe;
    }

    /**
     * Renvoie le bouton pour afficher la vue gestion etudiant
     * @return le bouton pour afficher la vue gestion etudiant 
     */
    public JButton getBtnGestionEtudiant(){
        return this.btnGestionEtudiant;
    }

    /**
     * Renvoie le bouton pour afficher la vue gestion demande
     * @return le bouton pour afficher la vue gestion demande
     */
    public JButton getBtnGestionDemande(){
        return this.btnGestionDemande;
    }

    /**
     * Renvoie le bouton quitter 
     * @return le bouton quitter 
     */
    public JButton getBtnQuitter(){
        return this.btnQuitter;
    }

    
}
