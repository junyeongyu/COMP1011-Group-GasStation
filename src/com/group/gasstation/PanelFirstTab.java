package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelFirstTab extends JPanel {
    private GasStation main;
    
    private JPanel pnlContainer, pnlPrice, pnlButton;
    private JLabel lblType, lblPrice;
    private JTextField txtRPrice, txtPPrice, txtSPrice;
    private JButton btnUpdate;
    
    private List<JLabel> labelGasTypeList;
    private List<JTextField> textFieldGasCurrentList;
    
    //public PanelFirstTab() {}
    public PanelFirstTab(GasStation mainGasStation) {
        main = mainGasStation;
        
        /// 1. Intialize all componentss
        pnlContainer = new JPanel();
        pnlContainer.setLayout(new BorderLayout());
        pnlPrice = new JPanel();
        pnlPrice.setLayout(new GridLayout(2,4));
        pnlButton = new JPanel();
        
        labelGasTypeList = main.getLabelGasTypeList();
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Price");
        textFieldGasCurrentList = main.gettextFieldGasCurrentList();
        
        btnUpdate = new JButton("Update");
        btnUpdate.setVisible(main.manager);
        
        
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlPrice.add(lblType);
        for (JLabel labelGasType : labelGasTypeList) {
            pnlPrice.add(labelGasType);
        }
        pnlPrice.add(lblPrice);
        for (JTextField textFieldGasCurrent : textFieldGasCurrentList) {
            pnlPrice.add(textFieldGasCurrent);
        }
        pnlButton.add(btnUpdate);
        pnlContainer.add(pnlPrice, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
        setVisible(true);
    }
}
