package com.group.gasstation;

import com.group.gasstation.db.DBManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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

public class GasStation extends JFrame
{
    private DBManager db;
    private FrameSunoco frameSunoco;
    private JPanel panelNorth;
    private PanelFirstTab panelFirstTab;
    private PanelSecondTab panelSecondTab;
    private JTabbedPane tabTabPane;
    private JLabel labelId, labelPassword;
    private JTextField textFieldId;
    private JPasswordField passwordFieldPassword;
    private JButton buttonLogin, buttonPump;
    
    private int id;
    private boolean manager;
    
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 450;
    
    public GasStation()
    {
        super("Tabs");
        
        /// 1. Intialize all components
        //frameSunoco = new FrameSunoco(this);
        tabTabPane = new JTabbedPane();
        panelNorth = new JPanel();
        labelId = new JLabel("ID");
        labelPassword = new JLabel("Password");
        textFieldId = new JTextField(10);
        passwordFieldPassword = new JPasswordField(10);
        buttonLogin = new JButton("Login");
        buttonPump = new JButton("Pump");
        
        /// 2. Set properties including eventhandlers
        //frameSunoco.setVisible(false);
        textFieldId.addActionListener((e) -> {
            passwordFieldPassword.grabFocus();
        });
        passwordFieldPassword.addActionListener((e) -> {
            buttonLogin.doClick();
        });
        buttonLogin.addActionListener((e) -> {
            // 0) Validation of form
            if (textFieldId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please input ID");
            }
            else if (passwordFieldPassword.getPassword().length == 0)
            {
                JOptionPane.showMessageDialog(null, "Please input Password");
            }
            else
            {
                // 1) Check login is successful
                Map<String, Object> result = db.getObject(String.format("SELECT id, password FROM user WHERE id = %s AND password= '%s'", 
                        textFieldId.getText(), String.valueOf(passwordFieldPassword.getPassword())));
                if (result == null)
                {
                    JOptionPane.showMessageDialog(null, "Your ID or password is wrong");
                }
                else
                {
                    // Wipe Text Fields if login is successful
                    textFieldId.setText("");
                    passwordFieldPassword.setText("");

                    id = (Integer) result.get("id");

                    // 2) Save employee information
                    result = db.getObject(String.format("SELECT manager from employee where id = %d", id));
                    manager = ((int) result.get("manager")) == 1;

                    // 3) Show tab menu if login is successful
                    tabTabPane.removeAll(); // in case, user login more than one times
                    tabTabPane.setVisible(true);
                    tabTabPane.addTab("First Tab", null, new PanelFirstTab(this), "My First Tab"); // change the location due to life cycle issue
                    tabTabPane.addTab("Second Tab", null, new PanelSecondTab(this), "My Second Tab"); // change the location due to life cycle issue

                    // 4) Show pump
                    buttonPump.setVisible(true);

                    // 5) Show buttons for managers
                    panelFirstTab.load(manager);
                    panelSecondTab.load(manager);

                    JOptionPane.showMessageDialog(null, "Login is successful.");
                }
            }
        });
        buttonPump.setVisible(false);
        buttonPump.addActionListener((e) -> {
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
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Center the window
        setVisible(true);
    }
    
    protected FrameSunoco getSunocoFrame()
    {
        return frameSunoco;
    }
    
    protected DBManager getDBManager()
    {
        return db;
    }
    
    protected void setDBManager(DBManager manager)
    {
        db = manager;
    }
    
    protected boolean getManager()
    {
        return manager;
    }
    
    protected void openPopup() // called from child component
    {
        // Initialize instance this moment due to life cycle issue
        frameSunoco = new FrameSunoco(this);
    }
    
    protected List<JLabel> getLabelGasTypeList()
    {
        List<JLabel> labelGasTypeList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, gas_name FROM gas_type ORDER BY id ASC");
        
        for (Map<String, Object> gasType : gasTypeList)
        {
            labelGasTypeList.add(new JLabel((String) gasType.get("gas_name")));
        }
        
        return labelGasTypeList;
    }
    
    protected List<JTextField> getTextFieldGasCurrentList(String field)
    {
        List<JTextField> labelGasCurrentList = new ArrayList<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, amount, price FROM gas_current ORDER BY id ASC");
        
        for (Map<String, Object> gasType : gasTypeList)
        {
            labelGasCurrentList.add(new JTextField(String.valueOf(gasType.get(field))));
        }
        
        return labelGasCurrentList;
    }
    
    protected Map<Integer,JButton> getButtonGasTypeMap()
    {
        Map<Integer,JButton> map = new LinkedHashMap<>();
        List<Map<String, Object>> gasTypeList = db.getList("SELECT id, gas_name FROM gas_type ORDER BY id ASC");
        
        for (Map<String, Object> gasType: gasTypeList)
        {
            map.put((Integer) gasType.get("id"), new JButton((String)gasType.get("gas_name")));
        }
        
        return map;
    }

    protected Map<Integer, JTextField> getTextFieldGasCurrentMap(String field)
    {
        Map<Integer,JTextField> map = new LinkedHashMap<>();
        List<Map<String, Object>> gasCurrentList = db.getList("SELECT id, amount, price FROM gas_current ORDER BY id ASC");
        
        for (Map<String, Object> gasType : gasCurrentList)
        {
            map.put((Integer) gasType.get("id"), new JTextField(String.format("%.2f", gasType.get(field))));
        }
        
        return map;
    }
}