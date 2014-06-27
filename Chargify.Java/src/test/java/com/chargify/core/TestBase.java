/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chargify.core;

/**
 *
 * @author kfrancis
 */
public class TestBase {
    
    private final String URL;
    private final String ApiKey;
    private final String ApiPassword;
    private ChargifyClient Client;

    public TestBase() {
        this.URL = "https://subdomain.chargify.com";
        this.ApiKey = "";
        this.ApiPassword = "X";
    }
    
    public ChargifyClient getClient()
    {
        if (this.Client == null) {
            this.Client = new ChargifyClient(this.URL, this.ApiKey, this.ApiPassword);
        }
        return this.Client;
    }
}
