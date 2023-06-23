package fr.iutfbleau.projetIHM2022FI2.FRAME.ETUDIANT;

import fr.iutfbleau.projetIHM2022FI2.API.*;
// import fr.iutfbleau.projetIHM2022FI2.FRAME.EventAllFrame;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT.FrameEns;

import javax.swing.JButton;


/**
 * Frame de l'etudiant 
 */
public class FrameEtu extends FrameEns{
    
    /**
     * btn retour a l'accueil
     */
    private JButton retour;

    /**
     * btn pour effectuer une demande
     */
    private JButton btnDemande;

    /**
     * btn pour voir les demandes de type 2
     */
    private JButton btnVoirDemande;

    /**
     *  {@link VueDemande}
     */
    private VueDemande vd;
    

    /***
     * 
     * @param acf  Construit une nouvelle fenêtre étudiant 
     * 
     */

    public FrameEtu(AbstractChangementFactory acf){
        super(acf);
        this.retour = super.retour;
        this.vd = new VueDemande(this);
        
        super.setTitle("Etudiant");
        
        this.btnDemande = new JButton("EFFECTUER UNE DEMANDE");
        this.btnDemande.setFont(super.taillePolice);
        this.btnDemande.addActionListener(new EventEtu(this));
        
        this.btnVoirDemande = new JButton("VOIR LES DEMANDES DE TYPE 2");
        this.btnVoirDemande.setFont(super.taillePolice);
        this.btnVoirDemande.addActionListener(new EventEtu(this));
        
        super.panel.remove(super.btnRecherche);
        super.panel.add(this.btnVoirDemande);
        super.panel.add(this.btnDemande);
        super.container.add("VueDemande",this.vd);        
    }


    
    /** 
     * @return VueDemande 
     * Renvoie la vue qui permet de faire des demandes 
     */
    public VueDemande getVueDemande(){
        return this.vd;
    }

    
    /** 
     * @return JButton 
     * Renvoie le buton qui permet de faire une demande 
     */
    public JButton getDemandes(){
        return this.btnDemande;
    }

    
    /** 
     * @param vd 
     * Change la Vue qui permet de faire une demande {@link VueDemande}
     */
    public void setVuedemande(VueDemande vd){
        this.vd = vd ;
        super.getContainer().add("VueDemande",this.vd);
    }

    
    
    /** 
     * @param button 
     * Change le bouton de la fenêtre 
     */
    public void setRetour(JButton button){
        this.retour = button;
        this.retour.addActionListener(new EventEtu(this));
    }

    
    /** 
     * @return JButton 
     * Renvoi le bouton qui permet de revenir au menu principal
     */
    public JButton getRetour(){
        return this.retour;
    }
}
