package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Vue associée à la deuxième vue du camembert
 * Cette classe gère l'affichage graphique du camembert et de sa légende
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 */

public class LeDeuxiemeCamembertView extends JPanel {
    private LeDeuxiemeCamembertModel model;
    private int[] sousMenuCounts;

    /**
     * Constructeur de la classe LeDeuxiemeCamembertView
     *
     * @param model Le modèle associé à la vue
     */
    public LeDeuxiemeCamembertView(LeDeuxiemeCamembertModel model) {
        this.model = model;
        this.sousMenuCounts = model.getSousMenuCounts();
        setLayout(new BorderLayout());
        add(createLegend(), BorderLayout.EAST);
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
                Color.RED, Color.BLUE, Color.YELLOW, Color.PINK, Color.CYAN, Color.MAGENTA,
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.GRAY, Color.BLACK,
                new Color(255, 165, 0), new Color(128, 0, 128), new Color(0, 128, 128),
                new Color(139, 69, 19), new Color(255, 192, 203), new Color(0, 0, 128)
        };

        ArrayList<Integer> uniqueSousMenus = new ArrayList<Integer>();
        for (int count : sousMenuCounts) {
            if (!uniqueSousMenus.contains(count)) {
                uniqueSousMenus.add(count);
            }
        }

        for (int i = 0; i < uniqueSousMenus.size(); i++) {
            JLabel label = new JLabel("Sous-menu ouvert : " + uniqueSousMenus.get(i));
            label.setForeground(predefinedColors[i % predefinedColors.length]);
            legendPanel.add(label);
        }

        return legendPanel;
    }

    /**
     * Cette méthode est appelée pour dessiner le camembert
     *
     * @param g L'objet Graphics utilisé pour dessiner
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color[] predefinedColors = {
                Color.RED, Color.BLUE, Color.YELLOW, Color.PINK, Color.CYAN, Color.MAGENTA,
                Color.LIGHT_GRAY, Color.DARK_GRAY, Color.GRAY, Color.BLACK,
                new Color(255, 165, 0), new Color(128, 0, 128), new Color(0, 128, 128),
                new Color(139, 69, 19), new Color(255, 192, 203), new Color(0, 0, 128)
        };

        List<Integer> uniqueSousMenus = new ArrayList<>();
        for (int count : model.getSousMenuCounts()) {
            if (!uniqueSousMenus.contains(count)) {
                uniqueSousMenus.add(count);
            }
        }

        double cx = getWidth() / 2.0;
        double cy = getHeight() / 2.0;
        double radius = Math.min(getWidth(), getHeight()) / 2.0 - 10;
        double totalSousMenus = uniqueSousMenus.stream().mapToDouble(Integer::doubleValue).sum();
        double startAngle = 0;
        int i = 0;
        for (int count : uniqueSousMenus) {
            double normalizedProportion = (totalSousMenus != 0) ? count / totalSousMenus : 0;
            double angle = normalizedProportion * 360;
            g2d.setColor(predefinedColors[i % predefinedColors.length]);
            g2d.fill(new Arc2D.Double(cx - radius, cy - radius, 2 * radius, 2 * radius, startAngle, angle, Arc2D.PIE));
            startAngle += angle;
            i++;
        }
    }
}
