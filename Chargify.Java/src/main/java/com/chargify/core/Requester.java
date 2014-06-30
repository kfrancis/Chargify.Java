package com.chargify.core;

import com.github.kevinsawicki.http.HttpRequest;
import java.net.URL;

class Requester {
    public HttpRequest get(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.get(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }

    public HttpRequest post(String address, String apiKey, String apiPassword, String requestType) throws Exception {
        HttpRequest request = HttpRequest.post(new URL(address));
        request.basic(apiKey, apiPassword);
        request.accept(String.format("application/%s", requestType));
        return request;
    }
}
