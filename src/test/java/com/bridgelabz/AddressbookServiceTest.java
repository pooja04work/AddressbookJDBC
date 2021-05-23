package com.bridgelabz;


import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressbookServiceTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchaddressbookCount() throws AddressbookException, SQLException, SQLException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        List<AddressbookData> addressbookData = addressbookServiceImplementation.getAddressbook();
        System.out.println(addressbookData);
        Assert.assertEquals(10, addressbookData.size());
    }

    @Test
    public void givenNewCity_WhenUpdated_ShouldMatch() throws AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        int addressbookData = addressbookServiceImplementation.getAddressbookData("Moitry", "Kokrajhar");
        Assert.assertEquals(1, addressbookData);
    }

    @Test
    public void givenCity_WhenRetrieved_ShouldMatchaddressbookCount() throws AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        List<AddressbookData> addressbookData = addressbookServiceImplementation.preparedStatmentForWholeTableDataByCity("Guwahati");
        System.out.println(addressbookData);
        Assert.assertEquals(3, addressbookData.size());
    }
    @Test
    public void givenState_WhenRetrieved_ShouldMatchaddressbookCount() throws AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        List<AddressbookData> addressbookData = addressbookServiceImplementation.preparedStatmentForWholeTableDataByState("Assam");
        System.out.println(addressbookData);
        Assert.assertEquals(7, addressbookData.size());
    }
    @Test
    public void givenNewDataOfAddressbook_WhenRetrieved_ShouldReturn1() throws SQLException, AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        String firstName = "limon";
        String lastName = "puri";
        String address = "India";
        String city = "Hajipur";
        String state = "Bihar";
        int zip = 784562;
        long phoneNo = 0654657565;
        String email = "limo@123";
        int result = addressbookServiceImplementation.preparedStatmentForInsertIntoAddressbookTable(firstName,lastName,address,city,state,zip,phoneNo,email);
        System.out.println(result);
        Assert.assertEquals(1, result);
    }

    @Test
    public void givenNewDataOfrelationship_WhenRetrieved_ShouldReturn1() throws SQLException, AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        int personId = 10;
        int addressbookNameId = 3;
        int addressbookTypeId = 2;

        int result = addressbookServiceImplementation.preparedStatmentForInsertIntoRelationshipTable(personId,addressbookNameId,addressbookTypeId);
        Assert.assertEquals(1, result);
    }
}
