package com.nashss.se.hms.models;

import java.util.Objects;

/**
 * Represents a patient in the system.
 */
public class PatientModel {
    private final String patientId;
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String contactNumber;
    private final String emailAddress;
    private final String address;


    /**
     * Represents a patient model with various attributes.
     *
     * @param patientId     unique id of the patient.
     * @param firstName     first name of the patient .
     * @param lastName      last name of the patient.
     * @param dateOfBirth   date of birth of the patient.
     * @param contactNumber contact number of the patient.
     * @param emailAddress  email address of the patient.
     * @param address       address of the patient.
     */
    public PatientModel(String patientId, String firstName, String lastName, String dateOfBirth,
                        String contactNumber, String emailAddress, String address) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    /**
     * Retrieves the patient ID.
     *
     * @return The patient ID.
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Retrieves the first name of the patient.
     *
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the patient.
     *
     * @return The last name of the patient.
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Retrieves the date of birth of the patient.
     *
     * @return The date of birth of the patient.
     */
    public String getDOB() {
        return dateOfBirth;
    }

    /**
     * Retrieves the date of contact number  of the patient.
     *
     * @return contact number of the patient.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Retrieves the email address of the patient.
     *
     * @return The email address of the patient.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Retrieves the address of the patient.
     *
     * @return The address pf the patient.
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "patientId='" + patientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contactNumber=" + contactNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientModel)) {
            return false;
        }
        PatientModel that = (PatientModel) o;
        return Objects.equals(getPatientId(), that.getPatientId()) && Objects.equals(getFirstName(),
                that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getDOB(), that.getDOB());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientId(), getFirstName(), getLastName(), getDOB());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String patientId;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String contactNumber;
        private String emailAddress;
        private String address;

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withDOB(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PatientModel build() {
            return new PatientModel(patientId, firstName, lastName, dateOfBirth, contactNumber, emailAddress, address);
        }

    }
}
