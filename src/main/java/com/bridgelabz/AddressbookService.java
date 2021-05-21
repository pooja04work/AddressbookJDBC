package com.bridgelabz;

import java.sql.SQLException;
import java.util.List;

public interface AddressbookService {
    public List<AddressbookData> getAddressbook()throws AddressbookException, SQLException;
    int getAddressbookData(String firstName, String city) throws AddressbookException;
    public List<AddressbookData> preparedStatmentForWholeTableData(String city) throws AddressbookException;
}
