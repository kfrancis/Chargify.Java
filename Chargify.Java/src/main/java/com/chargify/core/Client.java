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

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jeremywrowe, kfrancis
 */
public class Client {

    private final Requester requester;

    public Client()                    { this.requester = new Requester(); }
    public Client(Requester requester) { this.requester = requester; }

    /**
     * (http) GET
     * @param  path    The path to the resource without leading slash
     * @return         The request object
     * @throws Exception
     */
    public HttpRequest get(String path) throws Exception {
        return get(path, new HashMap<String, String>());
    }

    /**
     * (http) GET
     * @param  path    The path to the resource without leading slash
     * @param  params  The query params for the request
     * @return         The request object
     * @throws Exception
     */
    public HttpRequest get(String path, HashMap<String, String> params) throws Exception {
        String address = getAddress(path) + buildQueryParams(params);
        HttpRequest request = requester.get(address, Configuration.apiKey, Configuration.apiPassword, requestType());
        return request;
    }

    /**
     * (http) POST
     * @param  path   The path to the resource without leading slash
     * @param  body   The xml representation of the object that is being created
     * @return        The request object
     * @throws Exception
     */
    public HttpRequest post(String path, HashMap<String, String> body) throws Exception {
        String address = getAddress(path);
        HttpRequest request = requester.post(address, Configuration.apiKey, Configuration.apiPassword, body, requestType());
        return request;
    }

    /**
     * (http) PUT
     * @param  path   The path to the resource without leading slash
     * @param  body   The xml representation of the object that is being updated
     * @return        The request object
     * @throws Exception
     */
    public HttpRequest put(String path, HashMap<String, String> body) throws Exception {
        String address = getAddress(path);
        HttpRequest request = requester.put(address, Configuration.apiKey, Configuration.apiPassword, body, requestType());
        return request;
    }

    /**
     * (http) PUT
     * @param  path   The path to the resource without leading slash
     * @return        The request object
     * @throws Exception
     */
    public HttpRequest delete(String path) throws Exception {
        String address = getAddress(path);
        HttpRequest request = requester.delete(address, Configuration.apiKey, Configuration.apiPassword, requestType());
        return request;
    }

    private String getAddress(String path) {
        return String.format("%s%s.%s", getBaseUrl(), path, requestType());
    }

    private String getBaseUrl() {
        return Configuration.url.endsWith("/") ? Configuration.url : Configuration.url + "/";
    }

    private String requestType() {
        return (Configuration.json == true) ? "json" : "xml";
    }

    private String buildQueryParams(HashMap<String, String> params) throws Exception {
        if(params.isEmpty()) {
            return "";
        }

        StringBuilder urlParams = new StringBuilder("?");
        boolean multiple = false;
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(multiple) {
                urlParams.append("&");
            } else {
                multiple = true;
            }

            urlParams.append(entry.getKey());
            urlParams.append("=");
            urlParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return urlParams.toString();
    }
}
