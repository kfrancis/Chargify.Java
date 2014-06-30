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
import java.util.ArrayList;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @see <a href="https://docs.chargify.com/api-products">https://docs.chargify.com/api-products</a>
 * @author Kori
 */
public class Product extends Resource {

    public static Product find(int id) throws Exception {
        throw new NotImplementedException();
    }
    
    public static Product find(String handle) throws Exception {
        throw new NotImplementedException();
    }
    
    public static ArrayList<Product> all() throws Exception {
        throw new NotImplementedException();
    }
    
    public static ArrayList<Product> all(int parentId) throws Exception {
        throw new NotImplementedException();
    }
    
    public static Product create() throws Exception {
        throw new NotImplementedException();
    }
}
