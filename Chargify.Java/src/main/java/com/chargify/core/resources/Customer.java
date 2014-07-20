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
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Root(strict=false)
@Default(DefaultType.FIELD)
public class Customer extends Resource {

    @Getter @Setter @Element private int id;
    @Getter @Setter @Element private String email = "";
    @Getter @Setter @Element(name="first_name") private String firstName = "";
    @Getter @Setter @Element(name="last_name")  private String lastName = "";

    @Getter @Setter @Element(required=false) private String organization = "";
    @Getter @Setter @Element(required=false) private String reference = "";
    @Getter @Setter @Element(required=false) private String address = "";
    @Getter @Setter @Element(name="address_2", required=false) private String address2 = "";
    @Getter @Setter @Element(required=false) private String city = "";
    @Getter @Setter @Element(required=false) private String state = "";
    @Getter @Setter @Element(required=false) private String zip = "";
    @Getter @Setter @Element(required=false) private String country = "";
    @Getter @Setter @Element(required=false) private String phone = "";

    @Transient private boolean verified;
    @Transient private String portalCustomerCreatedAt = "";
    @Transient private String portalInviteLastSentAt = "";
    @Transient private String portalInviteLastAcceptedAt = "";
    @Transient private String createdAt = "";
    @Transient private String updatedAt = "";

    public static Customer find(int id) throws Exception {
        return find(ClientFactory.build(), id);
    }

    public static Customer find(Client client, int id) throws Exception {
        HttpRequest request = client.get("customers/" + id);
        if(request.ok()) {
            Serializer deserializer = new Persister();
            return deserializer.read(Customer.class, request.body());
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
            Serializer deserializer = new Persister();
            return deserializer.read(Customer.class, request.body());
        } else {
            throw new RecordNotFoundException("Customer " + reference + " not found");
        }
    }

    public static List<Customer> all(Client client) throws Exception {
        HttpRequest request = client.get("customers");
        Serializer deserializer = new Persister();

        if(request.ok()) {
            return deserializer.read(CustomerList.class, request.body()).getCustomers();
        } else {
           throw new Exception("Could not parse /customers.xml. Please verify your credentials.");
        }
    }

    public static List<Customer> all() throws Exception {
        return all(ClientFactory.build());
    }

    public Customer create(HashMap attributes) {
        throw new NotImplementedException();
    }

    public Customer update() {
        throw new NotImplementedException();
    }

    @Root(strict=false)
    private static class CustomerList {
        @ElementList(name="customer", inline=true) List<Customer> customers;

        public List<Customer> getCustomers() {
            return customers;
        }
    }
}
