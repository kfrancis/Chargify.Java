/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chargify.core;

import com.chargify.util.StringOutputStream;
import com.github.kevinsawicki.http.HttpRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.*;

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
    
    //<editor-fold defaultstate="collapsed" desc="Customer">

    /**
     *
     * @param Id
     */
    public Customer getCustomer(int Id)
    {
        try {
            String response = performGet(String.format("customers/%s.xml", Id));
            System.out.println(response);
            
            Serializer deserializer = new Persister();
            Customer result = deserializer.read(Customer.class, response);
            
            return result;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     *
     * @param customer
     */
    public void createCustomer(Customer customer) {
        try {
            Serializer serializer = new Persister();
            StringOutputStream outStream = new StringOutputStream();
            serializer.write(customer, outStream);
            
            String response = performPost("customers.xml", outStream.toString());
            
            System.out.println(response);
            
        } catch (Exception ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param customer
     * @return
     */
    public Customer updateCustomer(Customer customer) {
        try {
            Serializer serializer = new Persister();
            StringOutputStream outStream = new StringOutputStream();
            serializer.write(customer, outStream);
            
            String response = performPost("customers.xml", outStream.toString());
            System.out.println(response);
            
            Serializer deserializer = new Persister();
            Customer result = deserializer.read(Customer.class, response);
            
            return result;
        } catch (Exception ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Utility Methods">
    private String performGet(String methodPart) throws MalformedURLException
    {
        String output = "";
        try {
            String address = String.format("%s%s%s", this.URL, (this.URL.endsWith("/") ? "" : "/"), methodPart);
            URL url = new URL(address);
            HttpRequest request = HttpRequest.get(url);
            request.basic(this.ApiKey, this.ApiPassword);
            output = request.body();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return output;
    }
    
    private String performPost(String methodPart, String data) throws MalformedURLException
    {
        String output = "";
        try {
            String address = String.format("%s%s%s", this.URL, (this.URL.endsWith("/") ? "" : "/"), methodPart);
            URL url = new URL(address);
            HttpRequest request = HttpRequest.post(url);
            request.basic(this.ApiKey, this.ApiPassword);
            request.send(data);
            output = request.body();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return output;
    }
    //</editor-fold>
}
