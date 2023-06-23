package fr.iutfbleau.projetIHM2022FI2.FRAME.ETUDIANT;


import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import java.awt.event.ItemEvent;
// import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Color;

import fr.iutfbleau.projetIHM2022FI2.API.Groupe;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT.EventEns;
import fr.iutfbleau.projetIHM2022FI2.API.Changement;
import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;


/**
 *  Event de {@link FrameEtu} 
 */
public class EventEtu extends EventEns{
    
    /**
     * {@link FrameEtu}
     */
    private FrameEtu frame;


    /*
     * permet d'effacer lorsque l'on clique le texte des explications
    */
    private static int nbClick;

    /***
     * 
     * @param frameetu 
     * Construit un nouvelle évènement pour {@link FrameEtu}
    */ 
    public EventEtu(FrameEtu frameetu){
        super(frameetu);
        this.frame = frameetu;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         //Si l'étudiant clique sur le bouton "Retour"

         if(e.getSource().equals(this.frame.getRetour())){
            this.frame.getCard().first(frame.getContainer());
        }

        //Si l'étudiant clique sur "Effectuer une demande"  sur le menu principal
        
        if(e.getSource().equals(this.frame.getDemandes())){
            this.frame.getCard().show(this.frame.getContainer(), "VueDemande");
            this.frame.setRetour(this.frame.getVueDemande().getRetour());
        }
        
        //Si l'étudiant clique sur le bouton "transmettre"

        if (e.getSource().equals(this.frame.getVueDemande().getEnvoyer())) {

            //Vérifier si l'etudiant écrit une explication

            if (this.frame.getVueDemande().getExplication().equals("EXPLICATION :") || this.frame.getVueDemande().getExplication().equals("") || this.frame.getVueDemande().getExplication().equals("Veuillez transmettre une explication.")){
                this.frame.getVueDemande().getTextArea().setForeground(Color.red);
                this.frame.getVueDemande().getTextArea().setText("Veuillez transmettre une explication.");
                EventEtu.nbClick = 0;

            //Vérifier si l'étudiant s'identifie et choisit son groupe
            } 
            else if(this.frame.getVueDemande().getEtudiantDemande() != null && this.frame.getVueDemande().getGroupeDestination() != null){
                
                // //Recuperer l'id de l'étudiant
                
                Etudiant etu = this.frame.getVueDemande().getEtudiantDemande();
                
                // //Recuperer l'id du groupe de l'etudiant
                
                Groupe groupe_src = this.frame.getAcf().getGroupeFactory().getGroupesOfEtudiant(etu).iterator().next();

                // //Recuperer l'id du groupe auquel l'etudiant souhaite aller

                Groupe groupe_dest = this.frame.getVueDemande().getGroupeDestination();

                
                boolean verifEnvoie = true;

                for(Changement chg : this.frame.getAcf().getAllChangements()){
                    if(chg.getEtu().equals(etu)){
                        JOptionPane.showMessageDialog(this.frame, "Vous avez déja effectué une demande. Veuillez patientez.",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/attendre.png"));
                        verifEnvoie = false;
                        EventEtu.nbClick = 0;
                        break;
                    }else{
                        verifEnvoie = true;
                    }
                }

                if(verifEnvoie){

                    this.frame.getAcf().createChangement(groupe_src, etu, groupe_dest);
                    JOptionPane.showMessageDialog(this.frame, "Votre requête à bien été envoyée",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/checked.png"));
                

                    this.frame.getCard().first(this.frame.getContainer());
                    this.frame.getContainer().remove(3);
                    EventEtu.nbClick = 0;
                    this.frame.setVuedemande(new VueDemande(this.frame));
                }
                
            }
            else{
                // lorsque l'étudiant n'a pas choisis son nom || son groupe de destination 

                JOptionPane.showMessageDialog(this.frame, "Veuillez remplir l'intégralité de vos coordonnées",null, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/docs.png"));
            }
        }
    }
    

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
         //Si l'étudiant clique sur le champs de texte 

         if(e.getComponent().equals(this.frame.getVueDemande().getTextArea())) {
            
            EventEtu.nbClick++;
            if(nbClick == 1) {
                this.frame.getVueDemande().getTextArea().setText("");
                this.frame.getVueDemande().getTextArea().setForeground(Color.black);
            }
        }
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

 
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    
  
    @Override
    public void mouseEntered(MouseEvent e) {
       
        
    }

 
    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    



    @Override
    public void itemStateChanged(ItemEvent e) {
        
        Groupe promo1 = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
        for(Groupe g : promo1.getSousGroupes()){
            if(g.getName().equals(e.getItem())){
                this.frame.getVueDemande().setGroupeDestination(g);
            }
        }
        
        
        
        if(e.getItem().equals("Qui etes-vous ?")) {

            frame.getVueDemande().getNouveauGrp().removeAllItems();
            frame.getVueDemande().getNouveauGrp().addItem("Groupe potentiel");
            frame.getVueDemande().getNouveauGrp().addItem("Veuillez dabord vous identifiez");

            this.frame.getVueDemande().setEtudiantDemande(null);
        }


        if(e.getItem().equals("Groupe potentiel") || e.getItem().equals("Veuillez dabord vous identifiez")){

            // met les groupe destination a null si l'étudiant reclique sur "Groupe potentiel ou Veuillez dabord vous identifiez"
            
            this.frame.getVueDemande().setGroupeDestination(null);
        }
        

        Iterator<Etudiant> itetu= this.frame.getAcf().getGroupeFactory().getPromotion().getEtudiants().iterator();
        while(itetu.hasNext()){
            
            Etudiant e1 = itetu.next();
            String nom_prenom = e1.getNom()+" "+e1.getPrenom();

            if(e.getItem().equals(nom_prenom)){
           
            
                frame.getVueDemande().setEtudiantDemande(e1);
                frame.getVueDemande().getNouveauGrp().removeAllItems();
                frame.getVueDemande().getNouveauGrp().addItem("Groupe potentiel");
                
                //Groupe de l'étudiant cliqué
                Groupe groupe_etudiant = this.frame.getAcf().getGroupeFactory().getGroupesOfEtudiant(e1).iterator().next();
                
                //Toute la promotion
                Groupe promo2 = this.frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();
                

                for(Groupe g : promo2.getSousGroupes()){
                    if(g.equals(groupe_etudiant)){
                        continue;
                    }
                    else{

                        if(g.getSize() < groupe_etudiant.getSize()){
                            this.frame.getVueDemande().getNouveauGrp().addItem(g.getName());
                        }
                    }
                }
                break;    
            }

        }
    }    
}

