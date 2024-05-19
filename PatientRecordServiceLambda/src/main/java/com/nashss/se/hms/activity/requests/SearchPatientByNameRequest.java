package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * This class represents a request to search patients by name.
 */
@JsonDeserialize(builder = SearchPatientByNameRequest.Builder.class)
public class SearchPatientByNameRequest {


    private final String firstName;
    private final String lastName;

    /**
     * Constructs a new SearchPatientByNameRequest object.
     * @param firstName The first name of the patient to be searched by.
     * @param lastName The last name of the patient to be searched by.
     */
    private SearchPatientByNameRequest(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return "SearchPatientByNameRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
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

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public SearchPatientByNameRequest build() {
            return new SearchPatientByNameRequest(firstName, lastName);
        }
    }

}
