package com.chargify.core.resources;

import com.chargify.core.http.Client;
import com.chargify.core.http.ListResponse;
import com.chargify.core.http.Response;
import com.chargify.core.helpers.Maps;
import com.chargify.core.helpers.Persisted;
import com.chargify.core.resources.collections.AllocationList;
import com.github.kevinsawicki.http.HttpRequest;
import org.easymock.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMockSupport;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

public class AllocationTest extends EasyMockSupport {

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
    public void testAll() throws Exception {
        String body = getHeader() +
          "<allocations type=\"array\">" +
              allocationResponse(false) +
          "</allocations>";

        expect(client.get("subscriptions/2585595/components/11960/allocations")).andReturn(request);
        expect(request.code()).andReturn(200);
        expect(request.body()).andReturn(body);
        replayAll();

        ListResponse<Allocation, AllocationList> allocations = Allocation._all(client, 2585595, 11960);
        Allocation allocation = allocations.all().get(0);

        assertEquals("Did not have the correctly sized allocation list", 1, allocations.size());
        assertEquals("Wrong Allocation quantity", "7", allocation.getQuantity());
    }

    private String getHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }

    private String allocationResponse(boolean withHeader) {
        String output = "";
        if (withHeader) {
            output += getHeader();
        }
        return output += "<allocation>" +
            "<timestamp>2012-11-20T22:00:37Z</timestamp>" +
            "<quantity type=\"integer\">7</quantity>" +
            "<previous_quantity type=\"integer\">3</previous_quantity>" +
            "<component_id type=\"integer\">11960</component_id>" +
            "<memo>moving to 7</memo>" +
            "<subscription_id type=\"integer\">2585595</subscription_id>" +
        "</allocation>";
    }
}