package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    /**
     * Rigorous Test :-)
     */
    @Test
    public void threePlusFiveEqualsEight()
    {	
    	App app = new App();
        assertEquals(8, app.add(3,5));
    }
    
    @Test
    public void testAssertion() {
    	//test data
    	String str1 = new String("abc");
    	String str2 = new String("abc");
    	String str3 = null;
    	String str4 = "abc";
    	String str5  = "abc";
    	
    	int val1 = 5;
    	int val2 = 6;
    	
    	String[] expectedArray = {"one", "two", "three"};
    	String[] resultArray = {"one", "two", "three"};
    	
    	//check if 2 objects are equal  	 
    	assertEquals(str1, str2);   
    	//check if the first value is smaller than the second value
    	assertTrue(val1 < val2);    	   	
    	//check if the first value is greater than the second value
    	assertFalse(val1 > val2);  	
       //check if the objects is not null
    	assertNotNull(str1);  	
    	//check if the objects is null
    	assertNull(str3);
    	//check if two object references point to the same object
    	assertSame(str4, str5);
    	//check if two object references not point to the same object
    	assertNotSame(str1, str2);
    	//check whether two arrays are equal to each other
    	assertArrayEquals(expectedArray, resultArray);
    }
}
