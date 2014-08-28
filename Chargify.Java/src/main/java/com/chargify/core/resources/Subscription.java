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
import com.chargify.core.resources.collections.SubscriptionList;
import com.github.kevinsawicki.http.HttpRequest;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @see <a href="https://docs.chargify.com/api-subscriptions">https://docs.chargify.com/api-subscriptions</a>
 * @author Kori
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class Subscription extends Resource {

    @Getter @Setter @Element(required=false, name="cancel_at_end_of_period") private Boolean cancelAtEndOfPeriod = null;
    @Getter @Setter @Element(required=false, name="canceled_at") private Date canceledAt = null;
    @Getter @Setter @Element(required=false, name="cancellation_message") private String cancellationMessage = null;
    @Getter @Setter @Element(required=false, name="created_at") private Date createdAt = null;
    @Getter @Setter @Element(required=false, name="current_period_ends_at") private Date currentPeriodEndsAt = null;
    @Getter @Setter @Element(required=false, name="expires_at") private Date expiresAt = null;
    @Getter @Setter @Element(required=false, name="next_billing_at") private Date nextBillingAt = null;
    @Getter @Setter @Element(required=false, name="payment_collection_method") private String paymentCollectionMethod = null;
    @Getter @Setter @Element(required=false) private String state = null;
    @Getter @Setter @Element(required=false, name="trial_ended_at") private Date trialEndedAt = null;
    @Getter @Setter @Element(required=false, name="trial_started_at") private Date trialStartedAt = null;
    @Getter @Setter @Element(required=false, name="product_handle") private String productHandle = null;
    @Getter @Setter @Element(required=false, name="product_id") private Integer productId = null;
    @Getter @Setter @Element(required=false, name="customer_id") private Integer customerId = null;
    @Getter @Setter @Element(required=false, name="customer_reference") private String customerReference = null;
    @Getter @Setter @Element(required=false, name="payment_profile_id") private Integer paymentProfileId = null;
    @Getter @Setter @Element(required=false, name="vat_number") private String vatNumber = null;
    @Getter @Setter @Element(required=false, name="coupon_code") private String couponCode = null;
    @Getter @Setter @Element(required=false, name="product_change_delayed") private Boolean productChargeDelayed = null;
    
    /* Output only */
    @Setter @Element(required=false) private Integer id = null;
    @Setter @Element(required=false, name="activated_at") private Date activatedAt = null;
    @Setter @Element(required=false, name="next_assessment_at") private Date nextAssessmentAt = null;
    @Setter @Element(required=false, name="balanace_in_cents") private Integer balanceInCents = null;

    /**
     * Represents the subscription record as a hash
     * @return a hash of all the subscription attributes
     */
    @Override
    public HashMap<String, String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("subscription[id]", this.getId(), hash);
        guard("subscription[product_handle]", this.getProductHandle(), hash);
        guard("subscription[product_id]", this.getProductId(), hash);
        guard("subscription[customer_id]", this.getCustomerId(), hash);
        guard("subscription[customer_reference]", this.getCustomerReference(), hash);
        guard("subscription[payment_profile_id]", this.getPaymentProfileId(), hash);
        guard("subscription[cancel_at_end_of_period]", this.getCancelAtEndOfPeriod(), hash);
        guard("subscription[cancellation_message]", this.getCancellationMessage(), hash);
        guard("subscription[next_billing_at]", this.getNextBillingAt(), hash);
        guard("subscription[payment_collection_method]", this.getPaymentCollectionMethod(), hash);
        guard("subscription[vat_number]", this.getVatNumber(), hash);
        guard("subscription[coupon_code]", this.getCouponCode(), hash);
        guard("subscription[product_change_delayed]", this.getProductChargeDelayed(), hash);
        return hash;
    }
    
    /**
     * Creates or updates a record in Chargify
     * @return A subscription response object from the request
     * @throws Exception
     */
    public Response<Subscription> save() throws Exception {
       return _save(ClientFactory.build());
    }
    
    /**
     * Cancel a subscription
     * @param   id The Chargify id of the subscription
     * @return  The subscription response object from the request
     * @throws Exception
     */
    public Response<Subscription> cancel(int id) throws Exception {
        return _cancel(ClientFactory.build(), id);
    }

    /**
     * Retrieve details about a single subscription, using the subscription id
     * @param id            The id of the subscription
     * @return              The details of the subscription
     * @throws Exception
     */
    public static Response<Subscription> find(int id) throws Exception {
        return _find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve all subscriptions
     * @return              The list of subscriptions
     * @throws Exception
     */
    public static ListResponse<Subscription, SubscriptionList> all() throws Exception {
        return _all(ClientFactory.build());
    }
    
    /**
     * Retrieve details about a single subscription, using the subscription id
     * @param client        The communication client
     * @param id            The id of the subscription
     * @return              The details of the subscription
     * @throws Exception
     */
    public static Response<Subscription> _find(Client client, int id) throws Exception {
        HttpRequest request = client.get("subscriptions/" + id);
        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }
    
    /**
     * Retrieve all subscriptions
     * @param client        The communication client
     * @return              The list of subscriptions
     * @throws Exception
     */
    public static ListResponse<Subscription, SubscriptionList> _all(Client client) throws Exception {
        HttpRequest request = client.get("subscriptions");
        return new ListResponse<Subscription, SubscriptionList>(request.code(), request.body(), Subscription.class, SubscriptionList.class);
    }
    
    /**
     *
     * @param client    The communication client
     * @param id        The Chargify id of the subscription
     * @return          The details of the subscription as a result of the cancellation
     * @throws Exception
     */
    public Response<Subscription> _cancel(Client client, int id) throws Exception {
        HttpRequest request = client.delete("subscriptions/" + id);
        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }
    
    /**
     *
     * @param client    The communication client
     * @return          The details of the subscription as a result of the save
     * @throws Exception
     */
    public Response<Subscription> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ?
                client.put("subscriptions/" + id, this.asHash()) :
                client.post("subscriptions", this.asHash());
        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof Subscription);
    }
}
