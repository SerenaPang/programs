package com.mycompany.app;
import java.io.File;

/**
 * The app to save and search for the cosmetic product information from a text file
 */
public class CosmeticApp 
{
    public static void main( String[] args )
    { 
    	//take the command line argument which is the file path
    	if (args.length > 0) {
    		File file = new File(args[0]);
    		System.out.println("Found text file name: " + file.getName());
    		System.out.println("Path: " + file.getAbsolutePath());
    		//pass the file to File processor
    		GridLayoutFrame ui = new GridLayoutFrame(file);
        	ui.createUi();
    		
    	}else {
    		System.out.println("Please pass the path of the file as an argument");
    	}
    }
}
