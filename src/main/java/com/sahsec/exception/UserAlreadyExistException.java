package com.sahsec.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Throwable {

    public UserAlreadyExistException(final String message) {
        super(message);
    }

}