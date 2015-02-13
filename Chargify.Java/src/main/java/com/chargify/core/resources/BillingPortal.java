/*
 * The MIT License
 *
 * Copyright 2015 kfrancis, jeremywrowe.
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
import com.chargify.core.http.Response;
import com.github.kevinsawicki.http.HttpRequest;
import java.util.Date;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Billing Portal
 * @see <a href="https://docs.chargify.com/api-billing-portal">https://docs.chargify.com/api-billing-portal</a>
 */
@Root(strict=false)
@Default(DefaultType.FIELD)
public class BillingPortal extends Resource {
    
    @Getter @Element private String url = null;
    @Getter @Element(name="fetch_count") private Integer fetchCount = null;
    @Getter @Element(name="created_at") private Date createdAt = null;
    @Getter @Element(name="new_link_available_at") private String newLinkAvailableAt = null;
    @Getter @Element(name="expires_at") private Date expiresAt = null;
    
    @Override
    public HashMap<String,String> asHash() {
        HashMap<String, String> hash = new HashMap<>();
        guard("management_link[url]", this.getUrl(), hash);
        guard("management_link[fetch_count]", this.getFetchCount(), hash);
        guard("management_link[created_at]", this.getCreatedAt(), hash);
        guard("management_link[new_link_available_at]", this.getNewLinkAvailableAt(), hash);
        guard("management_link[expires_at]", this.getExpiresAt(), hash);
        return hash;
    }
    
    /**
     * Generate or retrieve the billing portal management link, if possible, from Chargify
     * @param customerId
     * @return
     * @throws Exception
     */
    public Response<BillingPortal> getPortalLink(Integer customerId) throws Exception {
        return _getPortalLink(ClientFactory.build(), customerId);
    }
    
    /**
     * Generate or retrieve the billing portal management link, if possible, from Chargify
     * @param client        The communication client
     * @param customerId    The id of the customer
     * @return              Returns the billing portal information
     * @throws Exception
     */
    public Response<BillingPortal> _getPortalLink(Client client, Integer customerId) throws Exception {
        HttpRequest request = client.get("portal/customers/" + customerId + "/management_link");
        return new Response<BillingPortal>(request.code(), request.body(), BillingPortal.class);
    }
}
