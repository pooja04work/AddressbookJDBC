package com.bridgelabz;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AddressbookServiceTestRA {
    @Before
    public void setup() {
        RestAssured.baseURI ="http://localhost";
        RestAssured.port = 3000;
    }
    public AddressbookData[] getAddressList() {
        Response response = RestAssured.get("/Addressbook");
        System.out.println("AddressBook Data in JsonServer: "+response.asString());
        AddressbookData[] dataArray = new Gson().fromJson(response.asString(),AddressbookData[].class);
        return dataArray;
    }

    @Test
    public void givenpayrollData_whenreterived_shouldMatchtheCount() {
        AddressbookData[] dataArray = getAddressList();
        AddressbookServiceRA addressbookservice = new AddressbookServiceRA(Arrays.asList(dataArray));
        int entries = addressbookservice.countEntries();
        Assert.assertEquals(3, entries);
    }
}
