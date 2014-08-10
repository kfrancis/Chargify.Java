package com.chargify.core;

import com.chargify.core.resources.Customer;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ResponseTest {

    @Test
    public void testIsSuccessWhen200() throws Exception {
        Response<Customer> response = new Response<Customer>(200, "", Customer.class);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testIsSuccessWhen201() throws Exception {
        Response<Customer> response = new Response<Customer>(201, "", Customer.class);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testIsSuccessWhen400() throws Exception {
        Response<Customer> response = new Response<Customer>(400, "", Customer.class);
        assertFalse(response.isSuccess());
    }

    @Test
    public void testIsErrorWhen200() throws Exception {
        Response<Customer> response = new Response<Customer>(200, "", Customer.class);
        assertFalse(response.isError());
    }

    @Test
    public void testIsErrorWhen201() throws Exception {
        Response<Customer> response = new Response<Customer>(201, "", Customer.class);
        assertFalse(response.isError());
    }

    @Test
    public void testIsErrorWhen400() throws Exception {
        Response<Customer> response = new Response<Customer>(400, "", Customer.class);
        assertTrue(response.isError());
    }

    @Test
    public void testGetResourceReturnsNullWhenError() throws Exception {
        Response<Customer> response = new Response<Customer>(400, "", Customer.class);
        assertNull(response.getResource());
    }

    @Test
    public void getResourceCreatesAResourceWhenSuccess() throws Exception {
        Response<Customer> response = new Response<Customer>(201, "<customer><email>jeremy.rowe@chargify.com</email><first_name>Jeremy</first_name><last_name>Rowe</last_name></customer>", Customer.class);
        Customer customer = response.getResource();
        assertEquals("Jeremy", customer.getFirstName());
        assertEquals("Rowe",   customer.getLastName());
        assertEquals(true,     customer.isPersisted());
    }

    @Test
    public void getResourceCreatesErrorsIfSerializationFails() throws Exception {
        Response<Customer> response = new Response<Customer>(201, "", Customer.class);
        assertNull(response.getResource());

        assertTrue(response.isError());

        ResponseErrors errors = response.errors();
        assertTrue(errors.any());
        assertEquals(errors.first(), "ParseError at [row,col]:[1,1]\nMessage: Premature end of file.");
    }

    @Test
    public void getErrorsReturnsEmptyErrorListWhenSuccess() throws Exception {
        Response<Customer> response = new Response<Customer>(201, "<customer><email>jeremy.rowe@chargify.com</email><first_name>Jeremy</first_name><last_name>Rowe</last_name></customer>", Customer.class);
        assertEquals(new ArrayList<String>(), response.errors().all());
    }

    @Test
    public void getErrorsReturnsListOfErrorsWhenFailure() throws Exception {
        Response<Customer> response = new Response<Customer>(422, "<errors><error>It be broke.</error><error>Howdy do.</error></errors>", Customer.class);
        ArrayList<String> expected = new ArrayList<String>() {
            {
                add("It be broke.");
                add("Howdy do.");
            }
        };
        assertEquals(expected, response.errors().all());
    }
}
