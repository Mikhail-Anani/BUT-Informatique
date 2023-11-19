package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import javax.swing.*;
import javax.swing.tree.*;
import java.sql.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Vue d'informations sur un protocole
 * Affiche les détails et les informations sur un protocole, y compris sa description
 * et une structure arborescente (arbre) correspondant à son menu
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class ProtocolInfoView {
    private JFrame frame;
    private JLabel descriptionLabel;
    private JTree menuTree;
    private int idProtocol1;
    private ProtocolModel protocolModel;
    private int idResultat;

    /**
     * Constructeur de ProtocolInfoView
     *
     * @param description La description du protocole
     * @param menuName    Le nom du menu associé au protocole
     * @param idProtocole L'identifiant du protocole
     */
    public ProtocolInfoView(String description, int menuName, int idProtocole) {
        idProtocol1 = idProtocole;
        protocolModel = new ProtocolModel();
        frame = new JFrame("Détails du Protocole");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Utiliser DISPOSE_ON_CLOSE peremt de ne pas quitter tout le programme
        frame.setSize(600, 600);

        descriptionLabel = new JLabel("<html><p style='width:200px;'>" + description + "</p></html>");

        DefaultMutableTreeNode rootNode;
        rootNode = createMenuTreeForMenu1();
        menuTree = new JTree(rootNode);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5; // Divise la largeur en deux parties égales
        constraints.weighty = 1.0;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel.add(new JScrollPane(descriptionLabel), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel.add(new JScrollPane(menuTree), constraints);

        frame.add(panel);
        frame.setVisible(true);

        idResultat = protocolModel.insertIntoResultat(idProtocol1);
        
        // Ajout de l'écouteur de fermeture de la fenêtre
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                handleWindowClosing();
            }
        });

        // Ajout de l'écouteur pour la sélection d'un nœud dans l'arbre
        menuTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) menuTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                String nodeInfo = node.getUserObject().toString();

                if (idResultat != -1) {
                    if (node.isLeaf()) {
                        int idOption = protocolModel.getOptionId(nodeInfo);
                        protocolModel.saveToDatabase(idOption, idResultat);
                        frame.dispose();
                    } else {
                        int idOption = protocolModel.getOptionId(nodeInfo);
                        protocolModel.saveToDatabase(idOption, idResultat);
                    }
                }
            }
        });
    }

    /**
     * Gère la fermeture de la fenêtre en effectuant des actions spécifiques
     * Si un résultat est associé à l'ID spécifié et qu'une option n'est pas sélectionnée,
     * alors l'option sélectionnée est retirée du résultat
     */
    private void handleWindowClosing() {
        // Gestion de la fermeture de la fenêtre
        if (idResultat != -1) {
            if (!leafNodeSelected()) {
                protocolModel.removeFromResultat(idResultat);
            }
        }
    }

    /**
     * Vérifie si le dernier nœud sélectionné dans l'arbre de menus est une option
     *
     * @return true si une option est sélectionnée, false sinon
     */
    private boolean leafNodeSelected() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) menuTree.getLastSelectedPathComponent();
        return (node != null && node.isLeaf());
    }


    /**
     * Crée une structure arborescente (arbre) pour le menu 1.
     *
     * @return La racine de l'arbre du menu 1
     */
    private DefaultMutableTreeNode createMenuTreeForMenu1() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu 1");
        ProtocolModel protocolModel = new ProtocolModel();
        int idOption = protocolModel.getMenuForProtocol(idProtocol1);
        String rootName = protocolModel.getMenuName(idOption);
        root = new DefaultMutableTreeNode(rootName);
        addChildrenToNode(root, idOption);
        return root;
    }


    /**
     * Ajoute les enfants d'un noeud à partir de l'identifiant de l'option de menu parent.
     *
     * @param node     Le noeud parent
     * @param idParent L'identifiant de l'option de menu parent
     */
    private void addChildrenToNode(DefaultMutableTreeNode node, int idParent) {
        ProtocolModel protocolModel = new ProtocolModel();
        ResultSet rs = protocolModel.getMenuChildren(idParent);
        try {
            while (rs.next()) {
                int idOption = rs.getInt("IdOption");
                String name = rs.getString("Nom");
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(name);
                node.add(childNode);
                addChildrenToNode(childNode, idOption);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Protocole invalide.");
            e.printStackTrace();
        }
    }
}