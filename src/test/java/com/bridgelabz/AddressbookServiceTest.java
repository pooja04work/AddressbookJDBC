package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class AddressbookServiceTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws AddressbookException, SQLException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        List<AddressbookData> addressbookData = addressbookServiceImplementation.getAddressbook();
        System.out.println(addressbookData);
        Assert.assertEquals(9, addressbookData.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() throws AddressbookException {
        AddressbookServiceImplementation addressbookServiceImplementation = new AddressbookServiceImplementation();
        int addressbookData = addressbookServiceImplementation.getAddressbookData("Moitry", "Kokrajhar");
        Assert.assertEquals(1, addressbookData);
    }
}