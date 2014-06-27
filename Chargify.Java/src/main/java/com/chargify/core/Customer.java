/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public Customer(String first_name, String last_name, String email, String organization, String reference, Integer id, String created_at, String updated_at) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.organization = organization;
        this.reference = reference;
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    @Element
    private String first_name;
    @Element
    private String last_name;
    @Element
    private String email;
    @Element
    private String organization;
    @Element
    private String reference;
    @Element
    private Integer id;
    @Element
    private String created_at;
    @Element
    private String updated_at;
    
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
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public String getUpdated_at() {
        return updated_at;
    }
    
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
//</editor-fold>

}