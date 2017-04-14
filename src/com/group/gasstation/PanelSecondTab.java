package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelSecondTab extends JPanel {
    private GasStation main;
    
    private JPanel pnlContainer, pnlAmount, pnlButton;
    private JLabel lblType, lblPrice;
    private JButton btnUpdate, btnReset;
        
    private List<JLabel> labelGasTypeList;
    private List<JTextField> textFieldGasCurrentList;
    
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
        
        labelGasTypeList = main.getLabelGasTypeList();
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Amount");
        textFieldGasCurrentList = main.gettextFieldGasCurrentList();
        
        btnUpdate = new JButton("Update");
        btnUpdate.setVisible(main.manager);
        
        btnReset = new JButton("Reset");
        btnReset.setVisible(main.manager);
        
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlAmount.add(lblType);
        for (JLabel labelGasType : labelGasTypeList) {
            pnlAmount.add(labelGasType);
        }
        pnlAmount.add(lblPrice);
        for (JTextField textFieldGasCurrent : textFieldGasCurrentList) {
            pnlAmount.add(textFieldGasCurrent);
        }
        
        pnlButton.add(btnUpdate, BorderLayout.EAST);
        pnlButton.add(btnReset, BorderLayout.WEST);
        
        pnlContainer.add(pnlAmount, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
    }
}
