package com.nashss.se.hms.exceptions;

/**
 * The MedicationNotFoundException is an exception that is thrown when a
 * medication with a specified medication ID is not found.
 */
public class MedicationNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -111486133021682490L;

    /**
     *
     */
    public MedicationNotFoundException() {
        super();
    }

    /**
     *
     * @param message a message sent when an exceptions occur.
     */
    public MedicationNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message a message sent  when  exception occurs.
     * @param cause the cause of the exception.
     */
    public MedicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause the cause of  the error.
     */
    public MedicationNotFoundException(Throwable cause) {
        super(cause);
    }


}
