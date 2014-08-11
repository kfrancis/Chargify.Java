package com.chargify.core.resources.collections;

import com.chargify.core.resources.Subscription;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict=false)
public class SubscriptionList implements ListResource<Subscription> {
    @ElementList(entry="subscription", inline=true, type=com.chargify.core.resources.Subscription.class) List<Subscription> subscriptions;

    @Override
    public List<Subscription> all() {
       return subscriptions;
    }
}
