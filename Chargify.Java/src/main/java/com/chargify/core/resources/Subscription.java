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

import com.chargify.core.Client;
import com.chargify.core.ClientFactory;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * @see <a href="https://docs.chargify.com/api-subscriptions">https://docs.chargify.com/api-subscriptions</a>
 * @author Kori
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class Subscription extends Resource {
    
    @Element(name="activated_at", required=false) private String activatedAt = "";
    @Element(name="balanace_in_cents", required=false) private int balanceInCents;
    @Element(name="cancel_at_end_of_period", required=false) private boolean cancelAtEndOfPeriod;
    @Element(name="canceled_at", required=false) private String canceledAt = "";
    @Element(name="cancellation_message", required=false) private String cancellationMessage = "";
    @Element(name="created_at", required=false) private String createdAt = "";
    @Element(name="current_period_ends_at", required=false) private String currentPeriodEndsAt = "";
    @Element(name="expires_at", required=false) private String expiresAt = "";
    @Element private int id;
    @Element(name="next_assessment_at", required=false) private String nextAssessmentAt = "";
    @Element(name="payment_collection_method", required=false) private String paymentCollectionMethod = "";
    @Element private String state = "";
    @Element(name="trial_ended_at", required=false) private String trialEndedAt = "";
    @Element(name="trial_started_at", required=false) private String trialStartedAt = "";
    
    public static Subscription find(int id) throws Exception {
        throw new NotImplementedException();
    }
    
    public static ArrayList<Subscription> all() throws Exception {
        throw new NotImplementedException();
    }
    
    public static Subscription create() {
        throw new NotImplementedException();
    }
    
    public static Subscription update() {
        throw new NotImplementedException();
    }
    
    public static Subscription cancel(int id) {
        throw new NotImplementedException();
    }
}
