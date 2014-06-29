package com.chargify.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ConfigurationTest {

    private HashMap configurationValues;
    private String url;
    private String subdomain;
    private String apiKey;
    private String apiPassword;
    private String sharedKey;
    private boolean cvvRequired;

    @Before
    public void setUp() throws Exception {
        url         = Configuration.url;
        subdomain   = Configuration.subdomain;
        apiKey      = Configuration.apiKey;
        apiPassword = Configuration.apiPassword;
        sharedKey   = Configuration.sharedKey;
        cvvRequired = Configuration.cvvRequired;

        configurationValues = new HashMap();
    }

    @After
    public void tearDown() throws Exception {
        Configuration.url         = url;
        Configuration.subdomain   = subdomain;
        Configuration.apiKey      = apiKey;
        Configuration.apiPassword = apiPassword;
        Configuration.sharedKey   = sharedKey;
        Configuration.cvvRequired = cvvRequired;
    }

    @Test
    public void testConfigureDefaults() throws Exception {
        assertNull("Wrong default value for url",         Configuration.url);
        assertNull("Wrong default value for subdomain",   Configuration.subdomain);
        assertNull("Wrong default value for apiKey",      Configuration.apiKey);
        assertNull("Wrong default value for apiPassword", Configuration.apiPassword);
        assertNull("Wrong default value for sharedKey",   Configuration.sharedKey);
        assertTrue("Wrong default value for json",        Configuration.json);
        assertFalse("Wrong default value for cvvRequired",Configuration.cvvRequired);
    }

    @Test
    public void testConfigurationDefaultsWithEmptyHash() throws Exception {
        Configuration.configure(configurationValues);

        assertNull("Wrong default value for url",         Configuration.url);
        assertNull("Wrong default value for subdomain",   Configuration.subdomain);
        assertNull("Wrong default value for apiKey",      Configuration.apiKey);
        assertNull("Wrong default value for apiPassword", Configuration.apiPassword);
        assertNull("Wrong default value for sharedKey",   Configuration.sharedKey);
        assertTrue("Wrong default value for json",        Configuration.json);
        assertFalse("Wrong default value for cvvRequired",Configuration.cvvRequired);
    }

    @Test
    public void testConfigurationOverridingValues() throws Exception {
        configurationValues.put("url",         "http://google.com");
        configurationValues.put("subdomain",   "acme");
        configurationValues.put("apiKey",      "keykey");
        configurationValues.put("apiPassword", "sekret");
        configurationValues.put("sharedKey",   "batman");
        configurationValues.put("json",        false);
        configurationValues.put("cvvRequired", true);


        Configuration.configure(configurationValues);

        assertEquals("Wrong value for url",         "http://google.com", Configuration.url);
        assertEquals("Wrong value for subdomain",   "acme",              Configuration.subdomain);
        assertEquals("Wrong value for apiKey",      "keykey",            Configuration.apiKey);
        assertEquals("Wrong value for apiPassword", "sekret",            Configuration.apiPassword);
        assertEquals("Wrong value for sharedKey",   "batman",            Configuration.sharedKey);
        assertFalse("Wrong value for json",                              Configuration.json);
        assertTrue("Wrong value for cvvRequired",                        Configuration.cvvRequired);
    }

}