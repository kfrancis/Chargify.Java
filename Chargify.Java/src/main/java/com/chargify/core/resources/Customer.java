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

import com.chargify.core.http.Client;
import com.chargify.core.http.ClientFactory;
import com.chargify.core.http.ListResponse;
import com.chargify.core.http.Response;
import com.chargify.core.helpers.Maps;
import com.chargify.core.resources.collections.CustomerList;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;

import java.util.*;

@Root(strict=false)
@Default(DefaultType.FIELD)
public class Customer extends Resource {

    @Getter @Setter @Element(required=false) private Integer id = null;
    @Getter @Setter @Element(required=false) private String email = null;
    @Getter @Setter @Element(required=false, name="first_name") private String firstName = null;
    @Getter @Setter @Element(required=false, name="last_name")  private String lastName = null;
    @Getter @Setter @Element(required=false) private String organization = null;
    @Getter @Setter @Element(required=false) private String reference = null;
    @Getter @Setter @Element(required=false) private String address = null;
    @Getter @Setter @Element(required=false, name="address_2") private String address2 = null;
    @Getter @Setter @Element(required=false) private String  city = null;
    @Getter @Setter @Element(required=false) private String  state = null;
    @Getter @Setter @Element(required=false) private String  zip = null;
    @Getter @Setter @Element(required=false) private String  country = null;
    @Getter @Setter @Element(required=false) private String  phone = null;
    @Getter @Setter @Element(required=false) private Boolean verified = null;

    @Getter @Element(required=false, name="portal_customer_created_at") private Date portalCustomerCreatedAt = null;
    @Getter @Element(required=false, name="portal_invite_last_sent_at") private Date portalInviteLastSentAt = null;
    @Getter @Element(required=false, name="portal_invite_last_accepted_at") private Date portalInviteLastAcceptedAt = null;
    @Getter @Element(required=false, name="created_at") private Date createdAt = null;
    @Getter @Element(required=false, name="updated_at") private Date updatedAt = null;

    /**
     * Creates or updates a record in Chargify.
     * @return a customer response object from the request
     * @throws Exception
     */
    public Response<Customer> save() throws Exception {
        return _save(ClientFactory.build());
    }

    /**
     * represents the customer record as a hash
     * @return a hash of all of the customer attributes
     */
    @Override
    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("customer[id]",           this.getId(),           hash);
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

    /**
     * Finds a Customer record by it's id
     * @param id the customer id
     * @return a customer response object from the request
     * @throws Exception
     */
    public static Response<Customer> find(int id) throws Exception {
        return _find(ClientFactory.build(), id);
    }

    /**
     * Finds a Customer record by it's reference
     * @param reference the customer reference for a Customer
     * @return a customer response object from the request
     * @throws Exception
     */
    public static Response<Customer> findByReference(String reference) throws Exception {
        return _findByReference(ClientFactory.build(), reference);
    }

    /**
     * Finds all Customer records
     * @return a list of customers
     * @throws Exception
     */
    public static ListResponse<Customer, CustomerList> all() throws Exception {
        return _all(ClientFactory.build());
    }

    // Private Interface

    public Response<Customer> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ?
                client.put("customers/" + id, this.asHash()) :
                client.post("customers", this.asHash());

        return new Response<Customer>(request.code(), request.body(), Customer.class);
    }

    public static Response<Customer> _find(Client client, int id) throws Exception {
        HttpRequest request = client.get("customers/" + id);
        return new Response<Customer>(request.code(), request.body(), Customer.class);
    }

    public static Response<Customer> _findByReference(Client client, String reference) throws Exception {
        HttpRequest request = client.get("customers/lookup", Maps.of("reference", reference));
        return new Response<Customer>(request.code(), request.body(), Customer.class);
    }

    public static ListResponse<Customer, CustomerList> _all(Client client) throws Exception {
        HttpRequest request = client.get("customers");
        return new ListResponse<Customer, CustomerList>(request.code(), request.body(), Customer.class, CustomerList.class);
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof Customer);
    }
}
