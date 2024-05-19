package com.nashss.se.hms.exceptions;

/**
 * Exception thrown when a patient is not found in the system.
 */
public class PatientNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1398547381682727285L;

    /**
     *
     */
    public PatientNotFoundException() {
        super();
    }

    /**
     *
     * @param message a message sent when exceptions is thrown.
     */
    public PatientNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message a message sent when exceptions is thrown.
     * @param cause cause of the exception.
     */
    public PatientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause error thrown.
     */
    public PatientNotFoundException(Throwable cause) {
        super(cause);
    }


}
