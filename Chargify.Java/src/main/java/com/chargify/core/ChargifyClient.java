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
public class ChargifyClient {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private String URL;
    private String ApiKey;
    private String ApiPassword;
    private String SharedKey;
    private boolean UseJSON = false;
    private boolean CvvRequired = false;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ChargifyClient() {
    }
    
    public ChargifyClient(String URL, String ApiKey, String ApiPassword){
        this.URL = URL;
        this.ApiKey = ApiKey;
        this.ApiPassword = ApiPassword;
    }
    
    public ChargifyClient(String URL, String ApiKey, String ApiPassword, String SharedKey) {
        this(URL, ApiKey, ApiPassword);
        this.SharedKey = SharedKey;
    }
    //</editor-fold>
    
    public void CreateCustomer(Customer customer) {
        
    }
    
    private String DoRequest() {
        String output = "";
        
        return output;
    }
}
