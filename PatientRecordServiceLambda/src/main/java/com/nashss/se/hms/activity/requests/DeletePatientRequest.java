package com.nashss.se.hms.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * DeletePatientRequest represents a request to delete a patient.
 * It contains the patient ID.
 */
@JsonDeserialize(builder = DeletePatientRequest.Builder.class)
public class DeletePatientRequest {

    private final String patientId;

    /**
     * Constructs a new DeletePatientRequest object with the specified patient ID.
     *
     * @param patientId the ID of the patient to be deleted
     */
    private DeletePatientRequest(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "DeletePatientRequest{" +
                "patientId='" + patientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String patientId;

        public Builder withPatientId(String patientId) {
            this.patientId = patientId;
            return this;
        }

        public DeletePatientRequest build() {
            return new DeletePatientRequest(patientId);
        }
    }
}
