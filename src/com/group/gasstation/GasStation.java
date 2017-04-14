package com.group.gasstation;

import com.group.gasstation.db.DBManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class GasStation extends JFrame {
    protected DBManager db;
    protected JFrame frameSunoco;
    private JPanel panelNorth, panelCentre,
                   panelFirstTab, panelSecondTab;
    private JTabbedPane tabTabPane;
    private JLabel labelId, labelPassword;
    private JTextField textFieldId;
    private JPasswordField passwordFieldPassword;
    private JButton buttonLogin, buttonPump;
    
    protected int id;
    protected boolean manager;
    
    public GasStation() {
        super("Tabs");
        
        /// 1. Intialize all components
        //frameSunoco = new FrameSunoco(this);
        tabTabPane = new JTabbedPane();
        panelNorth = new JPanel();
        panelCentre = new JPanel();
        labelId = new JLabel("ID");
        labelPassword = new JLabel("Password");
        textFieldId = new JTextField(10);
        passwordFieldPassword = new JPasswordField(10);
        buttonLogin = new JButton("Login");
        buttonPump = new JButton("Pump");
        
        /// 2. Set properties including eventhandlers
        //frameSunoco.setVisible(false);
        buttonLogin.addActionListener((e) -> {
            
            // 0) Validation of form
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input ID");
                return;
            } else if (passwordFieldPassword.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Please input Password");
                return;
            }
            
            // 1) Check login is successful
            Map<String, Object> result = db.getObject(String.format("SELECT id, password FROM user WHERE id = %s AND password= '%s'", 
                    textFieldId.getText(), String.valueOf(passwordFieldPassword.getPassword())));
            if (result == null) {
                JOptionPane.showMessageDialog(null, "Your ID or password is wrong");
                return;
            }
            id = (Integer) result.get("id");;
            
            // 2) Save employee information
            result = db.getObject(String.format("SELECT manager from employee where id = %d", id));
            manager = ((int) result.get("manager")) == 1;
            
            // 3) Show tab menu if login is successful
            tabTabPane.removeAll(); // in case, user login more than one times
            tabTabPane.setVisible(true);
            tabTabPane.addTab("First Tab", null, createFirstTab(), "My First Tab"); // chnage the location due to life cycle issue
            tabTabPane.addTab("Second Tab", null, createSecondTab(), "My Second Tab"); // chnage the location due to life cycle issue
            
            // 4) Show pump
            buttonPump.setVisible(true);
            
            JOptionPane.showMessageDialog(null, "Login is successful.");
        });
        buttonPump.setVisible(false);
        buttonPump.addActionListener((e)->{
            openPopup();
        });
        // Tabs
        tabTabPane.setVisible(false);
        
        // TODO: Test (it will be move into neccessary functions)
        //openPopup();
        
        /// 3. Decide relationship between components
        panelNorth.setLayout(new GridLayout(3, 2));
        panelNorth.add(labelId);
        panelNorth.add(textFieldId);
        panelNorth.add(labelPassword);
        panelNorth.add(passwordFieldPassword);
        panelNorth.add(buttonPump);
        panelNorth.add(buttonLogin);
        
        /// 4. Set components into current class
        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(tabTabPane, BorderLayout.CENTER);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Center the window
        //pack();
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
        //frameSunoco.setVisible(true);
        frameSunoco = new FrameSunoco(this); // Initialize instance this moment due to life cycle issue
    }
    protected List<JLabel> getLabelGasTypeList() {
        List<JLabel> labelGasTypeList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, gas_name FROM gas_type ORDER BY id ASC");
        for (Map<String, Object> gasType: gasTypeList) {
            labelGasTypeList.add(new JLabel((String) gasType.get("gas_name")));
        }
        return labelGasTypeList;
    }
    protected List<JTextField> getTextFieldGasCurrentList(String field) {
        List<JTextField> labelGasCurrentList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, amount, price FROM gas_current ORDER BY id ASC");
        for (Map<String, Object> gasType: gasTypeList) {
            labelGasCurrentList.add(new JTextField(String.valueOf(gasType.get(field))));
        }
        return labelGasCurrentList;
    }
    protected Map<Integer,JButton> getButtonGasTypeMap() {
        Map<Integer,JButton> map = new LinkedHashMap<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, gas_name FROM gas_type ORDER BY id ASC");
        for (Map<String, Object> gasType: gasTypeList) {
            map.put((Integer) gasType.get("id"), new JButton((String)gasType.get("gas_name")));
        }
        return map;
    }

    Map<Integer, JTextField> getTextFieldGasCurrentMap(String field) {
        Map<Integer,JTextField> map = new LinkedHashMap<>();
        List<Map<String, Object>> gasCurrentList = db.getList("SELECT id, amount, price FROM gas_current ORDER BY id ASC");
        for (Map<String, Object> gasType: gasCurrentList) {
            map.put((Integer) gasType.get("id"), new JTextField(String.format("%.2f", gasType.get(field))));
        }
        return map;
    }
}