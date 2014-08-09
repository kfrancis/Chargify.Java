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

package com.chargify.core.resources;

import com.chargify.core.Client;
import com.chargify.core.ClientFactory;
import com.chargify.core.helpers.Maps;
import com.chargify.core.transformers.DateFormatTransformer;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Root(strict=false)
@Default(DefaultType.FIELD)
public class Customer extends Resource {

    @Getter @Element(required=false) private Integer id = null;
    @Getter @Setter @Element private String email = null;
    @Getter @Setter @Element(name="first_name") private String firstName = null;
    @Getter @Setter @Element(name="last_name")  private String lastName  = null;
    @Getter @Setter @Element(required=false) private String organization = null;
    @Getter @Setter @Element(required=false) private String reference    = null;
    @Getter @Setter @Element(required=false) private String address      = null;
    @Getter @Setter @Element(name="address_2", required=false) private String address2 = null;
    @Getter @Setter @Element(required=false) private String  city     = null;
    @Getter @Setter @Element(required=false) private String  state    = null;
    @Getter @Setter @Element(required=false) private String  zip      = null;
    @Getter @Setter @Element(required=false) private String  country  = null;
    @Getter @Setter @Element(required=false) private String  phone    = null;
    @Getter @Setter @Element(required=false) private Boolean verified = null;

    @Getter @Element(required=false, name="portal_customer_created_at") private Date portalCustomerCreatedAt = null;
    @Getter @Element(required=false, name="portal_invite_last_sent_at") private Date portalInviteLastSentAt = null;
    @Getter @Element(required=false, name="portal_invite_last_accepted_at") private Date portalInviteLastAcceptedAt = null;
    @Getter @Element(required=false, name="created_at") private Date createdAt = null;
    @Getter @Element(required=false, name="updated_at") private Date updatedAt = null;

    public static Customer find(int id) throws Exception {
        return find(ClientFactory.build(), id);
    }

    public static Customer find(Client client, int id) throws Exception {
        HttpRequest request = client.get("customers/" + id);
        if(request.ok()) {
            Serializer deserializer = new Persister(DateFormatTransformer.matcher());
            Customer customer  = deserializer.read(Customer.class, request.body());
            customer.newRecord = false;
            return customer;
        }
        else {
            throw new RecordNotFoundException("Customer " + id + " not found");
        }
    }

    public static Customer findByReference(String reference) throws Exception {
        return findByReference(ClientFactory.build(), reference);
    }

    public static Customer findByReference(Client client, String reference) throws Exception {
        HttpRequest request = client.get("customers/lookup", Maps.of("reference", reference));
        if(request.ok()) {
            Serializer deserializer = new Persister(DateFormatTransformer.matcher());
            Customer customer  = deserializer.read(Customer.class, request.body());
            customer.newRecord = false;
            return customer;
        } else {
            throw new RecordNotFoundException("Customer " + reference + " not found");
        }
    }

    public static List<Customer> all(Client client) throws Exception {
        HttpRequest request = client.get("customers");
        Serializer deserializer = new Persister(DateFormatTransformer.matcher());

        if(request.ok()) {
            return deserializer.read(CustomerList.class, request.body()).getCustomers();
        } else {
           throw new Exception("Could not parse /customers.xml. Please verify your credentials.");
        }
    }

    public static List<Customer> all() throws Exception {
        return all(ClientFactory.build());
    }

    public Customer save(Client client) throws Exception {
        Serializer serializer = new Persister(DateFormatTransformer.matcher());
        HttpRequest request;

        if(this.isPersisted()) {
            request = client.put("customers/" + id, this.asHash());
        }
        else {
            request = client.post("customers", this.asHash());
        }

        if(request.code() == 201 || request.code() == 200) {
            Customer customer  = serializer.read(Customer.class, request.body());
            customer.newRecord = false;
            return  customer;
        } else {
            return null;
        }
    }

    public Customer update() {
        throw new NotImplementedException();
    }

    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<String, String>();
        guard("customer[first_name]",   this.getFirstName(),    hash);
        guard("customer[last_name]",    this.getLastName(),     hash);
        guard("customer[email]",        this.getEmail(),        hash);
        guard("customer[organization]", this.getOrganization(), hash);
        guard("customer[reference]",    this.getReference(),    hash);
        guard("customer[address]",      this.getAddress(),      hash);
        guard("customer[address_2]",    this.getAddress2(),     hash);
        guard("customer[city]",         this.getCity(),         hash);
        guard("customer[state]",        this.getState(),        hash);
        guard("customer[zip]",          this.getZip(),          hash);
        guard("customer[country]",      this.getCountry(),      hash);
        guard("customer[phone]",        this.getPhone(),        hash);
        guard("customer[verified]",     this.getVerified(),     hash);
        return hash;
    }

    @Root(strict=false)
    private static class CustomerList {
        @ElementList(name="customer", inline=true) List<Customer> customers;

        public List<Customer> getCustomers() {
            for(Customer customer : customers) {
                customer.newRecord = false;
            }
            return customers;
        }
    }
}
