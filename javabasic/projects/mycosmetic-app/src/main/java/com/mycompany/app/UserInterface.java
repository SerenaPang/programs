package com.mycompany.app;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * create a frame to show the user interface of the cosmetic app
 * */
public class UserInterface extends JFrame implements ActionListener{
	ArrayList<Cosmetic> listOfProduct = new ArrayList<>();
	
	static JTextField tBrand;
	static JTextField tName;
	static JTextField tCategory;
	static JTextField tId;
	static JTextField tSearch;
	
	
	static JFrame f;
	static JButton bSave;
	static JButton bSearch;
	static JLabel l;
	
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
			l.setText(tBrand.getText());
			tBrand.setText("  ");
			tBrand.setText("  ");
			tName.setText("  ");
			tCategory.setText("  ");
			tId.setText("  ");
		 
		} else if(s.equals("Search")) {
			tSearch.setText("  ");
		}
	}
	
}