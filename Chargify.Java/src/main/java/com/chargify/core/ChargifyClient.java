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

import com.github.kevinsawicki.http.HttpRequest;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.*;
import org.simpleframework.xml.stream.Format;

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
     * Retrieve a list of customers
     * @return  ArrayList<Customer> The list of customers
     */
    
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> result = new ArrayList<Customer>();
        try {
            String response = performGet("customers.xml");
            System.out.println(response);
            
            StringWriter writer = new StringWriter();
            Serializer deserializer = new Persister();
            //result = deserializer.read(Customer.class, response);
           
        } catch (Exception ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /**
     * Retrieve a customer from Chargify
     * @param   Id          The id of the customer to retrieve
     * @return  Customer    The customer
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
     * Retrieve a customer from Chargify
     * @param reference     The reference value of the customer
     * @return  Customer    The customer
     */
    public Customer getCustomer(String reference)
    {
        try {
            String response = performGet(String.format("customers/lookup.xml?reference=%s", reference));
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
     * Create a customer
     * @param customer      The customer to create
     * @return  Customer    The resulting customer
     */
    public Customer createCustomer(Customer customer) {
        try {
            StringWriter writer = new StringWriter();
            Format format = new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>");
            Serializer serializer = new Persister(format);
            serializer.write(customer, writer);
            String input = writer.toString();
            
            String response = performPost("customers.xml", input);
            
            Serializer deserializer = new Persister();
            Customer result = deserializer.read(Customer.class, response);
            
            return result;
        } catch (Exception ex) {
            Logger.getLogger(ChargifyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     *
     * @param customer
     * @return
     */
    public Customer updateCustomer(Customer customer) {
        try {
            StringWriter writer = new StringWriter();
            Format format = new Format("<?xml version=\"1.0\" encoding= \"UTF-8\" ?>");
            Serializer serializer = new Persister(format);
            serializer.write(customer, writer);
            String input = writer.toString();
            
            String response = performPost("customers.xml", input);
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
    private String getMethodExtension()
    {
        return (this.UseJSON == true) ? "json" : "xml";
    }
    
    private String performGet(String methodPart) throws MalformedURLException
    {
        String output = "";
        try {
            String address = String.format("%s%s%s", this.URL, (this.URL.endsWith("/") ? "" : "/"), methodPart);
            URL url = new URL(address);
            HttpRequest request = HttpRequest.get(url);
            request.basic(this.ApiKey, this.ApiPassword);
            request.accept(String.format("application/%s", getMethodExtension()));
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
            request.contentType(String.format("application/%s", getMethodExtension()));
            request.accept(String.format("application/%s", getMethodExtension()));
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
