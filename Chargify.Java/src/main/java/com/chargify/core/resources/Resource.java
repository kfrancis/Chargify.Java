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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

public abstract class Resource {

    private final Client client;
    protected boolean newRecord = true;

    public Integer getId() {
        return null;
    }

    public boolean isPersisted() {
        return !newRecord;
    }

    protected void guard(String key, Object value, HashMap<String, String> hash) {
        if (value != null) {
            hash.put(key, value.toString());
        }
    }

    public Resource() {
        this.client = ClientFactory.build();
    }

    public Resource(Client client) {
        this.client = client;
    }

    public HashMap<String, String> asHash() {
        throw new NotImplementedException();
    }

    public boolean canEqual(Object other) {
        throw new NotImplementedException();
    }

    @Override public boolean equals(Object other) {
        if (canEqual(other)) {
            Resource that = (Resource) other;
            return this.hashCode() == that.hashCode();
        }

        return false;
    }

    @Override public int hashCode() {
        int offset = 0;
        if(this.isPersisted()) {
            offset = this.getId() == null ? 0 : this.getId();
            offset += 1000;
        }

        return asHash().hashCode() + offset;
    }
}
