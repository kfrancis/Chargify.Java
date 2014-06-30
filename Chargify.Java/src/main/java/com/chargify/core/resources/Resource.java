package com.chargify.core.resources;

import com.chargify.core.Client;
import com.chargify.core.ClientFactory;

public class Resource {

    private final Client client;

    public Resource() {
        this.client = ClientFactory.build();
    }

    public Resource(Client client) {
        this.client = client;
    }
}
