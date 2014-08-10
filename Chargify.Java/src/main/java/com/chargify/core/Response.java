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
import com.chargify.core.resources.Resource;
import com.chargify.core.transformers.DateFormatTransformer;
import org.simpleframework.xml.core.Persister;

public class Response<T extends Resource> {

    private int status;
    private final String rawResponse;
    private final Persister serializer;
    private final Class resourceType;
    private ResponseErrors responseErrors;

    public Response(int status, String rawResponse, Class<T> resourceType) {
        this.status       = status;
        this.rawResponse  = rawResponse;
        this.serializer   = new Persister(DateFormatTransformer.matcher());
        this.resourceType = resourceType;
    }

    public boolean isSuccess() {
        return this.status > 199 && this.status < 300;
    }

    public boolean isError() {
        return this.status > 399;
    }

    public <T extends Resource> T getResource() {
        if(this.isSuccess()) {
            try {
                return Persisted.mark((T) serializer.read(this.resourceType, this.rawResponse));
            } catch (Exception ex) {
                this.status = 500;
                this.responseErrors = new ResponseErrors(ex.getLocalizedMessage());
            }
        }
        return null;

    }

    public ResponseErrors errors() {
        if(this.responseErrors != null) {
            return this.responseErrors;
        }

        if(this.isError()) {
            return _getErrors();
        }
        else {
            return new ResponseErrors();
        }
    }

    private ResponseErrors _getErrors() {
        if(this.isError()) {
            try {
                return serializer.read(ResponseErrors.class, this.rawResponse);
            } catch (Exception ex) {
                this.status = 500;
                return new ResponseErrors(ex.getLocalizedMessage());
            }
        } else {
            return new ResponseErrors();
        }
    }
}
