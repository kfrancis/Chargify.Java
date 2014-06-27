/*
 * The MIT License
 *
 * Copyright 2014 kfrancis, jeremywrowe.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.chargify.core;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author kfrancis
 */
public class ChargifyClientTest extends TestBase {
    
    public ChargifyClientTest() {
        super();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CreateCustomer method, of class ChargifyClient.
     */
    @Ignore
    @Test
    public void testCreateCustomer() {
        System.out.println("CreateCustomer");
        
        // Arrange
        Customer customer = new Customer("Kori", "Francis", "kori@chargify.com", "Chargify", java.util.UUID.randomUUID().toString());
        
        // Act
        getClient().createCustomer(customer);
        
        // Assert
        assertNotNull(this);
    }

    /**
     * Test of getCustomer method, of class ChargifyClient.
     */
    @Ignore
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        
        // Arrange
        int Id = 5656169;
        
        // Act
        Customer customer = getClient().getCustomer(Id);
        
        // Asssert
        assertNotNull(customer);
    }

    /**
     * Test of updateCustomer method, of class ChargifyClient.
     */
    @Ignore
    @Test
    public void testUpdateCustomer() {
        System.out.println("updateCustomer");
        
        // Arrange
        int Id = 5656169;
        Customer customer = getClient().getCustomer(Id);
        String oldFirstName = customer.getFirst_name();
        customer.setFirst_name(oldFirstName+"2");
        
        // Act
        Customer result = getClient().updateCustomer(customer);
        
        // Assert
        assertNotNull(result);
        assertTrue(result.getFirst_name() == oldFirstName+"2");
        
        // Cleanup
        customer.setFirst_name(oldFirstName);
        getClient().updateCustomer(customer);
    }
    
}
