package com.cgx.middleware.extension.exception;

public class MidFrameworkException extends RuntimeException {

    private static final long serialVersionUID = 3985283277125762473L;

    public MidFrameworkException(String message) {
        super(message);
    }

    public MidFrameworkException(Throwable cause) {
        super(cause);
    }

    public MidFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

}