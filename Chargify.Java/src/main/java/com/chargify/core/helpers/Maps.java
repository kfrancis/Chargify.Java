package com.chargify.core.helpers;

import java.util.HashMap;

public class Maps {
    public static HashMap<String, String> of(final String k1, final String v1) {
        return new HashMap<String, String>() {
            {
                put(k1, v1);
            }
        };
    }

    public static HashMap<String, String> of(final String k1, final String v1, final String k2, final String v2) {
        return new HashMap<String, String>() {
            {
                put(k1, v1);
                put(k2, v2);
            }
        };
    }

    public static HashMap<String, String> of(final String k1, final String v1, final String k2, final String v2, final String k3, final String v3) {
        return new HashMap<String, String>() {
            {
                put(k1, v1);
                put(k2, v2);
                put(k3, v3);
            }
        };
    }

    public static HashMap<String, String> of(final String k1, final String v1, final String k2, final String v2, final String k3, final String v3, final String k4, final String v4) {
        return new HashMap<String, String>() {
            {
                put(k1, v1);
                put(k2, v2);
                put(k3, v3);
                put(k4, v4);
            }
        };
    }
}
