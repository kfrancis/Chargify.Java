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

import java.util.Date;
import java.util.List;
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

    @Element private Integer id;
    @Element(name="activated_at", required=false) private Date activatedAt = null;
    @Element(name="balanace_in_cents", required=false) private Integer balanceInCents = null;
    @Element(name="cancel_at_end_of_period", required=false) private Boolean cancelAtEndOfPeriod = null;
    @Element(name="canceled_at", required=false) private Date canceledAt = null;
    @Element(name="cancellation_message", required=false) private String cancellationMessage = null;
    @Element(name="created_at", required=false) private Date createdAt = null;
    @Element(name="current_period_ends_at", required=false) private Date currentPeriodEndsAt = null;
    @Element(name="expires_at", required=false) private Date expiresAt = null;
    @Element(name="next_assessment_at", required=false) private Date nextAssessmentAt = null;
    @Element(name="payment_collection_method", required=false) private String paymentCollectionMethod = null;
    @Element(required=false) private String state = null;
    @Element(name="trial_ended_at", required=false) private Date trialEndedAt = null;
    @Element(name="trial_started_at", required=false) private Date trialStartedAt = null;

    public Response<Subscription> save() throws Exception {
       return _save(ClientFactory.build());
    }

    public Response<Subscription> _save(Client client) throws Exception {
        HttpRequest request = this.isPersisted() ?
                client.put("subscriptions/" + id, this.asHash()) :
                client.post("subscriptions", this.asHash());

        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }

    public Response<Subscription> cancel() throws Exception {
        return _cancel(ClientFactory.build());
    }

    public Response<Subscription> _cancel(Client client) throws Exception {
        HttpRequest request =  client.delete("subscriptions/" + id);
        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }

    /**
     * Retrieve details about a single subscription, using the subscription id
     * @param id            The id of the subscription
     * @return              The details of the subscription
     * @throws Exception
     */
    public static Response<Subscription> find(int id) throws Exception {
        return find(ClientFactory.build(), id);
    }
    
    /**
     * Retrieve details about a single subscription, using the subscription id
     * @param client        The communication client
     * @param id            The id of the subscription
     * @return              The details of the subscription
     * @throws Exception
     */
    public static Response<Subscription> find(Client client, int id) throws Exception {
        HttpRequest request = client.get("subscriptions/" + id);
        return new Response<Subscription>(request.code(), request.body(), Subscription.class);
    }
    
    /**
     * Retrieve all subscriptions
     * @param client        The communication client
     * @return              The list of subscriptions
     * @throws Exception
     */
    public static ListResponse<Subscription, SubscriptionList> all(Client client) throws Exception {
        HttpRequest request = client.get("subscriptions");
        return new ListResponse<Subscription, SubscriptionList>(request.code(), request.body(), Subscription.class, SubscriptionList.class);
    }
    
    /**
     * Retrieve all subscriptions
     * @return              The list of subscriptions
     * @throws Exception
     */
    public static ListResponse<Subscription, SubscriptionList> all() throws Exception {
        return all(ClientFactory.build());
    }
    

    @Override public boolean canEqual(Object other) {
        return (other instanceof Subscription);
    }
}
