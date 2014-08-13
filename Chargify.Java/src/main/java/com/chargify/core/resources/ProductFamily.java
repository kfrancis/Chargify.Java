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
import com.chargify.core.resources.collections.ProductFamilyList;
import com.github.kevinsawicki.http.HttpRequest;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
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
@Default(DefaultType.FIELD)
public class ProductFamily extends Resource {
    
    @Getter @Setter @Element(required=false) private Integer id = null;
    @Getter @Setter @Element(required=false, name="accounting_code") private String accountingCode = null;
    @Getter @Setter @Element(required=false) private String description = null;
    @Getter @Setter @Element(required=false) private String handle = null;
    @Getter @Setter @Element(required=false) private String name = null;
    
    /**
     * Represents the product family as a hash
     * @return a hash of all the product family input attributes
     */
    @Override
    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("product_family[accounting_code]", this.getAccountingCode(), hash);
        guard("product_family[description]",     this.getDescription(),    hash);
        guard("product_family[handle]",          this.getHandle(),         hash);
        guard("product_family[name]",            this.getName(),           hash);
        return hash;
    }
    
    public Response<ProductFamily> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ?
                client.put("product_families/"+id, this.asHash()) :
                client.post("product_families", this.asHash());
        return new Response<ProductFamily>(request.code(), request.body(), ProductFamily.class);
    }
    
    /**
     * Creates or updates a product family record in Chargify
     * @return A product family response object from the request
     * @throws Exception
     */
    public Response<ProductFamily> save() throws Exception {
        return _save(ClientFactory.build());
    }
    
    /**
     * Retrieve details about a specific product family
     * @param client        The communication client
     * @param id            The id of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static Response<ProductFamily> _find(Client client, int id) throws Exception {
        HttpRequest request = client.get("product_families/" + id);
        return new Response<ProductFamily>(request.code(), request.body(), ProductFamily.class);
    }
    
    /**
     * Retrieve details about a specific product family
     * @param id            The id of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static Response<ProductFamily> find(int id) throws Exception {
        return _find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve details about a specific product family, using the family handle
     * @param client        The communication client
     * @param handle        The handle of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static Response<ProductFamily> _findByHandle(Client client, String handle) throws Exception {
        HttpRequest request = client.get("product_families/handle/" + handle);
        return new Response<ProductFamily>(request.code(), request.body(), ProductFamily.class);
    }
    
    /**
     * Retrieve details about a specific product family, using the family handle
     * @param handle        The handle of the product family
     * @return              The details of the product family
     * @throws Exception
     */
    public static Response<ProductFamily> findByHandle(String handle) throws Exception {
        return _findByHandle(ClientFactory.build(), handle);
    }
    
    /**
     * Retrieve the details of all product families in a site
     * @param client        The communication client
     * @return              The list of product families
     * @throws Exception
     */
    public static ListResponse<ProductFamily, ProductFamilyList> _all(Client client) throws Exception {
        HttpRequest request = client.get("product_families");
        return new ListResponse<ProductFamily, ProductFamilyList>(request.code(), request.body(), ProductFamily.class, ProductFamilyList.class);
    }
    
    /**
     * Retrieve the details of all product families in a site
     * @return              The list of product families
     * @throws Exception
     */
    public static ListResponse<ProductFamily, ProductFamilyList> all() throws Exception {
        return _all(ClientFactory.build());
    }
    
    public static Response<ProductFamily> delete() throws Exception {
        throw new NotImplementedException();
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof ProductFamily);
    }
}
