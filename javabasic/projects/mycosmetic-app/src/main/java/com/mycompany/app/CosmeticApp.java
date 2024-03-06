package com.mycompany.app;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

/**
 * Hello world!
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
//    	TextFileReader reader = new TextFileReader();
//    	System.out.println("Reading text file: ");
//    	reader.read();
    	
    	UserInterface ui = new UserInterface();
    	ui.createUI();
    }
}
