/*
 * The MIT License
 *
 * Copyright 2014 kfrancis.
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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author kfrancis
 */
@Root
public class Customer {

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Customer() {
        super();
    }
    
    public Customer(String first_name, String last_name, String email, String organization, String reference) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.organization = organization;
        this.reference = reference;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    @Element
    private String first_name = "";
    @Element
    private String last_name = "";
    @Element
    private String email = "";
    @Element
    private String organization = "";
    @Element
    private String reference = "";
    @Element(required=false)
    private Integer id = Integer.MIN_VALUE;
    @Element(required=false)
    private String created_at = "";
    @Element(required=false)
    private String updated_at = "";
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getOrganization() {
        return organization;
    }
    
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getCreated_at() {
        return created_at;
    }
   
    public String getUpdated_at() {
        return updated_at;
    }
//</editor-fold>

}