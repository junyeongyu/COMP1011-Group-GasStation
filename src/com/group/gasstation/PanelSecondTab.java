package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class PanelSecondTab extends JPanel
{
    private final GasStation station;
    
    private final JPanel pnlContainer, pnlAmount, pnlButton;
    private final JLabel lblType, lblPrice;
    private final JButton btnUpdate, btnReset;
        
    private final Map<Integer, JLabel> labelGasTypeMap;
    private final Map<Integer, JTextField> textFieldGasCurrentMap;
    
    /**
     * Constructor
     * 
     * @param gasStation contains JTabbedPane that holds this object
     */
    public PanelSecondTab(GasStation gasStation)
    {
        station = gasStation;
        
        /// 1. Intialize all componentss
        pnlContainer = new JPanel();
        pnlContainer.setLayout(new BorderLayout());
        pnlAmount = new JPanel();
        pnlAmount.setLayout(new GridLayout(2, 4));
        pnlAmount.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlButton = new JPanel();
        pnlButton.setLayout(new BorderLayout());
        
        labelGasTypeMap = station.getLabelGasTypeMap();
        lblType = new JLabel("Gas Type");
        lblType.setBorder(BorderFactory.createLineBorder(Color.black));
        lblType.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPrice = new JLabel("Amount");
        lblPrice.setBorder(BorderFactory.createLineBorder(Color.black));
        lblPrice.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        textFieldGasCurrentMap = station.getTextFieldGasCurrentMap("amount");
        
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnUpdate.setVisible(station.getManager());
        
        btnReset = new JButton("Reset");
        btnReset.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnReset.setVisible(station.getManager());
        
        /// 2. Set properties including eventhandlers
        
        
        /// 3. Decide relationship between components
        pnlAmount.add(lblType);
        for(Map.Entry<Integer, JLabel> entry : labelGasTypeMap.entrySet())
        {
            JLabel labelGasTypeValue = entry.getValue();
            pnlAmount.add(labelGasTypeValue);
        }
        pnlAmount.add(lblPrice);
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            pnlAmount.add(textFieldGasCurrentValue);
        }
        
        pnlButton.add(btnUpdate, BorderLayout.EAST);
        pnlButton.add(btnReset, BorderLayout.WEST);
        
        pnlContainer.add(pnlAmount, BorderLayout.CENTER);
        pnlContainer.add(pnlButton, BorderLayout.SOUTH);
        
        /// 4. Set components into current class
        add(pnlContainer);
        setSize(800, 800);
    }
    
    public void load(boolean manager)
    {
        btnUpdate.setVisible(manager);
        btnReset.setVisible(manager);
        
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            textFieldGasCurrentValue.setEditable(manager);
        }
    }
}
