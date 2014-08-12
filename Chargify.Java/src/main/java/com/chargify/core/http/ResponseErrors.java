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

package com.chargify.core.http;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Root(strict=false)
public class ResponseErrors implements Iterable<String>, Iterator<String> {
    @ElementList(inline=true, required=true, entry="error", type=String.class) List<String> errors;

    private int currentIndex = 0;

    public ResponseErrors() {
        errors = new ArrayList<>();
    }

    public ResponseErrors(String message) {
        errors = new ArrayList<>();
        errors.add(message);
    }

    public boolean any() {
        return errors.size() > 0;
    }

    public int count() {
        return errors.size();
    }

    public int size() {
        return errors.size();
    }

    public String first() {
        return any() ? errors.get(0) : null;
    }

    public List<String> all() {
        return errors;
    }

    public void resetIterator() {
        currentIndex = 0;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < errors.size();
    }

    @Override
    public String next() {
        if (currentIndex == errors.size())
            throw new NoSuchElementException();

        currentIndex++;
        return errors.get(currentIndex - 1);
    }

    public void add(String error) {
        errors.add(error);
    }
}
