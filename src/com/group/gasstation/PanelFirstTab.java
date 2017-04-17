package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class PanelFirstTab extends JPanel
{
    private final GasStation station;
    
    private final JPanel pnlContainer, pnlPrice, pnlButton;
    private final JLabel lblType, lblPrice;
    private final JButton btnUpdate;
    
    private final Map<Integer, JLabel> labelGasTypeMap;
    private final Map<Integer, JTextField> textFieldGasCurrentMap;
    
    /**
     * Constructor
     * 
     * @param gasStation contains JTabbedPane that holds this object
     */
    public PanelFirstTab(GasStation gasStation)
    {
        station = gasStation;
        
        /// 1. Intialize all componentss
        pnlContainer = new JPanel();
        pnlContainer.setLayout(new BorderLayout());
        pnlPrice = new JPanel();
        pnlPrice.setLayout(new GridLayout(2,4));
        pnlButton = new JPanel();
        
        labelGasTypeMap = station.getLabelGasTypeMap();
        lblType = new JLabel("Gas Type");
        lblPrice = new JLabel("Price");
        textFieldGasCurrentMap = station.getTextFieldGasCurrentMap("price");
        
        btnUpdate = new JButton("Update");
        btnUpdate.setVisible(station.getManager());
        
        
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlPrice.add(lblType);
        for(Map.Entry<Integer, JLabel> entry : labelGasTypeMap.entrySet())
        {
            JLabel labelGasTypeValue = entry.getValue();
            pnlPrice.add(labelGasTypeValue);
        }
        pnlPrice.add(lblPrice);
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            pnlPrice.add(textFieldGasCurrentValue);
        }
        pnlButton.add(btnUpdate);
        pnlContainer.add(pnlPrice, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
        setVisible(true);
    }
    
    public void load(boolean manager)
    {
        btnUpdate.setVisible(manager);
        
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            textFieldGasCurrentValue.setEditable(manager);
        }
    }
}
