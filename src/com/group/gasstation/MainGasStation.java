package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainGasStation extends JFrame {
    protected JFrame frameSunoco;
    private JPanel panelNorth, panelCentre,
                   panelFirstTab, panelSecondTab;
    private JTabbedPane tabTabPane;
    
    public MainGasStation() {
        super("Tabs");
        
        /// 1. Intialize all components
        frameSunoco = new FrameSunoco(this);
        tabTabPane = new JTabbedPane();
        panelNorth = new JPanel();
        panelCentre = new JPanel();
        
        /// 2. Set properties including eventhandlers
        frameSunoco.setVisible(false);
        tabTabPane.addTab("First Tab", null, createFirstTab(), "My First Tab");
        tabTabPane.addTab("Second Tab", null, createSecondTab(), "My Second Tab");
        
        // TODO: Test (it will be move into neccessary functions)
        openPopup();
        
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
        return panelSecondTab;
    }
    protected void openPopup() { // called from child component
        frameSunoco.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainGasStation();
    }
}
