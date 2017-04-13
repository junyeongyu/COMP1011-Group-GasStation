package com.group.gasstation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    
    // Preset Amount
    private int presetAmount = 0;
    
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
        labelSaleA = new JLabel("1");
        labelSaleB = new JLabel("3");
        labelSaleC = new JLabel("5");
        labelSaleD = new JLabel("9");
        labelSaleE = new JLabel("9");
        labelSale = new JLabel("This Sale $");
        labelLiterA = new JLabel("0");
        labelLiterB = new JLabel("9");
        labelLiterC = new JLabel("5");
        labelLiterD = new JLabel("9");
        labelLiterE = new JLabel("9");
        labelLiter = new JLabel("Liters");
        labelPriceValue = new JLabel("0.00");
        labelPrice = new JLabel("Price Per Liter ¢");
        labelPreset = new JLabel("Preset Purchase Amount");
        // TextFields
        textFieldRegular = new JTextField("99.05");
        textFieldPlus = new JTextField("109.05");
        textFieldSupreme = new JTextField("119.05");
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
        setVisible(true); // show the window
    }
    
    // Start application
    public static void main(String[] args) {
        new FrameSunoco(null);
    }
}