package com.wavemaker.framework.exceptions;


public class CustomException extends RuntimeException {

    private String exceptionMessage;

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.exceptionMessage = errorMessage;
    }

    public CustomException(String errorMessage, Exception e) {
        super(errorMessage, e);
        this.exceptionMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Exception caused due to :" + exceptionMessage;
    }
}
