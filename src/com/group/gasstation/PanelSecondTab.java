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

/**
 * PanelSecondTab provide updating and resetting gas amount.
 */
public final class PanelSecondTab extends JPanel
{
    private final GasStation station;
    
    private final JPanel pnlContainer, pnlAmount, pnlButton;
    private final JLabel lblType, lblPrice;
    private final JButton btnUpdate, btnReset;
        
    private final Map<Integer, JLabel> labelGasTypeMap;
    private final Map<Integer, JTextField> textFieldGasCurrentMap;
    
    /**
     * Constructor with one parameter
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
        lblType.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPrice = new JLabel("Amount");
        lblPrice.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        textFieldGasCurrentMap = station.getTextFieldGasCurrentMap("amount");
        
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnUpdate.setVisible(station.getManager());
        
        btnReset = new JButton("Reset");
        btnReset.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        btnReset.setVisible(station.getManager());
        
        /// 2. Set properties including eventhandlers
        btnReset.addActionListener((e) -> {
            for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
            {
                JTextField textFieldGasCurrentValue = entry.getValue(); // gas amount
                textFieldGasCurrentValue.setText("1000");
            }
        });
        btnUpdate.addActionListener((e) -> {
            boolean isSuccess = true;
            for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
            {
                int id = entry.getKey(); // gas id
                JTextField textFieldGasCurrentValue = entry.getValue(); // gas price
                
                String sql = "UPDATE gas_current SET amount = %s WHERE id = %d";
                if (station.isNumeric(textFieldGasCurrentValue.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Value needs to be numbers. (id: " + id + ").");
                    isSuccess = false;
                    continue;
                }
                int result = station.getDBManager().execute(String.format(sql, textFieldGasCurrentValue.getText(), id));
                if (result == DBManager.FAIL) {
                    isSuccess = false;
                    JOptionPane.showMessageDialog(null, "Fail for updating gas amonut. (id: " + id + ").");
                }
            }
            if (isSuccess) {
                JOptionPane.showMessageDialog(null, "All data is successful updated.");
            }
        });
        
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
    
    /**
     * Showing the update and reset buttons only for manager
     * @param manager 
     */
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
