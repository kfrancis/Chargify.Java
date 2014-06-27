/*
 * The MIT License
 *
 * Copyright 2014 kfrancis.
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
public class CustomerTest {
    
    public CustomerTest() {
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
     * Test of getFirst_name method, of class Customer.
     */
    @Test
    public void testGetFirst_name() {
        System.out.println("getFirst_name");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getFirst_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirst_name method, of class Customer.
     */
    @Test
    public void testSetFirst_name() {
        System.out.println("setFirst_name");
        String first_name = java.util.UUID.randomUUID().toString();
        Customer instance = new Customer();
        instance.setFirst_name(first_name);
        assertEquals(first_name, instance.getFirst_name());
    }

    /**
     * Test of getLast_name method, of class Customer.
     */
    @Test
    public void testGetLast_name() {
        System.out.println("getLast_name");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getLast_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLast_name method, of class Customer.
     */
    @Test
    public void testSetLast_name() {
        System.out.println("setLast_name");
        String last_name = java.util.UUID.randomUUID().toString();
        Customer instance = new Customer();
        instance.setLast_name(last_name);
        assertEquals(last_name, instance.getLast_name());
    }

    /**
     * Test of getEmail method, of class Customer.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Customer.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "kori@chargify.com";
        Customer instance = new Customer();
        instance.setEmail(email);
        
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getOrganization method, of class Customer.
     */
    @Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getOrganization();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrganization method, of class Customer.
     */
    @Test
    public void testSetOrganization() {
        System.out.println("setOrganization");
        String organization = java.util.UUID.randomUUID().toString();
        Customer instance = new Customer();
        instance.setOrganization(organization);
        assertEquals(organization, instance.getOrganization());
    }

    /**
     * Test of getReference method, of class Customer.
     */
    @Test
    public void testGetReference() {
        System.out.println("getReference");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getReference();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReference method, of class Customer.
     */
    @Test
    public void testSetReference() {
        System.out.println("setReference");
        String reference = java.util.UUID.randomUUID().toString();
        Customer instance = new Customer();
        instance.setReference(reference);
        assertEquals(reference, instance.getReference());
    }

    /**
     * Test of getId method, of class Customer.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Customer instance = new Customer();
        Integer expResult = Integer.MIN_VALUE;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreated_at method, of class Customer.
     */
    @Test
    public void testGetCreated_at() {
        System.out.println("getCreated_at");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getCreated_at();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUpdated_at method, of class Customer.
     */
    @Test
    public void testGetUpdated_at() {
        System.out.println("getUpdated_at");
        Customer instance = new Customer();
        String expResult = "";
        String result = instance.getUpdated_at();
        assertEquals(expResult, result);
    }
    
}
