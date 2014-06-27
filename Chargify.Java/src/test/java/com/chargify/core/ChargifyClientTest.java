/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chargify.core;

import java.util.HashSet;
import java.util.Set;
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
        getClient().createCustomer(customer);
        
        // Assert
        assertNotNull(this);
    }

    /**
     * Test of getCustomer method, of class ChargifyClient.
     */
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
