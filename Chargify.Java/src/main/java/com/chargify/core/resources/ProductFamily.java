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

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
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
    
    public static ProductFamily find(int id) throws Exception {
        throw new NotImplementedException();
    }
    
    public static ProductFamily find(String handle) throws Exception {
        throw new NotImplementedException();
    }
    
    public static ArrayList<ProductFamily> all() throws Exception {
        throw new NotImplementedException();
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
}
