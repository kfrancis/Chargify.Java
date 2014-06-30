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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ConfigurationTest {

    private HashMap configurationValues;

    @Before
    public void setUp() throws Exception {
        configurationValues = new HashMap();
    }

    @After
    public void tearDown() throws Exception {
        Configuration.reset();
    }

    @Test
    public void testConfigureDefaults() throws Exception {
        assertNull("Wrong default value for url",         Configuration.url);
        assertNull("Wrong default value for subdomain",   Configuration.subdomain);
        assertNull("Wrong default value for apiKey",      Configuration.apiKey);
        assertNull("Wrong default value for apiPassword", Configuration.apiPassword);
        assertNull("Wrong default value for sharedKey",   Configuration.sharedKey);
        assertFalse("Wrong default value for json", Configuration.json);
        assertFalse("Wrong default value for cvvRequired",Configuration.cvvRequired);
    }

    @Test
    public void testConfigurationDefaultsWithEmptyHash() throws Exception {
        Configuration.setup(configurationValues);

        assertNull("Wrong default value for url",         Configuration.url);
        assertNull("Wrong default value for subdomain",   Configuration.subdomain);
        assertNull("Wrong default value for apiKey",      Configuration.apiKey);
        assertNull("Wrong default value for apiPassword", Configuration.apiPassword);
        assertNull("Wrong default value for sharedKey",   Configuration.sharedKey);
        assertFalse("Wrong default value for json", Configuration.json);
        assertFalse("Wrong default value for cvvRequired",Configuration.cvvRequired);
    }

    @Test
    public void testConfigurationOverridingValues() throws Exception {
        configurationValues.put("url",         "http://google.com");
        configurationValues.put("subdomain",   "acme");
        configurationValues.put("apiKey",      "keykey");
        configurationValues.put("apiPassword", "sekret");
        configurationValues.put("sharedKey",   "batman");
        configurationValues.put("json",        true);
        configurationValues.put("cvvRequired", true);


        Configuration.setup(configurationValues);

        assertEquals("Wrong value for url",         "http://google.com", Configuration.url);
        assertEquals("Wrong value for subdomain", "acme", Configuration.subdomain);
        assertEquals("Wrong value for apiKey", "keykey", Configuration.apiKey);
        assertEquals("Wrong value for apiPassword", "sekret", Configuration.apiPassword);
        assertEquals("Wrong value for sharedKey", "batman", Configuration.sharedKey);
        assertTrue("Wrong value for json", Configuration.json);
        assertTrue("Wrong value for cvvRequired", Configuration.cvvRequired);
    }

}