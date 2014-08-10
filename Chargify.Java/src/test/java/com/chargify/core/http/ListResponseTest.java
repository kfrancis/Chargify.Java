package com.chargify.core.http;

import com.chargify.core.helpers.Persisted;
import com.chargify.core.resources.Customer;
import com.chargify.core.resources.collections.CustomerList;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListResponseTest {

    @Test
    public void testIsErrorWhenStatusIs200() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "", Customer.class, CustomerList.class);
        assertFalse(responseList.isError());
    }

    @Test
    public void testIsErrorWhenStatusIs400() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(400, "", Customer.class, CustomerList.class);
        assertTrue(responseList.isError());
    }

    @Test
    public void testIsSuccessWhenStatusIs200() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "", Customer.class, CustomerList.class);
        assertTrue(responseList.isSuccess());
    }

    @Test
    public void testIsSuccessWhenStatusIs400() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(400, "", Customer.class, CustomerList.class);
        assertFalse(responseList.isSuccess());
    }

    @Test
    public void testAllWhenSuccess() throws Exception {
        ArrayList<Customer> expectedCustomers = new ArrayList<>();
        Customer jeremy = Persisted.mark(new Customer());
        Customer kori   = Persisted.mark(new Customer());

        jeremy.setFirstName("jeremy");
        kori.setFirstName("kori");

        expectedCustomers.add(jeremy);
        expectedCustomers.add(kori);

        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "<customers><customer><first_name>jeremy</first_name></customer><customer><first_name>kori</first_name></customer></customers>", Customer.class, CustomerList.class);

        assertEquals(expectedCustomers, responseList.all());
    }

    @Test
    public void testAllWhenCouldNotParseResponse() throws Exception {
        ArrayList<Customer> expectedCustomers = new ArrayList<>();
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "<customers", Customer.class, CustomerList.class);
        assertEquals(expectedCustomers, responseList.all());

        assertEquals(500, responseList.getStatus());
        assertEquals(1, responseList.errors().size());
    }

    @Test
    public void testAllWhenFailure() throws Exception {
        ArrayList<Customer> expectedCustomers = new ArrayList<>();
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(400, "<customers><customer><first_name>jeremy</first_name></customer><customer><first_name>kori</first_name></customer></customers>", Customer.class, CustomerList.class);
        assertEquals(expectedCustomers, responseList.all());
    }

    @Test
    public void testErrorsWhenSuccess() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "<errors><error>broken.</error></errors>", Customer.class, CustomerList.class);
        assertEquals(new ArrayList<String>(), responseList.errors().all());
    }

    @Test
    public void testErrorsWhenFailure() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(400, "<errors><error>broken.</error></errors>", Customer.class, CustomerList.class);
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual   = new ArrayList<>();
        expected.add("broken.");

        for(String error : responseList.errors()) {
            actual.add(error);
        }

        assertEquals(expected, actual);
    }

    @Test
    public void testIteratingOverResults() throws Exception {
        ListResponse<Customer, CustomerList> responseList = new ListResponse<>(200, "<customers><customer><first_name>jeremy</first_name></customer><customer><first_name>kori</first_name></customer></customers>", Customer.class, CustomerList.class);
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual   = new ArrayList<>();
        expected.add("jeremy");
        expected.add("kori");

        for(Customer customer : responseList) {
            actual.add(customer.getFirstName());
        }

        for(Customer _customer : responseList) {
            // No-Op - ensure that iteration can happen multiple times
        }

        assertEquals(expected, actual);
    }
}