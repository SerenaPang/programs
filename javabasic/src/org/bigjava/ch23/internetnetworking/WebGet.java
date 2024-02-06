package org.bigjava.ch23.internetnetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WebGet {
	/**
	 * This program demonstrates how to use a socket to communicate with a web server. supply the name of the host and the resource on the command line
	 * for example, java WebGet horstmann.com index.html.
	 * */
	public static void main(String[] args) throws IOException {
		//get command line arguments
		String host;
		String resource;
		if(args.length == 2) {
			host = args[0];
			resource = args[1];
		} else {
			System.out.println("Getting / from horstmann.com");
			host = "horstmann.com";
			resource = "/";
		}
		//open socket
		final int HTTP_PORT = 80;
		try (Socket s = new Socket(host, HTTP_PORT)) {
			//Get streams
			InputStream instream = s.getInputStream();
			OutputStream outstream = s.getOutputStream();
			
			//turn streams into scanners and writers
			Scanner in = new Scanner(instream);
			PrintWriter out = new PrintWriter(outstream);
			
			//send command
			String command = "GET " + resource + " HTTP/1.1\n" + "Host: " + host + "\n\n";
			out.print(command);
			out.flush();
			
			//read server response
			while(in.hasNextLine()) {
				String input = in.nextLine();
				System.out.println(input);
			}
		}
		//The try-with resources statement closes the socket
	}
}
