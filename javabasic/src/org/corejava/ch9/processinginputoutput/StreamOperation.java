package org.corejava.ch9.processinginputoutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class StreamOperation {
	Path filePath = Paths.get(null);
	Path aFile = filePath.getFileName();
	File file = null;
	Charset charset = StandardCharsets.UTF_8;
	
	public void obtainStream() {
		//File file = "cosmetic.txt";
		///Users/serenapang/Development/JavaBasics/javabasic/src/org/corejava/ch9/processinginputoutput/cosmetic.txt
		
		try {
			InputStream inStream= Files.newInputStream(null);// pass in a path
			//To read text input using a reader 
			Reader inReader = new InputStreamReader(inStream, StandardCharsets.UTF_8);
			//To read a short text file into a String
			String content = new String(Files.readAllBytes(filePath),StandardCharsets.UTF_8);
			//read the file as a sequence of lines
			List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * To process file as a Stream
	 * */
	public void processFileAsStream() {
		try(Stream<String> lines = Files.lines(filePath, charset)) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Text Output
	 * To write text to a file
	 * */
	public void writeText() {
		try {
			OutputStream outStream = Files.newOutputStream(filePath, null);
			//to write using a writer 
			Writer out = new OutputStreamWriter(outStream, charset);
			out.write("string");
			//Write to a file, construct a PrintWiter
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(filePath, charset));
			//write to another stream 
			PrintWriter pwStream = new PrintWriter(new OutputStreamWriter(outStream, charset));
			//if you already have the text to write ina string
			String content = "content";
			Files.write(filePath,content.getBytes(charset));
			//or
			Files.write(filePath, null, charset); 
			//the lines(null) can be a Collection<String>. or an Iterable<?> extends CharSequence>
			
			//To append to a file
			Files.write(filePath, content.getBytes(charset), StandardOpenOption.APPEND);
			//lines(null)
			Files.write(filePath, null, charset, StandardOpenOption.APPEND);
			
			//Use a Writer to write output.
			StringWriter strWriter = new StringWriter();
			//throwable.printStackTrace(new PrintWriter(writer));
			String stackTrace = strWriter.toString();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * The RandomAccessFile lets you read/write data anywhere in a file.
	 * 
	 * */
	public void randomAccessFiles() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(filePath.toString(), "rw");
			
			//to read and write numbers
			int value = randomAccessFile.readInt();
			//sets the file pointer to an byte position within the file
			//getFilePointer returns the current position of the file pointer
			randomAccessFile.seek(randomAccessFile.getFilePointer() - 4);
			randomAccessFile.writeInt(value + 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Memory mapped files is efficient for random access for very large files
	 * */
	public void memoryMappedFiles() {
		try {
			//get a channel to the file
			FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.WRITE);
			//map an area of the entire file into memory
			ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
			//to read and write values
			int offset = 20;
			int value = buffer.getInt(offset);
			buffer.putInt(offset, value + 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) {
		System.out.println("StreamOperations");
	}	

}
