package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

public class AddressbookServiceRA {


    public AddressbookServiceRA(List<AddressbookData> asList) {
        this.addressbook = new ArrayList<>(asList);
    }
    @Override
    public String toString() {
        return "AddressBookService [addressbook=" + addressbook + "]";
    }

    private List<AddressbookData> addressbook;

    public AddressbookServiceRA() {

    }


    public int countEntries() {
        return  addressbook.size();
    }

}
