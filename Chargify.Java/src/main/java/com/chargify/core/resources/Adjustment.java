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

import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 
 * @see <a href="https://docs.chargify.com/api-adjustments">https://docs.chargify.com/api-adjustments</a>
 */
@Root(strict=false)
public class Adjustment extends Resource {
    
    @Getter @Setter @Element private Integer id = null;
    @Getter @Setter @Element(name="amount_in_cents") private Integer amountInCents = null;
    @Getter @Setter @Element(name="created_at") private String createdAt = null;
    @Getter @Setter @Element(name="ending_balance_in_cents") private String endingBalanceInCents = null;
    @Getter @Setter @Element private String memo = null;
    @Getter @Setter @Element(name="subscription_id") private Integer subscriptionId = null;
    @Getter @Setter @Element(name="product_id") private Integer productId = null;
    @Getter @Setter @Element private Boolean success = null;
    @Getter @Setter @Element private String type = null;
    @Getter @Setter @Element(name="transaction_type") private String transactionType = null;
    @Getter @Setter @Element(name="payment_id") private Integer paymentId = null;
    
    public static Adjustment create() throws Exception {
        throw new NotImplementedException();
    }

    @Override public boolean canEqual(Object other) {
        return (other instanceof Adjustment);
    }
}
