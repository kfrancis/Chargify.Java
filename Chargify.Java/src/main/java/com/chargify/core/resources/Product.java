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
import com.chargify.core.resources.collections.ProductList;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

/**
 * A product is something that can be subscribed to.
 * @see <a href="https://docs.chargify.com/api-products">https://docs.chargify.com/api-products</a>
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class Product extends Resource {
    
    @Getter @Setter @Element(required=false) private Integer id = null;
    @Getter @Setter @Element(required=false, name="accounting_code") private String accountingCode = null;
    @Getter @Setter @Element(required=false) private String description = null;
    @Getter @Setter @Element(required=false, name="expiration_interval") private Integer expirationInterval = null;
    @Getter @Setter @Element(required=false, name="expiration_interval_unit") private String expirationIntervalUnit = null;
    @Getter @Setter @Element(required=false) private String handle = null;
    @Getter @Setter @Element(required=false, name="initial_charge_in_cents") private Integer initialChargeInCents = null;
    @Getter @Setter @Element(required=false) private Integer interval = null;
    @Getter @Setter @Element(required=false, name="interval_unit") private String intervalUnit = null;
    @Getter @Setter @Element(required=false) private String name = null;
    @Getter @Setter @Element(required=false, name="price_in_cents") private Integer priceInCents = null;
    @Getter @Setter @Element(required=false, name="request_credit_card") private Boolean requestCreditCard = null;
    @Getter @Setter @Element(required=false, name="require_credit_card") private Boolean requireCreditCard = null;
    @Getter @Setter @Element(required=false, name="require_billing_address") private Boolean requireBillingAddress = null;
    @Getter @Setter @Element(required=false, name="request_billing_address") private Boolean requestBillingAddress = null;
    @Getter @Setter @Element(required=false, name="return_params") private String returnParams = null;
    @Getter @Setter @Element(required=false, name="return_url") private String returnUrl = null;
    @Getter @Setter @Element(required=false) private Boolean taxable = null;
    @Getter @Setter @Element(required=false, name="trial_interval") private Integer trialInterval = null;
    @Getter @Setter @Element(required=false, name="trial_interval_unit") private String trialIntervalUnit = null;
    @Getter @Setter @Element(required=false, name="trial_price_in_cents") private Integer trialPriceInCents = null;
    @Getter @Setter @Element(required=false, name="update_return_url") private String updateReturnUrl = null;
    
    @Getter @Setter @Element(required=false, name="product_family") private ProductFamily productFamily = null;
    
    @Getter @Element(required=false, name="archived_at") private Date archivedAt = null;
    @Getter @Element(required=false, name="created_at") private Date createdAt = null;
    @Getter @Element(required=false, name="updated_at") private Date updatedAt = null;
    
    /**
     * Creates or updates a record in Chargify.
     * @return A product response object from the request
     * @throws Exception
     */
    public Response<Product> save() throws Exception {
        return _save(ClientFactory.build());
    }
    
    public Response<Product> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ? 
                client.put("products/" + id, this.asHash()) :
                client.post("products", this.asHash());
        return new Response<Product>(request.code(), request.body(), Product.class);
    }
    
    /**
     * Represents the product record as a hash
     * @return a hash of all the product input attributes
     */
    @Override
    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("product[price_in_cents]",            this.getPriceInCents(),          hash);
        guard("product[name]",                      this.getName(),                  hash);
        guard("product[handle]",                    this.getHandle(),                hash);
        guard("product[description]",               this.getDescription(),           hash);
        guard("product[product_family_id]",         this.getProductFamily().getId(), hash);
        guard("product[accounting_code]",           this.getAccountingCode(),        hash);
        guard("product[interval_unit]",             this.getIntervalUnit(),          hash);
        guard("product[interval]",                  this.getInterval(),              hash);
        guard("product[initial_charge_in_cents]",   this.getInitialChargeInCents(),  hash);
        guard("product[trial_price_in_cents]",      this.getTrialPriceInCents(),     hash);
        guard("product[trial_interval]",            this.getTrialInterval(),         hash);
        guard("product[trial_interval_unit]",       this.getTrialIntervalUnit(),     hash);
        guard("product[expiration_interval]",       this.getExpirationInterval(),    hash);
        guard("product[expiration_interval_unit]",  this.getExpirationIntervalUnit(),hash);
        guard("product[return_url]",                this.getReturnUrl(),             hash);
        guard("product[return_params]",             this.getReturnParams(),          hash);
        guard("product[taxable]",                   this.getTaxable(),               hash);
        guard("product[require_credit_card]",       this.getRequireCreditCard(),     hash);
        guard("product[request_credit_card]",       this.getRequestCreditCard(),     hash);
        guard("product[require_billing_address]",   this.getRequireBillingAddress(), hash);
        guard("product[request_billing_address]",   this.getRequestBillingAddress(), hash);
        return hash;
    }

    /**
     * Retrieve the product details using the product id
     * @param id            The id of the product to retrieve
     * @return              The product details (if found), throws RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Response<Product> find(int id) throws Exception {
        return _find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve the product details using the product handle
     * @param handle        The product's handle
     * @return              The product details (if found), RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Response<Product> findByHandle(String handle) throws Exception {
        return _findByHandle(ClientFactory.build(), handle);
    }
    
    /**
     * Retrieve the product details using the product id
     * @param client        The communication client
     * @param id            The id of the product to retrieve
     * @return              The product details (if found), throws RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Response<Product> _find(Client client, int id) throws Exception {
        HttpRequest request = client.get("products/" + id);
        return new Response<Product>(request.code(), request.body(), Product.class);
    }
    
    /**
     *
     * @param client        Communication client
     * @param handle        The product's handle
     * @return              The product details (if found), RecordNotFoundException otherwise
     * @throws Exception    
     */
    public static Response<Product> _findByHandle(Client client, String handle) throws Exception {
        HttpRequest request = client.get("products/handle/" + handle);
        return new Response<Product>(request.code(), request.body(), Product.class);
    }
    
    /**
     * Retrieve details of all products
     * @param client        The communication client
     * @return              The list of products
     * @throws Exception
     */
    public static ListResponse<Product, ProductList> _all(Client client) throws Exception {
        HttpRequest request = client.get("products");
        return new ListResponse<Product, ProductList>(request.code(), request.body(), Product.class, ProductList.class);
    }
    
    /**
     * Retrieve details of all products
     * @return              The list of products
     * @throws Exception
     */
    public static ListResponse<Product, ProductList> all() throws Exception {
        return _all(ClientFactory.build());
    }
    
    /**
     * Retrieve all products from a specific product family
     * @param client        The communication client
     * @param familyId      The id of the products parent product family
     * @return              The list of products
     * @throws Exception
     */
    public static ListResponse<Product, ProductList> _allFromFamily(Client client, int familyId) throws Exception {
        HttpRequest request = client.get("product_families/" + familyId + "/products");
        return new ListResponse<Product, ProductList>(request.code(), request.body(), Product.class, ProductList.class);
    }
    
    /**
     * Retrieve all products from a specific product family
     * @param familyId      The id of the products parent product family
     * @return              The list of products
     * @throws Exception
     */
    public static ListResponse<Product, ProductList> allFromFamily(int familyId) throws Exception {
        return _allFromFamily(ClientFactory.build(), familyId);
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof Product);
    }
}
