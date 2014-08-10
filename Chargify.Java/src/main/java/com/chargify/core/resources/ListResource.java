package com.chargify.core.resources;

import java.util.List;

public interface ListResource<T extends Resource> {
    List<T> all();
}
