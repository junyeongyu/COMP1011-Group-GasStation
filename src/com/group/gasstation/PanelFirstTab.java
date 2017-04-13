package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelFirstTab extends JPanel {
    private GasStation main;
    
    private JPanel pnlContainer, pnlPrice, pnlButton;
    private JLabel lblRegular, lblPlus, lblSupreme, lblType, lblPrice;
    private JTextField txtRPrice, txtPPrice, txtSPrice;
    private JButton btnUpdate;
    
    //public PanelFirstTab() {}
    public PanelFirstTab(GasStation mainGasStation) {
        main = mainGasStation;
        
        /// 1. Intialize all componentss
        pnlContainer = new JPanel();
        pnlContainer.setLayout(new BorderLayout());
        pnlPrice = new JPanel();
        pnlPrice.setLayout(new GridLayout(2,4));
        pnlButton = new JPanel();
        
        lblRegular = new JLabel("Regular");
        lblPlus = new JLabel("Plus");
        lblSupreme = new JLabel("Supreme");
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Price");
        
        txtRPrice = new JTextField(10);
        txtPPrice = new JTextField(10);
        txtSPrice = new JTextField(10);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setVisible(false);
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlPrice.add(lblType);
        pnlPrice.add(lblRegular);
        pnlPrice.add(lblPlus);
        pnlPrice.add(lblSupreme);
        pnlPrice.add(lblPrice);
        pnlPrice.add(txtRPrice);
        pnlPrice.add(txtPPrice);
        pnlPrice.add(txtSPrice);
        
        pnlButton.add(btnUpdate);
        
        pnlContainer.add(pnlPrice, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
        setVisible(true);
    }
}
