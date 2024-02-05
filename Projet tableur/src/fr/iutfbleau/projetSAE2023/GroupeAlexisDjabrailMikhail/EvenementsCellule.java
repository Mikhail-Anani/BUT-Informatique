package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class EvenementsCellule implements MouseListener {

    private static Border bordure = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);

    private Cellule celluleCliquee = null;
    private Barre barre;

    public EvenementsCellule(Barre barre){
        this.barre = barre;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        Cellule nouvelSelection = (Cellule) e.getSource();
        if (nouvelSelection != this.celluleCliquee){
            if (this.celluleCliquee != null){
                this.celluleCliquee.setBackground(this.celluleCliquee.getColor());
                this.celluleCliquee.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            this.celluleCliquee = nouvelSelection;
            this.celluleCliquee.setBorder(EvenementsCellule.bordure);
            this.celluleCliquee.setBackground(this.celluleCliquee.getColor());
            this.barre.setCellule(this.celluleCliquee);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        Cellule celluleSurvolee = (Cellule) e.getSource();
        if (celluleSurvolee != this.celluleCliquee){
            celluleSurvolee.setBackground(celluleSurvolee.getColor().darker());
        }
    }

    @Override
    public void mouseExited(MouseEvent e){
        Cellule celluleQuittee = (Cellule) e.getSource();
        if (celluleQuittee != this.celluleCliquee){
            celluleQuittee.setBackground(celluleQuittee.getColor());
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e){}

}