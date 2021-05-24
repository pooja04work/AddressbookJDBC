package com.bridgelabz;

import java.sql.*;
import java.util.*;

public class AddressbookServiceImplementation implements AddressbookService  {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookservice";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful...!!!!" + connection);
        return connection;
    }

    @Override
    public List<AddressbookData> getAddressbook() throws AddressbookException, SQLException {
        String sql = "select * from addressbook;";
        List<AddressbookData> addressbookData = new ArrayList<AddressbookData>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                String address = resultSet.getString(3);
                String city = resultSet.getString(4);
                String state = resultSet.getString(5);
                int zip = resultSet.getInt(6);
                long phoneNo = resultSet.getLong(7);
                String email = resultSet.getString(8);
                int person_id = resultSet.getInt(9);

                addressbookData.add(new AddressbookData(firstName, lastName, address, city, state, zip, phoneNo, email, person_id));
            }
        } catch (SQLException e) {
            throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.SQL_ERROR);
        }
        return addressbookData;
    }

    @Override
    public int getAddressbookData(String firstName, String city) throws AddressbookException {
        String sql = String.format("update  addressbook set city='kokrajhar' where firstName='Moitry'");
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.prepareStatement(sql);
            System.out.println(statement);
            return statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.UPDATE_ERROR);
        }
    }

    @Override
    public List<AddressbookData> preparedStatmentForWholeTableDataByCity(String city) throws AddressbookException {
        String sql = "select * from addressbook where city = 'Guwahati'";
        List<AddressbookData> addressbookData = new ArrayList<AddressbookData>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                String address = resultSet.getString(3);
                String state = resultSet.getString(5);
                int zip = resultSet.getInt(6);
                long phoneNo = resultSet.getLong(7);
                String email = resultSet.getString(8);
                int person_id = resultSet.getInt(9);

                addressbookData.add(new AddressbookData(firstName, lastName, address, city, state, zip, phoneNo, email, person_id));
            }
        } catch (SQLException e) {
            throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.CONNECTION_FAIL);
        }
        return addressbookData;
    }

    @Override
    public List<AddressbookData> preparedStatmentForWholeTableDataByState(String state) throws AddressbookException {
        String sql = "select * from addressbook where state = 'Assam'";
        List<AddressbookData> addressbookData = new ArrayList<AddressbookData>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                String address = resultSet.getString(3);
                String city = resultSet.getString(4);
                int zip = resultSet.getInt(6);
                long phoneNo = resultSet.getLong(7);
                String email = resultSet.getString(8);
                int person_id = resultSet.getInt(9);

                addressbookData.add(new AddressbookData(firstName, lastName, address, city, state, zip, phoneNo, email, person_id));
            }
        } catch (SQLException e) {
            throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.CONNECTION_FAIL);
        }
        return addressbookData;
    }

    @Override
    public int preparedStatmentForInsertIntoAddressbookTable(String firstName, String lastName, String  address, String  city, String state, int zip, long phoneNo, String email) throws AddressbookException {

        try {
            Connection connection = this.getConnection();
            System.out.println(connection);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into addressbook(FirstName,LastName,Address,City,State,Zip,PhoneNumber,Email) values(?,?,?,?,?,?,?,?); ");

            preparedStatement.setString(1, firstName );
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zip);
            preparedStatement.setLong(7, phoneNo);
            preparedStatement.setString(8, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException throwables) {
                throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.CONNECTION_FAIL);
            }
        }
        return 1;
    }

    @Override
    public int preparedStatmentForInsertIntoRelationshipTable(int personId, int addressbookNameId, int addressbookTypeId) throws AddressbookException {
        try {
            Connection connection = this.getConnection();
            System.out.println(connection);
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO relationship(PersonId,AddressbookTypeId,addressbookNameId) values(?,?,?); ");
            preparedStatement.setInt(1, personId );
            preparedStatement.setInt(2, addressbookNameId);
            preparedStatement.setInt(3, addressbookTypeId);

            int result= preparedStatement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException throwables) {
                throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.CONNECTION_FAIL);
            }        }
        return 1;
    }
    @Override
    public void addDetailsWithThreads(List<AddressbookData> addressbookDataList) {
        Map<Integer, Boolean> contactAdditionStatus = new HashMap<>();
        addressbookDataList.forEach(addressBookData -> {
            Runnable runnable = () -> {
                contactAdditionStatus.put(addressBookData.hashCode(), false);
                System.out.println("Person being added : " + Thread.currentThread().getName());
                try {
                    this.preparedStatmentForInsertIntoAddressbookTable(addressBookData.firstName, addressBookData.lastName,
                        addressBookData.address, addressBookData.city, addressBookData.state, addressBookData.zip,
                        addressBookData.phoneNo, addressBookData.email);
                } catch (AddressbookException e) {
                    e.printStackTrace();
                }
                contactAdditionStatus.put(addressBookData.hashCode(), true);
                System.out.println("Person added : " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(runnable, addressBookData.getFirstName());
//           Thread thread2 = new Thread(runnable, addressBookData.firstName);
            thread.start();
//          thread2.start();
        });
        while (contactAdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("" + addressbookDataList);
    }
}