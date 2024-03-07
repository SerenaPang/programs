package com.mycompany.app;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;

/**
 * The app to save and saerch forthe cosmetic product information from a text file
 *
 */
public class CosmeticApp 
{
    public static void main( String[] args )
    {
//    	TextFileWriter writer = new TextFileWriter();
//    	System.out.println("Writing text file: ");
//    	writer.write();
//    	

    	
    	UserInterface ui = new UserInterface();
    	ui.createUI();
    	
    	TextFileReader reader = new TextFileReader();
    	System.out.println("Reading text file: ");
    	reader.read();

    }
}
