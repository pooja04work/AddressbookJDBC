package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressbookServiceTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchaddressbookCount() throws AddressbookException, SQLException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        List<AddressbookData> addressbookData = addressbookServiceImplementation.getAddressbook();
        System.out.println(addressbookData);
        Assert.assertEquals(9, addressbookData.size());
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
}
