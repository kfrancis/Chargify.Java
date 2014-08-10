package com.chargify.core.http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResponseErrorsTest {

    @Test
    public void testAnyReturnsFalseWhenCollectionIsEmpty() throws Exception {
        ResponseErrors target = new ResponseErrors();
        assertFalse(target.any());
    }

    @Test
    public void testAnyReturnsTrueWhenCollectionHasAnItem() throws Exception {
        ResponseErrors target = new ResponseErrors("with error");
        assertTrue(target.any());
    }

    @Test
    public void testCountWithNoItemsReturns0() throws Exception {
        ResponseErrors target = new ResponseErrors();
        assertEquals(0, target.count());
    }

    @Test
    public void testCountWithItemsReturnsTheAmountOfItems() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        assertEquals(1, target.count());
    }

    @Test
    public void testSizeWithNoItemsReturns0() throws Exception {
        ResponseErrors target = new ResponseErrors();
        assertEquals(0, target.size());
    }

    @Test
    public void testSizeWithItemsReturnsTheAmountOfItems() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        assertEquals(1, target.size());
    }

    @Test
    public void testFirstWithNoItemsReturnsNull() throws Exception {
        ResponseErrors target = new ResponseErrors();
        assertNull(target.first());
    }

    @Test
    public void testFirstWithAnItemReturnsTheItem() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        assertEquals("with an item", target.first());
    }

    @Test
    public void testAll() throws Exception {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("with an item");
        ResponseErrors target = new ResponseErrors("with an item");

        assertEquals(expected, target.all());
    }

    @Test
    public void testNext() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        target.add("another item");

        assertEquals("with an item", target.next());
        assertEquals("another item", target.next());

        try {
            target.next();
            fail("should have thrown no such element exception");
        } catch(NoSuchElementException ex) {

        }

    }

    @Test
    public void testResetIterator() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        target.add("another item");

        assertEquals("with an item", target.next());
        assertEquals("another item", target.next());

        target.resetIterator();

        assertEquals("with an item", target.next());
    }

    @Test
    public void testHasNext() throws Exception {
        ResponseErrors target = new ResponseErrors("with an item");
        target.add("another item");

        assertTrue(target.hasNext());
        target.next();

        assertTrue(target.hasNext());
        target.next();

        assertFalse(target.hasNext());
    }

}