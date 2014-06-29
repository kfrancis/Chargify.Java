package com.chargify.core;

import java.util.HashMap;

public class Configuration {
    public static String  url         = null;
    public static String  subdomain   = null;
    public static String  apiKey      = null;
    public static String  apiPassword = null;
    public static String  sharedKey   = null;
    public static boolean json        = true;
    public static boolean cvvRequired = false;

    public static void configure(HashMap config) {
        Configuration.url         = (String)  config.getOrDefault("url",         Configuration.url);
        Configuration.subdomain   = (String)  config.getOrDefault("subdomain",   Configuration.subdomain);
        Configuration.apiKey      = (String)  config.getOrDefault("apiKey",      Configuration.apiKey);
        Configuration.apiPassword = (String)  config.getOrDefault("apiPassword", Configuration.apiPassword);
        Configuration.sharedKey   = (String)  config.getOrDefault("sharedKey",   Configuration.sharedKey);
        Configuration.json        = (boolean) config.getOrDefault("json",        Configuration.json);
        Configuration.cvvRequired = (boolean) config.getOrDefault("cvvRequired", Configuration.cvvRequired);
    }
}
