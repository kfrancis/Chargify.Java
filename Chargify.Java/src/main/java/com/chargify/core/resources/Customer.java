package com.chargify.core.resources;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

@Root(strict=false)
@Default(DefaultType.FIELD)
public class Customer extends Resource {

    public Customer find(String reference) {
        throw new NotImplementedException();
    }

    public Customer find(int id) {
        throw new NotImplementedException();
    }

    public ArrayList<Customer> all() {
        throw new NotImplementedException();
    }

    public Customer update() {
        throw new NotImplementedException();
    }
}
