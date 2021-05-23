package com.bridgelabz;

public class AddressbookException extends Throwable {
    String  errorMessage;
    public AddressbookException(String message, ExceptionType connectionFail) {
        this.errorMessage = message;
    }

    public AddressbookException(String message)  {
        this.errorMessage = message;
    }

    public enum ExceptionType{CONNECTION_FAIL, SQL_ERROR, UPDATE_ERROR}
    ExceptionType type;
}
