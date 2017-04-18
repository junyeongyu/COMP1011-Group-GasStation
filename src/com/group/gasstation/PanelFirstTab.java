package com.group.gasstation;

import com.group.gasstation.db.DBManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        pnlPrice.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlButton = new JPanel();
        
        labelGasTypeMap = station.getLabelGasTypeMap();
        lblType = new JLabel("Gas Type");
        lblType.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        textFieldGasCurrentMap = station.getTextFieldGasCurrentMap("price");
        
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnUpdate.setVisible(station.getManager());
        
        
        /// 2. Set properties including eventhandlers
        pnlContainer.setLayout(new BorderLayout());
        btnUpdate.addActionListener((e) -> {
            boolean isSuccess = true;
            for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
            {
                int id = entry.getKey(); // gas id
                JTextField textFieldGasCurrentValue = entry.getValue(); // gas price
                
                String sql = "UPDATE gas_current SET price = %s WHERE id = %d";
                if (station.isNumeric(textFieldGasCurrentValue.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Value needs to be numbers. (id: " + id + ").");
                    isSuccess = false;
                    continue;
                }
                int result = station.getDBManager().execute(String.format(sql, textFieldGasCurrentValue.getText(), id));
                if (result == DBManager.FAIL) {
                    isSuccess = false;
                    JOptionPane.showMessageDialog(null, "Fail for updating gas price. (id: " + id + ").");
                }
            }
            if (isSuccess) {
                JOptionPane.showMessageDialog(null, "All data is successful updated.");
            }
        });
        
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
