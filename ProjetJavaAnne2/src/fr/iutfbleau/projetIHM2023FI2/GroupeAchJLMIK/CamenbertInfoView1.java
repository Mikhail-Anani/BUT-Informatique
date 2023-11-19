package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Cette classe représente une vue affichant les détails d'un protocole sous la forme d'un camembert
 * Elle inclut également une légende pour expliquer chaque portion du camembert
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class CamenbertInfoView1 extends JComponent {
    private JFrame frame;
    private ArrayList<Double> proportion;
    private ArrayList<String> actions;
    private String BonneAction;

    /**
     * Constructeur de la classe CamenbertInfoView1.
     *
     * @param proportions Liste des proportions pour chaque action
     * @param actions     Liste des noms d'actions
     * @param BonneAction Nom de la bonne action
     * @param protocolId  Identifiant du protocole associé
     */
    public CamenbertInfoView1(ArrayList<Double> proportions, ArrayList<String> actions, String BonneAction, String protocolId) {
        this.proportion = proportions;
        this.actions = actions;
        this.BonneAction=BonneAction;

        frame = new JFrame("Détails du Protocole");
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.add(createLegend(), BorderLayout.EAST);

        JButton toggleButton = new JButton("Basculer");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Ferme la fenêtre actuelle
                LeDeuxiemeCamembertModel model = new LeDeuxiemeCamembertModel(protocolId);
                LeDeuxiemeCamembertView view = new LeDeuxiemeCamembertView(model);
                LeDeuxiemeCamembertController controller = new LeDeuxiemeCamembertController(model, view);
                JFrame fen=new JFrame("Second Camemebert");
                fen.add(view); // Ouvre une nouvelle fenêtre
                fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fen.setSize(700, 400);
                fen.setVisible(true);
            }
        });
        frame.add(toggleButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    /**
     * Crée un panneau légende pour expliquer chaque portion du camembert
     *
     * @return Un JPanel contenant la légende
     */
    private JPanel createLegend() {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
    
        Color[] predefinedColors = {
                Color.RED, Color.BLUE, Color.PINK, Color.CYAN, Color.MAGENTA,
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.GRAY, Color.BLACK,
                new Color(255, 165, 0), new Color(128, 0, 128), new Color(0, 128, 128),
                new Color(139, 69, 19), new Color(255, 192, 203), new Color(0, 0, 128)
        };
    
        if (proportion != null && !proportion.isEmpty() && actions != null) {
            for (int i = 0; i < proportion.size(); i++) {
                String action = (i < actions.size()) ? actions.get(i) : "Unknown Action";
                JLabel label = new JLabel(action);
    
                // Vérifie si l'action actuelle est la bonne action
                if (action.equals(this.BonneAction)) {
                    label.setForeground(Color.GREEN);  // Utilise la couleur verte pour la bonne action
                } else {
                    label.setForeground(predefinedColors[i % predefinedColors.length]);
                }
    
                legendPanel.add(label);
            }
        }
    
        return legendPanel;
    }
    

    /**
     * Cette méthode est appelée pour dessiner le camembert en utilisant Java Graphics
     *
     * @param g L'objet Graphics utilisé pour dessiner
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color[] predefinedColors = {
                Color.RED, Color.BLUE, Color.PINK, Color.CYAN, Color.MAGENTA,
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.GRAY, Color.BLACK,
                new Color(255, 165, 0), new Color(128, 0, 128), new Color(0, 128, 128),
                new Color(139, 69, 19), new Color(255, 192, 203), new Color(0, 0, 128)
        };

        if (proportion != null && !proportion.isEmpty()) {
            double cx = getWidth() / 2;
            double cy = getHeight() / 2;
            double radius = Math.min(getWidth(), getHeight()) / 2 - 10;

            double totalProportion = proportion.stream().mapToDouble(Math::abs).sum();

            double startAngle = 0;

            int i = 0;

            for (double p : proportion) {

                if (p < 0.0) {
                    g2d.setColor(Color.GREEN);
                } else {
                    g2d.setColor(predefinedColors[i % predefinedColors.length]);
                }

                double normalizedProportion = Math.abs(p) / totalProportion;
                double angle = normalizedProportion * 360;

                g2d.fill(new Arc2D.Double(cx - radius, cy - radius, 2 * radius, 2 * radius, startAngle, angle, Arc2D.PIE));

                startAngle += angle;

                // Incrémentation après avoir utilisé la valeur de i
                i++;
            }
        }
    }
}
