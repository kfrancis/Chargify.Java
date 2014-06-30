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
import com.chargify.core.Configuration;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

@Root(strict=false)
@Default(DefaultType.FIELD)
public class Customer extends Resource {

    @Element private int id;
    @Element private String email = "";
    @Element(name="first_name") private String firstName = "";
    @Element(name="last_name")  private String lastName = "";

    @Element(required=false) private String organization = "";
    @Element(required=false) private String reference = "";
    @Element(required=false) private String address = "";
    @Element(name="address_2", required=false) private String address2 = "";
    @Element(required=false) private String city = "";
    @Element(required=false) private String state = "";
    @Element(required=false) private String zip = "";
    @Element(required=false) private String country = "";
    @Element(required=false) private String phone = "";

    @Transient private boolean verified;
    @Transient private String portalCustomerCreatedAt = "";
    @Transient private String portalInviteLastSentAt = "";
    @Transient private String portalInviteLastAcceptedAt = "";
    @Transient private String createdAt = "";
    @Transient private String updatedAt = "";

    public static Customer find(int id) throws Exception {
        Client client = ClientFactory.build();
        // TODO: request should be returned instead of response
        //       so that we can check the return code. and respond
        //       with errors.
        String response = client.get("customers/" + id);
        Serializer deserializer = new Persister();
        return deserializer.read(Customer.class, response);
    }

    public static Customer find(String reference) {

        throw new NotImplementedException();
    }



    public static ArrayList<Customer> all() {
        throw new NotImplementedException();
    }

    public Customer update() {
        throw new NotImplementedException();
    }
}
