package org.bigjava.ch23.internetnetworking;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * This program demonstrates how to use a URL connection to communicate with a web server. 
 * Supply the URL on the command line, for example java URLGet http://horstmann.com/index.html
 * */
public class URLGet {
	public static void main(String[] args) throws IOException{
		//get command line arguments
		String urlString;
		if (args.length == 1) {
			urlString = args[0];
		} else {
			urlString = "http://horstmann.com";
			System.out.println("Using " + urlString);
		}
		//open connection
		URL u = new URL(urlString);
		URLConnection connection = u.openConnection();
		//check if response code is HTTP_OK(200)
		HttpURLConnection httpConnection = (HttpURLConnection)connection;
		int code = httpConnection.getResponseCode();
		String message = httpConnection.getResponseMessage();
		if (code != HttpURLConnection.HTTP_OK) {
			return;
		}
		//red server response
		InputStream instream = connection.getInputStream();
		Scanner in = new Scanner(instream);
		
		while(in.hasNextLine()) {
			String input = in.nextLine();
			System.out.println(input);
		}
	}
}
