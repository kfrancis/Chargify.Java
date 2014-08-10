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
import com.github.kevinsawicki.http.HttpRequest;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A product is something that can be subscribed to.
 * @see <a href="https://docs.chargify.com/api-products">https://docs.chargify.com/api-products</a>
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class Product extends Resource {
    
    @Getter @Setter @Element private Integer id;
    @Getter @Setter @Element(name="accounting_code") private String accountingCode = "";
    @Getter @Setter @Element(name="archived_at") private String archivedAt = "";
    @Getter @Setter @Element(name="created_at") private String createdAt = "";
    @Getter @Setter @Element private String description = "";
    @Getter @Setter @Element(name="expiration_interval") private int expirationInterval;
    @Getter @Setter @Element(name="expiration_interval_unit") private String expirationIntervalUnit = "";
    @Getter @Setter @Element private String handle = "";
    @Getter @Setter @Element(name="initial_charge_in_cents") private int initialChargeInCents;
    @Getter @Setter @Element private int interval;
    @Getter @Setter @Element(name="interval_unit") private String intervalUnit = "";
    @Getter @Setter @Element private String name = "";
    @Getter @Setter @Element(name="price_in_cents") private int priceInCents;
    @Getter @Setter @Element(name="request_credit_card") private boolean requestCreditCard;
    @Getter @Setter @Element(name="require_credit_card") private boolean requireCreditCard;
    @Getter @Setter @Element(name="return_params") private String returnParams = "";
    @Getter @Setter @Element(name="return_url") private String returnUrl = "";
    @Getter @Setter @Element private boolean taxable;
    @Getter @Setter @Element(name="trial_interval") private Integer trialInterval = null;
    @Getter @Setter @Element(name="trial_interval_unit") private String trialIntervalUnit = "";
    @Getter @Setter @Element(name="trial_price_in_cents") private Integer trialPriceInCents = null;
    @Getter @Setter @Element(name="update_return_url") private String updateReturnUrl = "";
    @Getter @Setter @Element(name="updated_at") private String updatedAt = "";
    @Getter @Setter @Element private ProductFamily productFamily = null;

    /**
     * Retrieve the product details using the product id
     * @param id            The id of the product to retrieve
     * @return              The product details (if found), throws RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Product find(int id) throws Exception {
        return find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve the product details using the product id
     * @param client        The communication client
     * @param id            The id of the product to retrieve
     * @return              The product details (if found), throws RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Product find(Client client, int id) throws Exception {
        HttpRequest request = client.get("products/" + id);
        if(request.ok()) {
            Serializer deserializer = new Persister();
            return deserializer.read(Product.class, request.body());
        }
        else {
            throw new RecordNotFoundException("Product " + id + " not found");
        }
    }
    
    /**
     * Retrieve the product details using the product handle
     * @param handle        The product's handle
     * @return              The product details (if found), RecordNotFoundException otherwise
     * @throws Exception
     */
    public static Product findByHandle(String handle) throws Exception {
        return findByHandle(ClientFactory.build(), handle);
    }
    
    /**
     *
     * @param client        Communication client
     * @param handle        The product's handle
     * @return              The product details (if found), RecordNotFoundException otherwise
     * @throws Exception    
     */
    public static Product findByHandle(Client client, String handle) throws Exception {
        HttpRequest request = client.get("products/handle/" + handle);
        if(request.ok()) {
            Serializer deserializer = new Persister();
            return deserializer.read(Product.class, request.body());
        }
        else {
            throw new RecordNotFoundException("Product " + handle + " not found.");
        }
    }
    
    /**
     * Retrieve details of all products
     * @param client        The communication client
     * @return              The list of products
     * @throws Exception
     */
    public static List<Product> all(Client client) throws Exception {
        HttpRequest request = client.get("products");
        Serializer deserializer = new Persister();
        if (request.ok()){ 
            return deserializer.read(ProductList.class, request.body()).getProducts();
        } else {
            throw new Exception("Could not pase /products.xml. Please verify your credentials.");
        }
    }
    
    /**
     * Retrieve details of all products
     * @return              The list of products
     * @throws Exception
     */
    public static List<Product> all() throws Exception {
        return all(ClientFactory.build());
    }
    
    /**
     * Retrieve all products from a specific product family
     * @param client        The communication client
     * @param familyId      The id of the products parent product family
     * @return              The list of products
     * @throws Exception
     */
    public static List<Product> allFromFamily(Client client, int familyId) throws Exception {
        HttpRequest request = client.get("product_families/" + familyId + "/products");
        Serializer deserializer = new Persister();
        if (request.ok()){ 
            return deserializer.read(ProductList.class, request.body()).getProducts();
        } else {
            throw new Exception("Could not parse /product_families/"+familyId+"/products.xml. Please verify your credentials.");
        }
    }
    
    /**
     * Retrieve all products from a specific product family
     * @param familyId      The id of the products parent product family
     * @return              The list of products
     * @throws Exception
     */
    public static List<Product> allFromFamily(int familyId) throws Exception {
        return allFromFamily(ClientFactory.build(), familyId);
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof Product);
    }

    public static Product create() throws Exception {
        throw new NotImplementedException();
    }
    
    @Root(strict=false)
    private static class ProductList {
        @ElementList(name="product", inline=true) List<Product> products;
        public List<Product> getProducts() {
            return products;
        }
    }
}
