package com.chargify.core;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ConnectionFactory {
    public static ChargifyClient get() {
        return new ChargifyClient();
    }
}
