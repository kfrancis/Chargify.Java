package com.chargify.core.resources;

import com.chargify.core.Client;
import com.chargify.core.ClientFactory;
import com.chargify.core.Configuration;
import com.chargify.core.helpers.Maps;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMockSupport;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

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
    public void testFindWithAnOkResponse() throws Exception {
        expect(client.get("customers/70")).andReturn(request);
        expect(request.ok()).andReturn(true);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        Customer customer = Customer.find(client, 70);

        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
    }

    @Test
    public void testFindRecordNotFound() throws Exception {
        expect(client.get("customers/10")).andReturn(request);
        expect(request.ok()).andReturn(false);
        replayAll();

        try {
            Customer.find(client, 10);
            fail("Should have raised RecordNotFoundException");
        } catch (RecordNotFoundException ex) {
            assertEquals("Wrong message", "Customer 10 not found", ex.getMessage());
        }
    }

    @Test
    public void testFindByReferenceWithAnOkResponse() throws Exception {
        expect(client.get("customers/lookup", Maps.of("reference", "jdub"))).andReturn(request);
        expect(request.ok()).andReturn(true);
        expect(request.body()).andReturn(customerResponse(true));
        replayAll();

        Customer customer = Customer.findByReference(client, "jdub");

        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
        assertEquals("Wrong Customer reference", "jdub", customer.getReference());
    }

    @Test
    public void testFindByReferenceRecordNotFound() throws Exception {
        expect(client.get("customers/lookup", Maps.of("reference", "jdub"))).andReturn(request);
        expect(request.ok()).andReturn(false);
        replayAll();

        try {
            Customer.findByReference(client, "jdub");
            fail("Should have raised RecordNotFoundException");
        } catch (RecordNotFoundException ex) {
            assertEquals("Wrong message", "Customer jdub not found", ex.getMessage());
        }
    }

    @Test
    public void testAll() throws Exception {
        String body = getHeader() +
          "<customers type=\"array\">" +
              customerResponse(false) +
          "</customers>";

        expect(client.get("customers")).andReturn(request);
        expect(request.ok()).andReturn(true);
        expect(request.body()).andReturn(body);
        replayAll();

        List<Customer> customers = Customer.all(client);
        Customer customer        = customers.get(0);

        assertEquals("Did not have the correctly sized customer list", 1, customers.size());
        assertEquals("Wrong Customer first name", "Jeremy", customer.getFirstName());
        assertEquals("Wrong Customer last name", "Rowe", customer.getLastName());
        assertEquals("Wrong Customer reference", "jdub", customer.getReference());
    }

    @Test
    public void testAllWithParsingException() throws Exception {
        expect(client.get("customers")).andReturn(request);
        expect(request.ok()).andReturn(false);

        try {
            replayAll();
            Customer.all(client);
            fail("Customer list did not throw an exception and it should have");
        } catch(Exception ex) {
            assertEquals("Incorrect exception message", "Could not parse /customers.xml. Please verify your credentials", ex.getMessage());
        }
    }

    @Test
    public void testSave() throws Exception {
        // Configuration.url         = "http://acme.chargify.dev/";
        // Configuration.apiKey      = "7feIiz7lk6TyM4JpsXyG";
        // Configuration.apiPassword = "x";
        // Configuration.json        = false;

        Customer customer = new Customer();
        customer.setEmail("bob@example.com");
        customer.setFirstName("Bob");
        customer.setLastName("Smith");
        customer.setAddress("123 yarn street");
        customer.setCity("china town");
        customer.setReference("cat" + new Random().nextInt());
        replayAll();
        // CREATE
        Customer c = customer.save(ClientFactory.build());
        // UPDATE
        c.setFirstName("jane");
        c.save(ClientFactory.build());
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