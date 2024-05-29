package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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
        assertEquals(3, app.add(3,5));
    }
}
