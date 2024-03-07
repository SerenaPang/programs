package com.mycompany.app;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * create a frame to show the user interface of the cosmetic app
 * */
public class UserInterface extends JFrame implements ActionListener{
	TextFileWriter writer = new TextFileWriter();
	
	static JTextField tBrand;
	static JTextField tName;
	static JTextField tCategory;
	static JTextField tId;
	static JTextField tSearch;
	
	
	static JFrame f;
	static JButton bSave;
	static JButton bSearch;
	static JLabel l;
	
	String searchId;
	
	UserInterface() {
		
	}
	
	public void createUI() {
		//create a new frame to store text field and button
		f = new JFrame("Cosmetic Product");
		//label to dispaly text
		l = new JLabel("nothing entered");
		//cerate a new button for user to submit info
		bSave = new JButton("Save");
		//create a new button for user to search product info
		bSearch = new JButton("Search");
		
		UserInterface ui = new UserInterface();
		bSave.addActionListener(ui);
		
		tBrand = new JTextField("enter the brand here", 40);
		tName = new JTextField("enter the name here", 40);
		tCategory = new JTextField("enter the category here", 40);
		tId = new JTextField("enter the id here", 40);
		tSearch = new JTextField("enter the id here to search", 40);
		
		JPanel p = new JPanel();
		
		p.add(tBrand);
		p.add(tName);
		p.add(tCategory);
		p.add(tId);
		
		
		p.add(bSave);
		p.add(l);
		
		p.add(tSearch);
		p.add(bSearch);
		
		
		//add panel to frame
		f.add(p);
		f.setSize(500, 500);
		f.show();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("Save") ) {
			l.setText(tBrand.getText() + " " + tName.getText() + " " + tCategory.getText() + " " + tId.getText());
			tBrand.setText("  ");
			tName.setText("  ");
			tCategory.setText("  ");
			tId.setText("  ");
			
			//create a cosmetic object and write it to the text file
			//Cosmetic cosme = new Cosmetic(tBrand.getText(), tName.getText(), tCategory.getText(), tId.getText());
			String b = tBrand.getText();
			String n = tName.getText();
			String c = tCategory.getText();
			String i = tId.getText();
			System.out.println("Writing text file: ");
	    	writer.writeLine(b, n, c, i);
		} else if(s.equals("Search")) {
			searchId = tSearch.getText();
			tSearch.setText("  ");
		}
	}
	
	/**
	 * This method gets the product id of the product that the user wants to search for
	 * */
	public String getSearchId() {
		return searchId;
	}
	
}