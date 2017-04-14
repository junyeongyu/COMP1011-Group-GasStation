package com.group.gasstation;

import com.group.gasstation.db.DBManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class GasStation extends JFrame {
    protected DBManager db;
    protected JFrame frameSunoco;
    private JPanel panelNorth, panelCentre,
                   panelFirstTab, panelSecondTab;
    private JTabbedPane tabTabPane;
    private JLabel labelId, labelPassword;
    private JTextField textFieldId, textFieldPassword;
    private JButton buttonLogin;
    
    protected int id;
    protected boolean manager;
    
    public GasStation() {
        super("Tabs");
        
        /// 1. Intialize all components
        frameSunoco = new FrameSunoco(this);
        tabTabPane = new JTabbedPane();
        panelNorth = new JPanel();
        panelCentre = new JPanel();
        labelId = new JLabel("ID");
        labelPassword = new JLabel("Password");
        textFieldId = new JTextField(10);
        textFieldPassword = new JTextField(10);
        buttonLogin = new JButton("Login");
        
        /// 2. Set properties including eventhandlers
        frameSunoco.setVisible(false);
        buttonLogin.addActionListener((e) -> {
            // Check login is successful
            Map<String, Object> result = db.getObject(String.format("SELECT id, password FROM user WHERE id = %s AND password= '%s'", 
                    textFieldId.getText(), textFieldPassword.getText()));
            if (result == null) {
                JOptionPane.showMessageDialog(null, "Your ID or password is wrong");
                return;
            }
            id = (Integer) result.get("id");;
            JOptionPane.showMessageDialog(null, "Login is successful.");
            
            // Save employee information
            result = db.getObject(String.format("SELECT manager from employee where id = %d", id));
            System.out.println(result.get("manager"));
            manager = ((int) result.get("manager")) == 1;
            System.out.println(manager);
            
        });
        // Tabs
        tabTabPane.addTab("First Tab", null, createFirstTab(), "My First Tab");
        tabTabPane.addTab("Second Tab", null, createSecondTab(), "My Second Tab");
        
        // TODO: Test (it will be move into neccessary functions)
        openPopup();
        
        /// 3. Decide relationship between components
        panelNorth.setLayout(new GridLayout(3, 2));
        panelNorth.add(labelId);
        panelNorth.add(textFieldId);
        panelNorth.add(labelPassword);
        panelNorth.add(textFieldPassword);
        panelNorth.add(new JPanel());
        panelNorth.add(buttonLogin);
        
        /// 4. Set components into current class
        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(tabTabPane, BorderLayout.CENTER);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        frameSunoco.setVisible(true);
    }
}
