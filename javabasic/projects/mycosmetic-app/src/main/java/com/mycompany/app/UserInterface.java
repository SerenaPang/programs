package com.mycompany.app;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * create a frame to show the user interface of the cosmetic app
 * */
public class UserInterface extends JFrame implements ActionListener{
	TextFileWriter writer = new TextFileWriter();
	
	static JTextField textBrand;
	static JTextField textName;
	static JTextField textCategory;
	static JTextField textId;
	static JTextField textSearch;
	
	
	static JFrame frame;
	static JButton bSave;
	static JButton bSearch;
	
	static JLabel lableBrand;	
	static JLabel lableName;
	static JLabel lableCategory;
	static JLabel lableId;
	static JLabel lableSearch;
	
	String searchId;
	
	final int MIN = 0;
	final int MAX = 1000;
	
	UserInterface() {
		
	}
	
	public void createUI() {
		//create a new frame to store text field and button
		frame = new JFrame("Cosmetic Product");
		//label to dispaly text
		lableBrand = new JLabel("Brand: ", 10);
		lableName = new JLabel("Name: ");
		lableCategory= new JLabel("Category: ");
		lableId= new JLabel("Id: ");
		lableSearch= new JLabel("Nothing on Search");
				
		//cerate a new button for user to submit info
		bSave = new JButton("Save");
		//create a new button for user to search product info
		bSearch = new JButton("Search");
		
		UserInterface ui = new UserInterface();
		bSave.addActionListener(ui);
		
		textBrand = new JTextField("enter the brand here", 50);
		textName = new JTextField("enter the name here",50);
		textCategory = new JTextField("enter the category here",50);
		textId = new JTextField("enter the id here",50);
		textSearch = new JTextField("enter the id here to search",50);
		
		JPanel panel = new JPanel();
		
		//adding labels and buttons to the panel
		panel.add(lableBrand);
		panel.add(textBrand);
		
		panel.add(lableName);
		panel.add(textName);
		
		panel.add(lableCategory);
		panel.add(textCategory);
		
		panel.add(lableId);
		panel.add(textId);
			
		panel.add(bSave);
					
		panel.add(lableSearch);	
		panel.add(textSearch);
		panel.add(bSearch);
				
		//add panel to frame
		frame.add(panel);
		frame.setSize(600, 400);
		frame.show();
	}
	
	public void actionPerformed(ActionEvent e){
		String actionButton = e.getActionCommand();
		if (actionButton.equals("Save") ) {
			//printing the user input on the label
			lableBrand.setText(textBrand.getText());
			lableName.setText(textName.getText());
			lableCategory.setText(textCategory.getText());
			lableId.setText(textId.getText());
					
			//create a cosmetic object and write it to the text file
			//Cosmetic cosme = new Cosmetic(tBrand.getText(), tName.getText(), tCategory.getText(), tId.getText());
			String brand = textBrand.getText();
			String name = textName.getText();
			String category = textCategory.getText();
			String id = textId.getText();
			
			//input validation
			if (validateNumInput(id)) {
				//write the user input to the text file
				System.out.println("Writing text file: ");
		    	writer.writeLine(brand, name, category, id);
			} else {
				lableId.setText("You should enter a numeric value for product id");
			}
			    	
	    	//set input boxes to blank for next input
	    	textBrand.setText("  ");
	    	textName.setText("  ");
			textCategory.setText("  ");
			textId.setText("  ");
			
		} //else if(actionButton.equals("Search")) {
		else {
			searchId = textSearch.getText();
			//input validation
			if (validateNumInput(searchId)) {
				lableSearch.setText("Searching for " + searchId);
				textSearch.setText("  ");	
			} else {
				lableSearch.setText("enter a numeric value to search");
			}				
		}
	}
	
/**
 * This method validate if the user input is number or not
 * */	
	public boolean validateNumInput(String input) {
		int numId;
		try {
			numId = Integer.parseInt(input);
		}catch (NumberFormatException e) {
			return false;
		}
		if (numId <= MAX && numId >= MIN) {
			return true;
		}
		return false;
	}
	

	/**
	 * This method gets the product id of the product that the user wants to search for
	 * */
	public String getSearchId() {
		return searchId;
	}
	
}