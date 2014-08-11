package com.chargify.core.resources;

import com.chargify.core.http.Client;
import com.chargify.core.http.ListResponse;
import com.chargify.core.http.Response;
import com.chargify.core.helpers.Maps;
import com.chargify.core.helpers.Persisted;
import com.chargify.core.resources.collections.CustomerList;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMockSupport;

import static org.easymock.EasyMock.expect;

import static org.junit.Assert.*;

public class CustomerTest extends EasyMockSupport {

    @Mock private Client client;
    @Mock private HttpRequest request;

    @Before
    public void setup() {
        client  = createMock(Client.class);
        request = createMock(HttpRequest.class);
    }

    @After
    public void tearDown() {
        verifyAll();
    }

    @Test
    public void testFind() throws Exception {
        expect(client.get("customers/70")).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        Response<Customer> response = Customer._find(client, 70);
        Customer customer = response.getResource();

        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
    }

    @Test
    public void testFindByReference() throws Exception {
        expect(client.get("customers/lookup", Maps.of("reference", "jdub"))).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        Response<Customer> response = Customer._findByReference(client, "jdub");
        Customer customer = response.getResource();

        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
        assertEquals("Wrong Customer reference", "jdub", customer.getReference());
    }

    @Test
    public void testAll() throws Exception {
        String body = getHeader() +
          "<customers type=\"array\">" +
              customerResponse(false) +
          "</customers>";

        expect(client.get("customers")).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(body);
        replayAll();

        ListResponse<Customer, CustomerList> customers = Customer._all(client);
        Customer customer = customers.all().get(0);

        assertEquals("Did not have the correctly sized customer list", 1, customers.size());
        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
        assertEquals("Wrong Customer reference", "jdub", customer.getReference());
    }

    @Test
    public void testSavingANewCustomer() throws Exception {
        Customer customer = new Customer();

        expect(client.post("customers", customer.asHash())).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        customer._save(client);
    }

    @Test
    public void testUpdatingAnExistingCustomer() throws Exception {
        Customer customer = Persisted.mark(new Customer());
        customer.setId(55);

        expect(client.put("customers/55", customer.asHash())).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        customer._save(client);
    }

    private String getHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }

    private String customerResponse(boolean withHeader) {
        String output = "";
        if (withHeader) {
            output += getHeader();
        }
        return output += "<customer>" +
            "<first_name>Jeremy</first_name>" +
            "<last_name>Rowe</last_name>" +
            "<email>jeremy.w.rowe@gmail.com</email>" +
            "<organization>jcorp</organization>" +
            "<reference>jdub</reference>" +
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
}