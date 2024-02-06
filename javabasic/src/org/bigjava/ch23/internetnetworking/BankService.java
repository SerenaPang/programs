package org.bigjava.ch23.internetnetworking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * execute Simple Bank Access Protocol commands from a socket
 * */
public class BankService implements Runnable{
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private Bank bank;
	
	/**
	 * constructs a service object that processes commands from a socket for a bank
	 * @param aSocket the socket
	 * @param aBank the bank
	 * */
	public BankService(Socket aSocket, Bank aBank) {
		s = aSocket;
		bank = aBank;
	}

	@Override
	public void run() {
		try
		{
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			doService();
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
		
	}

	/**
	 * executes all commands until the QUIT command or the end of input
	 * */
	private void doService() throws IOException{
		while(true) {
			if (!in.hasNext()) {
				return;
			}
			String command = in.next();
			if (!in.hasNext()) {
				return;
			} else {
				executeCommand(command);
			}
		}
		
	}

	/**
	 * executes a single command
	 * @param command the command to execute
	 * */
	private void executeCommand(String command) {
		// TODO Auto-generated method stub
		
	}
}
