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

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    public static String  url         = null;
    public static String  subdomain   = null;
    public static String  apiKey      = null;
    public static String  apiPassword = null;
    public static String  sharedKey   = null;
    public static boolean json        = false;
    public static boolean cvvRequired = false;

    public static void setup(HashMap config) {
        Configuration.url         = (String)  config.get("url");
        Configuration.subdomain   = (String)  config.get("subdomain");
        Configuration.apiKey      = (String)  config.get("apiKey");
        Configuration.apiPassword = config.containsKey("apiPassword") ? (String) config.get("apiPassword") : "X";
        Configuration.sharedKey   = config.containsKey("sharedKey") ? (String) config.get("sharedKey") : "";
        Configuration.json        = config.containsKey("json") ? (boolean) config.get("json") : false;
        Configuration.cvvRequired = config.containsKey("cvvRequired") ? (boolean) config.get("cvvRequired") : false;
    }

    public static void load() {
        Map<String, String> env = System.getenv();
        Configuration.url         = env.get("CHARGIFY_URL");
        Configuration.subdomain   = env.get("CHARGIFY_SUBDOMAIN");
        Configuration.apiKey      = env.get("CHARGIFY_API_KEY");
        Configuration.apiPassword = env.get("CHARGIFY_API_PASSWORD");
        Configuration.sharedKey   = env.get("CHARGIFY_SHARED_KEY");
        Configuration.json        = Boolean.getBoolean(env.get("CHARGIFY_USE_JSON"));
        Configuration.cvvRequired = Boolean.getBoolean(env.get("CHARGIFY_CVV_REQUIRED"));
    }

    public static void reset() {
        Configuration.url         = null;
        Configuration.subdomain   = null;
        Configuration.apiKey      = null;
        Configuration.apiPassword = null;
        Configuration.sharedKey   = null;
        Configuration.json        = false;
        Configuration.cvvRequired = false;
    }
}
