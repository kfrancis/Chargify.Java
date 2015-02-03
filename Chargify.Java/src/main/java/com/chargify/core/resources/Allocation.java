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
import com.chargify.core.http.ListResponse;
import com.chargify.core.http.Response;
import com.chargify.core.helpers.Maps;
import com.chargify.core.resources.collections.AllocationList;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import java.util.*;

/**
 *
 * @see <a href="https://docs.chargify.com/api-allocations">https://docs.chargify.com/api-allocations</a>
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class Allocation extends Resource {

    @Getter @Setter @Element(name="component_id") private Integer componentId = null;
    @Getter @Setter @Element(name="subscription_id") private Integer subscriptionId = null;
    @Getter @Setter @Element private Integer quantity = null;
    @Getter @Setter @Element(name="previous_quantity") private Integer previousQuantity = null;
    @Getter @Setter @Element private String memo = null;
    @Getter @Setter @Element private Date timestamp = null;
    @Getter @Setter @Element(name="proration_upgrade_scheme") private String prorationUpgradeScheme = null;
    @Getter @Setter @Element(name="proration_downgrade_scheme") private String prorationDowngradeScheme = null;
    
    /**
     * represents the allocation record as a hash
     * @return a hash of all of the allocation attributes
     */
    @Override
    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("allocation[component_id]", this.getComponentId(), hash);
        guard("allocation[subscription_id]", this.getSubscriptionId(), hash);
        guard("allocation[quantity]", this.getQuantity(), hash);
        guard("allocation[previous_quantity]", this.getPreviousQuantity(), hash);
        guard("allocation[memo]", this.getMemo(), hash);
        guard("allocation[timestamp]", this.getTimestamp(), hash);
        guard("allocation[proration_upgrade_scheme]", this.getProrationUpgradeScheme(), hash);
        guard("allocation[proration_downgrade_scheme]", this.getProrationDowngradeScheme(), hash);
        return hash;
    }
    
    /**
     * Retrieve a list of all allocations for a specific component of a subscription
     * @param subscriptionId The if of the subscription
     * @param componentId The id of the component to retrieve
     * @return A list of allocations, if applicable
     * @throws Exception
     */
    public static ListResponse<Allocation, AllocationList> all(Integer subscriptionId, Integer componentId) throws Exception {
        return _all(ClientFactory.build(), subscriptionId, componentId);
    }
    
    /**
     * Retrieve a list of all allocations for all components of a subscription
     * @param subscriptionId The id of the subscription
     * @return A list of allocations, if applicable
     * @throws Exception
     */
    public static ListResponse<Allocation, AllocationList> all(Integer subscriptionId) throws Exception {
        return _all(ClientFactory.build(), subscriptionId);
    }
    
    public static ListResponse<Allocation, AllocationList> _all(Client client, Integer subscriptionId, Integer componentId) throws Exception {
        HttpRequest request = client.get("subscriptions/" + subscriptionId + "/components/" + componentId + "/allocations");
        return new ListResponse<Allocation, AllocationList>(request.code(), request.body(), Allocation.class, AllocationList.class);
    }
    
    public static ListResponse<Allocation, AllocationList> _all(Client client, Integer subscriptionId) throws Exception {
        HttpRequest request = client.get("subscriptions/" + subscriptionId + "/components/allocations");
        return new ListResponse<Allocation, AllocationList>(request.code(), request.body(), Allocation.class, AllocationList.class);
    }
    
    /**
     * Method for creating a component allocation
     * @return The allocation details
     * @throws Exception
     */
    public Response<Allocation> save() throws Exception {
        return _save(ClientFactory.build());
    }
    
    public Response<Allocation> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ?
                client.put("subscriptions/" + this.subscriptionId + "/components/" + this.componentId + "/allocations", this.asHash()) :
                client.post("subscriptions/" + this.subscriptionId + "/components/" + this.componentId + "/allocations", this.asHash());

        return new Response<Allocation>(request.code(), request.body(), Allocation.class);
    }
}
