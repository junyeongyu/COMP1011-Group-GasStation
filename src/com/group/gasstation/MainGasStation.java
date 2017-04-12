package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainGasStation extends JFrame {
    private JPanel panelNorth, panelCentre,
                   panelFirstTab, panelSecondTab, panelThirdTab;
    private JTabbedPane tabTabPane;
    
    public MainGasStation() {
        super("Tabs");
        
        /// 1. Intialize all componentss
        tabTabPane = new JTabbedPane();
        panelNorth = new JPanel();
        panelCentre = new JPanel();
        
        /// 2. Set properties including eventhandlers
        tabTabPane.addTab("First Tab", null, createFirstTab(), "My First Tab");
        tabTabPane.addTab("Second Tab", null, createFirstTab(), "My Second Tab");
        tabTabPane.addTab("Third Tab", null, createFirstTab(), "My Third Tab");
        
        /// 3. Decide relationship between components
        panelNorth.setLayout(new GridLayout(3, 2));
        panelNorth.add(new JLabel("TEST"));
        panelNorth.add(new JLabel("TEST"));
        panelNorth.add(new JLabel("TEST"));
        panelNorth.add(new JLabel("TEST"));
        panelNorth.add(new JLabel("TEST"));
        panelNorth.add(new JLabel("TEST"));
        
        /// 4. Set components into current class
        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(tabTabPane, BorderLayout.CENTER);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private JPanel createFirstTab() {
        panelFirstTab = new PanelFirstTab(this);        
        return panelFirstTab;
    }
    private JPanel createSecondTab() {
        panelSecondTab = new PanelSecondTab(this);
        return panelFirstTab;
    }
    private JPanel createThirdTab() {
        panelThirdTab = new PanelThirdTab(this);
        return panelFirstTab;
    }
    
    public static void main(String[] args) {
        new MainGasStation();
    }
}
