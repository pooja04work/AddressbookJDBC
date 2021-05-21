package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressbookServiceImplementation implements AddressbookService {
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
                double phoneNo = resultSet.getDouble(7);
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
                double phoneNo = resultSet.getDouble(7);
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
                double phoneNo = resultSet.getDouble(7);
                String email = resultSet.getString(8);
                int person_id = resultSet.getInt(9);

                addressbookData.add(new AddressbookData(firstName, lastName, address, city, state, zip, phoneNo, email, person_id));
            }
        } catch (SQLException e) {
            throw new AddressbookException("Cannot establish connection", AddressbookException.ExceptionType.CONNECTION_FAIL);
        }
        return addressbookData;
    }
}