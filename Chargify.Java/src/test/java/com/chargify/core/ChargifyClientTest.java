/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chargify.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    @org.junit.Test
    public void testCreateCustomer() {
        System.out.println("CreateCustomer");
        
        // Arrange
        Customer customer = new Customer("Kori", "Francis", "kori@chargify.com", "Chargify", java.util.UUID.randomUUID().toString());
        
        // Act
        getClient().CreateCustomer(customer);
        
        // Assert
        assertNotNull(this);
    }
    
}
