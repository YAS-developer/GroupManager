package fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT;

import fr.iutfbleau.projetIHM2022FI2.API.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

/**
 * Frame de l'enseignant
*/

public class FrameEns extends JFrame{

    
    /**
     * L'usine abraitre {@link AbstractChangementFactory}
     */
    private AbstractChangementFactory acf;

    /**
     * panel de l'acceuil
     */ 
    protected JPanel panel;

    /**
     * container de FrameEns
     */
    protected Container container;

    /**
     * cardLayout du container
     */
    private CardLayout card;

    /**
     * bouton permettant l'accès de la liste des groupes
     */ 
    private JButton btnListeGroupe;

    /**
     * bouton permettant l'accès de la liste etu
     */ 
    private JButton btnListeEtu;

    /**
     * bouton permettant l'accès à la recherche d'un étudiant
     */ 
    protected JButton btnRecherche;

    /**
     * {@link VueGroupe}
     */
    private VueGroupe vg;

    /**
     * {@link VueEtuparGroupe}
     */
    private VueEtuparGroupe veg;

    /**
     * {@link VueRechercheEtu}
     */
    private VueRechercheEtu vre;

    /**
     * bouton permettant de revenir à l'accueil 
     */
    protected JButton retour;
    
    /**
     * couleur principale de FrameEns
     */
    private final Color bgColor = new Color(61,85,134);

    /**
     * taille de police principale de FrameEns
     */
    protected final Font taillePolice = new Font("Cursive",1,15);
    
    /**
     * @param acf L'usine abraitre {@link AbstractChangementFactory}
     */
    public FrameEns(AbstractChangementFactory acf){
        
        this.setTitle("Enseignant");
        this.acf = acf;
        this.vg = new VueGroupe(this);
        this.veg = new VueEtuparGroupe(this);
        this.vre = new VueRechercheEtu(this);
        

        this.panel = new JPanel();
        this.container = getContentPane();
        this.retour = new JButton();

        this.btnListeGroupe = new JButton("LISTE DES GROUPES");
        this.btnListeGroupe.setFont(taillePolice);
        this.btnListeEtu = new JButton("LISTE DES ETUDIANTS D'UN GROUPE");
        this.btnListeEtu.setFont(taillePolice);
        this.btnRecherche = new JButton("CHERCHER LE GROUPE D'UN ETUDIANT");
        this.btnRecherche.setFont(taillePolice);

        this.btnListeGroupe.addActionListener(new EventEns(this));
        this.btnListeEtu.addActionListener(new EventEns(this));
        this.btnRecherche.addActionListener(new EventEns(this));

        this.card = new CardLayout(20,10);
        this.container.setLayout(card);

        this.panel.setBackground(this.bgColor);
        this.panel.setLayout(new GridLayout(4,1));

        this.panel.add(this.btnListeGroupe);
        this.panel.add(this.btnListeEtu);
        this.panel.add(this.btnRecherche);
        this.container.setBackground(this.bgColor);

        this.container.add("Accueil",panel);
        this.container.add("VueGroupe",this.vg);
        this.container.add("VueEtuparGroupe",this.veg);
        this.container.add("VueRechercheEtu",this.vre);
        
        // this.addActionListener(new EventAllFrame(this));
        ConfigFrame();
    }


    /**
     * Configure la fenêtre  
     * 
     */ 

    public void ConfigFrame(){
        // this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
    }

    /***
     * 
     * @return une usine de changement {@link AbstractChangementFactory} de la Fenetre
     */

    public AbstractChangementFactory getAcf(){
        return this.acf;
    }

    /**
     * 
     * @return Le bouton pour la liste des groupes
     */
    
    public JButton getListe_groupe(){
        return this.btnListeGroupe;
    }

    /**
     * 
     * @return Le bouton de la liste d'un groupe donnée
     */

    public JButton getListe_etu(){
        return this.btnListeEtu;
    } 


    /**
     * 
     * @return JButton
     * Retourne le bouton de recherche 
     */

    public JButton getRecherche(){
        return this.btnRecherche;
    }

    /**
     * 
     * @return Container
     * Retourne le Container de la fenêtre  
     */

    public Container getContainer(){
        return this.container;
    }

    /**
     * 
     * @return CardLayout
     * Retourne le CardLayout du Container de la FrameEns
     */

    public CardLayout getCard() {
        return this.card;
    }
    
    /****
     * 
     * @return la classe {@link VueGroupe}
    */ 
    
    public VueGroupe getVueGroupe(){
        return this.vg;
    }

    /****
     * 
     * @return la classe {@link VueEtuparGroupe}
    */ 

    public VueEtuparGroupe getVueEtuparGroupe(){
        return this.veg;
    }


     /****
     * 
     * @return la classe {@link VueRechercheEtu}
    */ 

    public VueRechercheEtu getVueRechercheEtu(){
        return this.vre;
    }


    /****
     * 
     * @return JButton 
     * Retourne le bouton retour de fenêtre
    */ 
    public JButton getRetour(){
        return this.retour;
    }

    

    /**
     * 
     * @param button button retour de {@link VueGroupe} ou de {@link VueEtuparGroupe} ou de {@link VueRechercheEtu}
     */

    public void setRetour(JButton button){
        this.retour = button;
        this.retour.addActionListener(new EventEns(this));
    }

    /**
     * @param button 
     * Change le boutton recherche de la fenêtre
     *  
     */

    public void setRecherche(JButton button){
        
        this.panel.remove(this.btnRecherche);
        this.btnRecherche = button;
        this.btnRecherche.addActionListener(new EventEns(this));
        this.panel.add(this.btnRecherche);
    }


    /**
     * @return Color 
     * Retourne la couleur principale de fenêtre
     */

    public Color getBackgrounColor(){
        return this.bgColor;
    }
}