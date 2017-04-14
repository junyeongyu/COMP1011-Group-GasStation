package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
 * @author yujun-yeong
 */
public class FrameSunoco extends JFrame {
    // Panels
    private final JPanel panelNorth, panelWest, panelCenter, panelEast, panelSouth, panelSouthFirst, panelSouthSecond, panelSouthSecondSouth;
    private final JPanel panelCenterFirst, panelCenterSecond, panelCenterThird, panelCenterFourth, panelCenterFifth, panelCenterSixth;
    
    // Labels
    private final JLabel labelSunoco, labelSaleA, labelSaleB, labelSaleC, labelSaleD, labelSaleE, labelSale;
    private final JLabel labelLiterA, labelLiterB, labelLiterC, labelLiterD, labelLiterE, labelLiter;
    private final JLabel labelPriceValue, labelPrice, labelPreset;
    
    // TextFields
    private final JTextField textFieldRegular, textFieldPlus, textFieldSupreme;
    
    // Buttons
    private final JButton buttonRegular, buttonPlus, buttonSupreme, buttonStart, buttonExit;
    
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
    
    // Pricing
    private final double REGULAR_PRICE = 0.9905;
    private final double PLUS_PRICE = 1.0905;
    private final double SUPREME_PRICE = 1.1905;
    
    // Preset Amount
    private int presetAmount = 0;
    
    private double gasLiters = 0, gasPrice = 0, gasPricePerLiter = 0;
    
    // true while pumping
    private boolean isPumping = false;
    
    // Default Constructor
    //public FrameSunoco() {}
    public FrameSunoco(GasStation main) {
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
        textFieldRegular = new JTextField(String.format("%.2f", REGULAR_PRICE*100));
        textFieldPlus = new JTextField(String.format("%.2f", PLUS_PRICE*100));
        textFieldSupreme = new JTextField(String.format("%.2f", SUPREME_PRICE*100));
        // Buttons
        buttonRegular = new JButton("Regular");
        buttonPlus = new JButton("Plus");
        buttonSupreme = new JButton("Supreme");
        buttonStart = new JButton("Start");
        buttonExit = new JButton("Exit");
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
        textFieldRegular.setFont(normalBoldFont);
        textFieldPlus.setFont(normalBoldFont);
        textFieldSupreme.setFont(normalBoldFont);
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
            labelPreset.setText("Preset Purchase Amount" + (presetAmount == 0? "" : " $" + presetAmount));
        });
        // Gas grade event handler
        ActionListener gradeHandler = (e) -> {
            JButton source = (JButton) e.getSource();
            if(!buttonStart.isEnabled()) buttonStart.setEnabled(true);
            
            double ppl;
            if(source == buttonRegular) ppl = REGULAR_PRICE;
            else if(source == buttonPlus) ppl = PLUS_PRICE;
            else ppl = SUPREME_PRICE;
            
            gasPricePerLiter = ppl;
            labelPriceValue.setText(String.format("%.2f", ppl*100));
        };
        // add event handler to gas grade buttons
        buttonRegular.addActionListener(gradeHandler);
        buttonPlus.addActionListener(gradeHandler);
        buttonSupreme.addActionListener(gradeHandler);
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
                    long now = System.currentTimeMillis();
                    long then = now;
                    
                    while(isPumping)
                    {
                        now = System.currentTimeMillis();
                        if(now - then > 100) // milliseconds to sleep
                        {
                            then = now;

                            // update gas amounts here
                            gasLiters += 0.01;
                            gasPrice = gasLiters * gasPricePerLiter;

                            updateGasPrice(gasPrice);
                            updateGasLiters(gasLiters);

                            System.out.printf("Cost ($): %.2f\nGas (L): %.2f\n\n",
                                    gasPrice, gasLiters);
                        }
                    }
                }).start();
            }
            else
            {
                //buttonStart.setEnabled(false);
            }
        });
        // Button for exiting application
        buttonExit.addActionListener((e) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit System?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
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
        panelSouthFirst.add(textFieldRegular);
        panelSouthFirst.add(textFieldPlus);
        panelSouthFirst.add(textFieldSupreme);
        panelSouthFirst.add(buttonRegular);
        panelSouthFirst.add(buttonPlus);
        panelSouthFirst.add(buttonSupreme);
        panelSouthSecond.setLayout(new BorderLayout());
        panelSouthSecond.add(labelPreset, BorderLayout.NORTH);
        panelSouthSecond.add(sliderPreset, BorderLayout.CENTER);
        panelSouthSecond.add(panelSouthSecondSouth, BorderLayout.SOUTH);
        panelSouthSecondSouth.add(buttonStart);
        panelSouthSecondSouth.add(buttonExit);
        
        /// 4. Set components into current class
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelEast, BorderLayout.EAST);
        add(panelSouth, BorderLayout.SOUTH);
        setLocationRelativeTo(null); //Center the window
        setVisible(true); // show the window
    }
    
    
    
    // Update required JLabels
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
    
    // Start application
    public static void main(String[] args) {
        new FrameSunoco(null);
    }
}


        /*
private String[] getValues (double value) {
        String text = String.valueOf(value);
        String beforeDot = text.split("\\.")[0];
        String afterDot = text.split("\\.")[1];
        String[] numbers = new String [] {"0", "0", "0", "0", "0"};
        
        if (beforeDot.length() == 1) {
            numbers[2] = beforeDot;
        } else if (beforeDot.length() == 2) {
            numbers[1] = beforeDot.substring(0,1);
            numbers[2] = beforeDot.substring(1,2);
        } else if (beforeDot.length() == 3) {
            numbers[0] = beforeDot.substring(0,1);
            numbers[1] = beforeDot.substring(1,2);
            numbers[2] = beforeDot.substring(2,3);
        } else {
            throw new RuntimeException("You can not put more than 999.99");
        }
        
        if (afterDot.length() <= 1) {
            numbers[3] = afterDot;
        } else if (afterDot.length() <= 2) {
            numbers[3] = afterDot.substring(0,1);
            numbers[4] = afterDot.substring(1,2);
        }
        
        return numbers;
    }
    private void setSalesValue(String[] digits) {
        labelSaleA.setText(digits[0]);
        labelSaleB.setText(digits[1]);
        labelSaleC.setText(digits[2]);
        labelSaleD.setText(digits[3]);
        labelSaleE.setText(digits[4]);        
    }
    private void setSalesValue(double value) {
        setSalesValue(getValues(value));
    }
    private void setLitersValue(String[] digits) {
        labelLiterA.setText(digits[0]);
        labelLiterB.setText(digits[1]);
        labelLiterC.setText(digits[2]);
        labelLiterD.setText(digits[3]);
        labelLiterE.setText(digits[4]);        
    }
    private void setLitersValue(double value) {
        setSalesValue(getValues(value));
    }
        */