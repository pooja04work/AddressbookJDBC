package com.bridgelabz;

import java.sql.SQLException;
import java.util.List;

public interface AddressbookService {
    public List<AddressbookData> getAddressbook() throws AddressbookException, SQLException;

    int getAddressbookData(String firstName, String city) throws AddressbookException;

    public List<AddressbookData> preparedStatmentForWholeTableDataByCity(String city) throws AddressbookException;

    public List<AddressbookData> preparedStatmentForWholeTableDataByState(String state) throws AddressbookException;

    public int preparedStatmentForInsertIntoAddressbookTable(String firstName, String lastName, String address, String city, String state, int zip, long phoneNo, String email) throws AddressbookException;

    public int preparedStatmentForInsertIntoRelationshipTable(int personId, int addressbookNameId, int addressbookTypeId) throws AddressbookException;

    public void addDetailsWithThreads(List<AddressbookData> addressbookDataList);
}


