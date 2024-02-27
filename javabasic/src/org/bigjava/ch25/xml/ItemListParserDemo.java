package org.bigjava.ch25.xml;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
/**
 * This program parses an XML file containing an item list
 * it prints out the items that are described in the XML file
 * */
public class ItemListParserDemo {
	public static void main(String[] args) throws Exception 
	{
		ItemListParser parser = new ItemListParser();
		ArrayList<LineItem> items = parser.parse("/Users/serenapang/Development/JavaBasics/javabasic/src/org/bigjava/ch25/xml/items.xml");
		for (LineItem anItem : items)				
		{
			System.out.println(anItem);
		}
	}
}
