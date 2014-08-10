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

package com.chargify.core;

import com.chargify.core.helpers.Persisted;
import com.chargify.core.resources.ListResource;
import com.chargify.core.resources.Resource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ListResponse<T extends Resource, S extends ListResource> extends Response<T> implements Iterable<T>, Iterator<T> {
    private final Class<S> listType;
    private int currentIndex;
    private List<T> resources;

    public ListResponse(int status, String rawResponse, Class<T> resourceType, Class<S> listType) {
        super(status, rawResponse, resourceType);
        this.listType = listType;
        currentIndex = 0;
    }

    public T getResource() {
        throw new NotImplementedException();
    }

    public List<T> all() {
        memoizeResults();
        return resources;
    }

    public int count() {
        memoizeResults();
        return resources.size();
    }

    public int size() {
        memoizeResults();
        return resources.size();
    }

    public void resetIterator() {
        currentIndex = 0;
    }

    @Override
    public Iterator<T> iterator() {
        memoizeResults();
        return this;
    }

    @Override
    public boolean hasNext() {
        memoizeResults();
        return currentIndex < resources.size();
    }

    @Override
    public T next() {
        memoizeResults();
        if (currentIndex == resources.size())
            throw new NoSuchElementException();

        currentIndex++;
        return resources.get(currentIndex - 1);
    }

    private void memoizeResults() {
        if(resources != null) {
            return;
        }

        if(this.isSuccess()) {
            try {
                S resourceList = (S) serializer.read(this.listType, this.rawResponse);
                resources = (ArrayList<T>)resourceList.all();
                for(T resource : resources) {
                    Persisted.mark(resource);
                }
                resetIterator();
                return;
            } catch (Exception ex) {
                this.status = 500;
                this.responseErrors = new ResponseErrors(ex.getLocalizedMessage());
            }
        }

        resources = new ArrayList<>();
    }
}
