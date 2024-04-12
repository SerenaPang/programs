package com.mycompany.app;

import java.awt.BorderLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.SQLException;

import com.mycompany.dao.CosmeticDao;
import com.mycompany.model.Cosmetic;

import com.mycompany.dao.jdbc.CosmeticJdbcDao;
import com.mycompany.dao.jdbc.JdbcDataSource;

/**
 * This frame contains a panel that display buttons for the cosmetic product
 * user interface
 */
public class GridLayoutFrame extends JFrame {
	CosmeticDao cosmeticDao;
	String flag;

	private JLabel display;

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 300;

	// crete labels and buttons for saving the information
	static JTextField textBrand;
	static JTextField textName;
	static JTextField textCategory;
	static JTextField textId;

	static JLabel lableBrand;
	static JLabel lableName;
	static JLabel lableCategory;
	static JLabel lableId;

	static JLabel labelInfoEntered;
	static JLabel labelValidationMesage;

	final int idMIN = 0;
	final int idMAX = 1000;

	// crete label and textfiled for searching the information
	static JLabel lableSearch;
	static JTextField textSearch;
	String searchId;

	// crete label for copying the information from the text file to the database
	static JLabel lableCopy;

	public GridLayoutFrame(CosmeticDao cosmeticDao, String flag) {
		this.cosmeticDao = cosmeticDao;
		this.flag = flag;
		display = new JLabel("Enter Cosmetic Information");
		add(display, BorderLayout.NORTH);
		createSaveButtonPanel();
		// createSearchButtonPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * creates the context panel user interface for user to save the input
	 * information from the user, and creates a search button for user to search the
	 * matching id
	 */
	private void createSaveButtonPanel() {
		JPanel contextPanel = new JPanel();
		// contextPanel.setLayout(new GridLayout(7, 2));
		contextPanel.setLayout(new GridLayout(9, 2));
		add(contextPanel);

		lableBrand = new JLabel("Brand: ");
		lableName = new JLabel("Name: ");
		lableCategory = new JLabel("Category: ");
		lableId = new JLabel("Id: ");
		labelInfoEntered = new JLabel("");
		labelValidationMesage = new JLabel("");

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
		contextPanel.add(labelValidationMesage);

		// add save button to the panel
		contextPanel.add(makeSaveButton("SAVE"));

		// add update button to the panel
		contextPanel.add(makeUpdateButton("UPDATE"));

		// add search label and button to the panel
		lableSearch = new JLabel("Enter the id here to search");
		textSearch = new JTextField("", 50);

		contextPanel.add(lableSearch);
		contextPanel.add(textSearch);
		contextPanel.add(makeSearchButton("SEARCH"));

		// add copy label and button to the panel
		lableCopy = new JLabel("Push COPY to copy info from File to DB");
		contextPanel.add(lableCopy);
		contextPanel.add(makeCopyButton("COPY"));
	}

	/**
	 * makes a button representing the save button
	 * 
	 * @param button the save button of the ui
	 * @return the button
	 */
	private JButton makeSaveButton(String save) {
		JButton button = new JButton(save);
		ActionListener listener = new SaveButtonListener(save);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * makes a button representing the search button
	 * 
	 * @param button the search button of the ui
	 * @return the button
	 */
	private JButton makeSearchButton(String search) {
		JButton button = new JButton(search);
		ActionListener listener = new SearchButtonListener(search);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * makes a button representing the update button
	 * 
	 * @param button the update button of the ui
	 * @return the button
	 */
	private JButton makeUpdateButton(String update) {
		JButton button = new JButton(update);
		ActionListener listener = new UpdateButtonListener(update);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * makes a button representing the copy button that copies everything in the
	 * text file to the database
	 * 
	 * @param button the copy button of the ui
	 * @return the button
	 */
	private JButton makeCopyButton(String copy) {
		JButton button = new JButton(copy);
		ActionListener listener = new CopyButtonListener(copy);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * create a listener for update button, update and save all the information
	 * input from the user to the text file in the wanted format
	 */
	class UpdateButtonListener implements ActionListener {
		private String updateButton;

		/**
		 * constructs a listener whose actionPerformed method adds the info to the
		 * display
		 * 
		 * @param anOperation the operation to add
		 */
		public UpdateButtonListener(String anOperation) {
			updateButton = anOperation;
		}

		@Override
		public void actionPerformed(ActionEvent event) {

			// set input boxes to blank for update input
			textBrand.setText("");
			textName.setText("");
			textCategory.setText("");
			textId.setText("");

			String brand = textBrand.getText();
			String name = textName.getText();
			String category = textCategory.getText();
			String id = textId.getText();

			int numId = Integer.parseInt(id);
			Cosmetic cosmeToBeUpdated = cosmeticDao.findById(numId);

			// check to find if id exist in file/database, if exist update to the new info,
			// if not save new info to the file/databse
			if (cosmeToBeUpdated.getId() == numId) {
				System.out.println("Found updated ID!");
				// ask the user to enter new info to update
				JOptionPane.showMessageDialog(null, "Enter info to Updated!");
				// input validation
				if (validateEmptyInput(brand) && validateEmptyInput(name) && validateEmptyInput(category)
						&& validateNumInput(id)) {
					Cosmetic cosmetic = new Cosmetic(Integer.parseInt(id), name, brand, category);
					// show user that info has been updated successfully
					if (cosmeticDao.updateCosmetic(cosmetic)) {
						JOptionPane.showMessageDialog(null, "Product " + numId + "Updated!");
					}
					lableBrand.setText("brand: ");
					lableName.setText("name: ");
					lableCategory.setText("category: ");
					lableId.setText("id: ");
					// show the user input on the label after the information is updated
					labelInfoEntered.setText("id: " + textId.getText() + " name: " + textName.getText() + " brand: "
							+ textBrand.getText() + " category: " + textCategory.getText());

				} else {
					// labelValidationMesage.setText("You should enter a numeric value for product
					// id");
					JOptionPane.showMessageDialog(null,
							"Check if you enter a numeric value for product id OR Info filed is empty");
				}

			} else {
				// tell the user that info is not in file, save a new one or check id correct
				JOptionPane.showMessageDialog(null,
						"Product does not exist in the system, you can enter to save new product.");
			}
			// set input boxes to blank for next input
			textBrand.setText("");
			textName.setText("");
			textCategory.setText("");
			textId.setText("");
		}
	}

	/**
	 * create a listener for copy button, and copies everything in the text file to
	 * the database
	 */
	class CopyButtonListener implements ActionListener {
		private String copyButton;

		/**
		 * constructs a listener whose actionPerformed method adds the info to the
		 * display
		 */
		public CopyButtonListener(String anOperation) {
			copyButton = anOperation;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// if using database, remained the user there is no need to copy.
			if ("-db".equals(flag)) {
				JOptionPane.showMessageDialog(null, "You are writing in DATABASE now, NO NEED to copy");
			} else {
				// Otherwise copy everything from the text file to database
				// read all the content in the text file and extrate fields from each line
				try {
					// creatre a cosmetic object and pass it save method to save to the database
					lableCopy.setText("Copying to database...");
					List<Cosmetic> cosmetics = cosmeticDao.findAll();
					JdbcDataSource jdbcDataSource = new JdbcDataSource("mysqldb.properties");
					cosmeticDao = new CosmeticJdbcDao(jdbcDataSource);
					for (int i = 0; i < cosmetics.size(); i++) {
						// System.out.println(cosmetics.get(i).getName());
						cosmeticDao.save(cosmetics.get(i));
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * create a listener for save button, and save all the information input from
	 * the user to the text file in the wanted format
	 */
	class SaveButtonListener implements ActionListener {
		private String saveButton;

		/**
		 * constructs a listener whose actionPerformed method adds the info to the
		 * display
		 * 
		 * @param anOperation the operation to add
		 */
		public SaveButtonListener(String anOperation) {
			saveButton = anOperation;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// display.setText(display.getText() + saveButton);
			String brand = textBrand.getText();
			String name = textName.getText();
			String category = textCategory.getText();
			String id = textId.getText();

			// input validation
			if (validateEmptyInput(brand) && validateEmptyInput(name) && validateEmptyInput(category)
					&& validateNumInput(id)) {
				// write the user input to the text file
				System.out.println("Writing text file: ");
				Cosmetic cosmetic = new Cosmetic(Integer.parseInt(id), name, brand, category);

				cosmeticDao.save(cosmetic);

				lableBrand.setText("brand: ");
				lableName.setText("name: ");
				lableCategory.setText("category: ");
				lableId.setText("id: ");
				// show the user input on the label after the information is saved
				labelInfoEntered.setText("id: " + textId.getText() + " name: " + textName.getText() + " brand: "
						+ textBrand.getText() + " category: " + textCategory.getText());

			} else {
				// labelValidationMesage.setText("You should enter a numeric value for product
				// id");
				JOptionPane.showMessageDialog(null,
						"Check if you enter a numeric value for product id OR Info filed is empty");
			}

			// set input boxes to blank for next input
			textBrand.setText("");
			textName.setText("");
			textCategory.setText("");
			textId.setText("");
		}
	}

	/**
	 * create a listener for the search button, search the matching id and prints
	 * the result in the console
	 */
	class SearchButtonListener implements ActionListener {
		private String searchButton;

		/**
		 * constructs a listener whose actionPerformed method adds the info to the
		 * display
		 * 
		 * @param aDigit the digit to add
		 */
		public SearchButtonListener(String anOperation) {
			searchButton = anOperation;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			display.setText(display.getText() + searchButton);
			searchId = textSearch.getText();
			// input validation
			if (validateEmptyInput(searchId) && validateNumInput(searchId)) {
				lableSearch.setText("Searching for " + searchId);
				// InfoProcessor infoProcessor = new InfoProcessor();

				int numSearchId = Integer.parseInt(searchId);
				Cosmetic cosmetic = cosmeticDao.findById(numSearchId);
				if (cosmetic != null) {
					System.out.println("Found " + cosmetic);
				} else {
					System.out.println("Not Found");
				}

				textSearch.setText("");
			} else {
				// labelValidationMesage.setText("you should enter a numeric value to search");
				JOptionPane.showMessageDialog(null, "You should enter a numeric value to search");
				textSearch.setText("");
			}
		}
	}

	/**
	 * This method validate if the user input is number or not
	 */
	public boolean validateNumInput(String input) {
		int numId;
		try {
			numId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		if (numId <= idMAX && numId >= idMIN) {
			return true;
		}
		return false;
	}

	/**
	 * This method validate if the user input is empty or not
	 */
	public boolean validateEmptyInput(String input) {
		if (input.isBlank() || input.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * This method gets the product id of the product that the user wants to search
	 * for
	 */
	public String getSearchId() {
		return searchId;
	}

	/**
	 * create a cosmetic ui for the user
	 */
	public void createUi() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Cosmetic");
		this.setVisible(true);
	}
}