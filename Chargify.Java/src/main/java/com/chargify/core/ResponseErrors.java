package com.chargify.core;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict=false)
class ResponseErrors {
    @ElementList(name="errors", inline=true) List<String> messages;

    public ResponseErrors() {
        messages = new ArrayList<String>();
    }

    public ResponseErrors(String message) {
        messages = new ArrayList<String>();
        messages.add(message);
    }

    public boolean any() {
        return messages.size() > 0;
    }

    public List<String> getMessages() {
        return messages;
    }

}
