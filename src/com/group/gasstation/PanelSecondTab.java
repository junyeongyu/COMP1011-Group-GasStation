package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelSecondTab extends JPanel {
    private GasStation main;
    
    private JPanel pnlContainer, pnlAmount, pnlButton;
    private JLabel lblRegular, lblPlus, lblSupreme, lblType, lblPrice;
    private JTextField txtRAmount, txtPAmount, txtSAmount;
    private JButton btnUpdate, btnReset;
    
    //public PanelFirstTab() {}
    public PanelSecondTab(GasStation mainGasStation) {
        main = mainGasStation;
        
        /// 1. Intialize all componentss
        pnlContainer = new JPanel();
        pnlContainer.setLayout(new BorderLayout());
        pnlAmount = new JPanel();
        pnlAmount.setLayout(new GridLayout(2,4));
        pnlButton = new JPanel();
        pnlButton.setLayout(new BorderLayout());
        
        lblRegular = new JLabel("Regular");
        lblPlus = new JLabel("Plus");
        lblSupreme = new JLabel("Supreme");
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Amount");
        
        txtRAmount = new JTextField(10);
        txtPAmount = new JTextField(10);
        txtSAmount = new JTextField(10);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setVisible(false);
        
        btnReset = new JButton("Reset");
        btnReset.setVisible(false);
        
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlAmount.add(lblType);
        pnlAmount.add(lblRegular);
        pnlAmount.add(lblPlus);
        pnlAmount.add(lblSupreme);
        pnlAmount.add(lblPrice);
        pnlAmount.add(txtRAmount);
        pnlAmount.add(txtPAmount);
        pnlAmount.add(txtSAmount);
        
        pnlButton.add(btnUpdate, BorderLayout.WEST);
        pnlButton.add(btnReset, BorderLayout.WEST);
        
        pnlContainer.add(pnlAmount, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
    }
}
