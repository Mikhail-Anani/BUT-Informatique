package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Vue de l'application de protocole
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 */
public class CamenbertView {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    /**
     * Constructeur de la vue
     */
    public CamenbertView() {
        frame = new JFrame("Application de Protocole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        label = new JLabel("Veuillez choisir un protocole:");
        textField = new JTextField(20);
        button = new JButton("Valider");

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
     /**
     * Ajoute un auditeur d'événement au bouton de validation
     *
     * @param listener L'auditeur d'événement à ajouter
     */
    public void addValidationListener(ActionListener listener) {
        button.addActionListener(listener);
    }

/**
     * Renvoie la fenêtre JFrame associée à cette vue
     *
     * @return La fenêtre JFrame associée à cette vue
     */
     public JFrame getFrame() {
        return frame;
    }
    /**
     * Définit le JFrame de la vue
     *
     * @param frame Le JFrame à définir
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Récupère la valeur du champ de protocole
     *
     * @return La valeur du champ de protocole
     */

    public String getProtocolInput() {
        return textField.getText();
    }

    /**
     * Affiche un message à l'utilisateur
     *
     * @param message Le message à afficher
     */

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
