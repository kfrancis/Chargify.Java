package com.chargify.core.helpers;

import com.chargify.core.resources.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistedTest {

    @Test
    public void testMark() throws Exception {
        Customer customer = Persisted.mark(new Customer());
        assertTrue(customer.isPersisted());
    }

    @Test
    public void testUnmark() throws Exception {
        Customer customer = Persisted.unmark(new Customer());
        assertFalse(customer.isPersisted());
    }
}