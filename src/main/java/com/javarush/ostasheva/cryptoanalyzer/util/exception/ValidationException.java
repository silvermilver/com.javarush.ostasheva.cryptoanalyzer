package com.javarush.ostasheva.cryptoanalyzer.util.exception;

public class ValidationException extends RuntimeException {

    String reason;

    public ValidationException (String reason) {
        this.reason = reason;
    }

    public ValidationException (String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
