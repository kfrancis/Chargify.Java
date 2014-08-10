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

package com.chargify.core.helpers;

import com.chargify.core.resources.Resource;

import java.lang.reflect.Field;

public class Persisted {
    /**
     * Marks a resource as persisted. resource.isPersisted() will now return true.
     * @param resource the Chargify resource to mark as persisted
     * @return the resource now marked as persisted
     */
    public static <T extends Resource> T mark(T resource) throws Exception {
        return apply(resource, false);
    }

    /**
     * Unmarks a resource as persisted. resource.isPersisted() will now return false.
     * @param resource the Chargify resource to mark as new
     * @return the resource now marked as new
     */
    public static <T extends Resource> T unmark(T resource) throws Exception {
        return apply(resource, true);
    }

    private static <T extends Resource> T apply(T resource, boolean newRecord) throws Exception {
        Field f = Resource.class.getDeclaredField("newRecord");
        f.setAccessible(true);
        f.set(resource, newRecord);
        return resource;
    }
}
