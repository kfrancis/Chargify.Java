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

package com.chargify.core.http;

import com.chargify.core.Configuration;
import com.chargify.core.helpers.Maps;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.easymock.EasyMock.expect;

public class ClientTest extends EasyMockSupport {

    private Client client;

    @Mock private Requester requester;
    @Mock private HttpRequest request;

    @Before
    public void setUp() {
        HashMap config = new HashMap();
        config.put("url", "http://example.com");
        config.put("apiKey", "12345");
        config.put("apiPassword", "sekret");

        Configuration.setup(config);

        requester = createMock(Requester.class);
        request   = createMock(HttpRequest.class);
        client    = new Client(requester);
    }

    @After
    public void tearDown() {
        Configuration.reset();
    }

    @Test
    public void testGet() throws Exception {
        expect(requester.get("http://example.com/customers.xml", "12345", "sekret", "xml"))
            .andReturn(request);

        expect(request.body()).andReturn("<xml></xml>");
        replayAll();

        client.get("customers");
    }

    @Test
    public void testGetWithOneParam() throws Exception {
        expect(requester.get("http://example.com/customers/lookup.xml?reference=fred", "12345", "sekret", "xml"))
                .andReturn(request);

        expect(request.body()).andReturn("<xml></xml>");
        replayAll();

        HashMap<String, String> params = new HashMap<String, String>() {
            {
                put("reference", "fred");
            }
        };

        client.get("customers/lookup", Maps.of("reference", "fred"));
    }

    @Test
    public void testGetWithMultipleParams() throws Exception {
        expect(requester.get("http://example.com/customers/lookup.xml?foo=bar&reference=fred", "12345", "sekret", "xml"))
                .andReturn(request);

        expect(request.body()).andReturn("<xml></xml>");
        replayAll();

        client.get("customers/lookup", Maps.of("reference", "fred", "foo", "bar"));
    }

    @Test
    public void testPost() throws Exception {
        HashMap body = new HashMap();
        expect(requester.post("http://example.com/customers/1.xml", "12345", "sekret", body, "xml"))
                .andReturn(request);

        expect(request.body()).andReturn("<xml><foo>Wee</foo></xml>");
        replayAll();

        client.post("customers/1", body);
    }
}
