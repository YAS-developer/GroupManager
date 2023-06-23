package fr.iutfbleau.projetIHM2022FI2.FRAME;

import fr.iutfbleau.projetIHM2022FI2.FRAME.ADMINISTRATEUR.FrameAdmin;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ENSEIGNANT.FrameEns;
import fr.iutfbleau.projetIHM2022FI2.FRAME.ETUDIANT.FrameEtu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


// CETTE CLASSE PERMET DE METTRE EN LIEN LES 3 FRAMES : PAS EU ASSEZ DE TEMPS POUR LA CODER


public class EventAllFrame implements MouseListener{
    
    private FrameAdmin frameAdmin;    
    private FrameEns frameEns;    
    private FrameEtu frameEtu;


    public EventAllFrame(FrameAdmin admin) {
        this.frameAdmin = admin;
    }

    public EventAllFrame(FrameEns ens) {
        this.frameEns = ens;
    }

    public EventAllFrame(FrameEtu etu) {
        this.frameEtu = etu;
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



}
