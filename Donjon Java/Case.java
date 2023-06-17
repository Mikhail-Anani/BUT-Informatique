
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Case extends JLabel {
    private ImageIcon icon;
    private String nom;


    public Case() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        super.setIcon(icon);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getnom(){
        return nom;
    }

    public void setnom(String nom){
        this.nom=nom;
    }
}
