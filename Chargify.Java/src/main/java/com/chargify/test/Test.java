/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chargify.test;

import com.chargify.core.ChargifyClient;
import com.chargify.core.Customer;

/**
 *
 * @author kfrancis
 */
public class Test {
    public static void main(String[] args) {
        ChargifyClient client = new ChargifyClient("https://subdomain.chargify.com", "", "X");
        
        Customer customer = new Customer("Kori", "Francis", "kori@chargify.com", "Chargify", java.util.UUID.randomUUID().toString());
        client.CreateCustomer(customer);
    }
}
