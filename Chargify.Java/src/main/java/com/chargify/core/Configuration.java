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

public class Configuration {
    public static String  url         = null;
    public static String  subdomain   = null;
    public static String  apiKey      = null;
    public static String  apiPassword = null;
    public static String  sharedKey   = null;
    public static boolean json        = false;
    public static boolean cvvRequired = false;

    public static void setup(HashMap config) {
        Configuration.url         = (String)  config.getOrDefault("url",         Configuration.url);
        Configuration.subdomain   = (String)  config.getOrDefault("subdomain",   Configuration.subdomain);
        Configuration.apiKey      = (String)  config.getOrDefault("apiKey",      Configuration.apiKey);
        Configuration.apiPassword = (String)  config.getOrDefault("apiPassword", Configuration.apiPassword);
        Configuration.sharedKey   = (String)  config.getOrDefault("sharedKey",   Configuration.sharedKey);
        Configuration.json        = (boolean) config.getOrDefault("json",        Configuration.json);
        Configuration.cvvRequired = (boolean) config.getOrDefault("cvvRequired", Configuration.cvvRequired);
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
