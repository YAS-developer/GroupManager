package fr.iutfbleau.projetIHM2022FI2.FRAME.ETUDIANT;

import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Iterator;
import java.awt.Color;


/**
 * Vue pour effectuer une demande de changement pour l'etudiant 
 */
public class VueDemande extends JPanel{
    
    /**
     * {@link FrameEtu}
     */
    private FrameEtu frame;

    /**
     * btn retour a l'accueil
     */
    private JButton retour;

    /**
     * selectionner l'etudiant 
     */
    private JComboBox<String> etudiant;

    /**
     * nouveau_groupe
     */
    private JComboBox<String> nouveau_groupe;

    /**
     * explication de l'etudiant
     */
    private JTextArea texte;

    /**
     * btn pour envoyer la demande
     */
    private JButton envoyer;

    /**
     * etudiant qui effectue la demande
     */
    private Etudiant etudiant_demande;

    /**
     * groupe que l'etudiant souhaite integrer
     */
    private Groupe groupe_destination;


    /**
     * 
     * @param frame
     * Construit un nouveau panel pour faire des demandes
     */
    public VueDemande(FrameEtu frame){

        this.setBackground(new Color(61,85,134));
        this.frame = frame;
        this.retour = new JButton("Retour");
        this.frame.setRetour(retour);
        this.etudiant = new JComboBox<String>();
        this.nouveau_groupe = new JComboBox<String>();
        this.nouveau_groupe.addItem("Groupe potentiel");
        this.nouveau_groupe.addItem("Veuillez dabord vous identifiez");
        
        this.nouveau_groupe.addItemListener(new EventEtu(frame));

        this.texte = new JTextArea("EXPLICATION :");
        this.texte.addMouseListener(new EventEtu(frame));
        this.texte.setLineWrap(true);
        this.texte.setRows(1);
        this.texte.setColumns(100);
        
        this.envoyer = new JButton("Transmettre");
        this.envoyer.addActionListener(new EventEtu(frame));
        

        etudiant.addItem("Qui etes-vous ?");

        //  RECUPERE LA LISTE DE TOUT LES ETUDIANTS

        Iterator<Etudiant> itetu= this.frame.getAcf().getGroupeFactory().getPromotion().getEtudiants().iterator();
        while(itetu.hasNext()){
            Etudiant e = itetu.next();
            etudiant.addItem(e.getNom()+" "+e.getPrenom());
        }
        etudiant.addItemListener(new EventEtu(frame));
        
        GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);
        GridBagConstraints gbc = new GridBagConstraints();


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
        

        // Ajout de etudiant
        gbc.gridx = 1; 
        gbc.gridy = 2;
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;     
        gbc.anchor = GridBagConstraints.WEST;
        // gbc.ipadx = 100;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        this.add(etudiant,gbc);


        //Ajout de nouveau_groupe
        gbc.gridx = 1; 
        gbc.gridy = 3;
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        this.add(this.nouveau_groupe,gbc);

        // Ajout de texte
        gbc.gridx = 1; 
        gbc.gridy = 4;
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.BOTH;     
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 150, 0);
        gbc.weightx = 0.0; 
        gbc.weighty = 0.0; 
        this.add(this.texte,gbc);

        // Ajout de envoyer
        gbc.gridx = 2; 
        gbc.gridy = 4;
        gbc.gridwidth = 1;  
        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.NONE;    
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 150, 42);
        gbc.ipadx = 0;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        this.add(this.envoyer,gbc);
    }

    
    /** 
     * @return JButton 
     * Renvoie le buton qui permet de faire un retour vers la vue principale 
     */
    public JButton getRetour(){
        return this.retour;
    }

    
    /** 
     * @return etudiant
     * Renvoie la combobox qui contient les étudiants de la promo 
     */
    public JComboBox<String> getEtudiant(){
        return this.etudiant;
    }

    
    /** 
     * @return nouveau_groupe
     * Renvoie la combobox qui contient la liste des groupes
     */
    public JComboBox<String> getNouveauGrp(){
        return this.nouveau_groupe;
    }
    
    /** 
     * @return JButton 
     * Renvoie le buton qui permet d'envoyer une demande 
     */
    public JButton getEnvoyer() {
        return this.envoyer;
    }

    
    /** 
     * @return Etudiant 
     * Renvoie un étudiant qui fait la demande 
     */
    public Etudiant getEtudiantDemande(){
        return this.etudiant_demande;
    }

    
    /** 
     * @param e1 Etudiant {@link Etudiant}
     * Change l'étudiant 
     */
    public void setEtudiantDemande(Etudiant e1){
        this.etudiant_demande = e1;
    }

    
    /** 
     * @param g Groupe {@link Groupe}
     * Change le groupe
     */
    public void setGroupeDestination(Groupe g){
        this.groupe_destination = g;
    }

    
    /** 
     * @return Groupe 
     * Retourne le groupe destination d'un étudiant 
     */
    public Groupe getGroupeDestination(){
        return this.groupe_destination;
    }

    
    /** 
     * @return String 
     * Retourne le texte d'explication d'un etudiant
     */
    public String getExplication() {
        return this.texte.getText();
    }

    
    /** 
     * @return JTextArea 
     * Retourne l'objet qui contient l'explication de l'étudiant
     */
    public JTextArea getTextArea() {
        return this.texte;
    }
}
