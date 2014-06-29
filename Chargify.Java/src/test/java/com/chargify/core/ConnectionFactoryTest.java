package com.chargify.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    @Test
    public void testCreatesAnInstanceOfChargifyClient() throws Exception {
        assertEquals("did not return a ChargifyClient", ChargifyClient.class, ConnectionFactory.get().getClass());
    }
}