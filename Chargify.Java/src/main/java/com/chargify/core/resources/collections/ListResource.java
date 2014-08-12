package com.chargify.core.resources.collections;

import com.chargify.core.resources.Resource;
import java.util.*;

public interface ListResource<T extends Resource> {
    List<T> all();
}
