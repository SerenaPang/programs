package org.bigjava.ch20.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This frame has a menu with commands to change the font of a text sample
 * */
public class FontFrame2 extends JFrame{
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 400;
	
	private JLabel label;
	private String facename;
	private int fontstyle;
	private int fontsize;
	
	/**
	 * constructs the frame
	 * */
	public FontFrame2()
	{
		//construct text sample
		label = new JLabel("Big Java");
		add(label, BorderLayout.CENTER);
		
		//construct menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createFontMenu());
		
		facename = "Serif";
		fontsize = 24;
		fontstyle = Font.PLAIN;
		
		setLabelFont();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * sets the font of the text sample
	 * */
	private void setLabelFont() {
		Font f = new Font(facename, fontstyle, fontsize);
		label.setFont(f);
	}

	class ExitItemListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
	
	/**
	 * creates the font menu
	 * @return the menu
	 * */
	private JMenu createFontMenu() {
		JMenu menu = new JMenu("Font");
		menu.add(createFaceMenu());
		menu.add(createSizeMenu());
		menu.add(createStyleMenu());
		return menu;
	}

	/**
	 * creates the style menu
	 * @return the menu
	 * */
	private JMenu createStyleMenu() {
		JMenu menu = new JMenu("Style");
		menu.add(createStyleItem("Plain", Font.PLAIN));
		menu.add(createStyleItem("Bold", Font.BOLD));
		menu.add(createStyleItem("Italic",Font.ITALIC));
		menu.add(createStyleItem("Bold Italic", Font.BOLD + Font.ITALIC));
		return menu;
	}
	
	/**
	 * creates a menu item to change the font style and sets its action listener
	 * @param name the name of the menu item
	 * @param style the new font style
	 * @return the menu item
	 * */
	private JMenuItem createStyleItem(String name, final int style) {
		class StyleItemListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				fontstyle = style;
				setLabelFont();
			}
		}
		JMenuItem item = new JMenuItem(name);
		ActionListener listener = new StyleItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * creates the size menu
	 * @return the menu
	 * */
	private JMenu createSizeMenu() {
		JMenu menu = new JMenu("Size");
		menu.add(createSizeItem("Smaller", -1));
		menu.add(createSizeItem("Larger", 1));
		return menu;
	}
	
	/**
	 * creates a menu item to change the font size and set its action listener.
	 * @param name the name of the menu item
	 * @param increment the amount by which to change the size
	 * @return the menu item
	 * */
	private Component createSizeItem(String name, final int increment) {
		class SizeItemListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				fontsize = fontsize + increment;
				setLabelFont();
			}
		}
		JMenuItem item = new JMenuItem(name);
		ActionListener listener = new SizeItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * creates the face menu
	 * @return the menu
	 * */
	private JMenu createFaceMenu() {
		JMenu menu = new JMenu("Face");
		menu.add(createFaceItem("Serif"));
		menu.add(createFaceItem("SansSerif"));
		menu.add(createFaceItem("Monospaced"));
		return menu;
	}

	/**
	 * creates a menu item to change the font face and set its action listener
	 * @param name the name of the font face
	 * @return the menu item
	 * */
	private JMenuItem createFaceItem(final String name) {
		class FaceItemListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent event) {
				facename = name;
				setLabelFont();
			}
		}
		JMenuItem item = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		item.addActionListener(listener);
		return item;
	}

	/**
	 * creates the file menu
	 * @return the menu
	 * */
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		ActionListener listener = new ExitItemListener();
		exitItem.addActionListener(listener);
		menu.add(exitItem);
		return menu;
	}
}
