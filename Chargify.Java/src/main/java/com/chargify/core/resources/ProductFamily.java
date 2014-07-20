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
import com.github.kevinsawicki.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A product family is a grouping of related products
 * @see <a href="https://docs.chargify.com/product-families">https://docs.chargify.com/product-families</a>
 */
@Root(strict=false)
public class ProductFamily extends Resource {
    
    @Getter @Setter @Element private Integer id = null;
    @Getter @Setter @Element(name="accounting_code") private String accountingCode = null;
    @Getter @Setter @Element private String description = null;
    @Getter @Setter @Element private String handle = null;
    @Getter @Setter @Element private String name = null;
    
    /**
     * Retrieve details about a specific product family
     * @param client        The communication client
     * @param id            The id of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static ProductFamily find(Client client, int id) throws Exception {
        HttpRequest request = client.get("product_families/" + id);
        if(request.ok()) {
            Serializer deserializer = new Persister();
            return deserializer.read(ProductFamily.class, request.body());
        }
        else {
            throw new RecordNotFoundException("Product family " + id + " not found");
        }
    }
    
    /**
     * Retrieve details about a specific product family
     * @param id            The id of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static ProductFamily find(int id) throws Exception {
        return find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve details about a specific product family, using the family handle
     * @param client        The communication client
     * @param handle        The handle of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static ProductFamily findByHandle(Client client, String handle) throws Exception {
        HttpRequest request = client.get("product_families/handle/" + handle);
        if(request.ok()) {
            Serializer deserializer = new Persister();
            return deserializer.read(ProductFamily.class, request.body());
        }
        else {
            throw new RecordNotFoundException("Product family " + handle + " not found");
        }
    }
    
    /**
     * Retrieve details about a specific product family, using the family handle
     * @param handle        The handle of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static ProductFamily findByHandle(String handle) throws Exception {
        return findByHandle(ClientFactory.build(), handle);
    }
    
    /**
     * Retrieve the details of all product families in a site
     * @param client        The communication client
     * @return              The list of product families
     * @throws Exception
     */
    public static List<ProductFamily> all(Client client) throws Exception {
        HttpRequest request = client.get("product_families");
        Serializer deserializer = new Persister();
        if (request.ok()){ 
            return deserializer.read(ProductFamilyList.class, request.body()).getProductFamilies();
        } else {
            throw new Exception("Could not parse /product_families.xml. Please verify your credentials.");
        }
    }
    
    /**
     * Retrieve the details of all product families in a site
     * @return              The list of product families
     * @throws Exception
     */
    public static List<ProductFamily> all() throws Exception {
        return all(ClientFactory.build());
    }
    
    public static ProductFamily create() throws Exception {
        throw new NotImplementedException();
    }
    
    public static ProductFamily update() throws Exception {
        throw new NotImplementedException();
    }
    
    public static ProductFamily delete() throws Exception {
        throw new NotImplementedException();
    }
    
    @Root(strict=false)
    private static class ProductFamilyList {
        @ElementList(name="product_family", inline=true) List<ProductFamily> product_families;
        public List<ProductFamily> getProductFamilies() {
            return product_families;
        }
    }
}
