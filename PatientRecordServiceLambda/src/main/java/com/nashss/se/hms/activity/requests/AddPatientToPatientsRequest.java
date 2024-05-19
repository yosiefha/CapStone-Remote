package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Represents a request to add a new patient to the patients database.
 */
@JsonDeserialize(builder = AddPatientToPatientsRequest.Builder.class)
public class AddPatientToPatientsRequest {

    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String contactNumber;
    private final String emailAddress;
    private final String address;

    /**
     * Creates a new AddPatientToPatientsRequest object with the specified details.
     *
     * @param firstName     the first name of the patient
     * @param lastName      the last name of the patient
     * @param dateOfBirth   the date of birth of the patient
     * @param contactNumber the contact number of the patient
     * @param emailAddress  the email address of the patient
     * @param address       the address of the patient
     */
    private AddPatientToPatientsRequest(String firstName,
                                        String lastName, String dateOfBirth,
                                        String contactNumber, String emailAddress,
                                        String address) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.address = address;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "AddPatientToPatientsRequest{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + dateOfBirth + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String contactNumber;
        private String emailAddress;
        private String address;


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

        public AddPatientToPatientsRequest build() {
            return new AddPatientToPatientsRequest(firstName, lastName, dateOfBirth, contactNumber, emailAddress, address);
        }

    }

}
