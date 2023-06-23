package fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT;

import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.API.Groupe;

import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 * Event pour {@link FrameEns} 
*/
public class EventEns implements MouseListener, ItemListener, ActionListener {

    /**
     * FrameEns
     */
    private FrameEns frame;
    
    /**
     *  
    */
    private static int nbClick=0;

    /***
     * 
     * @param frame {@link FrameEns}
    */

    public EventEns(FrameEns frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // si l'etudiant clique sur le bouton retour d
        if(e.getSource().equals(this.frame.getRetour())){
            this.frame.getCard().first(frame.getContainer());
        }

        // si 
        if(e.getSource().equals(this.frame.getListe_groupe())){
            this.frame.getCard().show(this.frame.getContainer(), "VueGroupe");
            this.frame.setRetour(this.frame.getVueGroupe().getRetour());
        }
        if(e.getSource().equals(this.frame.getListe_etu())){
            this.frame.getCard().show(this.frame.getContainer(), "VueEtuparGroupe");
            this.frame.setRetour(this.frame.getVueEtuparGroupe().getRetour());
        }
        if(e.getSource().equals(this.frame.getRecherche())){
            this.frame.getCard().show(this.frame.getContainer(), "VueRechercheEtu");
            this.frame.setRetour(this.frame.getVueRechercheEtu().getRetour());
        }

        if(e.getSource().equals(this.frame.getVueRechercheEtu().getBtnRecherche())){
            
            // if(this.f.getVueRechercheEtu().getBarreRecherche())
            if(this.frame.getVueRechercheEtu().getBarreRecherche().getText().equals("")){
                JOptionPane.showMessageDialog(this.frame,"VEUILLEZ ENTREZ DES CARACTÈRES POUR LA RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
            }else{
                String texte = this.frame.getVueRechercheEtu().getTextOfRecherche();
                String texteMasjucule = texte.toUpperCase();
                String texteMinuscule = texte.toLowerCase();
                
                
                Groupe promo = this.frame.getAcf().getGroupeFactory().getPromotion();
    
                LinkedList<String> resultat = new LinkedList<String>();
                
                for(Etudiant etu : promo.getEtudiants()){
                    if(etu.getNom().startsWith(texte) || etu.getNom().startsWith(texteMinuscule) || etu.getNom().startsWith(texteMasjucule)){
                        String nomGroupeEtu = this.frame.getAcf().getGroupeFactory().getGroupesOfEtudiant(etu).iterator().next().getName();
                        resultat.add(nomGroupeEtu);
                        resultat.add(etu.getNom());
                        resultat.add(etu.getPrenom());
                    }
                }

                if(resultat.isEmpty()){
                    JOptionPane.showMessageDialog(this.frame,"AUCUN ETUDIANT NE CORRESPOND A VOTRE RECHERCHE",null,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("res/img/report.png"));
                }
                else{
                    int size = resultat.size();
                    size = size/3;

                    String[] column = {"GROUPE","NOM", "PRENOM"};
                    String[][] data = new String[size][3];

                    for(int i = 0; i < size; i++){
                        for(int j = 0; j < 3; j++){
                            data[i][j] =  resultat.remove();
                        }
                    }
                    this.frame.getVueRechercheEtu().getTableauRecherche().setModel(new DefaultTableModel(data,column));
                    EventEns.nbClick=0;
                }
            }
                
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getComponent().equals(this.frame.getVueRechercheEtu().getBarreRecherche())){
            EventEns.nbClick++;
            if(EventEns.nbClick == 1){
                this.frame.getVueRechercheEtu().getBarreRecherche().setText("");
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        String[] column = {"GROUPE","NOM","PRENOM"};
        String[][] data = {{null,null,null}};
        
        Groupe promo = frame.getAcf().getGroupeFactory().getPromotion().getSousGroupes().iterator().next();

        for(Groupe g : promo.getSousGroupes()){
            
            // RECUPERE LE SOUS GROUPE CLIQUE 
            if((g.getName()).equals(e.getItem())){
                
                Groupe grp = g;
                data = new String[grp.getMax()][3];
                
                Set<Etudiant> etu_set = grp.getEtudiants();
                Iterator <Etudiant> it_etu = etu_set.iterator();

                Etudiant e1 = it_etu.next();

                // Ajout des donnees le nom du groupe, le nom et prenom de l'etudiant

                for(int i = 0; i < grp.getMax() ; i++){

                    for(int j = 0; j < 3 ; j++){

                        if(j == 0){
                            data[i][j] = (String)e.getItem();
                        }
                        else if(j == 1){
                            data[i][j] =  e1.getNom();   
                        }
                        else{
                            data[i][j] =  e1.getPrenom();
                        }
                    }
                    if(it_etu.hasNext()){
                        e1 = it_etu.next();
                    }
                    else{
                        break;
                    }
                }
                this.frame.getVueEtuparGroupe().getTableau().setModel(new DefaultTableModel(data,column));
            }
        }
        if(e.getItem().equals("Selectionner un groupe")){
            this.frame.getVueEtuparGroupe().getTableau().setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
            },
            new String [] {
                "NOM", "PRENOM", "GROUPE"
            }
        ));
        }
    }

   

 
}
