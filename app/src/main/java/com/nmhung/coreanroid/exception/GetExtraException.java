package com.nmhung.coreanroid.exception;

public class GetExtraException extends RuntimeException  {
    public GetExtraException(String message) {
        super(message);
    }

    public GetExtraException() {
        this("Can Not Conver Json to Object");
    }
}
