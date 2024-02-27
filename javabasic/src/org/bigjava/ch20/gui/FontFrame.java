package org.bigjava.ch20.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This frame contains a text sample and a control panel to change the font of the text
 * */
public class FontFrame extends JFrame{
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 400;
	
	private JLabel label;
	private JCheckBox italicCheckBox;
	private JCheckBox boldCheckBox;
	private JRadioButton smallButton;
	private JRadioButton mediumButton;
	private JRadioButton largeButton;
	private JComboBox facenameCombo;
	private ActionListener listener;
	
	/**
	 * constructs the frame
	 * */
	public FontFrame()
	{
		//construct text sample
		label = new JLabel("Big Java");
		add(label, BorderLayout.CENTER);
		//this listener is shared among all components
		listener = new ChoiceListener();
		createControlPanel();
		setLabelFont();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	class ChoiceListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			setLabelFont();
		}
	}
	
	/**
	 * gets user choice for font name, style, and size and sets the font f the text sample
	 * */
	private void setLabelFont() {
		//get font name
		String facename = (String)facenameCombo.getSelectedItem();
		//get font style
		int style = 0;
		if (italicCheckBox.isSelected()) {
			style = style + Font.ITALIC;
		}
		if (boldCheckBox.isSelected()) {
			style = style + Font.BOLD;
		}
		//get font size
		int size = 0;
		
		final int SMALL_SIZE = 24;
		final int MEDIUM_SIZE = 36;
		final int LARGE_SIZE = 48;
		
		if(smallButton.isSelected()) {
			size = SMALL_SIZE;
		} else if (mediumButton.isSelected()) {
			size = MEDIUM_SIZE;
		} else if (largeButton.isSelected()) {
			size = LARGE_SIZE;
		}
		//set font of text field
		label.setFont(new Font(facename, style, size));
		label.repaint();
	}

	/**
	 * Creates the control panel to change the font
	 * */
	private void createControlPanel() {
		JPanel facenamePanel = createComboBox();
		JPanel sizeGroupPanel = createCheckBoxes();
		JPanel styleGroupPanel = createRadioButtons();

		//line up component panels
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3,1));
		controlPanel.add(facenamePanel);
		controlPanel.add(sizeGroupPanel);
		controlPanel.add(styleGroupPanel);
		
		//add panels to content pane
		add(controlPanel, BorderLayout.SOUTH);
	}

	/**
	 * creates the radio buttons to select the font size
	 * @return the panel containing the radio buttons 
	 * */
	private JPanel createRadioButtons() {
		smallButton = new JRadioButton("Small");
		smallButton.addActionListener(listener);
		
		mediumButton = new JRadioButton("Medium");
		mediumButton.addActionListener(listener);
		
		largeButton = new JRadioButton("Large");
		largeButton.addActionListener(listener);
		largeButton.setSelected(true);
		
		//add radio buttons to button group
		ButtonGroup group = new ButtonGroup();
		group.add(smallButton);
		group.add(mediumButton);
		group.add(largeButton);

		JPanel panel = new JPanel();
		panel.add(smallButton);
		panel.add(mediumButton);
		panel.add(largeButton);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
		
		return panel;
	}

	/**
	 * creates the check boxes for selecting bold and italic styles
	 * @return the panel containing the check boxes
	 * */
	private JPanel createCheckBoxes() {
		italicCheckBox = new JCheckBox("Italic");
		italicCheckBox.addActionListener(listener);
		
		boldCheckBox = new JCheckBox("Bold");
		boldCheckBox.addActionListener(listener);
		
		JPanel panel = new JPanel();
		panel.add(italicCheckBox);
		panel.add(boldCheckBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Style"));
		return panel;
	}

	/**
	 * creates the combo box with the font style choices.
	 * @return the panel containing the combo box
	 * */
	private JPanel createComboBox() {
		facenameCombo = new JComboBox();
		facenameCombo.addItem("Serif");
		facenameCombo.addItem("SansSerif");
		facenameCombo.addItem("Monospaced");
		facenameCombo.setEditable(true);
		facenameCombo.addActionListener(listener);
		
		JPanel panel = new JPanel();
		panel.add(facenameCombo);
		return panel;
	}
	
	
	
	
	
	
}
