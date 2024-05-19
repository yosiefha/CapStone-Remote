package com.nashss.se.hms.exceptions;

/**
 * This class represents an exception that is thrown when a
 * diagnosis is not found in the medical system.
 */
public class DiagnosisNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -509174827663617974L;


    /**
     *
     */
    public DiagnosisNotFoundException() {
        super();
    }

    /**
     *
     * @param message a message sent when an exception occur.
     */
    public DiagnosisNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message a message sent when an exceptions occur.
     * @param cause the cause of the exception.
     */
    public DiagnosisNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause a cause of the exception.
     */
    public DiagnosisNotFoundException(Throwable cause) {
        super(cause);
    }

}
