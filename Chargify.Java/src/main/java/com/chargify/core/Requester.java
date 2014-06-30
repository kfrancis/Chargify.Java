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

import com.github.kevinsawicki.http.HttpRequest;
import java.net.URL;

/**
 *
 * @author jeremywrowe, kfrancis
 */
public class Requester {

    /**
     * (http) get
     * @param address       The address of the API
     * @param apiKey        The username for basic authentication
     * @param apiPassword   The password for basic authentication
     * @param requestType   The URI part of the API method
     * @return              The request object
     * @throws Exception
     */
    public HttpRequest get(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.get(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }

    /**
     * (http) post
     * @param address       The address of the API
     * @param apiKey        The username for basic authentication
     * @param apiPassword   The password for basic authentication
     * @param requestType   The URI part of the API method
     * @return              The request object
     * @throws Exception
     */
    public HttpRequest post(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.post(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }
    
    /**
     * (http) put
     * @param address       The address of the API
     * @param apiKey        The username for basic authentication
     * @param apiPassword   The password for basic authentication
     * @param requestType   The URI part of the API method
     * @return              The request object
     * @throws Exception
     */
    public HttpRequest put(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.put(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }
    
    /**
     * (http) delete
     * @param address       The address of the API
     * @param apiKey        The username for basic authentication
     * @param apiPassword   The password for basic authentication
     * @param requestType   The URI part of the API method
     * @return              The request object
     * @throws Exception
     */
    public HttpRequest delete(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.delete(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }
}
