package com.chargify.core.resources;

import com.chargify.core.Client;
import com.chargify.core.Configuration;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import lombok.Getter;
import lombok.Setter;
import org.easymock.EasyMockSupport;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;

public class CustomerTest extends EasyMockSupport {

    @Mock private Client client;
    @Mock private HttpRequest request;

    @Before
    public void setup() {
        client  = createMock(Client.class);
        request = createMock(HttpRequest.class);
    }

    @Test
    public void testFindWithAnOkResponse() throws Exception {
        expect(client.get("customers/70")).andReturn(request);
        expect(request.ok()).andReturn(true);
        expect(request.body()).andReturn(okResponse);
        replayAll();

        Customer customer = Customer.find(client, 70);

        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
    }

    private String okResponse = "" +
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    "<customer>" +
      "<first_name>Jeremy</first_name>" +
      "<last_name>Rowe</last_name>" +
      "<email>jeremy.w.rowe@gmail.com</email>" +
      "<organization>jcorp</organization>" +
      "<reference nil=\"true\"/>" +
      "<id type=\"integer\">70</id>" +
      "<created_at type=\"datetime\">2014-06-27T11:01:41-04:00</created_at>" +
      "<updated_at type=\"datetime\">2014-06-27T11:01:41-04:00</updated_at>" +
      "<address>123 Seasme Street</address>" +
      "<address_2></address_2>" +
      "<city>Cary</city>" +
      "<state></state>" +
      "<zip>27513</zip>" +
      "<country>US</country>" +
      "<phone>555-555-5555</phone>" +
      "<verified type=\"boolean\">true</verified>" +
      "<portal_customer_created_at type=\"datetime\" nil=\"true\"/>" +
      "<portal_invite_last_sent_at type=\"datetime\" nil=\"true\"/>" +
      "<portal_invite_last_accepted_at type=\"datetime\" nil=\"true\"/>" +
    "</customer>";

}