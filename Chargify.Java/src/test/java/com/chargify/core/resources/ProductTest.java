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

package com.chargify.core.resources;

import com.chargify.core.http.Client;
import com.chargify.core.http.ListResponse;
import com.chargify.core.http.Response;
import com.chargify.core.helpers.Maps;
import com.chargify.core.helpers.Persisted;
import com.chargify.core.resources.collections.ProductList;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMockSupport;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 *
 * @author Kori
 */
public class ProductTest extends EasyMockSupport {
    @Mock private Client client;
    @Mock private HttpRequest request;
    
    @Before
    public void setup() {
        client  = createMock(Client.class);
        request = createMock(HttpRequest.class);
    }

    @After
    public void tearDown() {
        verifyAll();
    }
    
    @Test
    public void testFind() throws Exception {
        expect(client.get("products/70")).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(productResponse(true));
        replayAll();
        
        Response<Product> response = Product._find(client, 70);
        Product product = response.getResource();
        
        assertEquals("Wrong product name", "Basic", product.getName());
        assertEquals("Wrong product handle", "basic", product.getHandle());
    }
    
    private String getHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }

    private String productResponse(boolean withHeader) {
        String output = "";
        if (withHeader) {
            output += getHeader();
        }
        return output += 
            "<product>" +
                "<price_in_cents type=\"integer\">4900</price_in_cents>" +
                "<name>Basic</name>" +
                "<handle>basic</handle>" +
                "<description>basic product</description>" +
                "<accounting_code>0001</accounting_code>" +
                "<interval_unit>month</interval_unit>" +
                "<interval type=\"integer\">1</interval>" +
            "</product>";
    }
}
