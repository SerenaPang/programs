package com.mycompany.app;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * This frame contains a panel that display buttons for the cosmetic product 
 * user interface
 * */
public class GridLayoutFrame extends JFrame {
	FileProcessor writer = new FileProcessor();
	private JLabel display;
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;
	
	//crete labels and buttons for saving the information
	static JTextField textBrand;
	static JTextField textName;
	static JTextField textCategory;
	static JTextField textId;
	
	static JLabel lableBrand;	
	static JLabel lableName;
	static JLabel lableCategory;
	static JLabel lableId;
	
	static JLabel labelInfoEntered;
	
	final int idMIN = 0;
	final int idMAX = 1000;
	
	//crete labels and buttons for searching the information
	static JLabel lableSearch;
	static JTextField textSearch;
	String searchId;
	
	public GridLayoutFrame()
	{
		display = new JLabel("Enter Cosmetic Information");
		add(display, BorderLayout.NORTH);
		createSaveButtonPanel();
		//createSearchButtonPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	/**
	 * creates the context panel user interface for user to save the input information from the user,
	 * and creates a search button for user to search the matching id
	 * */
	private void createSaveButtonPanel() {
		JPanel contextPanel = new JPanel();
		//contextPanel.setLayout(new GridLayout(5, 2));
		contextPanel.setLayout(new GridLayout(7, 2));
		add(contextPanel);
		
		lableBrand = new JLabel("Brand: ");
		lableName = new JLabel("Name: ");
		lableCategory = new JLabel("Category: ");
		lableId = new JLabel("Id: ");
		labelInfoEntered = new JLabel("");
				
		textBrand = new JTextField("");
		textName = new JTextField("");
		textCategory = new JTextField("");
		textId = new JTextField("");
	
		
		contextPanel.add(lableBrand);
		contextPanel.add(textBrand);
		
		contextPanel.add(lableName);
		contextPanel.add(textName);
		
		contextPanel.add(lableCategory);
		contextPanel.add(textCategory);
		
		contextPanel.add(lableId);
		contextPanel.add(textId);
		
		contextPanel.add(labelInfoEntered);
		
		//add save button to the panel	
		contextPanel.add(makeSaveButton("SAVE"));
		
		//add search label and button to the panel
		lableSearch = new JLabel("Enter the id here to search");
		textSearch = new JTextField("",50);
		
		contextPanel.add(lableSearch);
		contextPanel.add(textSearch);
		contextPanel.add(makeSearchButton("SEARCH"));
	}
	
	/**
	 * makes a button representing the save button
	 * @param button the save button of the ui
	 * @return the button
	 * */
	private JButton makeSaveButton(String save) {
		JButton button = new JButton(save);
		ActionListener listener = new SaveButtonListener(save);
		button.addActionListener(listener);
		return button;
	}
	
	/**
	 * makes a button representing the search button
	 * @param button the search button of the ui
	 * @return the button
	 * */
	private JButton makeSearchButton(String search) {
		JButton button = new JButton(search);
		ActionListener listener = new SearchButtonListener(search);
		button.addActionListener(listener);
		return button;
	}
	
	/**
	 * create a listener for save button, and save all the information input from the user
	 * to the text file in the wanted format
	 * */
	class SaveButtonListener implements ActionListener
	{
		private String saveButton;
		/**
		 * constructs a listener whose actionPerformed method adds the info to the display
		 * @param aDigit the digit to add
		 * */
		public SaveButtonListener(String anOperation)
		{
			saveButton = anOperation;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			display.setText(display.getText() + saveButton);
			String brand = textBrand.getText();
			String name = textName.getText();
			String category = textCategory.getText();
			String id = textId.getText();
			
			//input validation
			if (validateEmptyInput(brand) && validateEmptyInput(name) && validateEmptyInput(category) && validateNumInput(id)) {
				//write the user input to the text file
				System.out.println("Writing text file: ");
		    	writer.writeLine(id, name, brand, category);
		    	
		    	lableBrand.setText("brand: ");
				lableName.setText("name: ");
				lableCategory.setText("category: ");
				lableId.setText("id: ");
				//show the user input on the label after the information is saved
				labelInfoEntered.setText("id: " + textId.getText() +
						" name: " + textName.getText() +
						" brand: " + textBrand.getText() +
						" category: " + textCategory.getText());
				
			} else {
				lableId.setText("You should enter a numeric value for product id");
			}
			    	
	    	//set input boxes to blank for next input
	    	textBrand.setText("");
	    	textName.setText("");
			textCategory.setText("");
			textId.setText("");	
		}		
	}
	
	/**
	 * create a listener for the search button, search the matching id and prints the result in the console
	 * */
	class SearchButtonListener implements ActionListener
	{
		private String searchButton;
		/**
		 * constructs a listener whose actionPerformed method adds the info to the display
		 * @param aDigit the digit to add
		 * */
		public SearchButtonListener(String anOperation)
		{
			searchButton = anOperation;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			display.setText(display.getText() + searchButton);
			searchId = textSearch.getText();		
			//input validation
			if (validateEmptyInput(searchId) && validateNumInput(searchId)) {	
				lableSearch.setText("Searching for " + searchId);
				InfoProcessor infoProcessor = new InfoProcessor();
				int numSearchId = Integer.parseInt(searchId);
		    	infoProcessor.getSearchItem(numSearchId);
				textSearch.setText("");	
			} else {
				lableSearch.setText("you should enter a numeric value to search");
				textSearch.setText("");	
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
		if (numId <= idMAX && numId >= idMIN) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method validate if the user input is empty or not
	 * */	
	public boolean validateEmptyInput(String input) {
		if (input.isBlank() || input.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * This method gets the product id of the product that the user wants to search for
	 * */
	public String getSearchId() {
		//return searchId;
		return null;
	}

	
	/**
	 * create a cosmetic ui for the user
	 * */
	public void createUi() {
		JFrame frame = new GridLayoutFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cosmetic");
		frame.setVisible(true);
	}
	
}