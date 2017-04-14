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
        
        labelGasTypeList = getLabelGasTypeList();
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Price");
        textFieldGasCurrentList = gettextFieldGasCurrentList();
        
        btnUpdate = new JButton("Update");
        System.out.println(main.manager);
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

    private List<JLabel> getLabelGasTypeList() {
        List<JLabel> labelGasTypeList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = main.db.getList("SELECT id, gas_name FROM gas_type");
        for (Map<String, Object> gasType: gasTypeList) {
            labelGasTypeList.add(new JLabel((String) gasType.get("gas_name")));
        }
        return labelGasTypeList;
    }

    private List<JTextField> gettextFieldGasCurrentList() {
        List<JTextField> labelGasCurrentList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = main.db.getList("SELECT id, amount, price FROM gas_current");
        for (Map<String, Object> gasType: gasTypeList) {
            labelGasCurrentList.add(new JTextField(String.valueOf(gasType.get("price"))));
        }
        return labelGasCurrentList;
    }
}
