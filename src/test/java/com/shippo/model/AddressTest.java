package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class AddressTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Address testObject = (Address) getDefaultObject();
        assertEquals("VALID", testObject.getObject_state());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Address.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Address testObject = (Address) getDefaultObject();
        Address retrievedObject;

        retrievedObject = Address.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Address.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        AddressCollection objectCollection = Address.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        AddressCollection addressCollection = Address.all(objectMap);
        assertEquals(addressCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("object_purpose", "PURCHASE");
        objectMap.put("name", "Undefault New Wu");
        objectMap.put("company", "Shippo");
        objectMap.put("street1", "Clayton St.");
        objectMap.put("street_no", "215");
        objectMap.put("street2", null);
        objectMap.put("city", "San Francisco");
        objectMap.put("state", "CA");
        objectMap.put("zip", "94117");
        objectMap.put("country", "US");
        objectMap.put("phone", "+1 555 341 9393");
        objectMap.put("email", "laura@goshipppo.com");
        objectMap.put("metadata", "Customer ID 123456");

        try {
            Address testObject = Address.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}