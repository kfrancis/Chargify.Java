package com.chargify.core;

import com.chargify.core.resources.Resource;
import com.chargify.core.transformers.DateFormatTransformer;
import org.simpleframework.xml.core.Persister;

import java.lang.reflect.Field;

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
        return _getResource();
    }

    public ResponseErrors getErrors() {
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

    private <T extends Resource> T _getResource() {
        if(this.isSuccess()) {
            try {
                return (T)_markResourceAsPersisted((T)serializer.read(this.resourceType, this.rawResponse));
            } catch (Exception ex) {
                this.status = 500;
                this.responseErrors = new ResponseErrors(ex.getLocalizedMessage());
            }
        }
        return null;

    }

    private ResponseErrors _getErrors() {
        try {
            return serializer.read(ResponseErrors.class, this.rawResponse);
        } catch (Exception ex) {
            this.status = 500;
            return new ResponseErrors(ex.getLocalizedMessage());
        }
    }

    private <T extends Resource> T _markResourceAsPersisted(T resource) throws Exception {
        Field f = Resource.class.getDeclaredField("newRecord");
        f.setAccessible(true);
        f.set(resource, false);
        return (T) resource;
    }

}
