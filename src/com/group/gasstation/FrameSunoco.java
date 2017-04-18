package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * This class provides GUI for user to pump the gas.
 */
public class FrameSunoco extends JFrame {
    // Panels
    private final JPanel panelNorth, panelWest, panelCenter, panelEast, panelSouth,
            panelSouthFirst, panelSouthSecond, panelSouthSecondSouth,
            panelCenterFirst, panelCenterSecond, panelCenterThird, panelCenterFourth, panelCenterFifth, panelCenterSixth;
    
    // Labels
    private final JLabel labelSale, labelSaleA, labelSaleB, labelSaleC, labelSaleD, labelSaleE,
            labelLiter, labelLiterA, labelLiterB, labelLiterC, labelLiterD, labelLiterE,
            labelSunoco, labelPriceValue, labelPrice, labelPreset;
    
    // TextFields
    private final Map<Integer, JTextField> textFieldGasCurrentMap;
    
    // Buttons
    private final JButton buttonStart, buttonCompleteSales;
    private final Map<Integer, JButton> buttonGasTypeMap;
    
    // Sliders
    private final JSlider sliderPreset;
    
    // Borders
    private final Border border, borderSoft, margin;
    
    // Fonts
    private final Font bigBoldFont, smallBoldFont, normalBoldFont;
    
    // Form size
    private final int FRAME_WIDTH = 620;
    private final int FRAME_HEIGHT = 670;
    private final int FRAME_HEIGHT_SOUTH = 240;
    
    // Preset Amount
    private int presetAmount = 0;
    
    private double gasLiters = 0, gasPrice = 0, gasPricePerLiter = 0;
    
    // true while pumping
    private boolean isPumping = false;
    
    // Gas id - selected by user
    private int gasId;
    
    /**
     * Constructor
     * 
     * @param station Instance of GasStation that leads to FrameSunoco
     */
    public FrameSunoco(GasStation station)
    {
        super("Pump");
        
        /// 1. Intialize all componentss
        // Panels
        panelNorth = new JPanel();
        panelWest = new JPanel();
        panelCenter = new JPanel();
        panelEast = new JPanel();
        panelSouth = new JPanel();
        panelSouthFirst = new JPanel();
        panelSouthSecond = new JPanel();
        panelSouthSecondSouth = new JPanel();
        panelCenterFirst = new JPanel();
        panelCenterSecond = new JPanel();
        panelCenterThird = new JPanel();
        panelCenterFourth = new JPanel();
        panelCenterFifth = new JPanel();
        panelCenterSixth = new JPanel();
        // Labels
        labelSunoco = new JLabel("SUNOCO");
        labelSaleA = new JLabel("0");
        labelSaleB = new JLabel("0");
        labelSaleC = new JLabel("0");
        labelSaleD = new JLabel("0");
        labelSaleE = new JLabel("0");
        labelSale = new JLabel("This Sale $");
        labelLiterA = new JLabel("0");
        labelLiterB = new JLabel("0");
        labelLiterC = new JLabel("0");
        labelLiterD = new JLabel("0");
        labelLiterE = new JLabel("0");
        labelLiter = new JLabel("Liters");
        labelPriceValue = new JLabel("0.00");
        labelPrice = new JLabel("Price Per Liter ¢");
        labelPreset = new JLabel("Preset Purchase Amount");
        // TextFields (Gas grade prices)
        textFieldGasCurrentMap = station.getTextFieldGasCurrentMap("price");
        // Buttons
        buttonGasTypeMap = station.getButtonGasTypeMap();
        buttonStart = new JButton("Start");
        buttonCompleteSales = new JButton("Complete Sales");
        // Slider
        sliderPreset = new JSlider(0, 200);
        // Borders
        border = BorderFactory.createRaisedBevelBorder();
        borderSoft = BorderFactory.createRaisedSoftBevelBorder();
        margin = new EmptyBorder(5,5,5,5); // new CompoundBorder(border, margin)
        // Fonts
        bigBoldFont = new Font("Ariel", Font.BOLD, 52);
        smallBoldFont = new Font("Ariel", Font.BOLD, 24);
        normalBoldFont = new Font("Ariel", Font.BOLD, 20);
        
        /// 2. Set properties including eventhandlers
        // Panels
        panelNorth.setBorder(border);
        panelWest.setBorder(border);
        panelCenter.setBorder(borderSoft);
        panelCenterFirst.setBorder(border);
        panelCenterThird.setBorder(border);
        panelEast.setBorder(border);
        panelSouth.setBorder(border);
        panelSouth.setPreferredSize(new Dimension(panelSouth.getWidth(), FRAME_HEIGHT_SOUTH));
        panelSouthFirst.setBorder(border);
        panelSouthSecond.setBorder(border);
        // Title label
        labelSunoco.setFont(new Font("Ariel", Font.BOLD, 36));
        // Display labels - Sale
        labelSaleA.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaleA.setFont(bigBoldFont);
        labelSaleB.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaleB.setFont(bigBoldFont);
        labelSaleC.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaleC.setFont(bigBoldFont);
        labelSaleD.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaleD.setVerticalAlignment(SwingConstants.NORTH);
        labelSaleD.setFont(smallBoldFont);
        labelSaleE.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaleE.setVerticalAlignment(SwingConstants.NORTH);
        labelSaleE.setFont(smallBoldFont);
        labelSale.setFont(normalBoldFont);
        // Display labels - Liters
        labelLiterA.setHorizontalAlignment(SwingConstants.CENTER);
        labelLiterA.setFont(bigBoldFont);
        labelLiterB.setHorizontalAlignment(SwingConstants.CENTER);
        labelLiterB.setFont(bigBoldFont);
        labelLiterC.setHorizontalAlignment(SwingConstants.CENTER);
        labelLiterC.setFont(bigBoldFont);
        labelLiterD.setHorizontalAlignment(SwingConstants.CENTER);
        labelLiterD.setVerticalAlignment(SwingConstants.NORTH);
        labelLiterD.setFont(smallBoldFont);
        labelLiterE.setHorizontalAlignment(SwingConstants.CENTER);
        labelLiterE.setVerticalAlignment(SwingConstants.NORTH);
        labelLiterE.setFont(smallBoldFont);
        labelLiter.setFont(normalBoldFont);
        // Labels for price
        labelPriceValue.setFont(normalBoldFont);
        labelPrice.setFont(normalBoldFont);
        // TextFields for type of gas
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet())
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            textFieldGasCurrentValue.setFont(normalBoldFont);
            textFieldGasCurrentValue.setEditable(false);
        }
        // Label of Preset Purchase Amount
        labelPreset.setFont(normalBoldFont);
        labelPreset.setHorizontalAlignment(SwingConstants.CENTER);
        labelPreset.setBorder(margin);
        // Slider which represents amount of gas
        sliderPreset.setBorder(border);
        sliderPreset.setValue(0);
        sliderPreset.setMajorTickSpacing(25);
        sliderPreset.setMinorTickSpacing(5);
        sliderPreset.setPaintTicks(true);
        sliderPreset.setPaintLabels(true);
        sliderPreset.setPreferredSize(new Dimension(FRAME_WIDTH, 66));
        sliderPreset.addChangeListener((e)-> {
            // Update labelPreset when sliderPreset is changed
            presetAmount = sliderPreset.getValue();
            labelPreset.setText("Preset Purchase Amount" + (presetAmount == 0 ? "" : " $" + presetAmount));
        });
        // Gas grade event handler
        ActionListener gradeHandler = (e) -> {
            JButton source = (JButton) e.getSource();
            if(!buttonStart.isEnabled()) buttonStart.setEnabled(true);
            
            double ppl = 0.0; // Price Per Liter
            for(Map.Entry<Integer, JButton> entry : buttonGasTypeMap.entrySet()) {
                int buttonGasTypeKey = entry.getKey();
                JButton buttonGasTypeValue = entry.getValue();
                if (source == buttonGasTypeValue) {
                    String priceString = textFieldGasCurrentMap.get(buttonGasTypeKey).getText();
                    ppl = Double.valueOf(priceString) / 100;
                    gasId = buttonGasTypeKey; // setting current gas id
                }
            }
            
            gasPricePerLiter = ppl;
            labelPriceValue.setText(String.format("%.2f", ppl*100));
        };
        // add event handler to gas grade buttons
        for(Map.Entry<Integer, JButton> entry : buttonGasTypeMap.entrySet())
        {
            JButton buttonGasTypeValue = entry.getValue();
            buttonGasTypeValue.addActionListener(gradeHandler);
        }
        // handle start and exit buttons
        buttonStart.setEnabled(false); // Can't pump until choose grade
        buttonStart.addActionListener((e)-> {
            // Handles pumping gas
            isPumping = !isPumping;
            buttonStart.setText(!isPumping ? "Start" : "Stop");
            
            if(isPumping)
            {
                // Create a new thread for pumping gas
                new Thread(() -> { 
                    // time trackers for sleep implementation
                    long now, then = 0L;
                    
                    while(isPumping)
                    {
                        now = System.currentTimeMillis();
                        if(now - then > 25) // milliseconds to sleep
                        {
                            then = now;

                            // update gas amounts here
                            gasLiters += 0.01;
                            gasPrice = gasLiters * gasPricePerLiter;

                            updateGasPrice(gasPrice);
                            updateGasLiters(gasLiters);
                        }
                    }
                }).start();
            }
            else
            {
                buttonStart.setEnabled(false);
            }
        });
        // Button for complete sales: 
        buttonCompleteSales.addActionListener((e) -> {
            // COMPLETE SALE should finish the transaction and update the Global tank as well as write it to the database.
            Map<String, Object> result = station.getDBManager().getObject("SELECT MAX(id) AS id FROM gas_log"); // to insert new id
            int id;
            if (result == null)
            {
                id = 301; // default - starting id
            }
            else
            {
                id = ((int) result.get("id")) + 1;
            }
            
            String sql = "INSERT INTO gas_log (id, gas_id, price, liter, total_price) VALUES (%d, %d, %s, %s, %s)";
            int resultValue = station.getDBManager().execute(String.format(sql, id, gasId, labelPriceValue.getText(), String.valueOf(gasLiters), String.valueOf(gasPrice)));
            if (resultValue > 0)
            {
                JOptionPane.showMessageDialog(null, "Thank you for your purchage.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "There is temporary transction error.\nPlease try again.");
            }
        });
        
        /// 3. Decide relationship between components
        // Add labelSunoco into panelNorth
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(labelSunoco);
        // Add neccessary panels into panelCenter
        panelCenter.setLayout(new GridLayout(6, 1));
        panelCenter.add(panelCenterFirst);
        panelCenter.add(panelCenterSecond);
        panelCenter.add(panelCenterThird);
        panelCenter.add(panelCenterFourth);
        panelCenter.add(panelCenterFifth);
        panelCenter.add(panelCenterSixth);
        panelCenterFirst.setLayout(new GridLayout(1, 5));
        panelCenterFirst.add(labelSaleA);
        panelCenterFirst.add(labelSaleB);
        panelCenterFirst.add(labelSaleC);
        panelCenterFirst.add(labelSaleD);
        panelCenterFirst.add(labelSaleE);
        panelCenterSecond.add(labelSale);
        panelCenterThird.setLayout(new GridLayout(1, 5));
        panelCenterThird.add(labelLiterA);
        panelCenterThird.add(labelLiterB);
        panelCenterThird.add(labelLiterC);
        panelCenterThird.add(labelLiterD);
        panelCenterThird.add(labelLiterE);
        panelCenterFourth.add(labelLiter);
        panelCenterFifth.add(labelPriceValue);
        panelCenterSixth.add(labelPrice);
        // Add neccessary panels into panelSouth 
        panelSouth.setLayout(new BorderLayout());
        panelSouth.add(panelSouthFirst, BorderLayout.CENTER);
        panelSouth.add(panelSouthSecond, BorderLayout.SOUTH);
        panelSouthFirst.setLayout(new GridLayout(2, 3));
        for(Map.Entry<Integer, JTextField> entry : textFieldGasCurrentMap.entrySet()) // retrive textfield by order
        {
            JTextField textFieldGasCurrentValue = entry.getValue();
            panelSouthFirst.add(textFieldGasCurrentValue);
        }
        for(Map.Entry<Integer, JButton> entry : buttonGasTypeMap.entrySet()) // retrive button by order
        {
            JButton buttonGasTypeValue = entry.getValue();
            panelSouthFirst.add(buttonGasTypeValue);
        }
        panelSouthSecond.setLayout(new BorderLayout());
        panelSouthSecond.add(labelPreset, BorderLayout.NORTH);
        panelSouthSecond.add(sliderPreset, BorderLayout.CENTER);
        panelSouthSecond.add(panelSouthSecondSouth, BorderLayout.SOUTH);
        panelSouthSecondSouth.add(buttonStart);
        panelSouthSecondSouth.add(buttonCompleteSales);
        
        /// 4. Set components into current class
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelEast, BorderLayout.EAST);
        add(panelSouth, BorderLayout.SOUTH);
        setLocationRelativeTo(null); //Center the window
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                station.setVisible(true);
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
                // do nothing
            }
            @Override
            public void windowIconified(WindowEvent e) {
                // do nothing
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
                // do nothing
            }
            @Override
            public void windowActivated(WindowEvent e) {
                // do nothing
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                // do nothing
            }
            
        });
        
        station.setVisible(false);
        setVisible(true); // show the window
    }
    
    /**
     * Only updates JLabels that need to be updated to optimize speed
     * 
     * @param price 
     */
    private void updateGasPrice(double price)
    {
        /*
            Turn price into a char array (digits)
        
            Ex: price = 203.67
                -> digits = ['2', '0', '3', '6', '7']
        */
        price = Math.floor(price * 100) / 100;
        StringBuilder sb = new StringBuilder(String.format("%.2f", price));
        char[] digits = sb.deleteCharAt(sb.length() - 3).toString().toCharArray();
        
        // only updates JLabels needing updating
        if(price >= 0.01)
        {
            labelSaleE.setText(String.valueOf(digits[digits.length - 1]));
            
            if(price >= 0.1)
            {
                labelSaleD.setText(String.valueOf(digits[digits.length - 2]));
                
                if(price >= 1)
                {
                    labelSaleC.setText(String.valueOf(digits[digits.length - 3]));
                    
                    if(price >= 10)
                    {
                        labelSaleB.setText(String.valueOf(digits[digits.length - 4]));
                        
                        if(price >= 100)
                        {
                            labelSaleA.setText(String.valueOf(digits[digits.length - 5]));
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Only updates JLabels that need to be updated to optimize speed
     * 
     * @param liters 
     */
    private void updateGasLiters(double liters)
    {
        /*
            Turn liters into a char array (digits)
        
            Ex: liters = 203.67
                -> digits = ['2', '0', '3', '6', '7']
        */
        liters = Math.floor(liters * 100) / 100;
        StringBuilder sb = new StringBuilder(String.format("%.2f", liters));
        char[] digits = sb.deleteCharAt(sb.length() - 3).toString().toCharArray();
        
        // only updates JLabels needing updating
        if(liters >= 0.01)
        {
            labelLiterE.setText(String.valueOf(digits[digits.length - 1]));
            
            if(liters >= 0.1)
            {
                labelLiterD.setText(String.valueOf(digits[digits.length - 2]));
                
                if(liters >= 1)
                {
                    labelLiterC.setText(String.valueOf(digits[digits.length - 3]));
                    
                    if(liters >= 10)
                    {
                        labelLiterB.setText(String.valueOf(digits[digits.length - 4]));
                        
                        if(liters >= 100)
                        {
                            labelLiterA.setText(String.valueOf(digits[digits.length - 5]));
                        }
                    }
                }
            }
        }
    }
}